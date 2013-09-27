package fr.faye.artstrit.findcallbacks;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.models.Artists;

public class ArtistsCallBack extends FindCallback<ParseObject> {

	private final String TAG;
	private Context context;
	private Artists artists = Artists.getInstance();

	
	public ArtistsCallBack(Context context){
		TAG = getClass().getSimpleName();
		this.context = context;
	}
	
	@Override
	public void done(List<ParseObject> artists_list, ParseException e) {
		 if (e == null) {
			 
			 Log.i(TAG, "artist size = "+artists_list.size());
			 for(int i=0 ; i<artists_list.size() ; i++){
				 
				 ParseObject artist_object = artists_list.get(i);				 
				 try {
					artists.addArtist(new Artist(artist_object));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			 }
				 
	        } else {
	            Log.d(TAG, "Error: " + e.getMessage());
	        }
	}

}
