package fpp.priangan.fujicon.angkot.rute;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import fpp.priangan.fujicon.angkot.CustomListhome;
import fpp.priangan.fujicon.angkot.R;
import fpp.priangan.fujicon.angkot.sheet.GMapV2GetRouteDirection;
import fpp.priangan.fujicon.angkot.slide.TheApp;

// com.google.android.gms.vision.barcode.Barcode;


public class Home2backup extends AppCompatActivity implements android.location.LocationListener,
		NavigationView.OnNavigationItemSelectedListener {
	ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();
	int tr=0;
	/*
	 * membuat variabel global
	 */
	//List<Overlay> mapOverlays;
	//Barcode.GeoPoint point1, point2;
	LocationManager locManager;
	Drawable drawable;
	Document document;
	GMapV2GetRouteDirection v2GetRouteDirection;
	LatLng fromPosition;
	LatLng toPosition;
	static GoogleMap mGoogleMap;
	Location location;
	double[] long_pergi,lat_pergi,lat_marker,long_marker;
	final int RQS_GooglePlayServices = 1;
	String latlong, line;
	double latitude, longitude;
	ProgressDialog pDialog;
	int lm = 0;
	JSONArray college = null;
	ListView lve;
	Button list, refresh;
	MarkerOptions markerOptions;
	LatLng latLngl = new LatLng(-6.936674547450142, 107.69859038293362), latLngt = new LatLng(-6.938276749765415, 107.67740026116371);
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	String posi,status,ank;
	int posis;
	AlertDialogManager alert = new AlertDialogManager();
	TextView tampung,nok;
	HashMap<String, String> map;
	private static final LatLng LOWER_MANHATTAN = new LatLng(-6.936674547450142, 107.69859038293362);
	private static final LatLng TIMES_SQUARE = new LatLng(-6.9370832526716795, 107.69287191331387);
	private static final LatLng BROOKLYN_BRIDGE = new LatLng(-6.938276749765415, 107.67740026116371);

	ViewPager viewPager;
	PagerAdapter adapter;
	//String[] no;
	String[] warna;
	//String[] nama_trayek;
	String[] lintasan;
	String[] harga;
	String[] operasi;
	//int[] gambar;
	TextView nom;
	FloatingActionButton fab3,fab4;
	BottomSheetLayout bottomSheetLayout;
	public Integer imageid[] = {
			R.drawable.ank1
	};
	private ListView lvdt;
	String []filterAddress = new String[1];
	String []filterAddress2 = new String[1];

	String []jarak = new String[1];
	String[] no= new String[] {
			/*"01B","01A","02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20","21",
			"22","23","24","26","27","28","31","32","33","34",
			"35","36"*/
			"01B"/*,"01A"*/,"02","03","04","05","06","07","08","09","10",
			"11","12","13","14","15","16","17","18","19","20","21",
			"22","23"/*,"24"*/,"26","27","28","31","32"/*,"33"*/,"34",
			"35","36"
	};

	String[] nama_trayek = new String[] {
			"Abdulmuis - Cicaheum via Aceh",
            /*"Abdulmuis - Cicaheum via Binong",*/
			"Abdulmuis - Dago",
			"Abdulmuis - Ledeng",
			"Abdulmuis - Elang",
			"Cicaheum - Ledeng",
			"Cicaheum - Ciroyom",
			"Cicaheum - Ciwastra",
			"Cicaheum - Cibaduyut",
			"Dago - ST Hall",
			"ST Hall - Sadang Serang",
			"Cimbuleuit - ST Hall",
			"ST Hall - Gedebage",
			"ST Hall - Sarijadi",
			"ST Hall - Gunung Batu",
			"Margahayu - Ledeng",
			"Riung Bandung - Dago",
			"Caringin - Dago",
			"Panghegar - Dipatiukur",
			"Ciroyom - Sarijadi",
			"Bumi Asri - Ciroyom",
			"Cikudapateh - Ciroyom",
			"Buah Batu - Sederhana",
			"Cijerah - Sederhana",
            /*"Cimindi - Sederhana",*/
			"Cisitu - Tegalega",
			"Cijerah - Ciwastra",
			"Cicadas - Elang",
			"Antapani - Ciroyom",
			"Cibiru - Cicadas",
            /*"Sekemirung - Panyileukan",*/
			"Caringin - Sadang Serang",
			"Cibaduyut - Karang Setra",
			"Cibogo - Elang"

	};
	int[] gambar=new int[]{
			R.drawable.b01,
            /*R.drawable.abdul_muis_cicaheum_via_binong,*/
			R.drawable.a02,
			R.drawable.a03,
			R.drawable.a04,
			R.drawable.a05,
			R.drawable.a06,
			R.drawable.a07,
			R.drawable.a08,
			R.drawable.a09,
			R.drawable.a10,
			R.drawable.a11,
			R.drawable.a12,
			R.drawable.a13,
			R.drawable.a14,
			R.drawable.a15,
			R.drawable.a16,
			R.drawable.a17,
			R.drawable.a18,
			R.drawable.a19,
			R.drawable.a20,
			R.drawable.cikudapateuh_ciroyom,
			R.drawable.buah_batu_sederhana,
			R.drawable.cijerah_sederhana,
           /* R.drawable.cimindi_sederhana,*/
			R.drawable.cisitu_tegalega,
			R.drawable.cijerah_ciwastra,
			R.drawable.cicadas_elang,
			R.drawable.antapani_ciroyom,
			R.drawable.cibiru_cicadas,
           /* R.drawable.sekemirung_panyileukan_dago_gedebage,*/
			R.drawable.caringin_sadang_serang,
			R.drawable.cibaduyut_karang_setra,
			R.drawable.cibogo_elang
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Bundle bundle = getIntent().getExtras();
		posi = bundle.getString("setk");
		status= bundle.getString("setstatus");
		tampung = (TextView) findViewById(R.id.tampung);
		tampung.setText(posi);
		//Toast.makeText(getApplicationContext(),"You Clicked "+posi,Toast.LENGTH_SHORT).show();
//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
//		getSupportActionBar().setDisplayShowHomeEnabled(true);
//		getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
//		getSupportActionBar().setDisplayUseLogoEnabled(true);
//
//
//		ActionBar actionBar = getSupportActionBar();
//		actionBar.setHomeButtonEnabled(true);
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setHomeAsUpIndicator(R.drawable.back);
//		actionBar.setDisplayShowHomeEnabled(true);

		////////////////
		bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
		fab3 = (FloatingActionButton) findViewById(R.id.fab3);
		fab4 = (FloatingActionButton) findViewById(R.id.fab4);
		fab4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(tr==0){

					fab4.setImageResource(R.drawable.ijo1);
					mGoogleMap.setTrafficEnabled(true);
					tr=tr+1;
				}else if(tr==1){
					fab4.setImageResource(R.drawable.merah1);
					mGoogleMap.setTrafficEnabled(false);
					tr=tr-1;
				}
			}
		});
		fab3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheethome, bottomSheetLayout, false));
				TextView textView14 = (TextView) findViewById(R.id.textView14);
				Button satu = (Button) findViewById(R.id.button);
				Button dua = (Button) findViewById(R.id.button2);
				Button tiga = (Button) findViewById(R.id.button3);
				int ank = 0;

				if (ank == 0) {
					satu.setVisibility(View.VISIBLE);
					dua.setVisibility(View.GONE);
					tiga.setVisibility(View.GONE);
				}

				String names[] = {
						"01B", "01A", "02", "03"
				};
				String desc[] = {
						"Abdulmuis - Cicaheum via Aceh",
						"Abdulmuis - Cicaheum via Binong",
						"Abdulmuis - Dago",
						"Abdulmuis - Ledeng"

				};

