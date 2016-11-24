package com.example.bit_user.gugudanfighter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		TextView finishScore = (TextView) findViewById(R.id.finishScore);
		Button noButton = (Button) findViewById(R.id.noButton);
		Button yesButton = (Button) findViewById(R.id.yesButton);
		Intent intent = getIntent();
		int total = intent.getIntExtra("total", 0);
		int score = intent.getIntExtra("score", 0);
		finishScore.setText(score + "/" + total);

		yesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FinishActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
		noButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FinishActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

	}
}
