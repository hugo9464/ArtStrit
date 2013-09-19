package fr.faye.artstrit.listeners;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseObject;

import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.windows.CreateArtistDialog;

public class AddArtistListener implements OnMapLongClickListener{

	private GoogleMap map;
	private FragmentManager fragmentManager;
	
	public AddArtistListener(GoogleMap map, FragmentManager fragmentManager){
		this.map = map;	
		this.fragmentManager = fragmentManager;
	}
	
	@Override
	public void onMapLongClick(LatLng position) {
		
		 DialogFragment newFragment = new CreateArtistDialog(position, map);
		    newFragment.show(fragmentManager, "artists");
		
		
//		
//		 ParseObject newArtist = new ParseObject("artists");
//		 newArtist.put(Artist.LAT, position.latitude);
//		 newArtist.put(Artist.LNG, position.longitude);
//		 newArtist.put(Artist.COMMENT, "commentaire bidon");
//		 newArtist.put(Artist.NAME, "nom bidon");
//		 newArtist.put(Artist.RATE, 3);
//		 newArtist.put(Artist.SEX, "sexe bidon");
//		 newArtist.put(Artist.TYPE, "dance");
//
//
//		 newArtist.saveInBackground();
	}

}
