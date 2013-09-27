package fr.faye.artstrit.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import fr.faye.artstrit.adpaters.WindowAdapter;
import fr.faye.artstrit.findcallbacks.ArtistsCallBack;
import fr.faye.artstrit.listeners.AddArtistListener;
import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.models.Artists;
import fr.faye.artstrit.windows.CreateArtistDialog;
import fr.faye.artstrit.windows.HelpDialog;
import fr.faye.projet.artstrit.R;

public class UserView extends FragmentActivity {

	public static GoogleMap map;
	public static Context context;

	protected static final String APP_KEY = "mv7tNBAlBiTwsKavBBTFZ9zElznCogs5xAgVbTIe";
	protected static final String CLIENT_KEY = "DOXaerMTFmqodzQMAb2nKE5PW8Y2Puea1spvP0rR";

	private static String TAG;
	private Artists artists = Artists.getInstance();

	public static float ZOOM_ON_PARIS = 11.5f;
	public static LatLng PARIS = new LatLng(48.856578, 2.340528);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getSimpleName();

		ActionBar actionBar = getActionBar();
		context = getApplicationContext();
		Parse.initialize(this, APP_KEY, CLIENT_KEY);
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.activity_user_view);
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.setMyLocationEnabled(true);

		map.setOnMapLongClickListener(new AddArtistListener(map,
				getSupportFragmentManager()));
		map.setInfoWindowAdapter(new WindowAdapter());

	}

	@Override
	protected void onResume() {
		super.onResume();

		zoomOnParis();

		getArtists();
	}

	private void zoomOnParis() {
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(PARIS,
				ZOOM_ON_PARIS));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != 0) {
			Bundle extras = data.getExtras();
			artists.setCurrent_picture((Bitmap) extras.get("data"));
			Toast.makeText(context,
					getResources().getString(R.string.picture_ok),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void getArtists() {

		if (isOnline()) {

			map.clear();

			Toast.makeText(context,
					getResources().getString(R.string.download),
					Toast.LENGTH_LONG).show();
			ParseQuery<ParseObject> artists_query = ParseQuery
					.getQuery("artists");
			artists_query.findInBackground(new ArtistsCallBack(this));
		} else
			Toast.makeText(context, getResources().getString(R.string.offline),
					Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_refresh:
			getArtists();
			return true;
		case R.id.action_add:

			if (isOnline()) {
				DialogFragment newFragment = new CreateArtistDialog(new LatLng(
						map.getMyLocation().getLatitude(), map.getMyLocation()
								.getLongitude()), map, true);
				newFragment.show(getSupportFragmentManager(), "artists");
			} else
				Toast.makeText(context,
						getResources().getString(R.string.offline),
						Toast.LENGTH_LONG).show();
			return true;
		case R.id.action_zoom:
			zoomOnParis();
			return true;
		case R.id.action_help:
			DialogFragment newHelpDialog = new HelpDialog();
			newHelpDialog.show(getSupportFragmentManager(), "help");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public static Marker addArtistToMap(Artist artist) {
		Bitmap b = null;

		if (artist.type().equals(Artist.THEATRE))
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.theater);
		else if (artist.type().equals(Artist.SPORT))
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.sport);
		else if (artist.type().equals(Artist.MUSIC))
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.music);
		else if (artist.type().equals(Artist.STATUE))
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.statue);
		else if (artist.type().equals(Artist.DANCE))
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dance);
		else
			b = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.other);
		return UserView.map.addMarker(new MarkerOptions().icon(
				BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
	}

	public static boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}
