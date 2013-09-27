package fr.faye.artstrit.models;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.Marker;

import fr.faye.artstrit.view.UserView;

public final class Artists {

	private static volatile Artists instance = null;

	private Map<String, Artist> artists_map;
	@SuppressWarnings("unused")
	private final String TAG;
	private Bitmap current_picture;

	private Artists() {
		super();
		this.TAG = getClass().getSimpleName();
		this.artists_map = new HashMap<String, Artist>();
		this.current_picture = null;
	}

	public final static Artists getInstance() {

		if (Artists.instance == null) {

			synchronized (Artists.class) {
				if (Artists.instance == null) {
					Artists.instance = new Artists();
				}
			}
		}
		return Artists.instance;
	}

	public void addArtist(Artist artist) {

		Marker new_marker = UserView.addArtistToMap(artist);
		artists_map.put(new_marker.getId(), artist);
		

	}

	public Artist getArtistByMarker(String markerId) {
		return artists_map.get(markerId);
	}


	public Bitmap current_picture() {
		return current_picture;
	}

	public void setCurrent_picture(Bitmap current_picture) {
		this.current_picture = current_picture;
	}
}