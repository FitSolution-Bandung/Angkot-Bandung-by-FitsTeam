package fpp.priangan.fujicon.angkot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

public class about extends AppCompatActivity {
	//Set waktu lama splashscreen
	//private static int splashInterval = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.about);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setTitle(null);
		getSupportActionBar().setTitle("");


		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.back);
		actionBar.setDisplayShowHomeEnabled(true);
		/*new Handler().postDelayed(new Runnable() {


			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(about.this, MainActivity.class);
				startActivity(i);


				//jeda selesai Splashscreen
				this.finish();
			}

			private void finish() {
				// TODO Auto-generated method stub

			}
		}, splashInterval);*/

	};

}
