package fpp.priangan.fujicon.angkot.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.R;
import fpp.priangan.fujicon.angkot.rute.Home;
import fpp.priangan.fujicon.angkot.rute.Home2;

public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables


	Context context;
	String[] no;
	//String[] warna;
	String[] nama_trayek;
	String[] lintasan;
	String[] lintasan2;
	//String[] harga;
	//String[] operasi;
	int[] gambar;
	LayoutInflater inflater;
    int pos=3;
	public ViewPagerAdapter(Context context, String[] no,String[] nama_trayek,String[] lintasan,String[] lintasan2, int[] gambar) {
		this.context = context;
		this.no=no;
		//this.warna=warna;
		this.nama_trayek=nama_trayek;
		this.lintasan=lintasan;
		this.lintasan2=lintasan2;
		//this.harga=harga;
		//this.operasi=operasi;
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
		TextView lintasan1,ln1,ln2;
		final TextView harga1;
		TextView operasi1;
		ImageView gambar1,rt1,rt2;
		Button rute1,rute2;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		no1 = (TextView) itemView.findViewById(R.id.no);
		//warna1 = (TextView) itemView.findViewById(R.id.warna);
		nama_trayek1 = (TextView) itemView.findViewById(R.id.nama_trayek);
		lintasan1 = (TextView) itemView.findViewById(R.id.lintasan);
		//harga1 = (TextView) itemView.findViewById(R.id.harga);
		//operasi1 = (TextView) itemView.findViewById(R.id.operasi);
		ln1 = (TextView) itemView.findViewById(R.id.textView21);
		ln2 = (TextView) itemView.findViewById(R.id.textView22);

		rute1 = (Button) itemView.findViewById(R.id.rute1);
		rute2 = (Button) itemView.findViewById(R.id.rute2);
		rt1 = (ImageView) itemView.findViewById(R.id.imageView4);
		rt2 = (ImageView) itemView.findViewById(R.id.imageView5);
		// Capture position and set to the TextViews
		no1.setText(no[position]);
		//warna1.setText(warna[position]);
		nama_trayek1.setText(nama_trayek[position]);
		lintasan1.setText(lintasan[position]);
		ln1.setText(Html.fromHtml(lintasan[position]));

		ln2.setText(Html.fromHtml(lintasan2[position]));
		//harga1.setText(harga[position]);
		//operasi1.setText(operasi[position]);

		// Locate the ImageView in viewpager_item.xml
		gambar1 = (ImageView) itemView.findViewById(R.id.gambar);
		// Capture position and set to the ImageView
		gambar1.setImageResource(gambar[position]);

		rt1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent openStep = new Intent(context, Home.class);
				Bundle bundle = new Bundle();
				String k = no1.getText().toString();
				String s="0";
				bundle.putString("setk", k);
				bundle.putString("setstatus", s);
				openStep.putExtras(bundle);
				context.startActivity(openStep);

//				Intent intent= new Intent(context, Home.class);
//				//intent.putextra("your_extra","your_class_value");
//				context.startActivity(intent);
			}
		});
		rt2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				/*Intent openStep = new Intent(context, Home2.class);
				Bundle bundle = new Bundle();
				String k=no1.getText().toString();
				String s="1";
				bundle.putString("setk", k);
				bundle.putString("setstatus", s);
				openStep.putExtras(bundle);
				context.startActivity(openStep);*/

				Intent openStep = new Intent(context, Home.class);
				Bundle bundle = new Bundle();
				String k = no1.getText().toString();
				String s="0";
				bundle.putString("setk", k);
				bundle.putString("setstatus", s);
				openStep.putExtras(bundle);
				context.startActivity(openStep);

//				Intent intent= new Intent(context, Home.class);
//				//intent.putextra("your_extra","your_class_value");
//				context.startActivity(intent);
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

/*public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables


	Context context;
	String[] no;
	String[] warna;
	String[] nama_trayek;
	String[] lintasan;
	String[] lintasan2;
	String[] harga;
	String[] operasi;
	int[] gambar;
	LayoutInflater inflater;
    int pos=3;
	public ViewPagerAdapter(Context context, String[] no, String[] warna,
			String[] nama_trayek,String[] lintasan,String[] lintasan2,String[] harga,String[] operasi, int[] gambar) {
		this.context = context;
		this.no=no;
		this.warna=warna;
		this.nama_trayek=nama_trayek;
		this.lintasan=lintasan;
		this.lintasan2=lintasan2;
		this.harga=harga;
		this.operasi=operasi;
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
		TextView lintasan1,ln1,ln2;
		final TextView harga1;
		TextView operasi1;
		ImageView gambar1,rt1,rt2;
		Button rute1,rute2;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		no1 = (TextView) itemView.findViewById(R.id.no);
		warna1 = (TextView) itemView.findViewById(R.id.warna);
		nama_trayek1 = (TextView) itemView.findViewById(R.id.nama_trayek);
		lintasan1 = (TextView) itemView.findViewById(R.id.lintasan);
		harga1 = (TextView) itemView.findViewById(R.id.harga);
		operasi1 = (TextView) itemView.findViewById(R.id.operasi);
		ln1 = (TextView) itemView.findViewById(R.id.textView21);
		ln2 = (TextView) itemView.findViewById(R.id.textView22);

		rute1 = (Button) itemView.findViewById(R.id.rute1);
		rute2 = (Button) itemView.findViewById(R.id.rute2);
		rt1 = (ImageView) itemView.findViewById(R.id.imageView4);
		rt2 = (ImageView) itemView.findViewById(R.id.imageView5);
		// Capture position and set to the TextViews
		no1.setText(no[position]);
		warna1.setText(warna[position]);
		nama_trayek1.setText(nama_trayek[position]);
		lintasan1.setText(lintasan[position]);
		ln1.setText(Html.fromHtml(lintasan[position]));

		ln2.setText(Html.fromHtml(lintasan2[position]));
		harga1.setText(harga[position]);
		operasi1.setText(operasi[position]);

		// Locate the ImageView in viewpager_item.xml
		gambar1 = (ImageView) itemView.findViewById(R.id.gambar);
		// Capture position and set to the ImageView
		gambar1.setImageResource(gambar[position]);

		rt1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent openStep = new Intent(context, Home.class);
				Bundle bundle = new Bundle();
				String k = no1.getText().toString();
				String s="0";
				bundle.putString("setk", k);
				bundle.putString("setstatus", s);
				openStep.putExtras(bundle);
				context.startActivity(openStep);

//				Intent intent= new Intent(context, Home.class);
//				//intent.putextra("your_extra","your_class_value");
//				context.startActivity(intent);
			}
		});
		rt2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				/*Intent openStep = new Intent(context, Home2.class);
				Bundle bundle = new Bundle();
				String k=no1.getText().toString();
				String s="1";
				bundle.putString("setk", k);
				bundle.putString("setstatus", s);
				openStep.putExtras(bundle);
				context.startActivity(openStep);///

Intent openStep = new Intent(context, Home.class);
Bundle bundle = new Bundle();
String k = no1.getText().toString();
String s="0";
bundle.putString("setk", k);
		bundle.putString("setstatus", s);
		openStep.putExtras(bundle);
		context.startActivity(openStep);

//				Intent intent= new Intent(context, Home.class);
//				//intent.putextra("your_extra","your_class_value");
//				context.startActivity(intent);
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

		}*/