package fpp.priangan.fujicon.angkot.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fpp.priangan.fujicon.angkot.R;
import fpp.priangan.fujicon.angkot.djikstra2.SQLHelper;

public class ViewPagerAdapterRutetes2 extends PagerAdapter {
	// Declare Variables


	MarkerOptions markerOptions;
	SQLHelper dbHelper;

	Cursor cursor;
	GoogleMap mMap;
	Context context;
	String[] no;
	String[] nama_trayek;
	String[] simpull;
	LatLng[] lalat;
	/*String[] warna;
	String[] nama_trayek;
	String[] lintasan;
	String[] harga;
	String[] operasi;*/
	int[] gambar;
	LayoutInflater inflater;
	int pos=3;
	public ViewPagerAdapterRutetes2(Context context, String[] no, String[] nama_trayek, int[] gambar, String[] simpull) {
		this.context = context;
		this.no=no;
		this.nama_trayek=nama_trayek;
		this.simpull=simpull;
	//	this.lalat=lalat;
		/*this.warna=warna;
		this.lintasan=lintasan;
		this.harga=harga;
		this.operasi=operasi;*/
		this.gambar=gambar;
	}



	@Override
	public int getCount() {
		return no.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		// Declare Variables
		int posisank = 0;
		final TextView no1;
		final TextView warna1,simpul;
		TextView nama_trayek1;
		TextView lintasan1;
		final TextView harga1;
		TextView operasi1;
		ImageView gambar1;
		Button rute1,rute2;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item_rute, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		no1 = (TextView) itemView.findViewById(R.id.no);
		simpul = (TextView) itemView.findViewById(R.id.simpul);
		/*warna1 = (TextView) itemView.findViewById(R.id.warna);
		nama_trayek1 = (TextView) itemView.findViewById(R.id.nama_trayek);
		lintasan1 = (TextView) itemView.findViewById(R.id.lintasan);
		harga1 = (TextView) itemView.findViewById(R.id.harga);
		operasi1 = (TextView) itemView.findViewById(R.id.operasi);

		rute1 = (Button) itemView.findViewById(R.id.rute1);
		rute2 = (Button) itemView.findViewById(R.id.rute2);*/
		//int s=position+1;
//		String kkk=no[position];
//		String kkks = kkk.trim().replace("null","");
//		String kkk1=noo[position];
//		String kkks1 = kkk1.trim().replace("null","");
//		if(no[position].equals(no[0])){
//
//			no1.setText("Naik Dari : "+kkks+"\n Menuju : "+kkks1+" Sejauh "+jarak[position]);
//		}else {
//
//			no1.setText("Dari : " + kkks + "\n Menuju : " + kkks1 + " Sejauh " + jarak[position]);
//
//		}
//		markerOptions = new MarkerOptions();
//		markerOptions.position(lalat[position]);
//		markerOptions.title(no[position]);
//		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52));
//
//		mMap.addMarker(markerOptions);
//

		/*warna1.setText(warna[position]);
		nama_trayek1.setText(nama_trayek[position]);
		lintasan1.setText(lintasan[position]);
		harga1.setText(harga[position]);
		operasi1.setText(operasi[position]);*/

		// Locate the ImageView in viewpager_item.xml
		gambar1 = (ImageView) itemView.findViewById(R.id.gambar);
		no1.setText(nama_trayek[position]);
		simpul.setText(simpull[position]);
//		String gg="R.drawable.cibogo_elang";
//		int fh= Integer.parseInt(gg);
//		gambar1.setImageResource(fh);
		if(nama_trayek[position].equals("Abdulmuis - Cicaheum via Binong")){
			posisank=0;
		}
		else if(nama_trayek[position].equals("Abdulmuis - Cicaheum via Aceh")){
			posisank=1;

		}else if(nama_trayek[position].equals("Abdulmuis - Dago")){
			posisank=2;

		}else if(nama_trayek[position].equals("Abdulmuis - Ledeng")){
			posisank=3;

		}else if(nama_trayek[position].equals("Abdulmuis - Elang")){
			posisank=4;

		}else if(nama_trayek[position].equals("Cicaheum - Ledeng")){
			posisank=5;

		}else if(nama_trayek[position].equals("Cicaheum - Ciroyom")){
			posisank=6;

		}else if(nama_trayek[position].equals("Cicaheum - Ciwastra")){
			posisank=7;


		}else if(nama_trayek[position].equals("Cicaheum - Cibaduyut")){
			posisank=8;

		}else if(nama_trayek[position].equals("Dago - ST Hall")){
			posisank=9;

		}else if(nama_trayek[position].equals("ST Hall - Sadang Serang")){
			posisank=10;

		}else if(nama_trayek[position].equals("Cimbuleuit - ST Hall via Eykman")){
			posisank=11;

		}else if(nama_trayek[position].equals("Cimbuleuit - ST Hall via Cihampelas")){
			posisank=12;

		}else if(nama_trayek[position].equals("ST Hall - Gedebage")){
			posisank=13;

		}else if(nama_trayek[position].equals("ST Hall - Sarijadi")){
			posisank=14;

		}else if(nama_trayek[position].equals("ST Hall - Gunung Batu")){
			posisank=15;

		}else if(nama_trayek[position].equals("Margahayu - Ledeng")){
			posisank=16;

		}else if(nama_trayek[position].equals("Riung Bandung - Dago")){
			posisank=17;

		}else if(nama_trayek[position].equals("Caringin - Dago")){
			posisank=18;

		}else if(nama_trayek[position].equals("Panghegar - Dipatiukur")){
			posisank=19;

		}else if(nama_trayek[position].equals("Ciroyom - Sarijadi via Sukajadi")){
			posisank=20;

		}else if(nama_trayek[position].equals("Ciroyom - Sarijadi via Setrasari")){
			posisank=21;

		}else if(nama_trayek[position].equals("Bumi Asri - Ciroyom")){
			posisank=22;

		}else if(nama_trayek[position].equals("Cikudapateh - Ciroyom")){
			posisank=23;

		}else if(nama_trayek[position].equals("Buah Batu - Sederhana")){
			posisank=24;

		}else if(nama_trayek[position].equals("Cijerah - Sederhana")){
			posisank=25;

		}else if(nama_trayek[position].equals("Sederhana - Cimindi")){
			posisank=26;

		}else if(nama_trayek[position].equals("Ciwastra - Ujungberung")){
			posisank=27;

		}else if(nama_trayek[position].equals("Cisitu - Tegalega")){
			posisank=28;

		}else if(nama_trayek[position].equals("Cijerah - Ciwastra")){
			posisank=29;

		}else if(nama_trayek[position].equals("Elang - Gedebage")){
			posisank=30;

		}else if(nama_trayek[position].equals("Abdulmuis - Mengger")){
			posisank=31;

		}else if(nama_trayek[position].equals("Cicadas - Elang")){
			posisank=32;

		}else if(nama_trayek[position].equals("Antapani - Ciroyom")){
			posisank=33;

		}else if(nama_trayek[position].equals("Cibiru - Cicadas")){
			posisank=34;

		}else if(nama_trayek[position].equals("Sekemirung - Panyileukan")){
			posisank=35;

		}else if(nama_trayek[position].equals("Caringin - Sadang Serang")){
			posisank=36;

		}else if(nama_trayek[position].equals("Cibaduyut - Karang Setra")){
			posisank=37;

		}else if(nama_trayek[position].equals("Cibogo - Elang")){
			posisank=38;

		}
		int[] gambar2=new int[]{
				R.drawable.a01,
				R.drawable.b01,
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
				R.drawable.b11,
				R.drawable.a12,
				R.drawable.a13,
				R.drawable.a14,
				R.drawable.a15,
				R.drawable.a16,
				R.drawable.a17,
				R.drawable.a18,
				R.drawable.a19,
				R.drawable.b19,
				R.drawable.a20,
				R.drawable.cikudapateuh_ciroyom,
				R.drawable.buah_batu_sederhana,
				R.drawable.cijerah_sederhana,
				R.drawable.cimindi_sederhana,
				R.drawable.ciwastra_uber,
				R.drawable.cisitu_tegalega,
				R.drawable.cijerah_ciwastra,
				R.drawable.elang_uber,
				R.drawable.abdulmuis_mengger,
				R.drawable.cicadas_elang,
				R.drawable.antapani_ciroyom,
				R.drawable.cibiru_cicadas,
				R.drawable.sekemirung_panyileukan_dago_gedebage,
				R.drawable.caringin_sadang_serang,
				R.drawable.cibaduyut_karang_setra,
				R.drawable.cibogo_elang
		};
		gambar1.setImageResource(gambar2[posisank]);

		gambar1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				String k = simpul.getText().toString();

			}
		});
		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}


}

