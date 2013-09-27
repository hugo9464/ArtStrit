package fr.faye.artstrit.adpaters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.models.Artists;
import fr.faye.artstrit.view.UserView;
import fr.faye.projet.artstrit.R;

public class WindowAdapter implements InfoWindowAdapter {
	
	private Artists artists;

	public WindowAdapter(){
		this.artists = Artists.getInstance();
	}
	
	@Override
	public View getInfoContents(final Marker m) {
		
		Artist artist = artists.getArtistByMarker(m.getId());
		
		LayoutInflater inflater = (LayoutInflater) UserView.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View vi = inflater.inflate(R.layout.infowindow, null);

		ImageView picture = (ImageView) vi
				.findViewById(R.id.picture);
		picture.setImageBitmap(artist.picture());
		
		
		TextView artist_name = (TextView) vi.findViewById(R.id.artist_name);
		artist_name.setText(artist.name());
		
		TextView artist_style = (TextView) vi.findViewById(R.id.artist_style);
		artist_style.setText(artist.type());
		
		TextView artist_comment = (TextView) vi.findViewById(R.id.artist_comment);
		artist_comment.setText(artist.comment());
		
		RatingBar artist_rate = (RatingBar) vi.findViewById(R.id.artist_rate);
		artist_rate.setRating((float) artist.rate());
		return vi;
	}

	@Override
	public View getInfoWindow(Marker m) {
		return null;
	}

}