//				"01B",/*,"01A"*/,"02","03","04","05","06","07","08","09","10",
//						"11","12","13","14","15","16","17","18","19","20","21",
//						"22","23"/*,"24"*/,"26","27","28","31","32"/*,"33"*/,"34",
//						"35","36"
				if(posi.equals("01B")){
					posis=0;
				}else if(posi.equals("02")){
					posis=1;
				}else if(posi.equals("03")){
					posis=2;
				}else if(posi.equals("04")){
					posis=3;
				}else if(posi.equals("05")){
					posis=4;
				}else if(posi.equals("06")){
					posis=5;
				}else if(posi.equals("07")){
					posis=6;
				}else if(posi.equals("08")){
					posis=7;
				}else if(posi.equals("09")){
					posis=8;
				}else if(posi.equals("10")){
					posis=9;
				}else if(posi.equals("11")){
					posis=10;
				}else if(posi.equals("12")){
					posis=11;
				}else if(posi.equals("13")){
					posis=12;
				}else if(posi.equals("14")){
					posis=13;
				}else if(posi.equals("15")){
					posis=14;
				}else if(posi.equals("16")){
					posis=15;
				}else if(posi.equals("17")){
					posis=16;
				}else if(posi.equals("18")){
					posis=17;
				}else if(posi.equals("19")){
					posis=18;
				}else if(posi.equals("20")){
					posis=19;
				}else if(posi.equals("21")){
					posis=20;
				}else if(posi.equals("22")){
					posis=21;
				}else if(posi.equals("23")){
					posis=22;
				}else if(posi.equals("26")){
					posis=23;
				}else if(posi.equals("27")){
					posis=24;
				}else if(posi.equals("28")){
					posis=25;
				}else if(posi.equals("31")){
					posis=26;
				}else if(posi.equals("32")){
					posis=27;
				}else if(posi.equals("34")){
					posis=28;
				}else if(posi.equals("35")){
					posis=29;
				}else if(posi.equals("36")){
					posis=30;
				}

				satu.setBackgroundResource(gambar[posis]);
				satu.setText(no[posis]);
				textView14.setText("Nama Trayek :"+nama_trayek[posis]);
				CustomListhome customList = new CustomListhome(Home2backup.this, filterAddress, filterAddress2, imageid,jarak);
				lvdt = (ListView) findViewById(R.id.lvdt);
				lvdt.setAdapter(customList);
				lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						///  Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();


					}
				});
			}
		});

		////////////////
	/*	String[] ket= new String[] {
				"Mulai dari St.Hall",
				"Menuju Jl.Kb Jukut",
				"Menuju Jl.Kb Sirih"
		};
		String[] no= new String[] {
				"1",
				"2",
				"3"
		};

		int[] gambar=new int[]{
				R.drawable.marker_50,R.drawable.marker_50,R.drawable.marker_50
		};
		// Locate the ViewPager in viewpager_main.xml
		viewPager = (ViewPager) findViewById(R.id.pager);
		// Pass results to ViewPagerAdapter Class
		adapter = new ViewPagerAdapterRute(Home.this,no,gambar,ket);
		// Binds the Adapter to the ViewPager
		viewPager.setAdapter(adapter);
//		Bundle bundle = getIntent().getExtras();
//		int posi = bundle.getInt("setk");
//		viewPager.setCurrentItem(posi);*/
//		no= new String[] {
//				"Mulai dari St.Hall",
//				"Menuju Jl.Kb Jukut",
//				"Menuju Jl.Kb Sirih"
//		};

		String[] warna= new String[] {
				"Hijau-Merah Tua","Hijau-Kuning","Hijau-Orange","Hijau-Biru Muda","Orange-Putih-Hijau","Hijau-Hitam","Hijau-Orange-Hijau","Coklat-Krem-Coklat","Merah-Putih-Merah","Hijau-Orange-Hijau","Hijau-Kuning-Hijau",
				"Hijau-Biru-Hijau","Hijau Muda-kuning-Hijau","Biru Tua-Orange-Hijau","Biru Muda-Orange-Hijau Lintas","Kuning-Biru-Kuning-Biru","Putih-Kuning-Hijau","Orange-Putih-Hijau","Putih-Kuning-Merah-Hijau","Hijau-Krem-Hijau","Hijau-Strip Krem-Orange","Putih-Kuning-Putih-Hijau",
				"Biru-Putih-Hijau","Hijau-Merah-Hijau","Biru Muda-Biru Tua-Hijau","Ungu-Putih-Hijau","Abu Abu-Putih-Hijau","Biru-Krem-Hijau","Coklat-Merah-Hijau","Hijau-Biru-Hijau","Pink-Putih-Hijau","Biru Muda-Kuning-Hijau",
				"Kuning-Putih-Hijau","Biru Muda-Biru Tua-Hijau","Hijau-Krem","Hijau-Hijau","Krem-Merah-Hitam"
		};
