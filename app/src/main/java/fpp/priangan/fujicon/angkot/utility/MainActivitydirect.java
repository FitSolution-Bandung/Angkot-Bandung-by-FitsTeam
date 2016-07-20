package fpp.priangan.fujicon.angkot.utility;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import fpp.priangan.fujicon.angkot.DirectionsJSONParser;
import fpp.priangan.fujicon.angkot.R;

public class MainActivitydirect extends FragmentActivity implements
		OnClickListener {

	GoogleMap googleMap;
	double latitude;
	double longitude;
	Button rute;
	Button rute_intent;
	LatLng Jakarta, Bandung,tes;
	PolylineOptions lineOptions;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_maindirect);

		rute = (Button) findViewById(R.id.rute);
		rute_intent = (Button) findViewById(R.id.rute_intent);

		rute.setOnClickListener(this);
		rute_intent.setOnClickListener(this);

		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);


		googleMap = fm.getMap();

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		googleMap.setMyLocationEnabled(true);
		
		Jakarta= new LatLng(-6.197216,106.84468);
		Bandung= new LatLng(-6.916543,107.615494);
		tes=new LatLng(-6.9125363478601844, 107.60256193578243);
		googleMap.addMarker(new MarkerOptions()
		.position(Jakarta)
		.title("Jakarta")
		.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
		.snippet(
				"Ibukota Indonesia"));
		
		 googleMap.addMarker(new MarkerOptions()
		.position(Bandung)
		.title("Bandung")
		.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_RED))
		.snippet(
				"Ibukota Jawa Barat"));

		googleMap.addMarker(new MarkerOptions()
				.position(tes)
				.title("tes")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
				.snippet(
						"tes"));
		
		 googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Jakarta, 12));
		
	}

	@Override
	public void onClick(View v) {
		if(v==rute){
			
			try {
				
				String url = getDirectionsUrl(Jakarta, Bandung,tes);
				DownloadTask downloadTask = new DownloadTask();
				downloadTask.execute(url);
				
			} catch (Exception e) {
				// TODO: handle exception
				Intent inten = new Intent(
						getApplicationContext(),
						MainActivitydirect.class);
				startActivity(inten);
				finish();
			}
			
		
			
		} else if (v==rute_intent){
			
			String modeDirection = "driving";
			String url = "http://maps.google.com/maps?saddr="+Jakarta.latitude+","+Jakarta.longitude+"&daddr="+Bandung.latitude+","+Bandung.longitude+"&directionsmode=" + modeDirection+"&daddr="+tes.latitude+","+tes.longitude+"&directionsmode=" + modeDirection ;
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
			startActivity(intent);
			
		}
	}

	

	// fungsi mendapatkan rute
		private String getDirectionsUrl(LatLng origin, LatLng dest ,LatLng tes) {

			// Awal rute
			String str_origin = "origin=" + origin.latitude + ","
					+ origin.longitude;

			// Tujuan rute
			String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
			String str_tes = "destination=" + tes.latitude + "," + tes.longitude;

			// Sensor enabled
			String sensor = "sensor=false";

			// Membuat parameters untuk dimasukkan web service rute map google
			String parameters = str_origin + "&"+ str_tes + "&" + str_dest + "&" + sensor;

			// Output format
			String output = "json";

			// URL untuk eksekusi rute
			String url = "https://maps.googleapis.com/maps/api/directions/"
					+ output + "?" + parameters;

			return url;
		}

		// Metode mendapatkan json data dari url

		private String downloadUrl(String strUrl) throws IOException {
			String data = "";
			InputStream iStream = null;
			HttpURLConnection urlConnection = null;
			try {
				URL url = new URL(strUrl);

				// Creating an http connection to communicate with url
				urlConnection = (HttpURLConnection) url.openConnection();

				// menghubungkan ke url
				urlConnection.connect();

				// Membaca data dari url
				iStream = urlConnection.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(
						iStream));

				StringBuffer sb = new StringBuffer();

				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				data = sb.toString();

				br.close();

			} catch (Exception e) {
				//Log.d("Exception while downloading url", e.toString());
			} finally {
				iStream.close();
				urlConnection.disconnect();
			}
			return data;
		}

		// class untuk download data dari Google Directions URL
		private class DownloadTask extends AsyncTask<String, Void, String> {

			// Mendowload data dalam non-ui thread
			@Override
			protected String doInBackground(String... url) {

				// For storing data from web service
				String data = "";

				try {
					// Fetching the data from web service
					data = downloadUrl(url[0]);
				} catch (Exception e) {
					Log.d("Background Task", e.toString());
					Intent i = new Intent(getApplicationContext(),
							MainActivitydirect.class);
					startActivity(i);
					finish();
				}
				return data;
			}

			// di eksekusi di layar tampilan,setelah selesai ekseksui di
			// doInBackground()
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				ParserTask parserTask = new ParserTask();

				// Thread untuk parsing the JSON data
				parserTask.execute(result);

			}
		}

		/** Class untuk mengekstrak Google Directions dalam format JSON */
		private class ParserTask extends
				AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

			// Parsing data di dalam thread background
			@Override
			protected List<List<HashMap<String, String>>> doInBackground(
					String... jsonData) {

				JSONObject jObject;
				List<List<HashMap<String, String>>> routes = null;

				try {
					jObject = new JSONObject(jsonData[0]);
					DirectionsJSONParser parser = new DirectionsJSONParser();

					// Mulai parsing data
					routes = parser.parse(jObject);
				} catch (Exception e) {
					Intent i = new Intent(getApplicationContext(),
							MainActivitydirect.class);
					startActivity(i);
					finish();
				}
				return routes;
			}

			// Mengeksekusi di tampilan setelah proses ektrak data selesai
			@Override
			protected void onPostExecute(List<List<HashMap<String, String>>> result) {
				ArrayList<LatLng> points = null;
				lineOptions = null;

				// Traversing through all the routes
				for (int i = 0; i < result.size(); i++) {
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();

					// Menginisialisasi i-th route
					List<HashMap<String, String>> path = result.get(i);

					// Fetching all the points in i-th route
					for (int j = 0; j < path.size(); j++) {
						HashMap<String, String> point = path.get(j);

						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));
						LatLng position = new LatLng(lat, lng);

						points.add(position);
					}

					// Menambahkan semua points dalam rute ke LineOptions
					lineOptions.addAll(points);
					lineOptions.width(2);
					lineOptions.color(Color.BLUE);

				}
				
				googleMap.addPolyline(lineOptions);
				
				
			}
		}

	
	
	
	
}
