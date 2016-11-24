package com.example.bit_user.gugudanfighter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button start = (Button) findViewById(R.id.start);
		Button score = (Button) findViewById(R.id.score);
		Button helper = (Button) findViewById(R.id.helper);

		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
		score.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
				startActivity(intent);
			}
		});
		helper.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, HelperActivity.class);
				startActivity(intent);
			}
		});
	}
}
