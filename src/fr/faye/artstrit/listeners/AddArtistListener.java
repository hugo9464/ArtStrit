package fr.faye.artstrit.listeners;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.LatLng;

import fr.faye.artstrit.view.UserView;
import fr.faye.artstrit.windows.CreateArtistDialog;
import fr.faye.projet.artstrit.R;

public class AddArtistListener implements OnMapLongClickListener {

	private GoogleMap map;
	private FragmentManager fragmentManager;

	public AddArtistListener(GoogleMap map, FragmentManager fragmentManager) {
		this.map = map;
		this.fragmentManager = fragmentManager;
	}

	@Override
	public void onMapLongClick(LatLng position) {

		if (UserView.isOnline()) {
			DialogFragment newFragment = new CreateArtistDialog(position, map,
					true);
			newFragment.show(fragmentManager, "artists");
		} else
			Toast.makeText(
					UserView.context,
					UserView.context.getResources().getString(R.string.offline),
					Toast.LENGTH_LONG).show();

	}

}
