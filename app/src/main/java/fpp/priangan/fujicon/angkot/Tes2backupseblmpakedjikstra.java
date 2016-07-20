package fpp.priangan.fujicon.angkot;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapterRute2;
import fpp.priangan.fujicon.angkot.rute.JSONParser;
import fpp.priangan.fujicon.angkot.sheet.GMapV2GetRouteDirection;


public class Tes2backupseblmpakedjikstra extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener, LocationListener,GoogleMap.OnMapClickListener {
    private ListView lvdt;
    int tr=0;
    ProgressDialog pDialog;
    int lm = 0;
    JSONArray college = null;
    String jarakkm,hargarute,jarakkm2,hargarute2,lokasiawaluser,lokasitujuanuser;
    TextView lat_pergi_akhirr,lat_pergi_awalr,id_awal,id_akhir,angkot_awal,angkot_akhir,t17,t19;
    String latlong, line;
    int id_latlong_pergi_lokasi,id_latlong_pergi_tujuan,id_latlong_pergi_sama,id_latlong_pergi_tujuan2,id_latlong_pergi_tujuan2status,id_latlong_pergi_lokasi2status,id_latlong_pergi_lokasi2,id_latlong_pergi_lokasi3,id_latlong_pergi_lokasi4;
    int id_latlong_pergi_sama1=0;
    double latpergi,longpergi,lattujuan,longtujuan,latpergiarange,longpergiarange,latpergiarange3,longpergiarange3,latpergiarange2,longpergiarange2,latpergiarange1,longpergiarange1;
    String simpul_lokasi,simpul_tujuan,angkot_awalr,angkot_akhirr,sra,sra1,srb,srb1,simpul_awalid,rutepergiid,id_latlong_pergiid,kid,hasilid,id_latlong_pergiid2,hasilid2;
    String statuspergi,statustujuan,idulang,simpul_awalidm,lat_pergiid,long_pergiid;
    String simpul_lokasi2,simpul_tujuan2,angkot_akhirr2,angkot_awalr2,sra2,id_latlong_pergi_lokasi3lat,id_latlong_pergi_lokasi3long;
    double id_latlong_pergi_lokasi3latdouble,id_latlong_pergi_lokasi3longdouble;
    private GoogleMap mMap;
    private GoogleMap mMap1;
    private GoogleApiClient client;
    GMapV2GetRouteDirection v2GetRouteDirection;
    Document document;
    double latitude;
    double longitude;
    private static final String LOG_TAG = "Tes2";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView,mAutocompleteTextView2;
    MarkerOptions markerOptions;
    TextView latt,longg,idid,latlonglokasi,latlongtujuan;
    LatLng latLngl,latLngt,latLngl1,latLngt1,latLngl2,latLngt2 ;
    String latl1,longl1,latlongl1,latt1,longtt1,latlongt1;
    String latl2,longl2,latlongl2,latt2,longtt2,latlongt2;
    String latl3,longl3,latlongl3,latt3,longtt3,latlongt3;
    double[] lat1 ;
    double[] long1;
    int posi;
    String nmank;
    double[] long_pergi,lat_pergi;
    String[] nama_angkot2;
    String nama_angkot,nama_angkot3,hasilfilter;
    double lat_pergi_awal,lat_pergi_akhir;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(-6.917464, 107.619123), new LatLng(-6.916887, 107.618217));
    ImageView delete,delete2;
    BottomSheetLayout bottomSheetLayout;
    TextView kmm,hrg,kk;
    int id_latlong_pergi1,id_latlong_pergi2;
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] no;
    String[] warna;
    String[] nama_trayek;
    String[] lintasan;
    String[] harga;
    String[] operasi;
    //int[] gambar;
    TextView nom;
    ArrayList<String> tampung= new ArrayList<String>();
    ArrayList<String> datameter = new ArrayList<String>();
    ArrayList<String> datajalanawal1 = new ArrayList<String>();
    ArrayList<String> datajalanawal2 = new ArrayList<String>();
    ArrayList<String> datajalanakhir1 = new ArrayList<String>();
    ArrayList<String> datajalanakhir2 = new ArrayList<String>();
    ArrayList<String> filterAddress1 = new ArrayList<String>();
    ArrayList<String> filterAddress3 = new ArrayList<String>();
    ArrayList<String> jarak2 = new ArrayList<String>();
    //String []filterAddress = new String[100];
    //String []filterAddress2 = new String[100];
