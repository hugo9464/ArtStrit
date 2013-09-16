package fr.faye.artstrit.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import fr.faye.artstrit.listeners.AddArtistListener;
import fr.faye.projet.artstrit.R;

public class UserView extends Activity {

	private GoogleMap map;
	protected static final String APP_KEY = "mv7tNBAlBiTwsKavBBTFZ9zElznCogs5xAgVbTIe";
	protected static final String CLIENT_KEY = "DOXaerMTFmqodzQMAb2nKE5PW8Y2Puea1spvP0rR";

	public static final String LAT = "Lat";
	public static final String LNG = "Lng";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, APP_KEY,
				CLIENT_KEY);
		ParseAnalytics.trackAppOpened(getIntent());
		setContentView(R.layout.map_fragment);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);

		map.setOnMapLongClickListener(new AddArtistListener(map));

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_view, menu);
		return true;
	}

}