//		String[] nama_trayek = new String[] {
//				"Abdulmuis - Cicaheum via Aceh",
//				"Abdulmuis - Cicaheum via Binong",
//				"Abdulmuis - Dago",
//				"Abdulmuis - Ledeng",
//				"Abdulmuis - Elang",
//				"Cicaheum - Ledeng",
//				"Cicaheum - Ciroyom",
//				"Cicaheum - Ciwastra",
//				"Cicaheum - Cibaduyut",
//				"Dago - ST Hall",
//				"ST Hall - Sadang Serang",
//				"Cimbuleuit - ST Hall",
//				"ST Hall - Gedebage",
//				"ST Hall - Sarijadi",
//				"ST Hall - Gunung Batu",
//				"Margahayu - Ledeng",
//				"Riung Bandung - Dago",
//				"Caringin - Dago",
//				"Panghegar - Dipatiukur",
//				"Ciroyom - Sarijadi",
//				"Bumi Asri - Ciroyom",
//				"Cikudapateh - Ciroyom",
//				"Buah Batu - Sederhana",
//				"Cijerah - Sederhana",
//				"Cimindi - Sederhana",
//				"Cisitu - Tegalega",
//				"Cijerah - Ciwastra",
//				"Cicadas - Elang",
//				"Antapani - Ciroyom",
//				"Cibiru - Cicadas",
//				"Sekemirung - Panyileukan",
//				"Caringin - Sadang Serang",
//				"Cibaduyut - Karang Setra",
//				"Cibogo - Elang",
//				"Ciburial - Ciroyom",
//				"Cicaheum - Cileunyi",
//				"Lembang - ST Hall"
//
//		};
		String[] lintasan= new String[] {
				"RUTE 1 (DARI ABDUL MUIS KE CICAHEUM)\n" +
						"Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Lombok – Jl. Aceh – Jl. Taman Pramuka – Jl. Cendana – Taman WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum\n" +
						" \n" +
						"RUTE 2 (DARI CICAHEUM KE ABDUL MUIS)\n" +
						"Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Cikutra – Jl. Katamso – Taman WR. Supratman – Jl. Cendana – Jl. Taman Pramuka – Jl. Aceh – Jl. Lombok – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
				"RUTE 1 (DARI ABDUL MUIS KE CICAHEUM)\n" +
						"Terminal Kebon Kelapa – Jl. Pungkur – Jl. Karapitan – Jl. Buah Batu – Jl. Banteng – Jl. Sancang – Jl. Lodaya – Jl. Martanegara – Jl. Turangga – Jl. Gatot Subroto – BSM – Binong – Jl. Kiara Condong – Jl. Jakarta – Jl. WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum\n" +
						" \n" +
						"RUTE 2 (DARI CICAHEUM KE ABDUL MUIS)\n" +
						"Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Cikutra – Jl. Katamso – Jl. WR. Supratman – Jl. Ahmad Yani – Jl. Jakarta – Jl. Kiara Condong – Binong – BSM – Jl. Turangga – Jl. Martanegara – Jl. Lodaya – Jl. Gajah – Jl. Buah Batu – Jl. Gurame – Jl. Moh. Ramdan – Jl. BKR – Jl. Moh. Toha – Jl. Dewi Sartika – Terminal Kebon Kelapa",
				"RUTE 1 (DARI ABDUL MUIS KE DAGO)\n" +
						"Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Sumbawa – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Sulawesi – Jl. Seram – Jl. RE Martadinata (Riau) – Jl. Ir. H. Juanda (Dago) – RS. Boromeus (Dago) – ITB (Jl. Ganesha, Dago) – Simpang Dago – Terminal Dago\n" +
						" \n" +
						"RUTE 2 (DARI DAGO KE ABDUL MUIS)\n" +
						"Terminal Dago – Jl. H. Juanda (Dago) – Simpang Dago – ITB (Jl. Ganesha, Dago) – RS. Boromeus (Dago) – Jl. Sultan Agung – Jl. Trunojoyo – Jl. RE. Martadinata – BIP (Jl. Merdeka Dago) – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
				"RUTE 1 (DARI ABDUL MUIS KE LEDENG)\n" +
						"Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Kautamaan Istri – Jl. Balong Gede – Jl. Pungkur – Jl. Karapitan – Jl. Sunda – Jl. Sumbawa – Jl. Lombok – Jl. Banda – Jl. RE Martadinata (Riau) – BIP (Dago) – Jl. Merdeka – Jl. Aceh – Jl. Wastu Kencana – Jl. Rivai – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
						"\n" +
						"RUTE 2 (DARI LEDENG KE ABDUL MUIS)\n" +
						"Terminal Ledeng – Jl. Setiabudi – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – BEC (Purnawarman) – Jl. Wastu Kencana – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Sumatera – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
				"RUTE 1 (DARI ABDUL MUIS KE ELANG)\n" +
						"Terminal Kebon Kelapa – Jl. Pungkur – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Astana Anyar – Jl. Panjunan – Jl. Kopo – Jl. Pasir Koja – Jl. Astana Anyar – Jl. Pagarsih – Jl. Nawawi – Jl. Aksan – Jl. Suryani – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Elang – Terminal Elang\n" +
						"\n" +
						"RUTE 2 (DARI ELANG KE ABDUL MUIS)\n" +
						"Terminal Elang – Jl. Sukarno-Hatta – Jl. Holis – Jl. Nana Rohana – Jl. Suryani – Jl. Situ Aksan – Jl. Pagarsih – Jl. Kalipah Apo – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa",
				"RUTE 1 (DARI CICAHEUM KE LEDENG)\n" +
						"Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Katamso – Jl. WR. Supratman – Jl. Diponegoro – Jl. Sulanjana – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Lamping – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
						"\n" +
						"RUTE 2 (DARI LEDENG KE CICAHEUM)\n" +
						"Terminal Ledeng – Jl. Setiabudi – Jl. Cihampelas – Jl. Siliwangi – Jl. Sumur Bandung – Jl. Tamansari – Jl. Sulanjana – Jl. Diponegoro – Jl. WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum",
				"RUTE 1 (DARI CICAHEUM KE CIROYOM)\n" +
						"Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Surapati (Suci) – Lapangan Gasibu (Surapati) – Jl. Panatayuda – Jl. Dipati Ukur – Simpang Dago – Jl. Sumur Bandung – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Eyckman – RS. Hasan Sadikin – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Ciroyom – Terminal Ciroyom\n" +
						"\n" +
						"RUTE 2 (DARI CIROYOM KE CICAHEUM)\n" +
						"Terminal Ciroyom – Jl. Ciroyom – Jl. Arjuna – Jl. Pajajaran – Jl. Pasir Kaliki – RS Hasan Sadikin – Jl. Eyckman – Jl. Cipaganti – Jl. Setiabudi – Jl. Cihampelas – Jl. Siliwangi – Simpang Dago – Jl. Dipati Ukur – Jl. Singa Perbangsa – Monumen Perjuangan – Jl. Japati – Lapangan Gasibu (Surapati) – Jl. Surapati (Suci) – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum",
				"*Terminal Cicaheum – *Jl. PHH. Mustofa (Suci) – Jl. Surapati (Suci) – Jl. Sentot Alibasyah – Jl. Diponegoro – Jl. WR. Supratman – Jl. Ahmad Yani – Jl. Jakarta – Jl. Kiara Condong – Jl. Terusan Kiara Condong – Jl. Margacinta – Jl. Ciwastra – Terminal Ciwastra\n" +
						"\n" +
						"RUTE 2 (DARI CIWASTRA KE CICAHEUM)\n" +
						"Terminal Ciwastra – Jl. Ciwastra – Jl. Margacinta – Jl. Terusan Kiara Condong – Jl. Kiara Condong – Jl. Jakarta – Jl. WR. Supratman – Jl. Diponegoro – Jl. Sentot Alibasyah Lapangan Gasibu – Jl. Surapati (Suci) – *Jl. PHH. Mustofa (Suci) – *Terminal Cicaheum\n" +
						" \n" +
						"*hampir tidak pernah dilewati",
				"RUTE 1 (DARI CICAHEUM KE CIBADUYUT)\n" +
						"*Terminal Cicaheum – *Jl. PHH. Mustofa (Suci) – *Jl. Katamso – *Jl. WR. Supratman – *Jl. Ahmad Yani – Jl. Jakarta – Jl. Kiara Condong – Jl. Sukarno-Hatta – Terminal Leuwi Panjang (Sukarno-Hatta) – Jl. Kopo – Jl. Cibaduyut – Terminal Cibaduyut\n" +
						"\n" +
						"RUTE 2 (DARI CIBADUYUT KE CICAHEUM)\n" +
						"Terminal Cibaduyut – Jl. Cibaduyut – Jl. Kopo – Terminal Leuwi Panjang (Sukarno-Hatta) – Jl. Sukarno-Hatta – Jl. Kiara Condong – Jl. Jakarta – *Jl. WR. Supratman – *Jl. Katamso – *Jl. Pahlawan – *Jl. Cikutra – *Jl. PHH. Mutofa (Suci) – *Terminal Cicaheum",
				"RUTE 1 (DARI DAGO KE ST.HALL)\n" +
						"Terminal Dago – Jl. Ir. H. Juanda – Simpang Dago – ITB (Jl. Ganesha, Dago) – RS Boromeus (Dago) – BIP (Merdeka) – Jl. Merdeka – Jl. Perintis Kemerdekaan – Jl. Braga – Jl. Suniaraja – Jl. Stasiun Barat (Stasiun Bandung)\n" +
						"RUTE 2 (DARI ST.HALL KE DAGO)\n" +
						"Jl. Stasiun Barat (Stasiun Bandung) – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Ir. H. Juanda – RS Boromeus (Dago) – ITB (Jl. Ganesha, Dago) – Simpang Dago – Terminal Dago",
				"RUTE 1 (DARI ST.HALL KE SADANG SERANG)\n" +
						"Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Braga – Jl. Lembong – Jl. Veteran – Jl. Sunda – Jl. Sumbawa – Jl. Lombok – Jl. Citarum – Jl. WR. Supratman – Jl. Katamso – Jl. Pahlawan – Jl. Cikutra Barat – Terminal Sadang Serang\n" +
						"\n" +
						"RUTE 1 (DARI SADANG SERANG KE ST.HALL)\n" +
						"Terminal Sadang Serang – Jl. Cikutra Barat – Jl. Pahlawan – Jl. Katamso – Jl. WR. Supratman – Jl. Citarum – Jl. Lombok – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Jl. Braga – Jl. Suniaraja – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Suniaraja – Terminal Stasiun",
				"RUTE 1 (DARI CIUMBULEUIT KE ST.HALL)\n" +
						"Terminal Ciumbuleuit – Jl. Ciumbuleuit – UNPAR (Ciumbuleuit) – Jl. Cihampelas – Jl. Bapa Husen – Jl. Sederhana -  Jl. Pasir Kaliki – RS. Hasan Sadikin – Jl. Pasteur – Jl. Cihampelas – Jl. Rivai – Jl. Cipto – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Terminal Stasiun\n" +
						"\n" +
						"RUTE 2 (DARI ST.HALL KE CIUMBULEUIT)\n" +
						"Terminal Stasiun – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Cipaganti – Jl. Eyckman – Jl. Sederhana – Jl. Sempuna – Jl. Cipaganti – Jl. Setiabudi – Jl. Ciumbuleuit – UNPAR (Ciumbuleuit) – Terminal Ciumbuleuit",
				"RUTE 1 (DARI ST.HALL KE GEDEBAGE)\n" +
						"St.Hall – Jl. Dulatip – Pasar Baru – Jl. Otto Iskandardinata (Otista) – Jl. Kepatihan – Jl. Dewi Sartika – Jl. Dalem Kaum – Alun-Alun (Asia Afrika) – Jl. Banceuy – Jl. ABC – Jl. Naripan – Jl. Sunda – Jl. Veteran – Jl. Ahmad Yani – Jl. Gatot Subroto – Jl. Burangrang – Jl. Halimun – Jl. Malabar – Jl. Talaga Bodas – Jl. Pelajar Pejuang – Jl. Martanegara – Jl. Reog – Jl. Karawitan – Jl. Kliningan – Jl. Buah Batu – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Pasar Induk Gede Bage\n" +
						"\n" +
						"RUTE 1 (DARI GEDEBAGE KE ST.HALL)\n" +
						"Pasar Induk Gede Bage – Jl. Sukarno-Hatta – Riung Bandung (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Buah Batu – Jl. Kliningan – Jl. Karawitan – Jl. Mas Kumambang – Jl. Martanegara – Jl. Pelajar Pejuang – Jl. Talaga Bodas – Jl. Malabar – Jl. Ahmad Yani – Pasar Kosambi (Ahmad Yani) – Jl. Sunda – Jl. Sumbawa – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – St.Hall",
				"RUTE 1 (DARI ST.HALL KE SARIJADI)\n" +
						"St.Hall – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawun) – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. DR. Junjunan (Terusan Pasteur) – BTC (Pasteur) – Jl. Surya Sumantri – Universitas Maranatha (Surya Sumantri) – Jl. Lemah Nendeut (Sarijadi) – Jl. Sari Rasa (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Asih (Sarijadi) – Terminal Sarijadi\n" +
						"\n" +
						"RUTE 1 (DARI SARIJADI KE ST.HALL)\n" +
						"Terminal Sarijadi – Jl. Sari Asih (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Jl. Sari Rasa (Sarijadi) – Jl. Surya Sumantri – Universitas Maranatha (Surya Sumantri) – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – St.Hall",
				"RUTE 1 (DARI ST.HALL KE GUNUNG BATU)\n" +
						"Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Perintis Kemerdekaan – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Rum – Jl. Gunawan – Jl. Otten – Jl. Pasteur – BEC (Pasteur) – Jl. Westhoff – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Gunung Batu – Terminal Gunung Batu\n" +
						"\n" +
						"RUTE 1 (DARI GUNUNG BATU KE ST.HALL)\n" +
						"Terminal Gunung Batu – Jl. Gunung Batu – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Westhoff – Jl. Pasteur – BEC (Pasteur) – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – Terminal Stasiun",
				"RUTE 1 (DARI MARGAHAYU KE LEDENG)\n" +
						"Terminal Margahayu – Jl. Ranca Bolang (Margahayu Raya) – Jl. Sukarno-Hatta – Jl. Kiara Condong – Jl. Jakarta – Jl. WR. Supratman – Jl. Cendana – Jl. Taman Pramuka – Jl. RE. Martadinata – ??? – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Rivai – Jl. Cipaganti – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng\n" +
						"\n" +
						"RUTE 1 (DARI LEDENG KE MARGAHAYU)\n" +
						"Terminal Ledeng – Jl. Setiabudi – Jl. Karang Sari – Jl. Sukajadi – Jl. Cemara – Jl. Jurang – Jl. Sederhana – Jl. Eyckman – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Ahmad Yani – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Sukarno-Hatta – Jl. Ranca Bolang (Margahayu Raya) – Terminal Margahayu",
				"RUTE 1 (DARI RIUNG BANDUNG KE DAGO)\n" +
						"Terminal Riung Bandung – Jl. Riung Bandung – Jl. Cipamolokan (Riung Bandung) – Jl. Sukarno-Hatta – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Kiara Condong – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Jl. RE. Martadinata – Jl. Anggrek – Jl. Gudang Utara – Jl. Patra Komala – Jl. Belitung – Jl. Banda – Jl. Diponegoro – Jl. Aria Jipang – Jl. Surapati (Suci) – Jl. Panatayuda – Jl. Dipati Ukur – Simpang Dago – Jl. Ir. H. Juanda – Terminal Dago\n" +
						"\n" +
						"RUTE 2 (DARI DAGO KE RIUNG BANDUNG)\n" +
						"Terminal Dago – Jl. Ir. H. Juanda (Dago) – Simpang Dago – Jl. Dipati Ukur – Jl. Panatayuda – Jl. Surapati (Suci) – Jl. Sentot Alibasyah – Jl. Diponegoro – Jl. Citarum – Jl. RE. Martadinata – Jl. Laswi – Jl. Sukabumi – Jl. Ahmad Yani – Jl. Kiara Condong – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Jl. Cipamolokan (Riung Bandung) – Jl. Riung Bandung – Terminal Riung Bandung",
				"RUTE 1 (DARI CARINGIN KE DAGO)\n" +
						"Pasar Induk Caringin – Jl. Babakan Ciparay – Jl. Sukarno-Hatta – Jl. Sukamulya – Jl. Terusan Jamika – Jl. Jamika – Jl. Sudirman – Jl. Guanan – Jl. Kebon Jati – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – Jl. Cikapayang – Jl. Surapati (Suci) – Jl. Pahlawan – Jl. Cikutra Barat – Jl. Cigadung Raya – Terminal Dago\n" +
						"\n" +
						"RUTE 2 (DARI DAGO KE CARINGIN)\n" +
						"Terminal Dago – Jl. Cigadung Raya – Jl. Cikutra Barat – Jl. Pahlawan – Jl. Surapati (Suci) – Jl. Cikapayang – Jl. Tamansari – Jl. Sawunggaling – Jl. Rangga Gading – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – Jl. Wastu Kencana – Jl. Purnawarman – Jl. Pajajaran – Jl. Cicendo – Jl. Rivai – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Arjuna – Jl. Supadio – Jl. Ciroyom – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Waringin – Jl. Sudirman – Jl. Jamika – Jl. Terusan Jamika – Jl. Sukamulya – Jl. Sukarno-Hatta – Jl. Babakan Ciparay – Pasar Induk Caringin (Sukarno-Hatta)",
				"RUTE 1 (DARI PANGHEGAR KE DIPATIUKUR)\n" +
						"Terminal Panghegar – Jl. Cisaranten – Jl. Cicukang – Jl. AH. Nasution (Raya Ujung Berung) – Sindanglaya (Nasution) – Terminal Cicaheum – Jl. Ahmad Yani – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Jl. RE. Martadinata – Jl. Ambon – Masjid Istiqamah – Jl. Cisanggarung – Jl. Cimanuk – Jl. Cimandiri – Jl. Cimalaya – Jl. Diponegoro – Jl. Sulanjana – Jl. Tamansari – Jl. Ganesha – ITB – RS. Boromeus – Jl. Hasanudin – Jl. Dipati Ukur – Terminal Dipati Ukur\n" +
						"\n" +
						"RUTE 2 (DARI DIPATIUKUR KE PANGHEGAR)\n" +
						"Terminal Dipati Ukur – Jl. Hasanudin – Jl. Ir. H. Juanda – RS. Boromeus – Jl. Ganesha – ITB – Jl. Tamansari – Jl. Sawunggaling – UNISBA & UNPAS (Tamansari) – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Banda – Jl. Belitung – Jl. Sumatera – Jl. Veteran – Jl. Ahmad Yani – Pasar Kosambi (Ahmad Yani) – Cicadas (Ahmad Yani) – Terminal Cicaheum – Jl. AH. Nasution – Sindanglaya (Nasution) – Ujung Berung (Nasution) – Jl. Cicukang – Jl. Cisaranten – Panghegar",
				"RUTE 1 (DARI CIROYOM KE SARIJADI)\n" +
						"Terminal Ciroyom – Jl. Arjuna – Jl. Pajajaran – Jl. Baladewa – Jl. Dursasana – Jl. Pasir Kaliki – Jl. Sukajadi – Jl. Sindang Sirna – Jl. Sindang Sirna – Jl. Geger Kalong Hilir – Jl. Sari Endah – Jl. Sari Jadi – Jl. Sari Manah (Sarijadi) – Jl. Sari Wangi (Sarijadi) – Terminal Sarijadi\n" +
						"\n" +
						"RUTE 2 (DARI SARIJADI KE CIROYOM)\n" +
						"Terminal Sarijadi – Jl. Sari Wangi (Sarijadi) – Jl. Sari Manah (Sarijadi) – Jl. Sari Asih (Sarijadi) – Jl. Sari Jadi – Jl. Geger Kalong Hilir – Jl. Cipedes – Jl. Sindang Sirna – Jl. Sirnagalih – Jl. Sukajadi – Jl. Sukamaju – Jl. Sederhana – RS. Hasan Sadikin – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Ciroyom – Terminal Ciroyom",
				"RUTE 1 (DARI BUMI ASRI KE CIROYOM)\n" +
						"Bumi Asri – Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Sukarno-Hatta – Jl. Sudirman – Jl. Rajawali Barat – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Stasiun Timur – Viaduct – Jl. Wastu Kencana – Jl. Rivai – Jl. Cipto – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. Pajajaran – Jl. Arjuna – Jl. Supadio – Jl. Ciroyom – Terminal Ciroyom\n" +
						"\n" +
						"RUTE 2 (DARI CIROYOM KE BUMI ASRI)\n" +
						"Terminal Ciroyom – Jl. Ciroyom – Jl. Garuda – Jl. Sudirman – Jl. Sukarno-Hatta – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Bumi Asri",
				"RUTE 1 (DARI CIKUDAPATEUH KE CIROYOM)\n" +
						"Jl. Cikudapateuh – Jl. Kembang Sepatu – Jl. Tarate – Jl. Samboja – Jl. Laswi – Jl. Gatot Subroto – Jl. Malabar – Jl. Buah Batu – Jl. Gurame – Jl. Moh. Ramdan – Jl. BKR – Jl. Peta – Jl. Kopo – Jl. Pasir Koja – Jl. Astana Anyar – Jl. Cibadak – Jl. Sudirman – Jl. Guanan – Jl. Kebon Jati – Jl. Arjuna – Terminal Ciroyom\n" +
						"\n" +
						"RUTE 2 (DARI CIROYOM KE CIKUDAPATEUH)\n" +
						"Terminal Ciroyom – Jl. Arjuna – Jl. Kebon Jati – Jl. Gardu Jati – Jl. Astana Anyar – Jl. Kopo – Jl. Peta – Jl. BKR – Jl. Moh. Ramdan – Jl. Banteng – Jl. Ahmad Yani – Jl. Cikudapateuh",
				"RUTE 1 (DARI BUAH BATU KE KEBON KELAPA)\n" +
						"Terminal Buah Batu – Jl. Buah Batu – Jl. Gurame – Jl. Karapitan – Jl. Lengkong Kecil – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa– Jl. Dewi Sartika – Jl. Banceuy – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Jl. Kebon Jukut – Jl. Kebon Kawung (Stasiun Bandung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Rivai – Jl. Rum – Jl. Gunawan – Jl. Otten – Jl. Pasteur – Jl. Pasir Kaliki (RS. Hasan Sadikin) – Jl. Sederhana – Terminal Sederhana\n" +
						"\n" +
						"RUTE 2 (DARI KEBON KELAPA KE BUAH BATU)\n" +
						"Terminal Sederhana – Jl. Jurang – Jl. Cemara – Jl. Sukajadi – Jl. Pasir Kaliki (RS. Hasan Sadikin) – Jl. Pasteur – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Jukut – Viaduct – Jl. Braga – Jl. Lembong – Jl. Tamblong – Jl. Lengkong Besar – Jl. Ciateul – Jl. Dewi Sartika – Terminal Kebon Kelapa– Jl. Dalem Kaum – Jl. Lengkong Besar – Jl. Cikawao – Jl. Buah Batu – Jl. Banteng – Jl. Gajah – Jl. Buah Batu – Terminal Buah Batu",
				"RUTE 1 (DARI CIJERAH KE SEDERHANA)\n" +
						"Jl. Melong Asih – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Garuda – Jl. Abdul Rahman Saleh – Jl. Pajajaran – Jl. Pandu – Jl. Dursasana – Jl. Pasir Kaliki – Jl. Sederhana\n" +
						"\n" +
						"RUTE 2 (DARI SEDERHANA KE CIJERAH)\n" +
						"Jl. Sederhana – Jl. Pasir Kaliki – Istana Plaza (Pasir Kaliki) – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Jl. Cijerah – Jl. Melong Asih",
				"RUTE 1 (DARI CIMINDI KE SEDERHANA)\n" +
						"Cimindi – Jl. Gunung Batu – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Cipedes Kidul – Jl. Sukajadi – Jl. Cemara – Jl. Jurang – Sederhana\n" +
						"\n" +
						"RUTE 2 (DARI SEDERHANA KE CIMINDI)\n" +
						"Jl. Sederhana – Jl. Sukajadi – Jl. Sukagalih – Jl. Cipedes Kidul – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Gunung Batu – Cimindi",
				"RUTE 1 (DARI CISITU KE TEGALEGA)\n" +
						"Terminal Cisitu – Jl. Cisitu Lama VIII – Jl. Cisitu – Jl. Sangkuriang – Jl. Siliwangi – Jl. Sumur Bandung – Jl. Tamansari – Jl. Siliwangi – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Suniaraja – Terminal Stasiun – Jl. Dulatip – Pasar Baru – Jl. Sudirman – Jl. Astana Anyar – Jl. Kalipah Apo – Jl. Otto Iskandardinata (Otista) – Terminal Tegalega\n" +
						"\n" +
						"RUTE 2 (DARI TEGALEGA KE CISITU)\n" +
						"Terminal Tegalega – Jl. Astana Anyar – Jl. Panjunan – Jl. Kopo – Jl. Pasir Koja – Jl. Pajagalan?? – Jl. Gardu Jati – Jl. Suniaraja – Terminal Stasiun – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cihampelas – Jl. Wastu Kencana – UNISBA & UNPAS (Tamansari) – Jl. Tamansari – ITB – Jl. Siliwangi – Jl. Sangkuriang – Jl. Cisitu – Jl. Cisitu Lama – Jl. Cisitu Lama VIII – Terminal Cisitu",
				"RUTE 1 (DARI CIJERAH KE CIWASTRA)\n" +
						"Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Caringin – Jl. Kopo – Jl. Peta – Jl. BKR – Jl. Moh. Ramdan – Jl. Sadakeling – Jl. Talaga Bodas – Jl. Pelajar Pejuang – Jl. Martanegara – Jl. Reog – Jl. Karawitan -  Jl. Kliningan – Jl. Buah Batu – Jl. Sukarno-Hatta – Jl. Terusan Kiara Condong – Jl. Margacinta – Jl. Ciwastra – Terminal Ciwastra\n" +
						"\n" +
						"RUTE 2 (DARI CIWASTRA KE CIJERAH)\n" +
						"Terminal Ciwastra – Jl. Ciwastra – Jl. Margacinta – Jl. Terusan Kiara Condong – Jl. Sukarno-Hatta – Jl. Buah Batu – Jl. Kliningan – Jl. Karawitan – Jl. Reog – Jl. Martanegara – Jl. Lodaya – Jl. Sadakeling – Jl. Buah Batu – Jl. Moh. Ramdan – Jl. BKR – Jl. Peta – Jl. Bojongloa – Jl. Sukarno-Hatta – Jl. Caringin – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah",
				"RUTE 1 (DARI CICADAS KE ELANG)\n" +
						"Cicadas – Jl. Kiara Condong – Jl. Gatot Subroto – Jl. Burangrang – Jl. Sadakeling – Jl. Buah Batu – Jl. Gurame – Jl. Karapitan – Jl. Lengkong Kecil – Jl. Lengkong Besar – Jl. Pungkur – Terminal Kebon Kelapa – Jl. Dewi Sartika – Alun-Alun – Jl. Banceuy – Jl. Suniaraja – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Dadali -  Jl. Kasuari – Jl. Rajawali Barat – Elang\n" +
						"\n" +
						"RUTE 2 (DARI ELANG KE CICADAS)\n" +
						"Elang – Jl. Rajawali Timur – Jl. Kebon Jati – Jl. Otto Iskandardinata (Otista) – Jl. Ciateul – Jl. Moh. Toha – Jl. Pungkur – Terminal Kebon Kelapa – Jl. Dewi Sartika – Jl. Dalem Kaum – Jl. Lengkong Besar – Jl. Cikawao – Jl. Sadakeling – Jl. Burangrang – Jl. Gatot Subroto – Jl. Kiara Condong – Jl. Jakarta – Jl. Ahmad Yani – Cicadas",
				"RUTE 1 (DARI ANTAPANI KE CIROYOM)\n" +
						"Terminal Antapani – Jl. Cibatu (Antapani) – Jl. Purwakarta (Antapani) – Jl. Jakarta – Jl. Sukabumi – Jl. Laswi – Stadion Persib (Ahmad Yani) – Jl. Ahmad Yani – Jl. Gudang Utara – Jl. Bangka – Jl. Belitung – Jl. Sumatera – Jl. Aceh – Jl. Merdeka – Jl. Perintis Kemerdekaan – Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Istana Plaza (Pajajaran) – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Ciroyom – Terminal Ciroyom\n" +
						"\n" +
						"RUTE 2 (DARI CIROYOM KE ANTAPANI)\n" +
						"Terminal Ciroyom – Jl. Ciroyom – Jl. Arjuna – Jl. Pajajaran – Istana Plaza (Pajajaran) – Jl. Cihampelas – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – BEC (Purnawarman) – Jl. Wastu Kencana – Jl. Aceh – Jl. Kalimantan – Jl. Belitung – Jl. Bangka – Jl. Gudang Utara – Jl. Ahmad Yani – Stadion Persib (Ahmad Yani) – Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Jakarta (Antapani) – Jl. Subang (Antapani) – Jl. Cibatu (Antapani) – Terminal Antapani",
				"RUTE 1 (DARI CIBIRU KE CICADAS)\n" +
						"Terminal Cibiru – Jl. Sukarno-Hatta – Pasar Induk Gede Bage (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Margahayu Raya (Sukarno-Hatta) – Jl. Kiara Condong – Jl. Jakarta – Jl. Ahmad Yani – Cicadas (Ahmad Yani)\n" +
						"\n" +
						"RUTE 2 (DARI CICADAS KE CIBIRU)\n" +
						"Cicadas (Ahmad Yani) – Jl. Kiara Condong – Jl. Sukarno-Hatta – Margahayu Raya (Sukarno-Hatta) – Metro (Sukarno-Hatta) – Riung Bandung (Sukarno-Hatta) – Pasar Induk Gede Bage (Sukarno-Hatta) – Terminal Cibiru",
				"RUTE 1 (DARI SEKEMIRUNG KE PANYILEUKAN/DARI DAGO KE GEDEBAGE)\n" +
						"*Terminal Awiligar – *Jl. Cigadung Raya – *Jl. Cibeunying Kolot – *Jl. Cikondang – *Jl. Cikutra Barat – *Jl. Sadang Serang – *Jl. TB. Ismail – Simpang Dago (Juanda) – Jl. Ir. H. Juanda – RS. Boromeus (Juanda) – Jl. Hasanudin – Jl. Pager Gunung – Jl. Dipati Ukur – Jl. Panatayuda – Jl. Surapati (Suci) – Lapangan Gasibu (Surapati) – Jl. PHH. Mustofa (Suci) – Terminal Cicaheum – Jl. AH. Nasution – Sindanglaya (Nasution) – Ujung Berung (Nasution) – Jl. Rumah Sakit – Pasar Induk Gede Bage (Sukarno-Hatta)\n" +
						"\n" +
						"RUTE 1 (DARI PANYILEUKAN KE SEKEMIRUNG/DARI GEDEBAGE KE DAGO)\n" +
						"Pasar Induk Gede Bage (Sukarno-Hatta) – Jl. Rumah Sakit – Jl. AH. Nasution – Ujung Berung (Nasution) – Sindanglaya (Nasution) – Terminal Cicaheum – Jl. PHH. Mustofa (Suci) – Jl. Cikutra – Jl. Pahlawan – Jl. Surapati (Suci) – Lapangan Gasibu (Surapati) – Jl. Panatayuda – Jl. Dipati Ukur – Jl. Ki Gede Utama – Jl. Hasanudin – RS. Boromeus (Juanda) – Jl. Ir. H. Juanda – Simpang Dago – *Jl. TB. Ismail – *Jl. Sadang Serang – *Jl. Cikutra Barat – *Jl. Cikondang – *Jl. Cibeunying Kolot – *Jl. Cigadung Raya – *Terminal Awiligar",
				"RUTE 1 (DARI CARINGIN KE SADANG SERANG)\n" +
						"Terminal Caringin – Jl. Caringin – Jl. Holis – Jl. Bojong Raya – Jl. Cijerah – Jl. Sudirman – Jl. Rajawali Barat – Jl. Garuda – Jl Abdul Rahman Saleh – Jl. Pajajaran – Jl. Pandu – Jl. Rajiman – Jl. Rivai – Jl. Wastu Kencana – Jl. Tamansari – Jl. Ganesha – Jl. Ir. H. Juanda (Dago) – Jl. TB. Ismail – Jl. Sadang Serang – Terminal Sadang Serang\n" +
						"\n" +
						"RUTE 2 (DARI SADANG SERANG KE CARINGIN)\n" +
						"Terminal Sadang Serang – Jl. Serang Serang – Jl. Tubagus Ismail – Simpang Dago – Jl. Tamansari – Jl. Sawunggaling – Jl. Tamansari – Jl. Wastu Kencana – Jl. RE. Martadinata – Jl. Purnawarman – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kresna – Jl. Bima – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Jl. Cijerah – Jl. Bojong Raya – Jl. Holis – Jl. Caringin – Terminal Caringin",
				"RUTE 1 (DARI CIBADUYUT KE KARANG SETRA)\n" +
						"Jl. Cibaduyut – Jl. Bojongloa – Jl. Peta – Jl. BKR – Jl. Moh. Toha – Jl. Pungkur – Terminal Kebon Kalapa – Jl. Pasir Koja – Jl. Pajagalan – Jl. Gardu Jati – Jl. Pasir Kaliki – Jl. Sukajadi – Karang Setra\n" +
						"\n" +
						"RUTE 2 (DARI KARANG SETRA KE CIBADUYUT)\n" +
						"\n" +
						"Karang Setra – Jl. Sukajadi – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cicendo – Jl. Pasir Kaliki – Jl. Kebon Jati – Jl. Dulatip – Jl. Sudirman – Jl. Astana Anyar – Jl. Pasir Koja – Terminal Kebon Kalapa – Jl. Otto Iskandardinata – Jl. Peta – Jl. Bojongloa – Jl. Cibaduyut",
				"RUTE 1 (DARI CIBOGO KE ELANG)\n" +
						"Cibogo – Jl. Hercules – Jl. Pinggir Tol – Jl. Sukawarna – Jl. Suparmin – Jl. Sebelah IPTN – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Sudirman – Elang\n" +
						"\n" +
						"RUTE 2 (DARI ELANG KE CIBOGO)\n" +
						"Elang – Jl. Rajawali Timur – Jl. Garuda – Jl. Abdul Rahman Saleh – Jl. Pajajaran – Jl. Sebelah IPTN – Jl. Suparmin – Jl. Sukawarna – Jl. DR. Junjunan (Terusan Pasteur) – Jl. Pinggir Tol – Jl. Hercules – Cibogo",
				"RUTE 1 (DARI CIBURIAL KE CIROYOM)\n" +
						"Ciburial (Dago Atas) – Terminal Dago – Jl. H. Juanda (Dago) – Simpang Dago – ITB (Jl. Ganesha, Dago) – RS. Boromeus (Dago) – Jl. Sulanjana – Jl. Tamansari – Jl. Sawunggaling – Jl. Tamansari – Jl. Wastu Kencana – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pajajaran – Jl. Abdul Rahman Saleh – Jl. Garuda – Jl. Ciroyom – Terminal Ciroyom\n" +
						"\n" +
						"RUTE 2 (DARI CIROYOM KE CIBURIAL)\n" +
						"Terminal Ciroyom – Jl. Ciroyom – Jl. Arjuna – Jl. Pajajaran – Jl. Cihampelas – Jl. Wastu Kencana – Jl. Tamansari – Jl. Sulanjana – Jl. H. Juanda (Dago) – RS. Boromeus (Dago) – ITB (Jl. Ganesha) – Simpang Dago – Terminal Dago – Ciburial (Dago Atas)",
				"RUTE 1 (DARI CICAHEUM KE CILEUNYI)\n" +
						"Terminal Cicaheum – Jl. AH. Nasution – Sindanglaya (Nasution) – Ujung Berung (Nasution) – Cilengkrang (Nasution) – Jl. Cibiru – Batas Kotamadya Bandung- Jl. Cinunuk – Jl. Cileunyi – Terminal Cileunyi – *Pintu Tol Cileunyi\n" +
						"\n" +
						"RUTE 2 (DARI CILEUNYI KE CICAHEUM)\n" +
						"Terminal Cileunyi – Jl. Cileunyi – Jl. Cinunuk – Batas Kotamadya Bandung – Jl. Cibiru – Jl. AH. Nasution – Cilengkrang (Nasution) – Ujung Berung (Nasution) – Sindanglaya (Nasution) – Terminal Cicaheum",
				"RUTE 1 (DARI LEMBANG KE ST.HALL)\n" +
						"Terminal Lembang – Jl. Lembang – Terminal Ledeng – Jl. Setiabudi – Jl. Sukawangi – Jl. Sukajadi – RS. Hasan Sadikin – Jl. Pasir Kaliki – Jl. Pajajaran – Jl. Cicendo – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – Jl. Kebon Jukut – St.Hall\n" +
						"\n" +
						"RUTE 1 (DARI ST.HALL KE LEMBANG)\n" +
						"St.Hall – Jl. Otista – Jl. Stasiun Timur – Viaduct – Jl. Kebon Jukut – Jl. Kebon Kawung – Stasiun Bandung (Kebon Kawung) – Jl. Pasir Kaliki – RS. Hasan Sadikin – Jl. Sukajadi – Jl. Setiabudi – Terminal Ledeng – Jl. Lembang – Terminal Lembang"
		};
		String[] harga= new String[] {
				"Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",
				"Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",
				"Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000","Rp.000",
				"Rp.000","Rp.000","Rp.000","Rp.000","Rp.000"
		};
		String[] operasi= new String[] {
				"00.00 wib - 00.00 wib","00.00 wib - 24.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib",
				"00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib",
				"00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib",
				"00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib","00.00 wib - 00.00 wib"
		};