//
    //  String []jarak = new String[100];
    int jkr;
    String hj;
    int jkr2;
    String hj2;
    ArrayList<String> datajalantampung = new ArrayList<String>();
    String simpul1,simpul2;
    InputMethodManager imm;

    public String []filterAddress = new String[3];
    public String []filterAddress2 = new String[3];

    public String []jarak = new String[3];

    public Integer imageid[] = {
//            R.drawable.or,
            R.drawable.jma1,
            R.drawable.ank1,
            R.drawable.amj1
    };

    public String []filterAddress6 = new String[4];
    public String []filterAddress7 = new String[4];

    public String []jarak7 = new String[4];

    public Integer imageid7[] = {
//            R.drawable.or,
            R.drawable.jma1,
            R.drawable.ank1,
            R.drawable.pindah1,
            R.drawable.amj1
    };
    FloatingActionButton fab3,fab4,fab5;


    int[] gambar=new int[]{
            R.drawable.abdul_muis_cicaheum_via_aceh,
            R.drawable.abdul_muis_cicaheum_via_binong,
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
            R.drawable.sthall_gedebage,
            R.drawable.sthall_sarijadi,
            R.drawable.sthall_gunung_batu,
            R.drawable.margahayu_ledeng,
            R.drawable.riungbandung_dago,
            R.drawable.caringin_dago,
            R.drawable.panghegar_dipatiukur,
            R.drawable.ciroyom_sarijadi,
            R.drawable.bumi_asri_ciroyom,
            R.drawable.cikudapateuh_ciroyom,
            R.drawable.buah_batu_sederhana,
            R.drawable.cijerah_sederhana,
            R.drawable.cimindi_sederhana,
            R.drawable.cisitu_tegalega,
            R.drawable.cijerah_ciwastra,
            R.drawable.cicadas_elang,
            R.drawable.antapani_ciroyom,
            R.drawable.cibiru_cicadas,
            R.drawable.sekemirung_panyileukan_dago_gedebage,
            R.drawable.caringin_sadang_serang,
            R.drawable.cibaduyut_karang_setra,
            R.drawable.cibogo_elang
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tes2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayShowHomeEnabled(true);

        //setContentView(R.layout.custom_viewsheet);
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mGoogleApiClient = new GoogleApiClient.Builder(Tes2backupseblmpakedjikstra.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();


        viewPager = (ViewPager) findViewById(R.id.pager);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                TextView textView14 = (TextView) findViewById(R.id.textView14);
                TextView textView15 = (TextView) findViewById(R.id.textView15);
                TextView textView19 = (TextView) findViewById(R.id.textView19);
                Button satu = (Button) findViewById(R.id.button);
                Button dua = (Button) findViewById(R.id.button2);
                Button tiga = (Button) findViewById(R.id.button3);
                int ank = 0;

                if (ank == 0) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.GONE);
                    tiga.setVisibility(View.GONE);
                } else if (ank == 1) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.GONE);
                } else if (ank == 2) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.VISIBLE);
                }

                String names[] = {
                        "01B", "01A", "02", "03"
                };
                String desc[] = {
                        "Abdulmuis - Cicaheum via Aceh",
                        "Abdulmuis - Cicaheum via Binong",
                        "Abdulmuis - Dago",
                        "Abdulmuis - Ledeng"

                };


                textView14.setText("Nama Trayek :"+nama_angkot);
                textView15.setText("Jarak :"+jarakkm);
                textView19.setText("Ongkos :"+hargarute);
                ////////////
                if(nama_angkot.equals("ST Hall - Sarijadi")){
                    posi=13;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cikudapateh - Ciroyom")){
                    posi=21;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cimbuleuit - ST Hall")){
                    posi=11;
                    nmank="11";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Sederhana - Buah Batu")){
                    posi=22;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Margahayu - Ledeng")){
                    posi=15;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Sadang Serang")){
                    posi=31;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Dago")){
                    posi=17;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Bumi Asri - Ciroyom")){
                    posi=20;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Ciroyom - Sarijadi")){
                    posi=19;
                    nmank="19";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cisitu - Tegalega")){
                    posi=25;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cijerah - Ciwastra")){
                    posi=26;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibiru - Cicadas")){
                    posi=29;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibaduyut - Karang Setra")){
                    posi=32;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibogo - Elang")){
                    posi=33;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Antapani - Ciroyom")){
                    posi=28;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }
                ///////////////
                CustomListRute customList = new CustomListRute(Tes2backupseblmpakedjikstra.this, filterAddress, filterAddress2, imageid,jarak);
                lvdt = (ListView) findViewById(R.id.lvdt);
                lvdt.setAdapter(customList);
                lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ///  Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();

                        if (i == 0) {
                            //bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                            bottomSheetLayout.dismissSheet();
                            //LatLng user = new LatLng(latitude, longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(latLngl));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    latLngl, 15));
//                            CustomListRute customList = new CustomListRute(Tes2.this, filterAddress, filterAddress2, imageid);
//                            lvdt = (ListView) findViewById(R.id.lvdt);
//                            lvdt.setAdapter(customList);
                        } else if (i == 1) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl1.latitude, latLngl1.longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        } else if (i == 2) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl2.latitude, latLngl2.longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        }
                    }
                });
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                TextView textView14 = (TextView) findViewById(R.id.textView14);
                TextView textView15 = (TextView) findViewById(R.id.textView15);
                TextView textView19 = (TextView) findViewById(R.id.textView19);
                Button satu = (Button) findViewById(R.id.button);
                Button dua = (Button) findViewById(R.id.button2);
                Button tiga = (Button) findViewById(R.id.button3);
                int ank = 0;

                if (ank == 0) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.GONE);
                    tiga.setVisibility(View.GONE);
                } else if (ank == 1) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.GONE);
                } else if (ank == 2) {
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.VISIBLE);
                }

                String names[] = {
                        "01B", "01A", "02", "03"
                };
                String desc[] = {
                        "Abdulmuis - Cicaheum via Aceh",
                        "Abdulmuis - Cicaheum via Binong",
                        "Abdulmuis - Dago",
                        "Abdulmuis - Ledeng"

                };


                textView14.setText("Nama Trayek :"+nama_angkot);
                textView15.setText("Jarak :"+jarakkm);
                textView19.setText("Ongkos :"+hargarute);
                ////////////
                if(nama_angkot.equals("ST Hall - Sarijadi")){
                    posi=13;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cikudapateh - Ciroyom")){
                    posi=21;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cimbuleuit - ST Hall")){
                    posi=11;
                    nmank="11";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Sederhana - Buah Batu")){
                    posi=22;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Margahayu - Ledeng")){
                    posi=15;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Sadang Serang")){
                    posi=31;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Dago")){
                    posi=17;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Bumi Asri - Ciroyom")){
                    posi=20;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Ciroyom - Sarijadi")){
                    posi=19;
                    nmank="19";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cisitu - Tegalega")){
                    posi=25;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cijerah - Ciwastra")){
                    posi=26;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibiru - Cicadas")){
                    posi=29;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibaduyut - Karang Setra")){
                    posi=32;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibogo - Elang")){
                    posi=33;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Antapani - Ciroyom")){
                    posi=28;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }
                ///////////////
//                String klj=id_latlong_pergi_lokasi3lat+","+id_latlong_pergi_lokasi3long;
//                new GeocoderTurun().execute(klj);
                CustomListRute2 customList = new CustomListRute2(Tes2backupseblmpakedjikstra.this, filterAddress6 , filterAddress7, imageid7,jarak7);
                lvdt = (ListView) findViewById(R.id.lvdt);
                lvdt.setAdapter(customList);
                lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ///  Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();

                        if (i == 0) {
                            //bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                            bottomSheetLayout.dismissSheet();
                            //LatLng user = new LatLng(latitude, longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(latLngl));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    latLngl, 15));
//                            CustomListRute customList = new CustomListRute(Tes2.this, filterAddress, filterAddress2, imageid);
//                            lvdt = (ListView) findViewById(R.id.lvdt);
//                            lvdt.setAdapter(customList);
                        } else if (i == 1) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl1.latitude, latLngl1.longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        } else if (i == 2) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl2.latitude, latLngl2.longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        }
                    }
                });
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tr==0){

                    fab4.setImageResource(R.drawable.ijo1);
                    mMap.setTrafficEnabled(true);
                    tr=tr+1;
                }else if(tr==1){
                    fab4.setImageResource(R.drawable.merah1);
                    mMap.setTrafficEnabled(false);
                    tr=tr-1;
                }
            }
        });
        ///////////////////////

        mAutocompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        latt = (TextView) findViewById(R.id.latt);
        longg = (TextView) findViewById(R.id.longg);
        idid = (TextView) findViewById(R.id.idid);
        mAutocompleteTextView.setThreshold(0);
        mAutocompleteTextView2.setThreshold(0);

