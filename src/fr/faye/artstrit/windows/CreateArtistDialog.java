package fr.faye.artstrit.windows;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.faye.artstrit.models.Artist;
import fr.faye.projet.artstrit.R;

public class CreateArtistDialog extends DialogFragment {
	
	private LayoutInflater inflater;
	private View vi;
	private static String TAG;
	private LatLng loc;
	private String name;
	private String comment;
	private String type;
	private float rate;
	private GoogleMap map;

	public CreateArtistDialog(LatLng position, GoogleMap map) {
		this.loc = position;
		this.map = map;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		TAG = getClass().getSimpleName();
		
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		inflater = getActivity().getLayoutInflater();
		vi = inflater.inflate(R.layout.create_artist_window, null);
		builder.setTitle(R.string.create_artist)
				.setView(vi)
			
						.setSingleChoiceItems(R.array.types, -1,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String[] types_array = getResources().getStringArray(R.array.types);
								type = types_array[which];
							}

						})
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								
								createArtist();
								EditText nameEditText = (EditText) vi.findViewById(R.id.name);
								Log.i(TAG, "name = "+nameEditText.getText().toString());
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

	protected void createArtist() {
		
		EditText nameEditText = (EditText) vi.findViewById(R.id.name);
		name = nameEditText.getText().toString();
		
		EditText commentEditText = (EditText) vi.findViewById(R.id.comment);
		comment = commentEditText.getText().toString();
		
		RatingBar ratingBar = (RatingBar) vi.findViewById(R.id.ratingBar);
		rate = ratingBar.getRating();
		
		Artist new_artist = new Artist(rate, comment, name, loc, type);
		map.addMarker(new MarkerOptions().position(loc));

		Log.i(TAG, "création nouvel artist : "+new_artist.toString());
	}
}