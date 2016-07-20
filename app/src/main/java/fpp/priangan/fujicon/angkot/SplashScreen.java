package fpp.priangan.fujicon.angkot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import fpp.priangan.fujicon.angkot.slide.MainActivitySlide;

public class SplashScreen extends Activity {
	//Set waktu lama splashscreen
	private static int splashInterval = 2000;
	String getrec;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {


			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(SplashScreen.this, Tes2.class);
				Bundle bundle = new Bundle();
				getrec="utama";
				bundle.putString("show", getrec);
				i.putExtras(bundle);
				startActivity(i);


				//jeda selesai Splashscreen
				this.finish();
			}

			private void finish() {
				// TODO Auto-generated method stub

			}
		}, splashInterval);

	};

}