//        mAutocompleteTextView.setTextColor(Color.GREEN);
//        mAutocompleteTextView2.setTextColor(Color.WHITE);
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mAutocompleteTextView2.setOnItemClickListener(mAutocompleteClickListener2);
//        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
//                BOUNDS_MOUNTAIN_VIEW, null);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this,android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
        mAutocompleteTextView2.setAdapter(mPlaceArrayAdapter);
        delete=(ImageView)findViewById(R.id.cross);
        delete2=(ImageView)findViewById(R.id.cross2);
        delete2.setOnClickListener(this);
        delete.setOnClickListener(this);

        lat_pergi_awalr=(TextView) findViewById(R.id.lat_pergi_awal);
        lat_pergi_akhirr=(TextView) findViewById(R.id.lat_pergi_akhir);
        id_awal=(TextView) findViewById(R.id.id_awal);
        id_akhir=(TextView) findViewById(R.id.id_akhir);
        angkot_awal=(TextView) findViewById(R.id.angkot_awal);
        angkot_akhir=(TextView) findViewById(R.id.angkot_akhir);
        //////////////map
        CekGPS();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_rute_angkot);
        mMap = mapFragment.getMap();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
//        mMap.setTrafficEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        mMap.setMyLocationEnabled(true);
        //mapFragment.getMapAsync(this);
        if(latitude!=0 && longitude !=0){
            Toast.makeText(getApplicationContext(), "Latitude : "+latitude+" Longitude : "+longitude, Toast.LENGTH_LONG).show();
            latt.setText(String.valueOf(latitude));
            longg.setText(String.valueOf(longitude));
        }
        /// zoom();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        zoom();
        mMap.setOnMapClickListener(this);
        mAutocompleteTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                idid.setText("0");
            }
        });
        mAutocompleteTextView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                idid.setText("1");
            }
        });

        mAutocompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                    idid.setText("0");
                }
            }
        });
        mAutocompleteTextView2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                    idid.setText("1");
                }
            }
        });
    }


    ////////////////edit1
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);

            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);

        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();
            String lokasi=mAutocompleteTextView.getText().toString();
            String tujuan=mAutocompleteTextView2.getText().toString();

            if(lokasi!=null && !lokasi.equals("")&&tujuan!=null && !tujuan.equals("")){
                mMap.clear();
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }

            imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
        }
    };
    ///////////////////////////////////////////////edit1

    //////////////////////////////////////////////edit2
    private AdapterView.OnItemClickListener mAutocompleteClickListener2
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback1);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
//            if(position==0)
//            {
//                mAutocompleteTextView.setTextColor(Color.parseColor("#999999"));
//
//            }
        }
    };
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback1
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();
            String lokasi=mAutocompleteTextView.getText().toString();
            String tujuan=mAutocompleteTextView2.getText().toString();

            if(lokasi!=null && !lokasi.equals("") && tujuan!=null && !tujuan.equals("")){
                mMap.clear();
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }else if(lokasi.equals("") && tujuan!=null && !tujuan.equals("")){
                String k = String.valueOf(latitude + "," + longitude);
                mMap.clear();
                new GeocoderLokasi().execute(k);
                new GeocoderTujuan().execute(tujuan);
            }
            imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
        }
    };
    //////////////////////////////////////////////edit2


    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }

    @Override
    public void onClick(View v) {
        if(v==delete){
            mAutocompleteTextView.setText("");
//            mMap.setTrafficEnabled(true);
        }else if(v==delete2){
//            mMap.setTrafficEnabled(false);
            mAutocompleteTextView2.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.settings:

            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }
    ////map


    @Override
    public void onLocationChanged(Location location) {
        latitude= location.getLatitude();
        longitude=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public void zoom(){
        LatLng user = new LatLng(latitude, longitude);
        String nama="Lokasi anda";
        mMap.addMarker(new MarkerOptions()
                .position(user)
                .title(nama)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                user, 15));
    }

    @Override
    public void onMapClick(LatLng latLng) {

        imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
        String filterAddress = "";
        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses =
                    geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
           /* LatLng nm = new LatLng(latLng.latitude, latLng.longitude);
            markerOptions.position(nm);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.or));
            mMap.addMarker(markerOptions);*/
            if (addresses.size() > 0) {

                for (int i = 0; i <2;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                    filterAddress += addresses.get(0).getAddressLine(i) + " ";


                if(idid.getText().equals("1")) {
                    mAutocompleteTextView2.setText(filterAddress);
                    String lokasi=mAutocompleteTextView.getText().toString();
                    String tujuan=mAutocompleteTextView2.getText().toString();

                    if (lokasi.equals("") && !tujuan.equals("")) {
                        String k = String.valueOf(latitude + "," + longitude);
                        new GeocoderLokasi().execute(k);
                        new GeocoderTujuan().execute(tujuan);

                    }else if (!lokasi.equals("") && !tujuan.equals("")) {
                        new GeocoderLokasi().execute(lokasi);
                        new GeocoderTujuan().execute(tujuan);


                    }
                }else if(idid.getText().equals("0")){
                    mAutocompleteTextView.setText(filterAddress);
                    String lokasi=mAutocompleteTextView.getText().toString();
                    String tujuan=mAutocompleteTextView2.getText().toString();

                    //Toast.makeText(Tes2.this,lokasi + " " + tujuan, Toast.LENGTH_SHORT).show();
                    if (!lokasi.equals("") && !tujuan.equals("")) {
                        new GeocoderLokasi().execute(lokasi);
                        new GeocoderTujuan().execute(tujuan);

                    }

                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }

        mMap.clear();
        LatLng user = new LatLng(latLng.latitude,latLng.longitude);
        String nama="";
        mMap.addMarker(new MarkerOptions()
                .position(user)
                .title(nama)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));
        //   mMap.clear();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Tes2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://fpp.priangan.fujicon.angkot/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Tes2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://fpp.priangan.fujicon.angkot/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private class GeocoderLokasi extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class

            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {
            mMap.clear();
            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            //googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLngl = new LatLng(address.getLatitude(), address.getLongitude());
                latlonglokasi= (TextView) findViewById(R.id.latlnglokasi);
                latlonglokasi.setText(String.valueOf(address.getLatitude() + "," + address.getLongitude()));
                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLngl);
                markerOptions.title("Titik Awal : " + addressText);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.or));

                mMap.addMarker(markerOptions);
                datajalanawal1.add("Titik Awal : " + addressText);
                //tampung.add(String.valueOf(address.getLatitude()+","+address.getLongitude()));
                latl1=String.valueOf(address.getLatitude());
                longl1=String.valueOf(address.getLongitude());
                latlongl1=latl1+","+longl1;
                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLngl));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLngl, 15));
            }

        }

    }
    private class GeocoderTujuan extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            //googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLngt = new LatLng(address.getLatitude(), address.getLongitude());
                latlongtujuan= (TextView) findViewById(R.id.latlngtujuan);
                latlongtujuan.setText(String.valueOf(address.getLatitude() + "," + address.getLongitude()));
                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLngt);
                markerOptions.title("Tujuan Anda : " + addressText);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.amj));

                mMap.addMarker(markerOptions);
                datajalanakhir2.add("Tujuan Anda : " + addressText);
                // tampung.add(String.valueOf(address.getLatitude()+","+address.getLongitude()));
                latt1=String.valueOf(address.getLatitude());
                longtt1=String.valueOf(address.getLongitude());
                latlongt1=latt1+","+longtt1;
                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLngl));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLngl, 15));
            }

            hasilid="";
            hasilid2="";
            datameter.clear();
            tampung.clear();
            hasilfilter="";
            id_latlong_pergi_sama1=0;
            id_latlong_pergi_sama=0;
            Arrays.fill(filterAddress, null);
            Arrays.fill(filterAddress2, null);
            Arrays.fill(filterAddress7, null);
            Arrays.fill(filterAddress6, null);
            Arrays.fill(jarak, null);
            new AmbilData1().execute();
            new AmbilData2().execute();




        }

    }


    public class AmbilData1 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Tes2backupseblmpakedjikstra.this);
