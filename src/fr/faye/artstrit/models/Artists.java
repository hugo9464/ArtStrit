package fr.faye.artstrit.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.faye.artstrit.view.UserView;
import fr.faye.projet.artstrit.R;

public final class Artists {
	 

    private static volatile Artists instance = null;

    private Map<String, List<Artist>> artists;
    private final String TAG;
    
    private Artists() {
        super();
        
        this.TAG = getClass().getSimpleName();
        this.artists = new HashMap<String, List<Artist>>();
    }

    
    public final static Artists getInstance() {

        if (Artists.instance == null) {

           synchronized(Artists.class) {
             if (Artists.instance == null) {
               Artists.instance = new Artists();
             }
           }
        }
        return Artists.instance;
    }


    public void addArtistToType(String type, Artist artist){
    	
    		if(artists.containsKey(type)){
    			artists.get(type).add(artist);
    		}
    		else {
    			List<Artist> new_liste = new ArrayList<Artist>();
    			new_liste.add(artist);
    			artists.put(type, new_liste);
    		}
    		Log.i(TAG, "adding artist "+artist.toString());

    		addArtistToMap(artist, type);
    }
    

    

	private void addArtistToMap(Artist artist, String type) {
	
		Bitmap b;
		
		switch(Type.valueOf(type.toUpperCase()))	{
		case SPORT:
			b = BitmapFactory.decodeResource(UserView.context.getResources(), R.drawable.sport);		
			UserView.map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
			break;
		case MUSIC:
			b = BitmapFactory.decodeResource(UserView.context.getResources(), R.drawable.music);
			UserView.map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
			break;
		case STATUE:
			b = BitmapFactory.decodeResource(UserView.context.getResources(), R.drawable.statue);
			UserView.map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
			break;
		case COMEDY:
			b = BitmapFactory.decodeResource(UserView.context.getResources(), R.drawable.theater);
			UserView.map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
			break;
		case DANCE:
			b = BitmapFactory.decodeResource(UserView.context.getResources(), R.drawable.dance);
			UserView.map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).position(artist.loc()));
			break;
		default:
			UserView.map.addMarker(new MarkerOptions().position(artist.loc()));
			break;
		
		}
		

		
	}


	public Map<String, List<Artist>> artists() {
		return artists;
	}


	public void setArtists(Map<String, List<Artist>> artists) {
		this.artists = artists;
	}



}