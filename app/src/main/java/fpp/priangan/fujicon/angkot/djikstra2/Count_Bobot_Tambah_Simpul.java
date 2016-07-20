package fpp.priangan.fujicon.angkot.djikstra2;

import org.json.JSONArray;
import org.json.JSONException;

import android.location.Location;

public class Count_Bobot_Tambah_Simpul{
	
	double bobot = 0;

	public void Count_Bobot_Tambah_Simpul(int index, int limit, JSONArray jArrCoordinates) throws JSONException{				

		// cuma dijalanin sekali, kalo limit 1, maka 1-0 = 0
		// 0 == 0 (limit)
		if(index == limit){
			// get JSON coordinate
			JSONArray latlngs = jArrCoordinates.getJSONArray(index);

			double lat_0 = latlngs.getDouble(0);
			double lng_0 = latlngs.getDouble(1);
			
			Location simpulAwal = new Location("");
			simpulAwal.setLatitude(lat_0);
			simpulAwal.setLongitude(lng_0);
			
			// get coordinate again
			JSONArray latlngs1 = jArrCoordinates.getJSONArray(++index);

			double lat_1 = latlngs1.getDouble(0);
			double lng_1 = latlngs1.getDouble(1);

			Location simpulTengah = new Location("");							
			simpulTengah.setLatitude(lat_1);
			simpulTengah.setLongitude(lng_1);
			
			//simpan jarak
			bobot += simpulAwal.distanceTo(simpulTengah);			
		
		}else{
			for(int i = 0; i < 1; i++){

				// get JSON coordinate
				JSONArray latlngs = jArrCoordinates.getJSONArray(index);

				double lat_0 = latlngs.getDouble(0);
				double lng_0 = latlngs.getDouble(1);
				
				Location simpulAwal = new Location("");
				simpulAwal.setLatitude(lat_0);
				simpulAwal.setLongitude(lng_0);
				
				// get coordinate again
				JSONArray latlngs1 = jArrCoordinates.getJSONArray(++index);

				double lat_1 = latlngs1.getDouble(0);
				double lng_1 = latlngs1.getDouble(1);

				Location simpulTengah = new Location("");							
				simpulTengah.setLatitude(lat_1);
				simpulTengah.setLongitude(lng_1);
				
				
				//simpan jarak
				bobot += simpulAwal.distanceTo(simpulTengah);
				
				if(index == limit) break; //jika dah smpe ke tengah, break; misal 0-72
				else --i;
			}
		}
	}
}