//            pDialog.setMessage("Loading Data lokasi ...");
            pDialog.setMessage("Loading..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        final String s = latlonglokasi.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://www.angkot.fujicon-japan.com/search_awal.php?lat_awal="+rows[0]+"&long_awal="+rows[1];

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    id_latlong_pergi_lokasi= c.getInt("id_latlong_pergi");
                    angkot_awalr= c.getString("id_rute_pergi_angkot");
                    latpergi = c.getDouble("lat_pergi");
                    longpergi = c.getDouble("long_pergi");
                    statuspergi=c.getString("status");
                    simpul_lokasi=c.getString("simpul");
                }

            } catch (JSONException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pDialog.dismiss();
         //   Toast.makeText(getApplicationContext(), "w"+simpul_lokasi, Toast.LENGTH_LONG).show();
            if (simpul_lokasi == null || simpul_lokasi.equals("")) {
                AlertDialog alertDialog = new AlertDialog.Builder(Tes2backupseblmpakedjikstra.this).create();
                alertDialog.setTitle("Informasi");
                alertDialog.setMessage("Aplikasi ini digunakan khusus Kota Bandung!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } else if (simpul_lokasi != null || !simpul_lokasi.equals("")) {
                try {

                    v2GetRouteDirection = new GMapV2GetRouteDirection();

                    latLngl1 = new LatLng(latpergi, longpergi);
                    lat_pergi_awalr.setText(String.valueOf(id_latlong_pergi_lokasi));
                    angkot_awal.setText(String.valueOf(angkot_awalr));
                    id_awal.setText(String.valueOf(simpul_lokasi));
                    angkot_awalr2 = angkot_awalr;
                    simpul_lokasi2 = simpul_lokasi;
                    // Toast.makeText(getApplicationContext(), "impul_lokasi "+simpul_lokasi, Toast.LENGTH_LONG).show();
                    final String s1 = latlonglokasi.getText().toString();
                    final String ss1 = s1 + ",";
                    String[] rows = ss1.split(",");
                    latLngt1 = new LatLng(Double.parseDouble(rows[0]), Double.parseDouble(rows[1]));
                    // drawMarker(latLngl1);
                    String filterAddress = "";
                    LatLng tambah = new LatLng(latLngl1.latitude, latLngl1.longitude);
                    MarkerOptions options1 = new MarkerOptions();
                    Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses =
                                geoCoder.getFromLocation(latLngl1.latitude, latLngl1.longitude, 1);

                        if (addresses.size() > 0) {
                            for (int i = 0; i < 3; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress += addresses.get(0).getAddressLine(i) + " ";
                        }
                        lokasiawaluser=filterAddress;
                        options1.position(tambah);
                        options1.title(filterAddress);
                        options1.icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.jma));
                        Marker marker = mMap.addMarker(options1);
                        datajalanawal2.add("Menuju : " + filterAddress);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }

                    GetRouteTask1 getRoute = new GetRouteTask1();
                    getRoute.execute();

                }catch(Exception e){
                }

            }
        }
    }
    public class AmbilData2 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        final String s = latlongtujuan.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://www.angkot.fujicon-japan.com/search_awal.php?lat_awal="+rows[0]+"&long_awal="+rows[1];

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    id_latlong_pergi_tujuan= c.getInt("id_latlong_pergi");
                    angkot_akhirr= c.getString("id_rute_pergi_angkot");
                    lattujuan = c.getDouble("lat_pergi");
                    longtujuan = c.getDouble("long_pergi");
                    statustujuan=c.getString("status");
                    simpul_tujuan=c.getString("simpul");
                }

            } catch (JSONException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //  pDialog.dismiss();
            //Toast.makeText(getApplicationContext(), "w2"+simpul_tujuan, Toast.LENGTH_LONG).show();
            if (simpul_tujuan == null || simpul_tujuan.equals("")) {
                AlertDialog alertDialog = new AlertDialog.Builder(Tes2backupseblmpakedjikstra.this).create();
                alertDialog.setTitle("Informasi");
                alertDialog.setMessage("Aplikasi ini digunakan khusus Kota Bandung!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            } else if (simpul_tujuan != null || !simpul_tujuan.equals("")) {
                try {
                    v2GetRouteDirection = new GMapV2GetRouteDirection();
                    latLngl2 = new LatLng(lattujuan, longtujuan);


                    angkot_akhir.setText(String.valueOf(angkot_akhirr));
                    lat_pergi_akhirr.setText(String.valueOf(id_latlong_pergi_tujuan));
                    id_akhir.setText(String.valueOf(simpul_tujuan));
                    angkot_akhirr2 = angkot_akhirr;
                    simpul_tujuan2 = simpul_tujuan;
                    //Toast.makeText(getApplicationContext(),"l"+simpul_tujuan2+""+angkot_akhirr, Toast.LENGTH_LONG).show();
                    final String s1 = latlongtujuan.getText().toString();
                    final String ss1 = s1 + ",";
                    String[] rows = ss1.split(",");
                    latLngt2 = new LatLng(Double.parseDouble(rows[0]), Double.parseDouble(rows[1]));

                    // drawMarker(latLngl2);
                    String filterAddress = "";
                    LatLng tambah = new LatLng(latLngl2.latitude, latLngl2.longitude);
                    MarkerOptions options = new MarkerOptions();
                    Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses =
                                geoCoder.getFromLocation(latLngl2.latitude, latLngl2.longitude, 1);

                        if (addresses.size() > 0) {
                            for (int i = 0; i < 3; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress += addresses.get(0).getAddressLine(i) + " ";
                        }
                        lokasitujuanuser=filterAddress;
                        options.position(tambah);
                        options.title(filterAddress);
                        options.icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ank));
                        Marker marker = mMap.addMarker(options);
                        datajalanakhir1.add("Dari : " + filterAddress);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                    ///CalculationByDistance(latLngl1, latLngt1);
//                new AmbilLine().execute();
//                try {
//                    new AmbilLine().execute();
//                }catch (Exception e){}

                    GetRouteTask2 getRoute = new GetRouteTask2();
                    getRoute.execute();
                } catch (Exception e) {
                }

            }
            // new AmbilLine().execute();

        }
    }

    private class GetRouteTask1 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";

        @Override
        protected String doInBackground(String... urls) {
            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);

            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
            if(latLngl1!=null && !latLngl1.equals("")){
                document = v2GetRouteDirection.getDocument(latLngl1, latLngt1, GMapV2GetRouteDirection.MODE_WALKING);

            }

            response = "Success";
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            /// mMap.clear();
            if(response.equalsIgnoreCase("Success")){
                ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                PolylineOptions rectLine = new PolylineOptions().width(10).color(
                        Color.BLACK);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                // Adding route on the map
                mMap.addPolyline(rectLine);
                markerOptions.position(latLngl1);
                markerOptions.title("1" + id_latlong_pergi_lokasi + "v" + simpul_lokasi2);
                markerOptions.draggable(true);
                //  mMap.addMarker(markerOptions);

                int Radius = 6371;// radius of earth in Km
                double lat1 = latLngl1.latitude;
                double lat2 = latLngt1.latitude;
                double lon1 = latLngl1.longitude;
                double lon2 = latLngt1.longitude;
                double dLat = Math.toRadians(lat2 - lat1);
                double dLon = Math.toRadians(lon2 - lon1);
                double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);
                double c = 2 * Math.asin(Math.sqrt(a));
                double valueResult = Radius * c;
                double km = valueResult / 1;
                DecimalFormat newFormat = new DecimalFormat("####");
                int kmInDec = Integer.valueOf(newFormat.format(km));
                int meterConversion = 1609;
                double meter=valueResult * meterConversion;
                int meterInDec = Integer.valueOf(newFormat.format(meter));
                datameter.add(String.valueOf(meterInDec));
                /// Toast.makeText(getApplicationContext(), "lokasi "+datameter, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
            }

           /* Dialog.dismiss();*/
        }
    }
    private class GetRouteTask2 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";
       /* @Override
        protected void onPreExecute() {
            Dialog = new ProgressDialog(Tes2.this);
            Dialog.setMessage("Loading..");//+latLngl2+latLngt2);
//            Dialog.setMessage("Loading route...")
            Dialog.show();
        }*/

        @Override
        protected String doInBackground(String... urls) {
            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);

            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
            if(latLngl2!=null && !latLngl2.equals("")){
                document = v2GetRouteDirection.getDocument(latLngl2, latLngt2, GMapV2GetRouteDirection.MODE_WALKING);

            }

            response = "Success";
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // mMap.clear();
            try {
                if (response.equalsIgnoreCase("Success")) {
                    ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                    PolylineOptions rectLine = new PolylineOptions().width(10).color(
                            Color.BLACK);

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    // Adding route on the map
                    mMap.addPolyline(rectLine);
                    markerOptions.position(latLngl2);
                    markerOptions.title(id_latlong_pergi_tujuan + "v" + simpul_tujuan2);
                    markerOptions.draggable(true);
                    // mMap.addMarker(markerOptions);

                    int Radius = 6371;// radius of earth in Km
                    double lat1 = latLngl2.latitude;
                    double lat2 = latLngt2.latitude;
                    double lon1 = latLngl2.longitude;
                    double lon2 = latLngt2.longitude;
                    double dLat = Math.toRadians(lat2 - lat1);
                    double dLon = Math.toRadians(lon2 - lon1);
                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                            + Math.cos(Math.toRadians(lat1))
                            * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                            * Math.sin(dLon / 2);
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double valueResult = Radius * c;
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    int meterConversion = 1609;
                    double meter = valueResult * meterConversion;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));
                    datameter.add(String.valueOf(meterInDec));

                   try{
                       new simpulsama().execute();
                   }catch(Exception e){}
                    /*if(angkot_awalr.equals(angkot_akhirr2) && statuspergi.equals(statustujuan)){
                        Toast.makeText(getBaseContext(), "sama"+statuspergi+""+angkot_awalr+" "+statustujuan+""+angkot_akhirr2, Toast.LENGTH_SHORT).show();
                    }
                    else if(angkot_awalr.equals(angkot_akhirr2) && !statuspergi.equals(statustujuan) && id_latlong_pergi_lokasi<id_latlong_pergi_tujuan){
                        Toast.makeText(getBaseContext(), "sama & beda"+statuspergi+""+angkot_awalr+" "+statustujuan+""+angkot_akhirr2+""+id_latlong_pergi_lokasi+"<"+id_latlong_pergi_tujuan, Toast.LENGTH_SHORT).show();
                    }
                    else if(!angkot_awalr.equals(angkot_akhirr2) && !statuspergi.equals(statustujuan)){
                        Toast.makeText(getBaseContext(), "beda & beda"+statuspergi+""+angkot_awalr+" "+statustujuan+""+angkot_akhirr2, Toast.LENGTH_SHORT).show();
                    }
                    else if(!angkot_awalr.equals(angkot_akhirr2) && statuspergi.equals(statustujuan)){
                        Toast.makeText(getBaseContext(), "beda s& beda"+statuspergi+""+angkot_awalr+" "+statustujuan+""+angkot_akhirr2, Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(getBaseContext(), "samabeda"+statuspergi+""+angkot_awalr+" "+statustujuan+""+angkot_akhirr2+""+id_latlong_pergi_lokasi+"<"+id_latlong_pergi_tujuan, Toast.LENGTH_SHORT).show();
*/

                } else {
                    Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                }

