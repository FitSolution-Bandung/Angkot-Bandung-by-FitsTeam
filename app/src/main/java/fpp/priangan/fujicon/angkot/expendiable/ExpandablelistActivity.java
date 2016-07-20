package fpp.priangan.fujicon.angkot.expendiable;
 
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.R;

public class ExpandablelistActivity extends AppCompatActivity {
	final Context context = this;
    private static final String[][] data = {{"ST.Hall - Gedebage"}
            ,{"Abdulmuis - Cicaheum via Aceh","Abdulmuis - Dago","Abdulmuis - Ledeng","Antapani - Ciroyom","ST.Hall - Gedebage","ST.Hall - Sadang Serang"},
            {"Abdulmuis - Elang","Antapani - Ciroyom","Cicaheum - Cibaduyut","Cibiru - Cicadas","Cicadas - Elang","Cicaheum - Ciwastra","Cikudapateuh - Ciroyom","Riung Bandung - Dago","ST.Hall - Gedebage","Margahayu - Ledeng"}
            ,{"Panghegar - Dipatiukur"}
            ,{"Antapani - Ciroyom"}
            ,{"Abdulmuis - Elang","Cibaduyut - Karang Setra","Cikudapateuh - Ciroyom","Cisitu - Tegalega"}
            ,{"Buah Batu - Sederhana","Cikudapateuh - Ciroyom"}
            ,{"Cibaduyut - Karang Setra","Cijerah - Ciwastra","Cikudapateuh - Ciroyom"}
            ,{"Abdulmuis - Ledeng","Buah Batu - Sederhana","Cicadas - Elang","Cijerah - Ciwastra","Cikdapateuh - Ciroyom","ST.Hall - Gedebage"}
            ,{"Caringin - Dago","Caringin - Sadang Serang","Cijerah - Ciwastra"}
            ,{"Cicaheum - Cibaduyut","Cibaduyut - Karang Setra"}
            ,{"Cibiru - Cicadas"}
            ,{"Abdulmuis - Ledeng","Antapani - Ciroyom","Buah Batu - Sederhana","Caringin - Dago","Cicaheum - Ciroyom","Cicaheum - Ledeng","Cisitu - Tegalega","Cimbuleuit - ST.Hall","ST.Hall Gunung Batu","Margahayu - Ledeng"}
            ,{"Abdulmuis - Ledeng","Cicaheum - Ciroyom","Cicaheum - Ledeng","Cimbuleuit - ST.Hall","Margahayu - Ledeng"}
            ,{"Antapani - Ciroyom","Bumi Asri - Ciroyom","Caringin - Dago","Cicaheum - Ciroyom","Cikudapateuh - Ciroyom","Ciroyom - Sarijadi"}
            ,{"Cimbuleuit - ST.Hall"}
            ,{"Buah Batu - Sederhana","Cicadas - Elang","ST.Hall - Gedebage"}
            ,{"Cicaheum - Ciroyom","Riung Bandung - Dago","Panghegar - Dipatiukur"}
            ,{"Abdulmuis - Elang","Cibogo - Elang","Cicadas - Elang"}
            ,{"Cibaduyut - Karang Setra","Cikudapateuh - Ciroyom","Cisitu - Tegalega"}
            ,{"Cicadas - Elang",("Cikudapateuh - Ciroyom"),"ST.Hall - Gedebage"}
            ,{"Cibiru - Cicadas","ST.Hall - Gedebage"}
            ,{"Abdulmuis - Dago","Caringin - Dago","Dago -ST.Hall","Riung Bandung - Dago"}
            ,{"Antapani - Ciroyom","Cicaheum - Cibaduyut","Cibiru - Cicadas","Cicadas - Elang","Cicaheum - Ciwastra","Riung Bandung - Dago","Panghegar - Dipatiukur","Margahayu - Ledeng"}
            ,{"Caringin - Dago"}
            ,{"Abdulmuis - Ledeng","Bumi Asri - Ciroyom","Caringin - Dago","Caringin - Sadangserang","Cibaduyut - Karangsetra","Cibogo - Elang","Cijerah - Sederhana"}
            ,{"Abdulmuis - Cicaheum via Aceh","Abdulmuis - Dago","Abdulmuis - Ledeng","Buah Batu - Sederhan","Cicadas - Elang"}
            ,{"Bumi Asri - Ciroyom","Caringin - Dago","Cibaduyut - Karang Setra","Cikudapateuh - Ciroyom","Cisitu - Tegalega","Cimbuleuit - ST.Hall","ST.Hall - Gedebage","ST.Hall - Gunung Batu","ST.Hall - Sarijadi"}
            ,{"Antapani - Ciroyom","Buah Batu - Sederhana","Caringin - Sadang Serang","Cicadas - Elang","Cimbuleuit - ST.Hall","ST.Hall - Gedebage","ST.Hall - Gunung Batu","Lembang - ST.Hall","ST.Hall - Sadang Serang","ST.Hall - Sarijadi"}
            ,{"Antapani - Ciroyom","Cicaheum - Cibaduyut","Cibiru - Cicadas","Cicadas - Elang","Cicaheum - Ciwastra","Cijerah - Ciwastra","Riung Bandung - Dago","Panghegar - Dipatiukur","Margahayu - Ledeng"}
            ,{"Cijerah - Ciwastra"}
            ,{"Cicadas - Elang","Cikudapateuh - Ciroyom","ST.Hall - Gedebage"}
            ,{"Antapani - Ciroyom","Buah Batu - Sederhana","Bumi Asri - Ciroyom","Caringin Dago","Caringin Sadang Serang","Cibaduyut Karang Setra","Cibogo Elang","Cicadas - Elang","Cicaheum - Ciroyom","Cijerah - Sederhana","Ciroyom - Sarijadi","Cisitu - Tegalega"}
            ,{"Antapani - Ciroyom","Buah Batu - Sederhana","Bumi Asri - Ciroyom","Caringin - Dago","Caringin - Sadang Serang","Cibaduyut - Karang Setra","Cicadas - Elang","Cicaheum - Ciroyom","Cijerah - Sederhana","Ciroyom - Sarijadi","Cisitu - Tegalega","ST.Hall Sarijadi","Cimbuleuit - ST.Hall","ST.Hall - Gedebage","ST.Hall - Gnung Batu","Lembang - ST.Hall","ST.Hall - Sadang Serang"}
            ,{"Abdulmuis - Elang","Cibaduyut - Karang Setra","Cikudapateuh - Ciroyom","Cisitu - Tegalega"}
            ,{"Cijerah - Ciwastra","ST.Hall - Gedebage"}
            ,{"Cibaduyut - Karang Setra","Cijerah - Ciwastra","Cikudapateuh - CIroyom"}
            ,{"Abdulmuis - Dago"}
            ,{"Buah Batu - Sederhana","Cijerah - Sederhan","Ciroyom - Sarjadi","Cimbuleuit - ST.Hall","Margahayu - Ledeng"}
            ,{"Abdulmuis - Elang","Bumi Asri - Ciroyom","Caringin - Dago","Cicaheum - Cibaduyut","Cibiru - Cicadas","Cijerah - Ciwastra","Riung Bandung - Dago","ST.Hall - Gedebage","Margahyu - Ledeng"}
            ,{"Abdulmuis - Cicaheum via Aceh","Abdulmuis - Dago","Abdulmuis - Ledeng","Antapani - Ciroyom","Panghegar - Dipatiukur","ST.Hall - Gedebage","ST.Hall - Sadang Serang"}
            ,{"Panghegar - Dipatiukur"}
            ,{"Abdulmuis - Ledeng","Antapani - Ciroyom","Buah Batu - Sederhana","Bumi Asri - Ciroyom","Caringin - Dago","Caringin - Sadang Serang","Cisitu - Tegalega","Cimbuleuit - ST.Hall","Dago - ST.Hall","Panghegar - Dipatiukur","ST.Hall - Gunung Batu","Margahayu - Ledeng"}

};
    private ExpandableListView expandableListView;
    Button button1;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_jalan);
//        button1 = (Button)findViewById(R.id.btn1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setTitle("");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowHomeEnabled(true);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView1);
        expandableListView.setAdapter(new SampleExpandableListAdapter(context, this, data)); 
        
//        button1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
//		    	String buffer = null;
//		        String output_String = "";
//		        for(int i=0; i<3; i++){
//		        	buffer = settings.getString(String.valueOf((int)i),"false");
//		        	if(buffer.equals("true"))
//		        		output_String += "group " + i + " ";
//		        }
//		        output_String += "is checked";
//				Toast.makeText(ExpandablelistActivity.this, output_String, Toast.LENGTH_SHORT).show();
//			}
//		});
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        if(backButtonCount >= 1)
//        {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
//            backButtonCount++;
//        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_expandablelist, menu);
        return true;
    }

}
