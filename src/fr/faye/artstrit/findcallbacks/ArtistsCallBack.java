package fr.faye.artstrit.findcallbacks;

import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.models.Artists;

public class ArtistsCallBack extends FindCallback<ParseObject> {

	private final String TAG;
	private Artists artists = Artists.getInstance();
	
	public ArtistsCallBack(){
		TAG = getClass().getSimpleName();
	}
	
	@Override
	public void done(List<ParseObject> artists_list, ParseException e) {
		 if (e == null) {
			 
			 Log.i(TAG, "artist size = "+artists_list.size());
			 for(int i=0 ; i<artists_list.size() ; i++){
				 
				 ParseObject artist_object = artists_list.get(i);				 
				 artists.addArtistToType(artist_object.getString(Artist.TYPE), new Artist(artist_object));
			 }
				 
	        } else {
	            Log.d(TAG, "Error: " + e.getMessage());
	        }
	}

}
