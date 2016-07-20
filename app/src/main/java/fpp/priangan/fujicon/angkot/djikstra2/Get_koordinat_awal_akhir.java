package fpp.priangan.fujicon.angkot.djikstra2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.widget.Toast;

public class Get_koordinat_awal_akhir extends Activity{

	// DB
	Cursor cursor;	
	
	int fix_simpul_awal = 0;
	String explode_lat_only = "";	
	Location posisiUser = new Location("");
	ArrayList<String> a_tmp_graph = new ArrayList<String>();
	
	// return JSON
	JSONObject jadi_json = new JSONObject();
	
	// AMBIL NODE DARI FIELD SIMPUL_AWAL DAN TUJUAN DI TABEL GRAPH, TRS DIGABUNG; contoh 1,0 DAN MASUKKAN KE ARRAY
	List<String> barisDobel = new ArrayList<String>();
	List<String> indexBarisYgDikerjakan = new ArrayList<String>();
	
	
	
	/*
	 * @fungsi
	 *  menyeleksi simpul yg akan dikerjakan
	 *  jika ada simpul 1-0 dan 0-1 maka yg dikerjakan hanya 1-0 ( karena koordinat 1-0 sama dengan 0-1 (koordinat hanya dibalik) )
	 * @parameter
	 *   latx : latitude user atau SMK
	 *   lngx : longitude user atau destination
	 *   context : MainActivity context
	 * @return
	 *   JSON (index coordinates, nodes0, nodes1)
	 */	
	public JSONObject Get_simpul(double latx, double lngx, Context context) throws JSONException {
		// TODO Auto-generated constructor stub		
		
		// your coordinate
		posisiUser.setLatitude(latx);
		posisiUser.setLongitude(lngx);		

		// TAMPUNG NODE DARI FIELD SIMPUL_AWAL DAN TUJUAN DI TABEL GRAPH, TRS DIGABUNG; contoh 1,0 DAN MASUKKAN KE ARRAY
		List<String> barisDobel = new ArrayList<String>();
		List<String> indexBarisYgDikerjakan = new ArrayList<String>();
			
		
		SQLHelper dbHelper = new SQLHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		// filter simpul yg akan dikerjakan
		cursor = db.rawQuery("SELECT * FROM graph where simpul_awal != '' and simpul_tujuan != '' and jalur != '' and bobot != ''", null);
		cursor.moveToFirst();
		
		
		// AMBIL NODE DARI FIELD SIMPUL_AWAL DAN SIMPUL_TUJUAN DI TABEL GRAPH, TRS DIGABUNG; contoh 1,0 DAN MASUKKAN KE ARRAY		
		// looping di bawah ini untuk MEMERIKSA BARIS DOBEL {simpulnya yg dobel} 1,0 -> 0,1 {1,0 dihitung tapi 0,1 gak dihitung}
		for (int i = 0; i < cursor.getCount(); i++){
			
			cursor.moveToPosition(i);
			
			// node dari field simpul_awal
			String fieldSimpulAwal = cursor.getString(1).toString();
			
			// node dari field simpul_akhir
			String fieldSimpulTujuan = cursor.getString(2).toString();
			
			String gabungSimpul = fieldSimpulAwal+","+fieldSimpulTujuan;			
			String gabung_balikSimpul = fieldSimpulTujuan+","+fieldSimpulAwal;

			// seleksi ruas yang dobel; contoh : 1,0 == 0,1
			// pilih salah satu, misal : 1,0
			if(barisDobel.isEmpty()){
				
				barisDobel.add(gabung_balikSimpul);
				
				// field id pada tabel graph
				indexBarisYgDikerjakan.add(cursor.getString(0).toString());
			}else{
				
				if(!barisDobel.contains(gabungSimpul)){	
					barisDobel.add(gabung_balikSimpul);
					
					// field id pada tabel graph
					indexBarisYgDikerjakan.add(cursor.getString(0).toString());
				}
			}
		}
		
		
		// list simpul yg dikerjakan
		StringBuilder indexBarisYgDikerjakan1 = new StringBuilder();		
		for(int j = 0; j < indexBarisYgDikerjakan.size(); j++){
			
			if(indexBarisYgDikerjakan1.length() == 0){
				
				// field id lagi pada tabel graph (khusus utk stringbuilder)
				indexBarisYgDikerjakan1.append(indexBarisYgDikerjakan.get(j));
			}else{
				indexBarisYgDikerjakan1.append(","+indexBarisYgDikerjakan.get(j)); // untuk where in ('0,1')
			}
		}


		// Query baris yang gak dobel
		cursor = db.rawQuery("SELECT * FROM graph where id in("+indexBarisYgDikerjakan1+")",null);
		cursor.moveToFirst();

		JSONObject obj = new JSONObject();
	
		// @@@@@@=========== Cari JARAK
		// looping semua record
		for(int k = 0; k < cursor.getCount(); k++){

			// VARIABEL BUAT CARI 1 JARAK DALAM 1 RECORD (1 record isinya banyak koordinat)			
			// simpan jarak user ke koordinat simpul dalam meter
			List<Double> jarakUserKeKoordinatSimpul = new ArrayList<Double>();	

			cursor.moveToPosition(k);
			
			// dapatkan koordinat Lat,Lng dari field koordinat (3)
			String json = cursor.getString(3).toString();

			// manipulating JSON
			JSONObject jObject = new JSONObject(json);
			JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");
			JSONArray jArrNodes = jObject.getJSONArray("nodes");
			

			// get coordinate
			for(int w = 0; w < jArrCoordinates.length(); w++){
				JSONArray latlngs = jArrCoordinates.getJSONArray(w);
				Double lats = latlngs.getDouble(0);
				Double lngs = latlngs.getDouble(1);

				//SET LAT,LNG
				Location koordinatSimpul = new Location("");
				koordinatSimpul.setLatitude(lats);
				koordinatSimpul.setLongitude(lngs);					
				
				//CARI JARAK DARI POSISI USER KE koordinate sekitar SIMPUL (dalam meter)
				double jarak = posisiUser.distanceTo(koordinatSimpul);
				
				jarakUserKeKoordinatSimpul.add(jarak);

			}
			
			// CARI bobot yg paling kecil
			int index_koordinatSimpul = 0;
			for(int m = 0; m < jarakUserKeKoordinatSimpul.size(); m++){
				
				if(jarakUserKeKoordinatSimpul.get(m) <= jarakUserKeKoordinatSimpul.get(0)){
					jarakUserKeKoordinatSimpul.set(0, jarakUserKeKoordinatSimpul.get(m));
					
					// index array dari value yg terkecil
					index_koordinatSimpul = m;
				}				
			}	


			// field id dari table graph
			int row_id = cursor.getInt(0);
			
			JSONObject list = new JSONObject();					
			
			// masukkan index koordinat array, bobot terkecil dan jumlah koordinat ke JSON
			list.put("row_id", row_id);
			list.put("index", index_koordinatSimpul);
			list.put("bobot", jarakUserKeKoordinatSimpul.get(0));
			list.put("nodes", jArrNodes.getString(0));
			list.put("count_koordinat", (jArrCoordinates.length() - 1));
			
			JSONArray ja = new JSONArray();
			ja.put(list);
			
			// Create json
			// example output : 
			// {"0" : [{"row_id":17, "index":"7", "bobot":"427.66", "count_koordinat":"15", "nodes":"0-1"}]}
			obj.put("" + k, ja);

		}//end looping baris DB

		
		
		double x = 0;
		double y = 0;
		int rowId_json = 0;
		int indexCoordinate_json = 0;
		int countCoordinate_json = 0;
		String nodes_json = "";

		// cari bobot terkecil dari JSON
		for(int s = 0; s < obj.length(); s++){
			
			if(s == 0){
				// first
				JSONArray a = obj.getJSONArray("0");			
				JSONObject b = a.getJSONObject(0);
				x = Double.parseDouble(b.getString("bobot"));
				
				// ==========
				// row id field
				rowId_json = Integer.parseInt(b.getString("row_id"));
				// index coordinate sekitar simpul
				indexCoordinate_json = Integer.parseInt(b.getString("index"));
				// jumlah coordinate
				countCoordinate_json = Integer.parseInt(b.getString("count_koordinat"));
				// nodes
				nodes_json = b.getString("nodes").toString();
				// ==========
				
			}else{
				// second, dst
				JSONArray c = obj.getJSONArray("" + s);			
				JSONObject d = c.getJSONObject(0);
				y = Double.parseDouble(d.getString("bobot"));
				
				// dapatkan value terkecil (bobot)
				if(y <= x){
					// bobot
					x = y;

					// ==========
					// row id field
					rowId_json = Integer.parseInt(d.getString("row_id"));
					// index coordinate sekitar simpul
					indexCoordinate_json = Integer.parseInt(d.getString("index"));
					// jumlah coordinate
					countCoordinate_json = Integer.parseInt(d.getString("count_koordinat"));
					// nodes
					nodes_json = d.getString("nodes").toString();
					// ==========
					
				}		
			}
			
		}		
		
		// nodes : 0-1
		String[] exp_nodes = nodes_json.split("-");

		
		int field_simpul_awal = Integer.parseInt(exp_nodes[0]);
		int field_simpul_tujuan = Integer.parseInt(exp_nodes[1]);

		// Koordinat yg didapat di awal atau diakhir, maka gak perlu nambah simpul
		if(indexCoordinate_json == 0 || indexCoordinate_json == countCoordinate_json){
			
			//tentukan simpul awal atau akhir yg dekat dgn posisi user
			if(indexCoordinate_json == 0){
				
				// nodes di field simpul_awal
				fix_simpul_awal = field_simpul_awal; 
			}else if(indexCoordinate_json == countCoordinate_json){
				
				// nodes di field simpul_akhir
				fix_simpul_awal = field_simpul_tujuan;
			}
			
			jadi_json.put("status", "jalur_none");
			
		}
		//Koordinat yang didapat berada ditengah2 simpul 0 - 1 (misal)
		else{
			// cari simpul dobel, simpulnya dibalik
			cursor = db.rawQuery("SELECT id FROM graph where simpul_awal = "+ field_simpul_tujuan + " and simpul_tujuan = " + field_simpul_awal, null);
			cursor.moveToFirst();
			cursor.moveToPosition(0);
			
			int dobel = cursor.getCount();
			
			//ada simpul yg dobel (1,0) dan (0,1)
			if(dobel == 1){

				jadi_json.put("status", "jalur_double");

			}
			//gak dobel, hanya (1,0)
			else if(dobel == 0){
				
				jadi_json.put("status", "jalur_single");

			}
		}
		
		
		// JSON
		jadi_json.put("node_simpul_awal0", field_simpul_awal);
		jadi_json.put("node_simpul_awal1", field_simpul_tujuan);				
		jadi_json.put("index_coordinate_json", indexCoordinate_json);
		//jadi_json.put("destination_simpul", judulTabel_simpulTujuan);
		jadi_json.put("explode_lat_only", explode_lat_only);
				
		return jadi_json;
		
	}//public

}