//		int[] gambar=new int[]{
//				R.drawable.marker_50,
//				R.drawable.marker_50,
//				R.drawable.marker_50
//		};

		// Locate the ViewPager in viewpager_main.xml
		viewPager = (ViewPager) findViewById(R.id.pager);
		// Pass results to ViewPagerAdapter Class
//		adapter = new ViewPagerAdapterRute(Home.this, no,gambar);
//		// Binds the Adapter to the ViewPager
//		viewPager.setAdapter(adapter);


		try {
			v2GetRouteDirection = new GMapV2GetRouteDirection();
			SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			mGoogleMap = supportMapFragment.getMap();

			// Enabling MyLocation in Google Map
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
			mGoogleMap.setMyLocationEnabled(true);
			mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
			mGoogleMap.getUiSettings().setCompassEnabled(true);
			mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
			mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
			//mGoogleMap.setTrafficEnabled(true);
			mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
			markerOptions = new MarkerOptions();
			//fromPosition = new LatLng(-6.936674547450142, 107.69859038293362);
			//toPosition = new LatLng(-6.938276749765415, 107.67740026116371);
//	GetRouteTask getRoute = new GetRouteTask();
//	getRoute.execute();


			CheckGPS();
			cekInternet();



		}catch (Exception e){

		}


	}





	private double getDistanceInfo(double lat1, double lng1, String destinationAddress) {
		StringBuilder stringBuilder = new StringBuilder();
		Double dist = 0.0,dist2 = 0.0;
		try {

			destinationAddress = destinationAddress.replaceAll(" ","%20");
			String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + lat1 + "," + lng1 + "&destination=" + destinationAddress + "&mode=walking&sensor=false";

			HttpPost httppost = new HttpPost(url);

			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			stringBuilder = new StringBuilder();


			response = client.execute(httppost);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

		JSONObject jsonObject = new JSONObject();
		try {

			jsonObject = new JSONObject(stringBuilder.toString());

			JSONArray array = jsonObject.getJSONArray("routes");

			JSONObject routes = array.getJSONObject(0);
			//Toast.makeText(getApplicationContext(), "kk " +latlong, Toast.LENGTH_LONG).show();
			JSONArray legs = routes.getJSONArray("legs");

			JSONObject steps = legs.getJSONObject(0);

			JSONObject distance = steps.getJSONObject("distance");
			///Toast.makeText(getApplicationContext(), "kk " +distance, Toast.LENGTH_LONG).show();
			refresh.setText(distance.toString());
			Log.i("Distance", distance.toString());
			dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );
			dist2 = Double.parseDouble(distance.getString("value"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dist;
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		return false;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	public class AmbilData extends AsyncTask<String, String, String> {
		ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Home2backup.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url;

			//url = "http://115.178.53.63:8010/app_angkot/get_latlong_angkot.php?kode="+posi+"&status="+status;
			url = "http://www.angkot.fujicon-japan.com/get_latlong_angkot.php?kode="+posi+"&status="+status;
			JSONParser jParser = new JSONParser();

			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				college = json.getJSONArray("fujicon_dr_daily_report2");
				Log.e("error", json.getString("success1"));
				lat_pergi = new double[college .length()];
				long_pergi = new double[college.length()];

				for (int i = 0; i < college.length(); i++) {
					JSONObject c = college.getJSONObject(i);
					lat_pergi[i] = college.getJSONObject(i).getDouble("lat_pergi");
					long_pergi[i] = college.getJSONObject(i).getDouble("long_pergi");lat_lng.add( new LatLng(lat_pergi[i], long_pergi[i]) );
				}

			} catch (JSONException e) {

			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			PolylineOptions jalurBiasa = new PolylineOptions();
			jalurBiasa.addAll(lat_lng).width(5).color(0xff00ff00).geodesic(true);
			mGoogleMap.addPolyline(jalurBiasa);
			/*int gj=1,gn=0,tg=2;
			try{
				for (int i = 0; i < lat_pergi.length; i++) {

					String lat11= String.valueOf(lat_pergi[gn]);
					String long11=String.valueOf(long_pergi[gn]);
					String lat22=String.valueOf(lat_pergi[gj]);
					String long22=String.valueOf(long_pergi[gj]);
					String lat33=String.valueOf(lat_pergi[tg]);
					String long33=String.valueOf(long_pergi[tg]);

					if(gn!=0) {
						if ((lat11 != null || !lat11.equals("") && long11 != null || !long11.equals(""))) {
							double[][] pos = {{lat_pergi[gn - 1], long_pergi[gn - 1]}, {lat_pergi[gj - 1], long_pergi[gj - 1]}};
							PolylineOptions rectOptions = new PolylineOptions();
							for (int ii = 0; ii < pos.length; ii++) {
								rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
							}
							Polyline polyline = mGoogleMap.addPolyline(rectOptions);

						}
					}
					if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))){
						double[][] pos ={{lat_pergi[gn], long_pergi[gn]},{lat_pergi[gj], long_pergi[gj]}};
						PolylineOptions rectOptions = new PolylineOptions();
						for (int ii = 0; ii < pos.length; ii++){
							rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
						}
						Polyline polyline = mGoogleMap.addPolyline(rectOptions);

					}
					if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))&&(lat33!=null || !lat33.equals(""))&& long33!=null || !long33.equals("")){
						double[][] pos ={{lat_pergi[gn], long_pergi[gn]},{lat_pergi[gj], long_pergi[gj]},{lat_pergi[tg], long_pergi[tg]}};
						PolylineOptions rectOptions = new PolylineOptions();
						for (int ii = 0; ii < pos.length; ii++){
							rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
						}
						Polyline polyline = mGoogleMap.addPolyline(rectOptions);

					}
					gj=gj+2;
					gn=gn+2;
					tg=tg+2;
				}
				//pDialog.dismiss();
			}catch (Exception e){

			}
			/*nearBy(line);
			addLines(line);
			try {
				String arr[] = latlong.split("l");
				for (int i = 0; i < arr.length; i++) {
				ambil(arr[i]);

				}
			}catch (Exception e){

			}*/
			Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
			///int gj=1,gn=0,tg=2;

			//int kls=lat_marker.length-1;


			try{
				LatLng markerawal = new LatLng(lat_pergi[0], long_pergi[0]);
				int kl=lat_pergi.length-1;
				int kli=long_pergi.length-1;
				LatLng markertujuan = new LatLng(lat_pergi[kl], long_pergi[kli]);
				drawMarkerlokasi(markerawal);
				drawMarkertujuan(markertujuan);
/**/

				LatLng []lalat=new LatLng[kli];
				//	int sp=0,ep=1;
				for(int il = 0; il <1; il++){
					//LatLng markertujuan2 = new LatLng(lat_marker[il], long_marker[il]);
					//drawMarkerlokasi(markertujuan2);

					int Radius = 6371;// radius of earth in Km
					double lat1 = lat_pergi[0];
					double lat2 = lat_pergi[kl];
					double lon1 =long_pergi[0];
					double lon2 =long_pergi[kli];
					double dLat = Math.toRadians(lat2 - lat1);
					double dLon = Math.toRadians(lon2 - lon1);
					double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
							+ Math.cos(Math.toRadians(lat1))
							* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
							* Math.sin(dLon / 2);
					double c = 2 * Math.asin(Math.sqrt(a));
					double valueResult = Radius * c;
					double km = valueResult / 1;
					DecimalFormat newFormat = new DecimalFormat("####");
					int kmInDec = Integer.valueOf(newFormat.format(km));
					int meterConversion = 1609;
					double meter=valueResult * meterConversion;
					int meterInDec = Integer.valueOf(newFormat.format(meter));
					jarak[il]= String.valueOf(kmInDec);//+""+meterInDec);

					java.util.List<Address> addresses =geoCoder.getFromLocation(lat_pergi[0], long_pergi[0], 1);
					java.util.List<Address> addresses2 =geoCoder.getFromLocation(lat_pergi[kl], long_pergi[kli], 1);
					if (addresses.size() > 0) {
						for (int i = 0; i <3;i++)// addresses.get(0).getMaxAddressLineIndex(); i++)
							if(i==0){
								filterAddress[il] += addresses.get(0).getAddressLine(i) + "\n";
							}else{
								filterAddress[il] += addresses.get(0).getAddressLine(i) + " ";
							}

					}
					if (addresses2.size() > 0) {
						for (int i = 0; i <3;i++)// addresses2.get(0).getMaxAddressLineIndex(); i++)

							if(i==0){
								filterAddress2[il] += addresses2.get(0).getAddressLine(i) + "\n";
							}else{
								filterAddress2[il] += addresses2.get(0).getAddressLine(i) + " ";
							}
					}
					//lalat[il]=new LatLng(lat_marker[il], long_marker[il]);
					//sp=sp+1;
					//ep=ep+1;
				}


				///adapter = new ViewPagerAdapterRute(Home2.this, filterAddress, filterAddress2,jarak);
//		// Binds the Adapter to the ViewPager
				//viewPager.setAdapter(adapter);/**/

//				CustomListhome customList = new CustomListhome(Home2.this, filterAddress, filterAddress2, imageid,jarak);
//				lvdt = (ListView) findViewById(R.id.lvdt);
//				lvdt.setAdapter(customList);
//				lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//					@Override
//					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//						///  Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
//
//
//					}
//				});
			}catch (Exception e){

			}
			pDialog.dismiss();
		}
	}

	public class AmbilDataMarker extends AsyncTask<String, String, String> {
		ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Home2backup.this);
			pDialog.setMessage("Loading Marker ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url;

			url = "http://www.angkot.fujicon-japan.com/get_marker_angkot.php?kode="+posi+"&status="+status;

			JSONParser jParser = new JSONParser();

			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				college = json.getJSONArray("fujicon_dr_daily_report2");
				Log.e("error", json.getString("success1"));
				lat_marker = new double[college .length()];
				long_marker = new double[college.length()];

				for (int i = 0; i < college.length()+1; i++) {
					JSONObject c = college.getJSONObject(i);
					lat_marker[i] = college.getJSONObject(i).getDouble("lat_marker");
					long_marker[i] = college.getJSONObject(i).getDouble("long_marker");
				}

			} catch (JSONException e) {

			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
			int gj=1,gn=0,tg=2;

			int kls=lat_marker.length-1;


			try{
				LatLng markerawal = new LatLng(lat_marker[0], long_marker[0]);
				int kl=lat_marker.length-1;
				int kli=long_marker.length-1;
				LatLng markertujuan = new LatLng(lat_marker[kl], long_marker[kli]);
				drawMarkerlokasi(markerawal);
				drawMarkertujuan(markertujuan);

				String []filterAddress = new String[kli];
				String []filterAddress2 = new String[kli];
				String []jarak = new String[kli];
				LatLng []lalat=new LatLng[kli];
				int sp=0,ep=1;
				for(int il = 0; il <kl; il++){
					LatLng markertujuan2 = new LatLng(lat_marker[il], long_marker[il]);
					//	drawMarkerlokasi(markertujuan2);

					int Radius = 6371;// radius of earth in Km
					double lat1 = lat_marker[sp];
					double lat2 = lat_marker[ep];
					double lon1 =long_marker[sp];
					double lon2 =long_marker[ep];
					double dLat = Math.toRadians(lat2 - lat1);
					double dLon = Math.toRadians(lon2 - lon1);
					double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
							+ Math.cos(Math.toRadians(lat1))
							* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
							* Math.sin(dLon / 2);
					double c = 2 * Math.asin(Math.sqrt(a));
					double valueResult = Radius * c;
					double km = valueResult / 1;
					DecimalFormat newFormat = new DecimalFormat("####");
					int kmInDec = Integer.valueOf(newFormat.format(km));
					int meterConversion = 1609;
					double meter=valueResult * meterConversion;
					int meterInDec = Integer.valueOf(newFormat.format(meter));
					jarak[il]= String.valueOf(meterInDec);

					java.util.List<Address> addresses =geoCoder.getFromLocation(lat_marker[il], long_marker[il], 1);
					java.util.List<Address> addresses2 =geoCoder.getFromLocation(lat_marker[ep], long_marker[ep], 1);
					if (addresses.size() > 0) {
						for (int i = 0; i <3;i++)// addresses.get(0).getMaxAddressLineIndex(); i++)
							filterAddress[il] += addresses.get(0).getAddressLine(i) + " ";
					}
					if (addresses2.size() > 0) {
						for (int i = 0; i <3;i++) //addresses2.get(0).getMaxAddressLineIndex(); i++)
							filterAddress2[il] += addresses2.get(0).getAddressLine(i) + " ";
					}

					//lalat[il]=new LatLng(lat_marker[il], long_marker[il]);
					sp=sp+1;
					ep=ep+1;
				}



//				adapter = new ViewPagerAdapterRute(Home2backup.this, filterAddress, filterAddress2,jarak);
////		// Binds the Adapter to the ViewPager
//				viewPager.setAdapter(adapter);
			}catch (Exception e){

			}
		}
	}
	public void lempar(String langlong){

		String hjk=langlong;
		//Toast.makeText(getApplicationContext(), "kks " +hjk, Toast.LENGTH_LONG).show();
		String arr[] = hjk.split("l");
		for(int i = 0; i < arr.length-1; i++) {
			ambil(arr[i]);

		}
	}
	public void ambil(String k){

		///Toast.makeText(getApplicationContext(), "k " + k, Toast.LENGTH_LONG).show();
		String m=k+",k";
		String arr2[] = m.split(",");
//		Toast.makeText(getApplicationContext(), "kk " +arr2[0] +"   "+arr2[1], Toast.LENGTH_LONG).show();
		String a=arr2[0];
		String b=arr2[1];
		double latasal= Double.parseDouble(a);
		double longasal=Double.parseDouble(b);
		//Toast.makeText(getApplicationContext(), "kk " +latasal +"   "+longasal, Toast.LENGTH_LONG).show();
		//	for(int j = 0; j < arr2.length; j++){
//						String latasal=arr2[0].trim();
//						String longasal=arr2[1].trim();
		//	Toast.makeText(getApplicationContext(), "k " + arr2[0].trim()+"xs"+ arr2[1].trim(), Toast.LENGTH_LONG).show();
		//}
		//String latasal = arr2[0].trim();
//		double latasal= Double.parseDouble(arr2[0]);
//		double longasal=Double.parseDouble(arr2[1]);
		LatLng marker = new LatLng(latasal,longasal);
//        String nama=arr2[0].trim()+","+arr2[1].trim();
		// Toast.makeText(getApplicationContext(), latasal, Toast.LENGTH_LONG).show();
		drawMarker(marker);
		//PolylineOptions my = new PolylineOptions().add(new LatLng(latasal,longasal)).width(2).color(Color.BLUE).geodesic(true);
		//Polyline polyline = googleMap.addPolyline(my);

//		googleMap
//				.addPolyline((new PolylineOptions())
//						.add(marker).width(5).color(Color.BLUE)
//						.geodesic(true));


	}
	private void drawMarker(LatLng point){//,String nama) {

		LatLng tambah = new LatLng(point.latitude, point.longitude);
		MarkerOptions options = new MarkerOptions();
		options.position(tambah);
		//options.title(nama);
		options.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.marker_51));
		Marker marker = mGoogleMap.addMarker(options);


	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		try {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void CheckGPS() {
		try {
			LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("info");
				builder.setMessage("Apakah anda akan mengaktifkan GPS?");
				builder.setPositiveButton("Ya",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								Intent i = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(i);

							}
						});
				builder.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());
		if (status != ConnectionResult.SUCCESS) {
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();
		} else {
			Criteria criteria = new Criteria();
			LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			String provider = locationmanager.getBestProvider(criteria, true);
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
			Location location = locationmanager.getLastKnownLocation(provider);
			if (location != null) {
				onLocationChanged(location);
			}
			locationmanager.requestLocationUpdates(provider, 500, 0, this);
			LatLng posisi = new LatLng(latitude, longitude);

			mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posisi,
					14));

		}
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public void cekInternet(){
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			//new AmbilDataMarker().execute();
			new AmbilData().execute();

			//pDialog.hide();
			//pDialog.hide();

					/*String adda="-6.936674547450142, 107.69859038293362" +
							    "l-6.9370832526716795, 107.69287191331387" +
							    "l-6.938276749765415, 107.67740026116371" +
							"l-6.938945719205945, 107.66806080937386" +
							"l-6.945479603564764, 107.64214731752872" +
							"l-6.947984368299918, 107.63377547264099" +
							"l-6.942418354248699, 107.62794569134712" +
							"l-6.93890245255447, 107.62930154800415" +
							"l-6.9363889860995105, 107.62556053698063" +
							"l-6.933046108182481, 107.62719333171844" +
							"l-6.932596129176434, 107.62565642595291" +
							"l-6.929452254991758, 107.62642420828342" +
							"l-6.928388538419909, 107.62241832911968" +
							"l-6.918814981328451, 107.62423485517502" +
							"l-6.919842107760921, 107.6222600787878" +
							"l-6.92225647846148, 107.61781364679337" +
							"l-6.915923971216355, 107.61839434504509" +
							"l-6.911611720170731, 107.61616542935371" +
							"l-6.912690119460327, 107.61306077241898" +
							"l-6.910072001365267, 107.61234529316425" +
							"l-6.909851660736927, 107.61063203215599" +
							"l-6.913652022923101, 107.61034302413464" +
							"l-6.913864373665916, 107.60902673006058" +
							"l-6.916221527069024, 107.60899789631367" +
							"l-6.915495943117425, 107.60750256478786" +
							"l-6.915459331053629, 107.60721892118454" +
							"l-6.914395582943145, 107.60716527700424" +
							"l-6.914460819129001, 107.60446697473526" +
							"l-6.912737382582777, 107.60467283427715" +
							"l-6.9125363478601844, 107.60256193578243" +
							"l-6.912334647373759, 107.59801425039768" +
							"l-6.916273449547612, 107.59821943938732";*/



		} else {

			alert.showAlertDialog(Home2backup.this, "Peringatan",
					"cek koneksi internet.", false);
		}
	}



	//////poli
	private void setUpMapIfNeeded() {
		// check if we have got the googleMap already
		if (mGoogleMap == null) {
			mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (mGoogleMap != null) {
				//	addLines();
			}
		}
	}

	private void addLines(String ln) {

		int gj=1,gn=0,tg=2;
		ArrayList<double[][]> array2list = new ArrayList<>();
		try {
			String arr[] = ln.split("l");
			for (int i = 0; i < arr.length; i++) {

				String k=arr[gn];
				String m=k+",k";
				String arr2[] = m.split(",");

				String a=arr2[0];
				String b=arr2[1];
				double latasal= Double.parseDouble(a);
				double longasal=Double.parseDouble(b);
/////////////////////////////////////////////
				String k2=arr[gj];
				String m2=k2+",k";
				String arr22[] = m2.split(",");

				String a2=arr22[0];
				String b2=arr22[1];
				double latasal2= Double.parseDouble(a2);
				double longasal2=Double.parseDouble(b2);
/////////////////////////////////////////////
				String k3=arr[tg];
				String m3=k3+",k";
				String arr23[] = m3.split(",");

				String a3=arr23[0];
				String b3=arr23[1];
				double latasal3= Double.parseDouble(a3);
				double longasal3=Double.parseDouble(b3);

				double[][] pos ={{latasal, longasal},{latasal2, longasal2},{latasal3, longasal3}};



				//Toast.makeText(getApplicationContext(), "s: " + pos, Toast.LENGTH_LONG).show();
//				try {
//
//					//array2list.add(pos);
//
//					// print array in rectangular form
//					for (int r=0; r<pos.length; r++) {
//						for (int c=0; c<pos[r].length; c++) {
//							pos[r][c]= 30;//your value
//						}
//					}
//
//				} catch (Exception e) {
//
//				}

				//	Toast.makeText(getApplicationContext(), "sss: " + pos, Toast.LENGTH_LONG).show();

				PolylineOptions rectOptions = new PolylineOptions();
				for (int ii = 0; ii < pos.length; ii++){
					rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.RED);
				}
				Polyline polyline = mGoogleMap.addPolyline(rectOptions);
				gj=gj+2;
				gn=gn+2;
				tg=tg+2;
			}


		}catch (Exception e){

		}



		//////////////////////////////
