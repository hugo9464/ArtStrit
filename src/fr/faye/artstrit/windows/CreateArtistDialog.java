package fr.faye.artstrit.windows;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import fr.faye.artstrit.models.Artist;
import fr.faye.artstrit.models.Artists;
import fr.faye.artstrit.view.UserView;
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
	private Artists artists;
	private boolean isValid;

	public CreateArtistDialog(LatLng position, GoogleMap map, boolean isValid) {
		this.loc = position;
		this.map = map;
		this.artists = Artists.getInstance();
		this.isValid = isValid;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		TAG = getClass().getSimpleName();

		Log.i(TAG, "creating dialog");
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		inflater = getActivity().getLayoutInflater();
		vi = inflater.inflate(R.layout.create_artist_window, null);

		if (!isValid) {
			EditText nameEditText = (EditText) vi.findViewById(R.id.name);
			nameEditText.setHint(getResources()
					.getString(R.string.missing_name));
			nameEditText.setHintTextColor(Color.RED);
		}

		ImageButton take_picture = (ImageButton) vi
				.findViewById(R.id.take_picture);
		take_picture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View vi) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePictureIntent, 1);
			}
		});
		
		Spinner styles_spinner = (Spinner) vi.findViewById(R.id.styles_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UserView.context,
		        R.array.types, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_item);
		// Apply the adapter to the spinner
		styles_spinner.setAdapter(adapter);
		styles_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String[] types_array = getResources()
						.getStringArray(R.array.types);
				type = types_array[pos];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		builder.setTitle(R.string.create_artist)
				.setView(vi)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								createArtist();

							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								Toast.makeText(
										UserView.context,
										getResources().getString(
												R.string.canceled),
										Toast.LENGTH_SHORT).show();

							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

	protected void createArtist() {

		isValid = true;

		EditText nameEditText = (EditText) vi.findViewById(R.id.name);
		name = nameEditText.getText().toString();
		if (name.isEmpty())
			isValid = false;

		EditText commentEditText = (EditText) vi.findViewById(R.id.comment);
		comment = commentEditText.getText().toString();

		RatingBar ratingBar = (RatingBar) vi.findViewById(R.id.ratingBar);
		rate = ratingBar.getRating();

		if (isValid) {
			Artist new_artist = new Artist(rate, comment, name, loc, type);
			artists.addArtist(new_artist);
		} else {
			Toast.makeText(UserView.context,
					getResources().getString(R.string.error_name),
					Toast.LENGTH_LONG).show();

			DialogFragment newFragment = new CreateArtistDialog(loc, map, false);
			newFragment.show(getFragmentManager(), "artists");
		}

	}
}