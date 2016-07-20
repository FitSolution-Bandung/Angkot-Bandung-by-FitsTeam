package fpp.priangan.fujicon.angkot;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Info_angkot extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    String getrec;
    private ListView listView;
    private String names[] = {
            "01A","01B","02","03","04","05","06","07","08","09",
            "10","11A","11B","12","13","14","15","16","17","18",
            "19A","19B","20","21","22","23","24","25","26","27",
            "28","41","30","31","32","33","34","35","36"
    };
    /*,"Ciburial","80","Lembang"*/
    private String desc[] = {
            "Abdulmuis - Cicaheum via Binong",
            "Abdulmuis - Cicaheum via Aceh",
            "Abdulmuis - Dago",
            "Abdulmuis - Ledeng",
            "Abdulmuis - Elang",
            "Cicaheum - Ledeng",
            "Cicaheum - Ciroyom",
            "Cicaheum - Ciwastra",
            "Cicaheum - Cibaduyut",
            "Dago - ST Hall",
            "ST Hall - Sadang Serang",
            "Cimbuleuit - ST Hall via Eykman",
            "Cimbuleuit - ST Hall via Cihampelas",
            "ST Hall - Gedebage",
            "ST Hall - Sarijadi",
            "ST Hall - Gunung Batu",
            "Margahayu - Ledeng",
            "Riung Bandung - Dago",
            "Caringin - Dago",
            "Panghegar - Dipatiukur",
            "Ciroyom - Sarijadi via Sukajadi",
            "Ciroyom - Sarijadi via Setrasari",
            "Bumi Asri - Ciroyom",
            "Cikudapateh - Ciroyom",
            "Buah Batu - Sederhana",
            "Cijerah - Sederhana",
            "Sederhana - Cimindi",
            "Ciwastra - Ujungberung",
            "Cisitu - Tegalega",
            "Cijerah - Ciwastra",
            "Elang - Gedebage",/*31*/
            "Abdulmuis -Mengger",
            "Cicadas - Elang",
            "Antapani - Ciroyom",
            "Cibiru - Cicadas",
            "Sekemirung - Panyileukan",
            "Caringin - Sadang Serang",
            "Cibaduyut - Karang Setra",
            "Cibogo - Elang"

    };

            /*"Ciburial - Ciroyom",
            "Cicaheum - Cileunyi",
            "Lembang - ST Hall"*/


    private Integer imageid[] = {
            R.drawable.abdul_muis_cicaheum_via_binong,
            R.drawable.abdul_muis_cicaheum_via_aceh,
            R.drawable.abdul_muis_dago,
            R.drawable.abdul_muis_ledeng,
            R.drawable.abdul_muis_elang,
            R.drawable.cicaheum_ledeng,
            R.drawable.cicaheum_ciroyom,
            R.drawable.cicaheum_ciwastra,
            R.drawable.cicaheum_cibaduyut,
            R.drawable.dago_sthall,
            R.drawable.sthall_sadang_serang,
            R.drawable.ciumbuleuit_sthall,
            R.drawable.ciumbuleuit_sthall2,
            R.drawable.sthall_gedebage,
            R.drawable.sthall_sarijadi,
            R.drawable.sthall_gunung_batu,
            R.drawable.margahayu_ledeng,
            R.drawable.riungbandung_dago,
            R.drawable.caringin_dago,
            R.drawable.panghegar_dipatiukur,
            R.drawable.ciroyom_sarijadi,
            R.drawable.ciroyom_sarijadi2,
            R.drawable.bumi_asri_ciroyom,
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
    /*,
    R.drawable.ciburial_ciroyom,
    R.drawable.cicaheum_cileunyi,
    R.drawable.lembang_sthall*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_angkot);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.drawable.mockup_logo_bar);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setTitle("");


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowHomeEnabled(true);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.back);
//        actionBar.setDisplayShowHomeEnabled(true);

//        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
//        supportActionBar.setLogo(R.drawable.or);
//        supportActionBar.setDisplayHomeAsUpEnabled(true);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//
//        new ActionBar.LayoutParams(
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.MATCH_PARENT,
//                Gravity.CENTER
//        );
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/


        CustomList customList = new CustomList(this, names, desc, imageid);
        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();

                if (i == 0) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=0;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }
                else if (i == 1) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=1;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 2) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=2;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 3) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=3;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 4) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=4;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 5) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=5;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 6) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=6;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 7) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=7;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 8) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=8;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 9) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=9;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 10) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=10;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 11) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=11;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 12) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=12;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 13) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=13;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 14) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=14;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 15) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=15;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 16) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=16;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 17) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=17;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 18) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=18;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 19) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=19;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 20) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=20;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 21) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=21;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 22) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=22;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 23) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=23;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 24) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=24;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 25) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=25;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 26) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=26;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 27) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=27;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 28) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=28;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 29) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=29;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 30) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=30;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 31) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=31;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 32) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=32;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 33) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=33;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 34) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=34;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 35) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=35;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 36) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=36;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 37) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=37;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i == 38) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=38;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }/*else if (i == 39) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=39;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i ==40) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=40;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }else if (i ==41) {
                    Intent openStep = new Intent(Info_angkot.this, Info_detail_angkot.class);
                    Bundle bundle = new Bundle();
                    int k=41;
                    bundle.putInt("setk", k);
                    openStep.putExtras(bundle);
                    startActivity(openStep);
                }*/
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
////        if(backButtonCount >= 1)
////        {
////            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            startActivity(intent);
////        }
////        else
////        {
////            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
////            backButtonCount++;
////        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_place) {
            Intent openStep = new Intent(this, Tes2.class);
            Bundle bundle = new Bundle();
            getrec="utama";
            bundle.putString("show", getrec);
            openStep.putExtras(bundle);
            startActivity(openStep);
        }else if (id == R.id.nav_camera) {
            Intent openStep = new Intent(this, Tes2.class);
            Bundle bundle = new Bundle();
            getrec="utama1";
            bundle.putString("show", getrec);
            openStep.putExtras(bundle);
            startActivity(openStep);
        } else if (id == R.id.nav_gallery) {
            Intent openStep = new Intent(this, Info_jalan.class);
            startActivity(openStep);
        } else if (id == R.id.nav_slideshow) {
            Intent openStep = new Intent(this, Info_angkot.class);
            startActivity(openStep);
        } else if (id == R.id.nav_manage) {
            Intent openStep = new Intent(this,about.class);
            startActivity(openStep);
        }/*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
