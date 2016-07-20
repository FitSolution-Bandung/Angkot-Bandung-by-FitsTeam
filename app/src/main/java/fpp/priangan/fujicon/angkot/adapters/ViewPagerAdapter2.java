package fpp.priangan.fujicon.angkot.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fpp.priangan.fujicon.angkot.R;
import fpp.priangan.fujicon.angkot.rute.Home;
import fpp.priangan.fujicon.angkot.rute.Home2;

public class ViewPagerAdapter2 extends PagerAdapter {
	// Declare Variables


	Context context;
	String[] no;
	String[] warna;
	LayoutInflater inflater;
    int pos=3;
	public ViewPagerAdapter2(Context context, String[] no, String[] warna) {
		this.context = context;
		this.no=no;
		this.warna=warna;

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

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		no1 = (TextView) itemView.findViewById(R.id.no);
		warna1 = (TextView) itemView.findViewById(R.id.warna);
//		nama_trayek1 = (TextView) itemView.findViewById(R.id.nama_trayek);
//		lintasan1 = (TextView) itemView.findViewById(R.id.lintasan);
//		harga1 = (TextView) itemView.findViewById(R.id.harga);
//		operasi1 = (TextView) itemView.findViewById(R.id.operasi);



		// Capture position and set to the TextViews
		no1.setText(no[position]);
		warna1.setText(warna[position]);
//		nama_trayek1.setText(nama_trayek[position]);
//		lintasan1.setText(lintasan[position]);
//		harga1.setText(harga[position]);
//		operasi1.setText(operasi[position]);

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