//                Dialog.dismiss();
            }catch (Exception e){

            }
        }
    }
    public class simpulsama extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        ;
        @Override
        protected String doInBackground(String... arg0) {
            String url;
//            url = "http://115.178.53.63:8010/app_angkot/simpulsama.php?simpul1=,48-44&simpul2=19-11";

//            if(simpul_lokasi.equals("")){
//                Toast.makeText(getApplicationContext(), "gal", Toast.LENGTH_LONG).show();
//            }
            //else{
                jkr = simpul_lokasi.indexOf("-");
                hj = simpul_lokasi.substring(1, jkr);
//            }
//            if(simpul_tujuan.equals("") || simpul_tujuan==null){
//         Toast.makeText(getApplicationContext(), "gal", Toast.LENGTH_LONG).show();
//
//            }else{

                jkr2 = simpul_tujuan.indexOf("-");
                hj2 = simpul_tujuan.substring(1, jkr2);
//            }

            url = "http://www.angkot.fujicon-japan.com/simpulsama.php?simpul1="+hj+"&simpul2="+hj2;
            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    id_latlong_pergi_sama= c.getInt("id_rute_pergi_angkot");
                }

            } catch (JSONException e) {

            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            id_latlong_pergi_sama1=id_latlong_pergi_sama;
            Toast.makeText(getApplicationContext(), hj+""+hj2+" id"+id_latlong_pergi_sama1+"w"+simpul_lokasi+"&simpul2="+simpul_tujuan, Toast.LENGTH_LONG).show();
            if(id_latlong_pergi_sama1==0){
                 Toast.makeText(getApplicationContext(), "dua arrah", Toast.LENGTH_LONG).show();
               ///
               // try{new AmbilLineDua1().execute();}catch(Exception e){}
            }
            else if(id_latlong_pergi_sama1!=0){
                //satu arrah
                try{new AmbilData3().execute();}catch(Exception e){}
                // try{new AmbilLine().execute();}catch(Exception e){}
                Toast.makeText(getApplicationContext(), "satu arrah", Toast.LENGTH_LONG).show();
            }

//            Toast.makeText(getApplicationContext(), simpul_lokasi+" "+simpul_tujuan+" "+id_latlong_pergi_sama1, Toast.LENGTH_LONG).show();
        }
    }
    public class AmbilData3 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        final String s = latlonglokasi.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://www.angkot.fujicon-japan.com/searchawal2.php?lat_awal="+rows[0]+"&long_awal="+rows[1]+"&idrute="+id_latlong_pergi_sama1;

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    id_latlong_pergi_lokasi2= c.getInt("id_latlong_pergi");
                    id_latlong_pergi_lokasi2status= c.getInt("status");

                }

            } catch (JSONException e) {

            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
          /*  pDialog.dismiss();*/

            try {
                  Toast.makeText(getApplicationContext(),rows[0]+"1"+rows[1]+" 2 "+id_latlong_pergi_sama1+" 3 "+ id_latlong_pergi_lokasi2status+"satu arrah"+id_latlong_pergi_lokasi2, Toast.LENGTH_LONG).show();
                try{new AmbilData4().execute();}catch(Exception e){}
            }catch (Exception e){}


        }
    }
    public class AmbilData4 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();



        final String s = latlongtujuan.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://www.angkot.fujicon-japan.com/searchawal2.php?lat_awal="+rows[0]+"&long_awal="+rows[1]+"&idrute="+id_latlong_pergi_sama1;

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    id_latlong_pergi_tujuan2= c.getInt("id_latlong_pergi");
                    id_latlong_pergi_tujuan2status= c.getInt("status");
                }

            } catch (JSONException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //  pDialog.dismiss();

            try{
                if(id_latlong_pergi_lokasi2status==id_latlong_pergi_tujuan2status) {
                    Toast.makeText(getApplicationContext(), "satuine", Toast.LENGTH_LONG).show();

                    new AmbilLine().execute();
                }else if(id_latlong_pergi_lokasi2status!=id_latlong_pergi_tujuan2status) {
                    //  Toast.makeText(getApplicationContext(), "dua arrah2", Toast.LENGTH_LONG).show();
                  //  try{new AmbilLineDua1().execute();}catch(Exception e){}
                }
            }catch(Exception e){}
//            Toast.makeText(getApplicationContext(), id_latlong_pergi_lokasi2+"ada"+id_latlong_pergi_tujuan2, Toast.LENGTH_LONG).show();

        }
    }
    public class AmbilLine extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

        @Override
        protected String doInBackground(String... arg0) {
            String url;

            url = "http://www.angkot.fujicon-japan.com/satu_angkot.php?id_awal="+id_latlong_pergi_lokasi2+"&id_akhir="+id_latlong_pergi_tujuan2;

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);

            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));
                lat_pergi = new double[college .length()];
                long_pergi = new double[college.length()];

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    lat_pergi[i] = college.getJSONObject(i).getDouble("lat_pergi");
                    long_pergi[i] = college.getJSONObject(i).getDouble("long_pergi");
                    nama_angkot=college.getJSONObject(i).getString("nama_rute");
                }

            } catch (JSONException e) {

            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "id_awal="+id_latlong_pergi_lokasi2+"&id_akhir="+id_latlong_pergi_tujuan2+""+nama_angkot, Toast.LENGTH_LONG).show();
            /*pDialog.dismiss();*/
            int gj=1,gn=0,tg=2;
            try{
                Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                int kli=long_pergi.length-1;
                // String []filterAddress = new String[kli];
                //String []filterAddress2 = new String[kli];

                // String []jarak = new String[kli];
               // LatLng []lalat=new LatLng[kli];
               // int sp=0,ep=1;\


                for (int i = 0; i < lat_pergi.length+2; i++) {

                    String lat11= String.valueOf(lat_pergi[gn]);
                    String long11=String.valueOf(long_pergi[gn]);
                    String lat22=String.valueOf(lat_pergi[gj]);
                    String long22=String.valueOf(long_pergi[gj]);
                    String lat33=String.valueOf(lat_pergi[tg]);
                    String long33=String.valueOf(long_pergi[tg]);


                   if(gn!=0) {
                        if ((lat11 != null || !lat11.equals("") && long11 != null || !long11.equals(""))) {
                            double[][] pos = {{lat_pergi[gn - 1], long_pergi[gn - 1]}, {lat_pergi[gj - 1], long_pergi[gj - 1]}};
                            PolylineOptions rectOptions = new PolylineOptions();
                            for (int ii = 0; ii < pos.length; ii++) {
                                rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                            }
                            Polyline polyline = mMap.addPolyline(rectOptions);

                        }
                    }

                   if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))){
                        double[][] pos ={{lat_pergi[gn], long_pergi[gn]},{lat_pergi[gj], long_pergi[gj]}};
                        PolylineOptions rectOptions = new PolylineOptions();
                        for (int ii = 0; ii < pos.length; ii++){
                            rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                        }
                        Polyline polyline = mMap.addPolyline(rectOptions);

                    }
                     if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))&&(lat33!=null || !lat33.equals(""))&& long33!=null || !long33.equals("")){
                        double[][] pos ={{lat_pergi[gn], long_pergi[gn]},{lat_pergi[gj], long_pergi[gj]},{lat_pergi[tg], long_pergi[tg]}};
                        PolylineOptions rectOptions = new PolylineOptions();
                        for (int ii = 0; ii < pos.length; ii++){
                            rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                        }
                        Polyline polyline = mMap.addPolyline(rectOptions);

                    }

                    ////////////////////
                    gj=gj+2;
                    gn=gn+2;
                    tg=tg+2;

                }


            }catch (Exception e){

            }
            Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());


            int kls=lat_pergi.length-1;

            try{


                LatLng markerawal = new LatLng(lat_pergi[0], long_pergi[0]);
                latl2 = String.valueOf(lat_pergi[0]);
                longl2 = String.valueOf(long_pergi[0]);
                latlongl2 = latl2 + "," + longl2;

                int kl = lat_pergi.length - 1;
                int kli = long_pergi.length - 1;
                LatLng markertujuan = new LatLng(lat_pergi[kl], long_pergi[kli]);
                latt2 = String.valueOf(lat_pergi[kl]);
                longtt2 = String.valueOf(long_pergi[kli]);
                latlongt2 = latt2 + "," + longtt2;

                tampung.add(latl1);// posisi awal
                tampung.add(longl1);

                tampung.add(latl2);// posisi awal angkot
                tampung.add(longl2);

                tampung.add(latt1);// posisi akhir
                tampung.add(longtt1);

                tampung.add(latt2);// posisi akhir angkot
                tampung.add(longtt2);

                int tmp = tampung.size()/2;


                int tmp1=tmp-1;
               /* String []filterAddress = new String[tmp1];
                String []filterAddress2 = new String[tmp1];

                String []jarak = new String[tmp1];*/
                int sp1=0,ep2=2,sp3=1,ep4=3;
                for(int il = 0; il <tmp1; il++){

                    int Radius = 6371;// radius of earth in Km
                    double lat1 = Double.parseDouble(tampung.get(sp1));
                    double lat2 = Double.parseDouble(tampung.get(ep2));
                    double lon1 = Double.parseDouble(tampung.get(sp3));
                    double lon2 = Double.parseDouble(tampung.get(ep4));
                    double dLat = Math.toRadians(lat2 - lat1);
                    double dLon = Math.toRadians(lon2 - lon1);
                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                            + Math.cos(Math.toRadians(lat1))
                            * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                            * Math.sin(dLon / 2);
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double valueResult = Radius * c;
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    int meterConversion = 1609;
                    double meter=valueResult * meterConversion;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));
                    jarak[il]= String.valueOf(meterInDec);

                    List<Address> addresses =geoCoder.getFromLocation(Double.parseDouble(tampung.get(sp1)), Double.parseDouble(tampung.get(sp3)), 1);
                    List<Address> addresses2 =geoCoder.getFromLocation(Double.parseDouble(tampung.get(ep2)), Double.parseDouble(tampung.get(ep4)), 1);
                    if (addresses.size() > 0) {
                        for (int i = 0; i <1;i++)// addresses.get(0).getMaxAddressLineIndex(); i++)
                            if(i==0){
                                filterAddress[il] += addresses.get(0).getAddressLine(i) + "\n";
                            }else{
                                filterAddress[il] += addresses.get(0).getAddressLine(i) + " ";
                            }

                    }
                    if (addresses2.size() > 0) {
                        for (int i = 0; i <1;i++)// addresses2.get(0).getMaxAddressLineIndex(); i++)

                            if(i==0){
                                filterAddress2[il] += addresses2.get(0).getAddressLine(i) + "\n";
                            }else{
                                filterAddress2[il] += addresses2.get(0).getAddressLine(i) + " ";
                            }
                    }


                    sp1=sp1+2;
                    ep2=ep2+2;
                    sp3=sp3+2;
                    ep4=ep4+2;
                    // Toast.makeText(getApplicationContext(), filterAddress[il]+" ke "+filterAddress2[il]+" "+tmp+ "d" + tampung, Toast.LENGTH_LONG).show();

                }
                CalculationByDistance(markerawal,markertujuan);
                /*String[] nama_trayek = new String[]{datajalanawal1.get(0) + " " + datajalanawal2.get(0) + " Sejauh " + datameter.get(0) + " Meter ", datajalanakhir1.get(0) + " " + datajalanakhir2.get(0) + " Sejauh " + datameter.get(1) + " meter"};
                StringBuilder phoneNumber = new StringBuilder();
                phoneNumber.append("Naik Jurusan "+nama_angkot+"\n" +"Jarak :"+jarakkm+"\n" +"Ongkos :"+hargarute+"\n"+
                        "Dari :"+filterAddress[0]);
                filterAddress[0] = phoneNumber.toString();*/
                String[] array = new String[nama_trayek.length + filterAddress.length];
                adapter = new ViewPagerAdapterRute2(Tes2backupseblmpakedjikstra.this, filterAddress , filterAddress2,jarak);
