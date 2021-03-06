package com.letterhero.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public void buttonPressed(View v) {
		switch (v.getId()) {
		case R.id.playButton:
			Intent intent = new Intent(v.getContext(), FourLetterHero.class);
			startActivity(intent);
			break;
		case R.id.highscoreButton:
			showHighScoreDialog();
			break;
		case R.id.instructionButton:
			Intent intent1 = new Intent(v.getContext(), Instructions.class);
			startActivity(intent1);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void showHighScoreDialog() {
		SharedPreferences sp = this.getSharedPreferences(GameConsts.PREF_NAME, MODE_PRIVATE);

		String currentHighscoreName = sp.getString(GameConsts.PREF_USERNAME_KEY, null);
		int currentHighscore = sp.getInt(GameConsts.PREF_HIGHSCORE_KEY, 0);

		if (currentHighscoreName != null && currentHighscore > 0) {
			new AlertDialog.Builder(this)
					.setTitle("All hail the current hero : " + currentHighscoreName)
					.setMessage("Their top score : " + currentHighscore)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									return;
								}
							}).show();
		} else {
			Toast.makeText(getApplication(), "No current hero", Toast.LENGTH_SHORT).show();
		}

	}
}
