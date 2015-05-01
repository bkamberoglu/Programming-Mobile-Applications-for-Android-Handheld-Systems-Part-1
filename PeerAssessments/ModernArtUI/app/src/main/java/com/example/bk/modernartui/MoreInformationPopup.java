package com.example.bk.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MoreInformationPopup extends DialogFragment {
	static private final String URL = "http://www.moma.org";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.dialog_text)
				.setPositiveButton(R.string.dialog_visit,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {

								Intent intent = new Intent(Intent.ACTION_VIEW,
										Uri.parse(URL));
								Intent chooserIntent = Intent.createChooser(
										intent,
										getResources().getString(
												R.string.open_with));
								startActivity(chooserIntent);
							}
						}).setNegativeButton(R.string.dialog_not_now, null);

		return builder.create();
	}
}