//		// Binds the Adapter to the ViewPager
                viewPager.setAdapter(adapter);



                ///////////////////////
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                TextView textView14= (TextView) findViewById(R.id.textView14);
                TextView textView15= (TextView) findViewById(R.id.textView15);
                TextView textView19= (TextView) findViewById(R.id.textView19);
                Button satu= (Button) findViewById(R.id.button);
                Button dua= (Button) findViewById(R.id.button2);
                Button tiga= (Button) findViewById(R.id.button3);
                // ListView lvdt= (ListView) findViewById(R.id.lvdt);
                int ank=0;

                if(ank==0){
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.GONE);
                    tiga.setVisibility(View.GONE);
                }else if(ank==1){
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.GONE);
                }else if(ank==2){
                    satu.setVisibility(View.VISIBLE);
                    dua.setVisibility(View.VISIBLE);
                    tiga.setVisibility(View.VISIBLE);
                }



//                satu.setBackgroundResource(gambar[Integer.parseInt(posi)]);
//                satu.setText(no[Integer.parseInt(posi)]);
                textView14.setText("Nama Trayek :"+nama_angkot);
                textView15.setText("Jarak :"+jarakkm);
                textView19.setText("Ongkos :" + hargarute);
                String names[] = {
                        "01B","01A","02","03"
                };
                String desc[] = {
                        "Abdulmuis - Cicaheum via Aceh",
                        "Abdulmuis - Cicaheum via Binong",
                        "Abdulmuis - Dago",
                        "Abdulmuis - Ledeng"

                };

                ////////////
                if(nama_angkot.equals("ST Hall - Sarijadi")){
                    posi=13;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cikudapateh - Ciroyom")){
                    posi=21;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cimbuleuit - ST Hall")){
                    posi=11;
                    nmank="11";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Sederhana - Buah Batu")){
                    posi=22;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Margahayu - Ledeng")){
                    posi=15;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Sadang Serang")){
                    posi=31;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Caringin - Dago")){
                    posi=17;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Bumi Asri - Ciroyom")){
                    posi=20;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Ciroyom - Sarijadi")){
                    posi=19;
                    nmank="19";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cisitu - Tegalega")){
                    posi=25;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cijerah - Ciwastra")){
                    posi=26;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibiru - Cicadas")){
                    posi=29;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibaduyut - Karang Setra")){
                    posi=32;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Cibogo - Elang")){
                    posi=33;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }else if(nama_angkot.equals("Antapani - Ciroyom")){
                    posi=28;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
                    satu.setText(nmank);
                }
                ///////////////
