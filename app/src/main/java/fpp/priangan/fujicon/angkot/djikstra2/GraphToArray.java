package fpp.priangan.fujicon.angkot.djikstra2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpp.priangan.fujicon.angkot.Tes2;


/*
 * CONVERT GRAPH FROM DB TO ARRAY
 */
public class GraphToArray {
	
	// DB
	SQLHelper dbHelper;
	SQLiteDatabase db;
	protected Cursor cursor;
	
	// Array Graph
	String[][] graph = new String[1100][1100];
	
	
	/* Convert Graph from DB to Array
	 * parameter mainContext : context MainActivity
	 * return array String[][]
	 */
	public String[][] convertToArray(Context mainContext){
		
		dbHelper = new SQLHelper(mainContext);
		db = dbHelper.getReadableDatabase();
	
		// PINDAHKAN GRAPH DARI DB KE GRAPH ARRAY 
		cursor = db.rawQuery("SELECT * FROM graph order by simpul_awal,simpul_tujuan asc", null);
		cursor.moveToFirst();
		
		String temp_index_baris = "";
		int index_kolom = 0;
		int jml_baris = cursor.getCount();
		
		for(int i = 0; i < jml_baris; i++){
			
			// baris
			cursor.moveToPosition(i);
			
			// Cari index kolom
			int simpulAwalDB = Integer.parseInt(cursor.getString(1)); // simpul_tujuan
			
			if(temp_index_baris == ""){
				temp_index_baris = String.valueOf(simpulAwalDB);
			}else{
				// simpul_awal berikutnya tidak sama dgn sebelumnya, reset index_kolom = 0
				if(Integer.parseInt(temp_index_baris) != simpulAwalDB){
					index_kolom = 0;
					temp_index_baris = String.valueOf(simpulAwalDB);
				}
			}
						
			// masukkan ke graph array
			String simpulTujuan_dan_Bobot = "";
			if(cursor.getString(2).equals("") && cursor.getString(3).equals("") && cursor.getString(4).equals("")){ //tidak ada derajat keluar
				simpulTujuan_dan_Bobot = ";";
			}
			// ada derajat keluar
			else{			
				
				// example output : 2->789.98
				simpulTujuan_dan_Bobot = cursor.getString(2).toString()+"->"+cursor.getString(4).toString(); //simpul_tujuan dan bobot
			}
			
			graph[simpulAwalDB][index_kolom] = simpulTujuan_dan_Bobot;
			index_kolom++;
		}// for
		
		
		return graph;
		
	}


}
