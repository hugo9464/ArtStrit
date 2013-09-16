package fr.faye.artstrit.listeners;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseObject;

import fr.faye.artstrit.view.UserView;

public class AddArtistListener implements OnMapLongClickListener{

	private GoogleMap map;
	
	public AddArtistListener(GoogleMap map){
		this.map = map;	
	}
	
	@Override
	public void onMapLongClick(LatLng position) {
		map.addMarker(new MarkerOptions().position(position));
		
		 ParseObject newArtist = new ParseObject("testArtist");
		 newArtist.put(UserView.LAT, position.latitude);
		 newArtist.put(UserView.LNG, position.longitude);
		 newArtist.saveInBackground();
	}

}