//                Integer imageid[] = {
//                        R.drawable.or,
//                        R.drawable.jma,
//                        R.drawable.ank,
//                        R.drawable.amj
//                };
                CustomListRute customList = new CustomListRute(Tes2backupseblmpakedjikstra.this, filterAddress , filterAddress2, imageid,jarak);
                lvdt = (ListView) findViewById(R.id.lvdt);
                lvdt.setAdapter(customList);
                lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ///  Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();

                        if (i == 0) {
//                            bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
//                            CustomListRute customList = new CustomListRute(Tes2.this, filterAddress, filterAddress2, imageid);
//                            lvdt = (ListView) findViewById(R.id.lvdt);
//                            lvdt.setAdapter(customList);
                            bottomSheetLayout.dismissSheet();
                            //LatLng user = new LatLng(latitude, longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(latLngl));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    latLngl, 15));
                        } else if (i == 1) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl1.latitude, latLngl1.longitude);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        } else if (i == 2) {
                            bottomSheetLayout.dismissSheet();
                            LatLng user = new LatLng(latLngl2.latitude, latLngl2.longitude);
//                            String nama = "Lokasi anda";
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(user));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    user, 15));
                        }
                    }
                });
                ///////////////////////
            }catch (Exception e){

            }
            fab5.setVisibility(View.GONE);
            fab3.setVisibility(View.VISIBLE);
        }
    }
    public void CekGPS() {
        try {

        /* pengecekan GPS hidup / tidak */
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Info");
                builder.setMessage("Anda akan mengaktifkan GPS?");

                builder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent i = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(i);
                            }
                        });

                builder.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.dismiss();
                            }
                        });
                builder.create().show();

            }

        } catch (Exception e) {

        }

        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getBaseContext());

