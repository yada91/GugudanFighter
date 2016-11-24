package com.example.bit_user.gugudanfighter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
	private static final int TIME_LIMIT = 10;
	private TextView textView;
	private TextView testView;
	private TextView scoreView;
	private LinearLayout line1;
	private LinearLayout line2;
	private LinearLayout line3;
	int result, answer, total, score;
	Timer timer = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		textView = (TextView) findViewById(R.id.timeView);
		testView = (TextView) findViewById(R.id.testView);
		scoreView = (TextView) findViewById(R.id.scoreView);
		line1 = (LinearLayout) findViewById(R.id.line1);
		line2 = (LinearLayout) findViewById(R.id.line2);
		line3 = (LinearLayout) findViewById(R.id.line3);
		//////////////////////////////////////////
		timer.schedule(new GameTimerTask(), 1000, 1000);
		makeNewTest();
	}

	public void makeNewTest() {
		/////////////////////////////////////////
		Map<String, Object> map = makeTest();
		testView.setText(map.get("test") + " ");
		answer = (int) map.get("result");
		/////////////////////////////////////////
		List<Integer> list = makeExample(answer);
		Ascending ascending = new Ascending();
		Collections.sort(list, ascending);
		makeButton(list);
		/////////////////////////////////////////
		scoreView.setText(score + "/" + total);
		/////////////////////////////////////////
	}

	public Map<String, Object> makeTest() {

		int i = (int) (Math.random() * 9) + 1;
		int j = (int) (Math.random() * 9) + 1;

		String test = i + " * " + j + " = ";
		int result = i * j;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", test);
		map.put("result", result);
		return map;
	}

	public List<Integer> makeExample(int answer) {

		int a;
		List<Integer> list = new ArrayList<Integer>();
		int count = 1;
		list.add(0, answer);
		while (list.size() < 9) {
			boolean flag = false;
			int x = (int) (Math.random() * 9) + 1;
			int y = (int) (Math.random() * 9) + 1;
			a = x * y;
			for (int i = 0; i <= list.size() - 1; i++) {
				if (a == list.get(i)) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			if (flag) {
				list.add(a);
			}
		}
		return list;
	}

	public void makeButton(List<Integer> list) {

		line1.removeAllViews();
		line2.removeAllViews();
		line3.removeAllViews();
		Button btn[] = new Button[9];

		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(this);
			btn[i].setText(list.get(i) + "");
			btn[i].setTextSize(17);
			btn[i].setId(list.get(i));
			if (i < 3) {
				line1.addView(btn[i]);
			} else if (i >= 6) {
				line2.addView(btn[i]);
			} else {
				line3.addView(btn[i]);
			}
			btn[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					result = view.getId();

					if (answer == result) {
						Toast.makeText(GameActivity.this, "정답", Toast.LENGTH_SHORT).show();
						total++;
						score++;
						makeNewTest();
					} else {
						Toast.makeText(GameActivity.this, "오답", Toast.LENGTH_SHORT).show();
						total++;
						makeNewTest();
					}

				}
			});
		}

	}

	class Ascending implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}

	}

	private class GameTimerTask extends TimerTask {
		private int seconds;

		@Override
		public void run() {
			seconds++;
			if (seconds >= TIME_LIMIT) {
				timer.cancel();
				Intent intent = new Intent(GameActivity.this, FinishActivity.class);
				intent.putExtra("total", total);
				intent.putExtra("score", score);
				startActivity(intent);

			}
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					textView.setText("남은시간: " + (TIME_LIMIT - seconds));
				}
			});
		}
	}
}


