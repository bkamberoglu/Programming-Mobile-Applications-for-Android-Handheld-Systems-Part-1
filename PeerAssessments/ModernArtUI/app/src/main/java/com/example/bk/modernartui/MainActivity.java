package com.example.bk.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private SeekBar seekBar;
	private RelativeLayout rectacle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rectacle = (RelativeLayout) findViewById(R.id.rectacle);
		seekBar = (SeekBar) findViewById(R.id.seekBar);

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				progress = progresValue;

				for (int i = 0; i < rectacle.getChildCount(); i++) {
					View child = rectacle.getChildAt(i);

					int originalColor = Color.parseColor((String) child
							.getTag());
					int invertedColor = (0x00FFFFFF - (originalColor | 0xFF000000))
							| (originalColor & 0xFF000000);

					if (getResources().getColor(R.color.white) != originalColor
							&& getResources().getColor(R.color.lightgray) != originalColor) {

						int origR = (originalColor >> 16) & 0x000000FF;
						int origG = (originalColor >> 8) & 0x000000FF;
						int origB = originalColor & 0x000000FF;

						int invR = (invertedColor >> 16) & 0x000000FF;
						int invG = (invertedColor >> 8) & 0x000000FF;
						int invB = invertedColor & 0x000000FF;

						child.setBackgroundColor(Color.rgb(
								(int) (origR + (invR - origR)
										* (progress / 100f)),
								(int) (origG + (invG - origG)
										* (progress / 100f)),
								(int) (origB + (invB - origB)
										* (progress / 100f))));
						child.invalidate();
					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showDialog(MenuItem item) {

		new MoreInformationPopup().show(getFragmentManager(), TAG);
	}
}
