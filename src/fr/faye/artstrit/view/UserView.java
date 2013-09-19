package fr.faye.artstrit.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import fr.faye.artstrit.findcallbacks.TypesCallBack;
import fr.faye.artstrit.listeners.AddArtistListener;
import fr.faye.projet.artstrit.R;

public class UserView extends FragmentActivity {

	public static GoogleMap map;
	public static Context context;

	protected static final String APP_KEY = "mv7tNBAlBiTwsKavBBTFZ9zElznCogs5xAgVbTIe";
	protected static final String CLIENT_KEY = "DOXaerMTFmqodzQMAb2nKE5PW8Y2Puea1spvP0rR";

	private static String TAG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getSimpleName();
		context = getApplicationContext();
		Parse.initialize(this, APP_KEY, CLIENT_KEY);
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.activity_user_view);

		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		
		map.setMyLocationEnabled(true);

		map.setOnMapLongClickListener(new AddArtistListener(map, getSupportFragmentManager()));

	}

	@Override
	protected void onResume() {
		super.onResume();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.856578,
				2.351828), 10));
		map.animateCamera(CameraUpdateFactory.zoomIn());

		getArtists();
		Log.i(TAG, "artistes récupérés");
	}

	private void getArtists() {
		ParseQuery<ParseObject> types_query = ParseQuery.getQuery("types");
		types_query.findInBackground(new TypesCallBack());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_view, menu);
		return true;
	}

}