// menampilkan status google play service
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
                    requestCode);
            dialog.show();

        } else {
            // Google Play Services tersedia

            try {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                // Membuat kriteria untuk penampungan provider
                Criteria criteria = new Criteria();

                // Mencari provider terbaik
                String provider = locationManager.getBestProvider(criteria,
                        true);

                // Mendapatkan lokasi terakhir
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = locationManager
                        .getLastKnownLocation(provider);

                if (location != null) {
                    onLocationChanged(location);
                }
                locationManager.requestLocationUpdates(provider, 5000, 0, this);
            } catch (Exception e) {


            }
        }
    }
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        int meterConversion = 1609;
        double meter=valueResult * meterConversion;
        int meterInDec = Integer.valueOf(newFormat.format(meter));


        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

//
        jarakkm = kmInDec + " KM ";/// + meterInDec + " Meter";
        if(kmInDec==1 ||kmInDec==0){
            hargarute="2000";
            // hrg.setText("Rp.2000");
        }else{
            double ambiljarak=kmInDec-1;
            double jarak_bagi=ambiljarak;
            double hitung=jarak_bagi*500;
            double hasil=hitung+2000;
            //hrg.setText("Rp."+String.valueOf(hasil));
            hargarute="Rp."+String.valueOf(hasil);
        }


        return Radius * c;
    }
}