//		double[][] pos = { {-6.936674547450142, 107.69859038293362},{-6.9370832526716795,107.69287191331387},{-6.938276749765415, 107.67740026116371} };
//		PolylineOptions rectOptions = new PolylineOptions();
//		for (int i = 0; i < pos.length; i++){
//			rectOptions.add(new LatLng(pos[i][0], pos[i][1]));
//		}
//		Polyline polyline = googleMap.addPolyline(rectOptions);
/////////////////////////////////////////////////////////////////
//		LatLng[] pos = {new LatLng(-6.936674547450142, 107.69859038293362), new LatLng(-6.9370832526716795,107.69287191331387), new LatLng(-6.938276749765415, 107.67740026116371)};
//		PolylineOptions rectOptions = new PolylineOptions();
//		for (LatLng l : pos){
//			rectOptions.add(l);
//		}
//		Polyline polyline = googleMap.addPolyline(rectOptions);
////////////////////////////////////////
//		googleMap
//				.addPolyline((new PolylineOptions())
//						.add(LOWER_MANHATTAN,TIMES_SQUARE, BROOKLYN_BRIDGE
//								).width(5).color(Color.BLUE)
//						.geodesic(true));
		// move camera to zoom on map
		//googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOWER_MANHATTAN,
		//		13));
	}
	///akhir poli
	public void nearBy(String ln) {
		try {
			String arr[] = ln.split("l");
			for (int i = 0; i < arr.length; i++) {

				String m=arr[i]+",k";
				String arr2[] = m.split(",");

				String Lat1 =arr2[0];  //medecin.getString(5);//doctor's latitude-6.940083, 107.659277
				String Lon1 =arr2[1];//medecin.getString(6);//doctor's longitude
				fromPosition= new LatLng(-6.94187719,107.65980991);//my location
				toPosition = new LatLng(Double.parseDouble(Lat1), Double.parseDouble(Lon1));//doctor location
				if (CalDist(fromPosition, toPosition) < 1) {//if distance is < 6km add marker to the map

					Toast.makeText(getApplicationContext(), " valid"+fromPosition+ toPosition, Toast.LENGTH_SHORT).show();
					CalculationByDistance(fromPosition,toPosition);
					GetRouteTask getRoute = new GetRouteTask(fromPosition,toPosition);
					getRoute.execute();


//					LatLng marker = new LatLng(Double.parseDouble(Lat1),Double.parseDouble(Lon1));
//				drawMarker(marker);
				}
//			else {
//				Toast.makeText(getApplicationContext(), "Gps signal not valid", Toast.LENGTH_SHORT).show();//can't find my position
//			}

			}


		}catch (Exception e){

		}


	}
	public static double CalDist(LatLng StartP, LatLng EndP) {
		int Radius = 6371;//­radius of earth in Km
		double lat1 = StartP.latitude ;
		double lat2 = EndP.latitude ;
		double lon1 = StartP.longitude ;
		double lon2 = EndP.longitude ;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return Radius * c;
	}
	//////////////////////////////coba

	private class GetRouteTask extends AsyncTask<String, Void, String> {


		private ProgressDialog Dialog;
		String response = "";

		public GetRouteTask(LatLng fromPosition, LatLng toPosition) {
			fromPosition = fromPosition;
			toPosition = toPosition;
		}

		@Override
		protected void onPreExecute() {
			pDialog = new ProgressDialog(Home2backup.this);
			pDialog.setMessage("Loading route...");
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			//Get All Route values
			document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_WALKING);
			response = "Success";
			return response;

		}

		@Override
		protected void onPostExecute(String result) {
			//mGoogleMap.clear();
			if(response.equalsIgnoreCase("Success")){
				ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
				PolylineOptions rectLine = new PolylineOptions().width(10).color(
						Color.GRAY);

				for (int i = 0; i < directionPoint.size(); i++) {
					rectLine.add(directionPoint.get(i));
				}
				// Adding route on the map
				mGoogleMap.addPolyline(rectLine);
				markerOptions.position(toPosition);
				markerOptions.draggable(true);
				mGoogleMap.addMarker(markerOptions);

			}

			pDialog.dismiss();
		}
	}
	//@Override
