package fpp.priangan.fujicon.angkot.rute;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.R;

public class List extends Activity {

	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	AlertDialogManager alert = new AlertDialogManager();

	ProgressDialog pDialog;
	String status= "1";

	JSONArray college = null;
	ListView lve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		lve = (ListView) findViewById(R.id.list);

		cekInternet();

		
	}




	public class AmbilData extends AsyncTask<String, String, String> {

		ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(List.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String url;
			url = "http://115.178.53.63:8010/app_angkot/node.php";

			JSONParser jParser = new JSONParser();

			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				college = json.getJSONArray("fujicon_dr_daily_report2");

				String success = json.getString("success1");

				if (success.equals("1")) {

					for (int i = 0; i < college.length(); i++) {
						JSONObject c = college.getJSONObject(i);
						HashMap<String, String> map = new HashMap<String, String>();

//						String id = c.getString("id").trim();
//						String latitude = c.getString("latitude");
//						String longitude = c.getString("longitude");
						String latlong = c.getString("latlong");

//						map.put("id", id);
//						map.put("nama", nama);
//						map.put("latitude", latitude);
						map.put("latlong", latlong);
						dataList.add(map);
					}
				} else {

					pDialog.dismiss();
					status = "0";

				}

			} catch (JSONException e) {

				pDialog.dismiss();

			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			if (status.equals("0")) {
				Toast.makeText(getApplicationContext(), "data tidak ada",
						Toast.LENGTH_SHORT).show();

			}

			ListAdapter adapter = new SimpleAdapter(getApplicationContext(),
					dataList, R.layout.list_item1, new String[] {"latlong" }, new int[] { R.id.nama });

			lve.setAdapter(adapter);

		}

	}

	public void cekInternet() {
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {

			new AmbilData().execute();

		} else {

			alert.showAlertDialog(
					List.this,
					"Peringatan",
					"Internet tidak tersedia, Silakn cek koneksi internet.",
					false);
		}
	}

}
