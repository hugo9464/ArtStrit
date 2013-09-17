package fr.faye.artstrit.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public final class Artists {
	 

    private static volatile Artists instance = null;

    private Map<String, List<Artist>> artists;
    private final String TAG;
    
    private Artists() {
        super();
        this.TAG = getClass().getSimpleName();
        setArtists(new HashMap<String, List<Artist>>());
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
    }
    
	public Map<String, List<Artist>> artists() {
		return artists;
	}


	public void setArtists(Map<String, List<Artist>> artists) {
		this.artists = artists;
	}



}