//	protected void onStop() {
//		super.onStop();
//		finish();
//	}


	/////////////////////////////////coba ak
	public double CalculationByDistance(LatLng StartP, LatLng EndP) {
		int Radius=6371;//radius of earth in Km
		double lat1 = StartP.latitude;
		double lat2 = EndP.latitude;
		double lon1 = StartP.longitude;
		double lon2 = EndP.longitude;
		double dLat = Math.toRadians(lat2-lat1);
		double dLon = Math.toRadians(lon2-lon1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double valueResult= Radius*c;
		double km=valueResult/1;
		DecimalFormat newFormat = new DecimalFormat("####");
		int kmInDec =  Integer.valueOf(newFormat.format(km));
		double meter=valueResult%1000;
		int  meterInDec= Integer.valueOf(newFormat.format(meter));
		Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   "+meterInDec);
		Toast.makeText(getApplicationContext(), "Radius Value"+""+valueResult+"   KM  " + kmInDec+" Meter   "+meterInDec, Toast.LENGTH_LONG).show();
		return Radius * c;
	}
	private void drawMarkerlokasi(LatLng point) {//,String nama) {
		String filterAddress = "";
		LatLng tambah = new LatLng(point.latitude, point.longitude);
		MarkerOptions options = new MarkerOptions();
		Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
		try {
			java.util.List<Address> addresses =
					geoCoder.getFromLocation(point.latitude, point.longitude, 1);

			if (addresses.size() > 0) {
				for (int i = 0; i <3;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
					filterAddress += addresses.get(0).getAddressLine(i) + " ";
			}
			options.position(tambah);
			options.title("Start : " + filterAddress);
			options.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.jma));

			Marker marker = mGoogleMap.addMarker(options);
			mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
					tambah, 15));
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	private void drawMarkertujuan(LatLng point) {//,String nama) {
		String filterAddress = "";
		LatLng tambah = new LatLng(point.latitude, point.longitude);
		MarkerOptions options = new MarkerOptions();
		Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
		try {
			java.util.List<Address> addresses =
					geoCoder.getFromLocation(point.latitude, point.longitude, 1);

			if (addresses.size() > 0) {
				for (int i = 0; i <3;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
					filterAddress += addresses.get(0).getAddressLine(i) + " ";
			}
			options.position(tambah);
			options.title("Finish : " + filterAddress);
			options.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.amj));
			Marker marker = mGoogleMap.addMarker(options);
			mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
					tambah, 15));
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

	public static void draw(LatLng point) {//,String nama) {
		String filterAddress = "";
		LatLng tambah = new LatLng(point.latitude, point.longitude);
		MarkerOptions options = new MarkerOptions();
		Geocoder geoCoder = new Geocoder(TheApp.getAppContext(), Locale.getDefault());
		try {
			java.util.List<Address> addresses =
					geoCoder.getFromLocation(point.latitude, point.longitude, 1);

			if (addresses.size() > 0) {
				for (int i = 0; i <3;i++)// addresses.get(0).getMaxAddressLineIndex(); i++)
					filterAddress += addresses.get(0).getAddressLine(i) + " ";
			}
			options.position(tambah);
			options.title("Finish : " + filterAddress);
			options.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.marker_52));
			Marker marker = mGoogleMap.addMarker(options);
			mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
					tambah, 15));
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
}
