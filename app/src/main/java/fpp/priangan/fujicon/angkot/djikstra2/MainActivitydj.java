package fpp.priangan.fujicon.angkot.djikstra2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.R;
import fpp.priangan.fujicon.angkot.sheet.GMapV2GetRouteDirection;

public class MainActivitydj extends FragmentActivity implements OnMapClickListener, OnMapLongClickListener {

	// DB
	SQLHelper dbHelper;
	Cursor cursor;

	GMapV2GetRouteDirection v2GetRouteDirection;

	Document document;
	// Google Maps
	GoogleMap googleMap;

	public String __global_endposition = null;
	public String __global_startposition = null;
	public int __global_simpul_awal;
	public int __global_simpul_akhir;	
	public String __global_old_simpul_awal = "";
	public String __global_old_simpul_akhir = "";
	public int __global_maxRow0;
	public int __global_maxRow1;
	private String[][] __global_graphArray;
	private LatLng __global_yourCoordinate_exist = null;
	LatLng endy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_djikstra);

		// create DB
		dbHelper = new SQLHelper(this);
        try {
        	dbHelper.createDataBase();
        } 
        catch (Exception ioe) {
        	Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
        }
 		
        // BUAT MAP
      	if(googleMap == null){
      		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.peta)).getMap();
      		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.915315, 107.603846), 12.0f));
      		if(googleMap != null){
      			googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.peta)).getMap();
      		}
      	}

      	// event map
      	googleMap.setOnMapClickListener(this);
      	googleMap.setOnMapLongClickListener(this);
        
		// Query DB to show all SMK
		dbHelper = new SQLHelper(this);
		final SQLiteDatabase db = dbHelper.getReadableDatabase();		
		cursor = db.rawQuery("SELECT * FROM sekolah", null);
		cursor.moveToFirst();
		
		// tampung nama F
		ArrayList<String> spinner_list_smk = new ArrayList<String>();		
		// Adapter spinner smk
		ArrayAdapter<String> adapter_spinner_smk;	
		
		// nama-nama SMK dimasukkan ke array
		spinner_list_smk.add("-- Pilih Jalan --");
		for(int i = 0; i < cursor.getCount(); i++){
			cursor.moveToPosition(i);
			spinner_list_smk.add(cursor.getString(1).toString());
		}
		
		// masukkan list SMK ke spinner (dropdown)
		Spinner spinner = (Spinner) findViewById(R.id.spinner_list_smk);	
	    adapter_spinner_smk = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_list_smk);
		adapter_spinner_smk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		spinner.setAdapter(adapter_spinner_smk);
		spinner.setBackgroundColor(Color.WHITE);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
	
				if(arg0.getItemAtPosition(arg2).toString() != "-- Pilih Jalan --"){

	 				String pilih_smk = arg0.getItemAtPosition(arg2).toString();
	 				cursor = db.rawQuery("SELECT koordinat FROM sekolah where sekolah = '" + pilih_smk + "'", null);
	 				cursor.moveToFirst();
	 				cursor.moveToPosition(0);

	 				// get coordinate SMK from field koordinat
	 				__global_endposition = cursor.getString(0).toString();
	 				
	 				
	 				// user men-tap peta
	 				if(__global_yourCoordinate_exist != null){

	 					// your coordinate
	 					double latUser = __global_yourCoordinate_exist.latitude;
	 					double  lngUser = __global_yourCoordinate_exist.longitude;
	 					
	 					// destination coordinate SMK
	 					String[] exp_endCoordinate = __global_endposition.split(",");
	 					double lat_endposition = Double.parseDouble(exp_endCoordinate[0]);
	 					double lng_endposition = Double.parseDouble(exp_endCoordinate[1]);
	 					
	 					
	 					// ========================================================================
	 					// CORE SCRIPT
	 					// fungsi cari simpul awal dan tujuan, buat graph sampai algoritma dijkstra
	 					// ========================================================================
	 					try {
	 						startingScript(latUser, lngUser, lat_endposition, lng_endposition);
	 					} catch (JSONException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 					
	 				}else{
	 					Toast.makeText(getApplicationContext(), "Tap pada peta untuk menentukan posisi Anda", Toast.LENGTH_LONG).show();
	 				}

				}// if -- pilih SMK --				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		    	
	    });//setOnItemSelectedListener

		/*try {
			String kdank="13";
			cursor = db.rawQuery("SELECT lintasan FROM angkot where kode_angkot= '" + kdank + "'", null);
			cursor.moveToFirst();
			cursor.moveToPosition(0);

			String as=cursor.getString(0).toString();
			String[] exp = as.split("->");
			drawJalur2(exp);
		} catch (JSONException e) {
			e.printStackTrace();
		}*/

	}

	@Override
	public void onMapLongClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
		// your coordinate position
		double latUser = arg0.latitude;
		double lngUser = arg0.longitude;
		endy = new LatLng(latUser, lngUser);
		__global_yourCoordinate_exist = arg0;
		
		// destination coordinate position
		String endposition = __global_endposition;

		if(endposition != null){

			// dipecah coordinate SMK
			String[] exp_endposition = endposition.split(",");
			double lat_endposition = Double.parseDouble(exp_endposition[0]);
			double lng_endposition = Double.parseDouble(exp_endposition[1]);

			// ========================================================================
			// CORE SCRIPT 
			// fungsi cari simpul awal dan tujuan, buat graph sampai algoritma dijkstra
			// ========================================================================
			try {
				startingScript(latUser, lngUser, lat_endposition, lng_endposition);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			Toast.makeText(getApplicationContext(), "pilih Jalan dulu", Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	/*
	 * ===========
	 * CORE SCRIPT
	 * ===========
	 * 
	 * @fungsi utama
	 *  (1) mendapatkan koordinat awal dan akhir di sekitar jalur angkutan umum
	 *  
	 *  (2) koordinat awal kemudian di konversi menjadi simpul awal
	 *      dan koordinat akhir di konversi menjadi simpul akhir
	 *      
	 *  (3) simpul awal dan akhir kemudian dijadikan 'inputan' untuk perhitungan algoritma dijsktra
	 *  
	 *  (4) setelah dilakukan perhitungan, didapatkan jalur terpendek lalu digambar jalurnya menggunakan polyline
	 *
	 * @parameter
	 *  latUser dan lngUser : koordinat posisi user
	 *  lat_endposition dan lng_endposition : koordinat posisi SMK
	 * 
	 * @return
	 *  no return
	 */
	public void startingScript(double latUser, double lngUser, double lat_endposition, double lng_endposition) throws JSONException{				
		    	
		// delete temporary record DB
		deleteTemporaryRecord();
		
		// reset google map
		googleMap.clear();
		
		// convert graph from DB to Array; graph[][]
		GraphToArray DBGraph = new GraphToArray();
		__global_graphArray = DBGraph.convertToArray(this); // return graph[][] Array
		
		// get max++ row temporary DB
		maxRowDB();
		
		// GET COORDINATE AWAL DI SEKITAR SIMPUL
		// coordinate awal lalu di konversi ke simpul awal
		// return __global_simpul_awal, __global_graphArray[][]
		// ==========================================
		Get_koordinat_awal_akhir start_coordinate_jalur = new Get_koordinat_awal_akhir();
		getSimpulAwalAkhirJalur(start_coordinate_jalur, latUser, lngUser, "awal");

		// GET COORDINATE AKHIR DI SEKITAR SIMPUL
		// coordinate akhir lalu di konversi ke simpul akhir
		// return __global_simpul_akhir, __global_graphArray[][]
		// ==========================================
		Get_koordinat_awal_akhir destination_coordinate_jalur = new Get_koordinat_awal_akhir();		
		getSimpulAwalAkhirJalur(destination_coordinate_jalur, lat_endposition, lng_endposition, "akhir");

		// ALGORITMA DIJKSTRA
		// ==========================================
		dijkstra algo = new dijkstra();
		algo.jalurTerpendek(__global_graphArray, __global_simpul_awal, __global_simpul_akhir);

		// no result for algoritma dijkstra
		if(algo.status == "die"){
		
			Toast.makeText(getApplicationContext(), "Lokasi Anda sudah dekat dengan lokasi tujuan", Toast.LENGTH_LONG).show();
		
		}else{
			// return jalur terpendek; example 1->5->6->7
	       	String[] exp = algo.jalur_terpendek1.split("->");
			 
	       	// DRAW JALUR ANGKUTAN UMUM
			// =========================================
	       	drawJalur(algo.jalur_terpendek1, exp);			
		}

	}
	
	
	/*
	 * @fungsi
	 *  menggambar jalur angkutan umum
	 *  menentukan jenis angkutan umum yang melewati jalur tsb
	 *  membuat marker untuk your position dan destination position
	 * @parameter
	 *  exp[] : jalur terpendek; example 1->5->6->7
	 * @return
	 *  no return
	 */
	public void drawJalur(String alg, String[] exp) throws JSONException{
		
        int start = 0;
		
        // GAMBAR JALURNYA
        // ======================
		dbHelper = new SQLHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();  
		
        for(int i = 0; i < exp.length-1; i++){
        
        	ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();
        	
        	cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal ="+exp[start]+" and simpul_tujuan ="+exp[(++start)], null);
			cursor.moveToFirst();

			
			// dapatkan koordinat Lat,Lng dari field koordinat (3)
			String json = cursor.getString(0).toString();

			// get JSON
			JSONObject jObject = new JSONObject(json);
			JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

			// get coordinate JSON
			for(int w = 0; w < jArrCoordinates.length(); w++){
				
				JSONArray latlngs = jArrCoordinates.getJSONArray(w);
				Double lats = latlngs.getDouble(0);
				Double lngs = latlngs.getDouble(1);
					
				
				lat_lng.add( new LatLng(lats, lngs) );

			}
			
			// buat rute
			PolylineOptions jalurBiasa = new PolylineOptions();
			jalurBiasa.addAll(lat_lng).width(5).color(0xff00ff00).geodesic(true);
			googleMap.addPolyline(jalurBiasa);
			
        }
        
        
        // BUAT MARKER UNTUK YOUR POSITION AND DESTINATION POSITION
        // ======================
        // your position

        googleMap.addMarker(new MarkerOptions()
        .position(__global_yourCoordinate_exist)
        .title(__global_yourCoordinate_exist + "Your position")
        .snippet("Your position")
        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.jma))).showInfoWindow();
         
		String[] exp_endCoordinate = __global_endposition.split(",");
		double lat_endPosition = Double.parseDouble(exp_endCoordinate[0]);
		double lng_endPosition = Double.parseDouble(exp_endCoordinate[1]);		
		LatLng endx = new LatLng(lat_endPosition, lng_endPosition);
        
		// destination position
		googleMap.addMarker(new MarkerOptions()
        .position(endx)
        .title(endx+"Destination position")
        .snippet("Destination position")
//        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.amj))).showInfoWindow();
		
        // TENTUKAN JENIS ANGKUTAN UMUM YANG MELEWATI JALUR TERSEBUT
        // ==========================================================
		// misal exp[] = 1->5->6->7
		int m = 0;
		
		
		String[] awal = __global_old_simpul_awal.split("-"); // misal 4-5
		String[] akhir = __global_old_simpul_akhir.split("-"); // misal 8-7

		int ganti_a = 0;
		int ganti_b = 0;
		int simpulAwalDijkstra = Integer.parseInt(exp[0]);

		String gabungSimpul_all = "";
    	Map<String, ArrayList> listAngkutanUmum = new HashMap<String, ArrayList>();
    	ArrayList<Integer> listSimpulAngkot = new ArrayList<Integer>();

    	// cari simpul_old sebelum koordinat dipecah
    	// misal 4-5 dipecah menjadi 4-6-5, berarti simpul_old awal = 5, simpul_old akhir = 4
        for(int e = 0; e < (exp.length - 1); e++){
        	
        	if(e == 0){ // awal

        		// dijalankan jika hasil algo hanya 2 simpul, example : 4->5
        		if(exp.length == 2 /* 2 simpul (4-5)*/){
            		
        			// ada simpul baru di awal (10) dan di akhir (11), example 10->11
        			if( exp[0].equals(String.valueOf(__global_maxRow0)) && exp[1].equals(String.valueOf(__global_maxRow1)) ){				
        				
    					if(String.valueOf(__global_maxRow0).equals(akhir[0])){
    						ganti_b = Integer.parseInt(akhir[1]);
    					}else{
    						ganti_b = Integer.parseInt(akhir[0]);
    					}
    					
    					if(String.valueOf(ganti_b).equals(awal[0])){
    						ganti_a = Integer.parseInt(awal[1]);
    					}else{
    						ganti_a = Integer.parseInt(awal[0]);
    					}
        			}
        			else{
        				// ada simpul baru di awal (10), example 10->5
        				// maka cari simpul awal yg oldnya
        				if( exp[0].equals(String.valueOf(__global_maxRow0)) ){
        					
            				if(exp[1].equals(awal[1])){
	        					ganti_a = Integer.parseInt(awal[0]);
            				}else{
            					ganti_a = Integer.parseInt(awal[1]);
            				}
	            				ganti_b = Integer.parseInt(exp[1]);
        				}
        				// ada simpul baru di akhir (10), example 5->10
        				// maka cari simpul akhir yg oldnya
        				else if( exp[1].equals(String.valueOf(__global_maxRow0)) ){
        					
        					if(exp[0].equals(akhir[0])){
	        					ganti_b = Integer.parseInt(akhir[1]);
            				}else{
            					ganti_b = Integer.parseInt(akhir[0]);
            				}       					
        					ganti_a = Integer.parseInt(exp[0]);   					
        				}
        				// tidak ada penambahan simpul sama sekali
        				else{
        					ganti_a = Integer.parseInt(exp[0]);
        					ganti_b = Integer.parseInt(exp[1]);
        				}
        			}
        			
        			/*
        			// 4 == 4
        			if(exp[0].equals(awal[0])){
            			ganti_a = Integer.parseInt(awal[0]);
            			//ganti_b = Integer.parseInt(awal[1]);      				
        			}else{
            			ganti_a = Integer.parseInt(awal[1]);
            			//ganti_b = Integer.parseInt(awal[0]);       				
        			}
        			
        			if(String.valueOf(ganti_a).equals(akhir[0])){
            			ganti_b = Integer.parseInt(akhir[1]);
            			//ganti_b = Integer.parseInt(awal[1]);      				
        			}else{
            			ganti_b = Integer.parseInt(akhir[0]);
            			//ganti_b = Integer.parseInt(awal[0]);       				
        			}
        			*/
        			
        			/*
        			 *         			// 4 == 4
        			if(exp[0].equals(awal[0])){
            			ganti_a = Integer.parseInt(akhir[0]);
            			ganti_b = Integer.parseInt(awal[1]);      				
        			}else{
            			ganti_a = Integer.parseInt(awal[1]);
            			ganti_b = Integer.parseInt(akhir[0]);       				
        			}
        			 */
        			
        		}
        		// hasil algo lebih dr 2 : 4->5->8->7-> etc ..
        		else{        			
            		if(exp[1].equals(awal[1])){ // 5 == 5
            			ganti_a = Integer.parseInt(awal[0]); // hasil 4
            		}else{
            			ganti_a = Integer.parseInt(awal[1]); // hasil 5
            		}
            		
        			ganti_b = Integer.parseInt( exp[++m] );
        		}
        	}	        
        	else if(e == (exp.length - 2)){ // akhir
        		
        		if(exp[ (exp.length - 2) ].equals(akhir[1])){ // 7 == 7
        			ganti_b = Integer.parseInt(akhir[0]); // hasil 8
        		}else{
        			ganti_b = Integer.parseInt(akhir[1]); // hasil 7
        		}
        		
        		ganti_a = Integer.parseInt( exp[m] );
        		
        	}else{ // tengah tengah
        		ganti_a = Integer.parseInt( exp[m] );
        		ganti_b = Integer.parseInt( exp[++m] );
        	}

        	gabungSimpul_all += "," + ganti_a + "-" + ganti_b + ","; // ,1-5,
        	String gabungSimpul = "," + ganti_a + "-" + ganti_b + ","; // ,1-5,
        	
			cursor = db.rawQuery("SELECT * FROM angkutan_umum where simpul like '%" + gabungSimpul + "%'", null);
			cursor.moveToFirst();

			ArrayList<String> listAngkutan = new ArrayList<String>();
			
			for(int ae = 0; ae < cursor.getCount(); ae++){				
				cursor.moveToPosition(ae);
				listAngkutan.add( cursor.getString(1).toString() );				
			}        	
        	
			listAngkutanUmum.put("angkutan" + e, listAngkutan);
			
			// add simpul angkot
			listSimpulAngkot.add( Integer.parseInt(exp[e]) ); 

        }
 
		
        String replace_jalur = gabungSimpul_all.replace(",,", ","); //  ,1-5,,5-6,,6-7, => ,1-5,5-6,6-7,
		cursor = db.rawQuery("SELECT * FROM angkutan_umum where simpul like '%" + replace_jalur + "%'", null);
		cursor.moveToFirst();
		cursor.moveToPosition(0);
		
		// ada 1 angkot yg melewati jalur dari awal sampek akhir
		if(cursor.getCount() > 0){

			String siAngkot = cursor.getString(1).toString();

			// get coordinate
			cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal = '" + simpulAwalDijkstra + "'", null);
			cursor.moveToFirst();
			String json_coordinate = cursor.getString(0).toString();
			
			// manipulating JSON
			JSONObject jObject = new JSONObject(json_coordinate);
			JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");
			JSONArray latlngs = jArrCoordinates.getJSONArray(0);
			
			// first latlng
			Double lats = latlngs.getDouble(0);
			Double lngs = latlngs.getDouble(1);

			googleMap.addMarker(new MarkerOptions()
	        .position(new LatLng(lats, lngs))
	        .title("Angkot")
	        .snippet(siAngkot)
	        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ank))).showInfoWindow();
			
			// die()
			return;
		}
		
		// ada 2 atau lebih angkot yg melewati jalur dari awal sampek akhir
		int banyakAngkot = 0;
		int indexUrut = 0;
		int indexSimpulAngkot = 1;
        int lengthAngkutan = listAngkutanUmum.size();
        Map<String, ArrayList> angkotFix = new HashMap<String, ArrayList>();

        for(int en = 0; en < lengthAngkutan; en++ ){

        	// temporary sementara sebelum di retainAll()
        	ArrayList<String> temps = new ArrayList<String>();
        	for(int u = 0; u < listAngkutanUmum.get("angkutan0").size(); u++){
        		temps.add( listAngkutanUmum.get("angkutan0").get(u).toString() );
        	}        	        	
        	
        	if(en > 0 ){
	    		ArrayList listSekarang1 = listAngkutanUmum.get("angkutan0");
				ArrayList listSelanjutnya1 = listAngkutanUmum.get("angkutan" + en);	
				
				// intersection
				listSekarang1.retainAll(listSelanjutnya1);
	            
	            if(listSekarang1.size() > 0){
	            	
	            	listSimpulAngkot.remove(indexSimpulAngkot);
	            	--indexSimpulAngkot;

	            	listAngkutanUmum.remove("angkutan" + en);

	            	if(en == (lengthAngkutan - 1)){
	            		
		            	ArrayList<String> tempDalam = new ArrayList<String>();
		            	for(int es = 0; es < listSekarang1.size(); es++){
		            		tempDalam.add( listSekarang1.get(es).toString() );
		            	}
		            	
	            		angkotFix.put("angkutanFix" + indexUrut, tempDalam);
		            	++indexUrut;	
	            	}
	            }	            
	            else if(listSekarang1.size() == 0){
	            	
	            	angkotFix.put("angkutanFix" + indexUrut, temps);
	            	
	            	ArrayList<String> tempDalam = new ArrayList<String>();
	            	for(int es = 0; es < listSelanjutnya1.size(); es++){
	            		tempDalam.add( listSelanjutnya1.get(es).toString() );
	            	}
	            	
	            	//if(en == 1) break;
	            	listAngkutanUmum.get("angkutan0").clear();
	            	listAngkutanUmum.put("angkutan0", tempDalam);
	            	
	            	//if(en != (listAngkutanUmum.size() - 1)){
	            		listAngkutanUmum.remove("angkutan" + en);	
	            	//}
	            	
		            ++indexUrut;
		            
	            	if(en == (lengthAngkutan - 1)){
	            		
		            	ArrayList<String> tempDalam2 = new ArrayList<String>();
		            	for(int es = 0; es < listSelanjutnya1.size(); es++){
		            		tempDalam2.add( listSelanjutnya1.get(es).toString() );
		            	}
		            	
	            		angkotFix.put("angkutanFix" + indexUrut, tempDalam2);
		            	++indexUrut;	
	            	}		            
	            }
	        	
	        	++indexSimpulAngkot;
        	}
        }
        
        for(int r = 0; r < listSimpulAngkot.size(); r++){
        	String simpulx = listSimpulAngkot.get(r).toString();
			// get coordinate simpulAngkutan
			cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal = '" + simpulx + "'", null);
			cursor.moveToPosition(0);
			
			// dapatkan koordinat Lat,Lng dari field koordinat (3)
			String json = cursor.getString(0).toString();

			// get JSON
			JSONObject jObject = new JSONObject(json);
			JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

			// get first coordinate JSON
			JSONArray latlngs = jArrCoordinates.getJSONArray(0);
			Double lats = latlngs.getDouble(0);
			Double lngs = latlngs.getDouble(1);
				
			LatLng simpulAngkot = new LatLng(lats, lngs);
			String siAngkot = angkotFix.get("angkutanFix" + r).toString();
			
			if(r == 0){
				googleMap.addMarker(new MarkerOptions()
		        .position(simpulAngkot)
		        .title("Angkot")
		        .snippet(siAngkot)
		        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ank))).showInfoWindow();
			}else{
				googleMap.addMarker(new MarkerOptions()
		        .position(simpulAngkot)
		        .title("Angkot")
		        .snippet(siAngkot)
		        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ank)));
			}
        }
        
	}
	
	public void getSimpulAwalAkhirJalur(Get_koordinat_awal_akhir objects, double latx, double lngx, String statusObject) throws JSONException{
		
		// return JSON index posisi koordinat, nodes0, nodes1
		JSONObject jStart = objects.Get_simpul(latx, lngx, this);

		// index JSON
		String status = jStart.getString("status");
		int node_simpul_awal0 = jStart.getInt("node_simpul_awal0");
		int node_simpul_awal1 = jStart.getInt("node_simpul_awal1");
		int index_coordinate_json = jStart.getInt("index_coordinate_json");
		Toast.makeText(getApplicationContext(), status+"status", Toast.LENGTH_LONG).show();
		
		int fix_simpul_awal = 0;
		
		// jika koordinat tepat di atas posisi simpul/node
		// maka tidak perlu menambahkan simpul baru
		if(status.equals("jalur_none")){
			
			//tentukan simpul awal atau akhir yg dekat dgn posisi user
			if(index_coordinate_json == 0){ // awal			
				fix_simpul_awal = node_simpul_awal0;				
			}else{ // akhir				
				fix_simpul_awal = node_simpul_awal1;				
			}
			
			if(statusObject == "awal"){	
				
				// return
				__global_old_simpul_awal = node_simpul_awal0 + "-" + node_simpul_awal1;
				__global_simpul_awal = fix_simpul_awal; // misal 0				
			}else{
				
				// return
				__global_old_simpul_akhir = node_simpul_awal0 + "-" + node_simpul_awal1;
				__global_simpul_akhir = fix_simpul_awal; // misal 0				
			}
		
						
		}
		// jika koordinat berada diantara simpul 5 dan simpul 4 atau simpul 4 dan simpul 5
		// maka perlu menambahkan simpul baru
		else if(status.equals("jalur_double")){

			// return		
			if(statusObject == "awal"){				
				
				// cari simpul (5,4) dan (4-5) di Tambah_simpul.java
				Tambah_simpul obj_tambah = new Tambah_simpul();
				obj_tambah.dobelSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json, 
										this, __global_graphArray, 401
									); // 401 : row id yg baru
										
				
				// return
				__global_old_simpul_awal = obj_tambah.simpul_lama;
				__global_simpul_awal = obj_tambah.simpul_baru; // misal 6
				__global_graphArray = obj_tambah.modif_graph; // graph[][]

			}else{
			
				// cari simpul (5,4) dan (4-5) di Tambah_simpul.java
				Tambah_simpul obj_tambah = new Tambah_simpul();
				obj_tambah.dobelSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json, 
										this, __global_graphArray, 501
									); // 501 : row id yg baru
										
				
				// return
				__global_old_simpul_akhir = obj_tambah.simpul_lama;
				__global_simpul_akhir = obj_tambah.simpul_baru; // misal 4			
				__global_graphArray = obj_tambah.modif_graph; // graph[][]
								
			}

		}
		// jika koordinat hanya berada diantara simpul 5 dan simpul 4
		// maka perlu menambahkan simpul baru
		else if(status.equals("jalur_single")){

			if(statusObject == "awal"){
				
				// cari simpul (5,4) di Tambah_simpul.java
				Tambah_simpul obj_tambah1 = new Tambah_simpul();
				obj_tambah1.singleSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json, 
										this, __global_graphArray, 401
									); // 401 : row id yg baru
										
				
				// return
				__global_old_simpul_awal = obj_tambah1.simpul_lama;
				__global_simpul_awal = obj_tambah1.simpul_baru; // misal 6
				__global_graphArray = obj_tambah1.modif_graph; // graph[][]
				
			}else{
				
				// cari simpul (5,4) di Tambah_simpul.java
				Tambah_simpul obj_tambah1 = new Tambah_simpul();
				obj_tambah1.singleSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json, 
						this, __global_graphArray, 501
					); // 501 : row id yg baru

				
				// return
				__global_old_simpul_akhir = obj_tambah1.simpul_lama;
				__global_simpul_akhir = obj_tambah1.simpul_baru; // misal 4			
				__global_graphArray = obj_tambah1.modif_graph; // graph[][]	
			}		
		}		
	}
	
	
	/*
	 * @fungsi
	 *  delete temporary record DB
	 *  (temporary ini digunakan untuk menampung sementara simpul baru)
	 * @parameter
	 *  no parameter
	 * @return
	 *  no returen
	 */
	public void deleteTemporaryRecord(){
		
		// delete DB
		final SQLiteDatabase dbDelete = dbHelper.getWritableDatabase();

		// delete temporary record DB
		for(int i = 0; i < 4; i++){								
			//hapus simpul awal tambahan, mulai dr id 401,402,403,404
			String deleteQuery_ = "DELETE FROM graph where id ='"+ (401+i) +"'";
			dbDelete.execSQL(deleteQuery_);	
			
			//hapus simpul tujuan tambahan, mulai dr id 501,502,503,504
			String deleteQuery = "DELETE FROM graph where id ='"+ (501+i) +"'";
			dbDelete.execSQL(deleteQuery);	
		}
	}
	
	public void maxRowDB(){
		
		dbHelper = new SQLHelper(this);
		SQLiteDatabase dbRead = dbHelper.getReadableDatabase();		
		
		cursor = dbRead.rawQuery("SELECT max(simpul_awal), max(simpul_tujuan) FROM graph", null);
		cursor.moveToFirst();
		int max_simpul_db		= 0;
		int max_simpulAwal_db 	= Integer.parseInt(cursor.getString(0).toString());			
		int max_simpulTujuan_db = Integer.parseInt(cursor.getString(1).toString());
		
		if(max_simpulAwal_db >= max_simpulTujuan_db){
			max_simpul_db = max_simpulAwal_db;
		}else{
			max_simpul_db = max_simpulTujuan_db;
		}
		
		// return
		__global_maxRow0 = (max_simpul_db+1);
		__global_maxRow1 = (max_simpul_db+2);
	}


	public void drawJalur2(String[] exp) throws JSONException{
		int start = 0;

		// GAMBAR JALURNYA
		// ======================
		dbHelper = new SQLHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		for(int i = 0; i < exp.length-1; i++){

			ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();

			cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal ="+exp[start]+" and simpul_tujuan ="+exp[(++start)], null);
			cursor.moveToFirst();


			// dapatkan koordinat Lat,Lng dari field koordinat (3)
			String json = cursor.getString(0).toString();

			// get JSON
			JSONObject jObject = new JSONObject(json);
			JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

			// get coordinate JSON
			for(int w = 0; w < jArrCoordinates.length(); w++){

				JSONArray latlngs = jArrCoordinates.getJSONArray(w);
				Double lats = latlngs.getDouble(0);
				Double lngs = latlngs.getDouble(1);


				lat_lng.add( new LatLng(lats, lngs) );

			}

			// buat rute
			PolylineOptions jalurBiasa = new PolylineOptions();
			jalurBiasa.addAll(lat_lng).width(5).color(0xff00ff00).geodesic(true);
			googleMap.addPolyline(jalurBiasa);

		}


	}

}
