package fr.faye.artstrit.windows;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import fr.faye.projet.artstrit.R;

public class HelpDialog extends DialogFragment{
	public HelpDialog() {
		
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {


		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View vi = inflater.inflate(R.layout.help_window, null);

		builder.setTitle(R.string.help_dialog_title)
				.setView(vi)
	
				.setNegativeButton(R.string.close_help,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

}
