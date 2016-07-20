package fpp.priangan.fujicon.angkot.adapters;

import android.content.Context;
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

public class ViewPagerAdapterRutetes2 extends PagerAdapter {
	// Declare Variables


	MarkerOptions markerOptions;
	GoogleMap mMap;
	Context context;
	String[] no;
	String[] nama_trayek;
	LatLng[] lalat;
	/*String[] warna;
	String[] nama_trayek;
	String[] lintasan;
	String[] harga;
	String[] operasi;*/
	int[] gambar;
	LayoutInflater inflater;
	int pos=3;
	public ViewPagerAdapterRutetes2(Context context, String[] no, String[] nama_trayek, int[] gambar) {
		this.context = context;
		this.no=no;
		this.nama_trayek=nama_trayek;
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
		final TextView no1;
		TextView warna1;
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
//		String gg="R.drawable.cibogo_elang";
//		int fh= Integer.parseInt(gg);
//		gambar1.setImageResource(fh);
		gambar1.setImageResource(gambar[position]);


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

