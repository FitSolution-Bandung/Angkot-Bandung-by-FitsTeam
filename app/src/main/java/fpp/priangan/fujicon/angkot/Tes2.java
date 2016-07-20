package fpp.priangan.fujicon.angkot;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.RelativeLayout;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapterRute2;
import fpp.priangan.fujicon.angkot.djikstra2.Get_koordinat_awal_akhir;
import fpp.priangan.fujicon.angkot.djikstra2.GraphToArray;
import fpp.priangan.fujicon.angkot.djikstra2.SQLHelper;
import fpp.priangan.fujicon.angkot.djikstra2.Tambah_simpul;
import fpp.priangan.fujicon.angkot.djikstra2.dijkstra;
import fpp.priangan.fujicon.angkot.expendiable.ExpandablelistActivity;
import fpp.priangan.fujicon.angkot.rute.JSONParser;
import fpp.priangan.fujicon.angkot.sheet.GMapV2GetRouteDirection;


public class Tes2 extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener, LocationListener,NavigationView.OnNavigationItemSelectedListener,GoogleMap.OnMapClickListener {
    ///////////////////
    private Marker lastClicked;
    // DB
    LatLng lokasiuserpertama,lokasitujuanpertama;
    public ProgressDialog dialog;
    boolean doubleBackToExitPressedOnce = false;
    String getrec;
    SQLHelper dbHelper;
    Cursor cursor;
    Button btproses;
    String angkot_,jarak_,tarif_,armada_,warna2_;
    int posis,posis2,posis3,warna_=0xff008000;
    // Google Maps
    GoogleMap googleMap;
    ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();
    ArrayList<LatLng> lat_lng_gbr = new ArrayList<LatLng>();
    JSONArray jArrCoordinates;
    public String __global_endposition = null;
    public String __global_startposition = null;
    public int __global_simpul_awal;
    public int __global_simpul_akhir;
    public String __global_old_simpul_awal = "";
    public String __global_old_simpul_akhir = "";
    public int __global_maxRow0;
    public int __global_maxRow1;
    private String[][] __global_graphArray;
    private LatLng __global_yourCoordinate_exist = null;
    private LatLng __global_yourCoordinate_exist2 = null;
    private LatLng __global_yourCoordinate_exist3 = null;
    LatLng endy,endx,endt,endl;
    String siangkotnya,siangkotnya2,ank1,ank2,ank3,hargaasli,hargaasli2;
    /////////////////////////
    double latUser1,latUser;
    RelativeLayout rl;
    double lngUser1,lngUser;
    double la,lo;
    private ListView lvdt;
    int tr=0;
    ProgressDialog pDialog;
    int lm = 0;
    double klat,klong,jlat,jlong,llat,llong,llat2,llong2;
    JSONArray college = null;
    String jarakkm,hargarute,jarakkm2,hargarute2,lokasiawaluser,lokasitujuanuser,hargarute3;
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
    Document document,document2;
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
    public String []address = new String[3];
    public String []address1 = new String[3];
    public String []address2 = new String[4];
    public String []address3 = new String[4];
    public String []filterAddress = new String[3];
    public String []filterAddress2 = new String[3];
    ArrayList<LatLng> arrayAkirank = new ArrayList<LatLng>();
    public String []jarak = new String[3];
    public String []jarak22 = new String[4];


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
    FloatingActionButton fab3,fab4,fab5,fabgo,fabpr;
    int[] gambar2=new int[]{
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

    int[] gambar=new int[]{
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mockup_logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setTitle("");


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.back);
//        actionBar.setDisplayShowHomeEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //mDrawerToggle.setDrawerIndicatorEnabled(false);
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.your_custom_icon, getActivity().getTheme());
//        mDrawerToggle.setHomeAsUpIndicator(drawable);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
///////////////////////
        btproses=(Button) findViewById(R.id.button4);
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
//        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setAllGesturesEnabled(true);
//        mMap.setTrafficEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        mMap.setMyLocationEnabled(true);
        //mapFragment.getMapAsync(this);
        if(latitude!=0 && longitude !=0){
//            Toast.makeText(getApplicationContext(), "Latitude : "+latitude+" Longitude : "+longitude, Toast.LENGTH_LONG).show();
            latt.setText(String.valueOf(latitude));
            longg.setText(String.valueOf(longitude));
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

// create DB
        dbHelper = new SQLHelper(this);
        try {
            dbHelper.createDataBase();
        }
        catch (Exception ioe) {
            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
        }
        //setContentView(R.layout.custom_viewsheet);
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mGoogleApiClient = new GoogleApiClient.Builder(Tes2.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();


        viewPager = (ViewPager) findViewById(R.id.pager);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fabgo = (FloatingActionButton) findViewById(R.id.fabr);
        fabpr = (FloatingActionButton) findViewById(R.id.myFAB);
        rl= (RelativeLayout) findViewById(R.id.rl);

        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("show");
        if(stuff.equals("utama")){
            fabgo.setVisibility(View.VISIBLE);
            fabpr.setVisibility(View.VISIBLE);
        }else if(stuff.equals("utama1")){
            rl.setVisibility(View.VISIBLE);
            fab4.setVisibility(View.VISIBLE);
//            viewPager.setVisibility(View.VISIBLE);

        }

        fabgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabgo.setVisibility(View.GONE);
                fabpr.setVisibility(View.GONE);
                rl.setVisibility(View.VISIBLE);
                fab4.setVisibility(View.VISIBLE);
            }
        });
        fabpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng user = new LatLng(-6.915315, 107.603846);
                String nama="Lokasi anda";
                mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(nama)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        user, 15));
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
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
                if(nama_angkot.equals("[ST Hall - Sarijadi]")){
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
                CustomListRute customList = new CustomListRute(Tes2.this, filterAddress, filterAddress2, imageid,jarak);
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
                });*/
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));


//                CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                CalculationByDistance3(lokasiuserpertama, __global_yourCoordinate_exist2);
                CalculationByDistance(__global_yourCoordinate_exist2, endl);
//                CalculationByDistance4(endl, endx);
                CalculationByDistance4(endl, lokasitujuanpertama);
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

                if(siangkotnya.equals("Abdulmuis - Cicaheum via Binong")){
                    posi=0;
                    nmank="01A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
                else if(siangkotnya.equals("Abdulmuis - Cicaheum via Aceh")){
                    posi=1;
                    nmank="01B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Dago")){
                    posi=2;
                    nmank="02";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Ledeng")){
                    posi=3;
                    nmank="03";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sadang Serang")){
                    posi=10;
                    nmank="10";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Eykman")){
                    posi=11;
                    nmank="11A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Cihampelas")){
                    posi=12;
                    nmank="11B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gedebage")){
                    posi=13;
                    nmank="12";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                    posi=14;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                    posi=15;
                    nmank="14";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Margahayu - Ledeng")){
                    posi=16;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Riung Bandung - Dago")){
                    posi=17;
                    nmank="16";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Dago")){
                    posi=18;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Panghegar - Dipatiukur")){
                    posi=19;
                    nmank="18";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Sukajadi")){
                    posi=20;
                    nmank="19A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Setrasari")){
                    posi=21;
                    nmank="19B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                    posi=22;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                    posi=23;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                    posi=24;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Sederhana")){
                    posi=25;
                    nmank="23";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sederhana - Cimindi")){
                    posi=26;
                    nmank="24";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciwastra - Ujungberung")){
                    posi=27;
                    nmank="25";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cisitu - Tegalega")){
                    posi=28;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                    posi=29;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Elang - Gedebage")){
                    posi=30;
                    nmank="28";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Mengger")){
                    posi=31;
                    nmank="29";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicadas - Elang")){
                    posi=32;
                    nmank="30";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Antapani - Ciroyom")){
                    posi=33;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibiru - Cicadas")){
                    posi=34;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sekemirung - Panyileukan")){
                    posi=35;
                    nmank="33";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                    posi=36;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                    posi=37;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibogo - Elang")){
                    posi=38;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
//                else{
//                    posi=28;
//                    nmank="31";
//                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
//                }
                textView14.setText("Nama Trayek :" + siangkotnya);
                textView15.setText("Jarak :" + jarakkm);
                textView19.setText("Ongkos :" + hargarute);

                ///adress1
                String filterAddressh = "";
                Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                    if (addresses.size() > 0) {

                        for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                    }
                    address[0] = String.valueOf(filterAddressh);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
//                 String a=String.valueOf(mAutocompleteTextView.getText().toString());
//
//                 address[1] = String.valueOf(a);
//                 address[2] = String.valueOf(a);
//                 address[3] = String.valueOf(a);

//                 /adress2
                String filterAddress2 = "";
                Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                    if (addresses2.size() > 0) {

                        for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                    }

                    address1[0] = String.valueOf(filterAddress2);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
//                 String b=String.valueOf(filterAddress2);
//                 address[1] = String.valueOf(b);
//
                ///adress3
                String filterAddress3= "";
                Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                    if (addresses3.size() > 0) {

                        for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                    }

                    // address[1] = String.valueOf(filterAddress3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
//                 String c=String.valueOf(filterAddress3);
//                 address[2] = String.valueOf(c);
//
//                 ///adress4
                String filterAddress4= "";
                Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses4 = geoCoder4.getFromLocation(latUser1, lngUser1, 1);

                    if (addresses4.size() > 0) {

                        for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                    }

                    address1[1] = String.valueOf(filterAddress4);
                    address[2] = String.valueOf(filterAddress4);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
//                 String d=String.valueOf(filterAddress4);
//                 address[3] = String.valueOf(d);
                address1[2] = String.valueOf(hargarute);
                address[1] = String.valueOf(siangkotnya);
                CustomListRute customList = new CustomListRute(Tes2.this,address , address1, imageid,jarak);
                lvdt = (ListView) findViewById(R.id.lvdt);
                lvdt.setAdapter(customList);

                fab3.setVisibility(View.VISIBLE);
                fab5.setVisibility(View.GONE);
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
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
                CustomListRute2 customList = new CustomListRute2(Tes2.this, filterAddress6 , filterAddress7, imageid7,jarak7);
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
                });*/
                try {
//                 Toast.makeText(getApplicationContext(), __global_yourCoordinate_exist+"1"+__global_yourCoordinate_exist2+"status", Toast.LENGTH_LONG).show();

                  /*  v2GetRouteDirection = new GMapV2GetRouteDirection();

                    GetRouteTask3 getRoute = new GetRouteTask3();
                    getRoute.execute();
                    GetRouteTask4 getRoute1 = new GetRouteTask4();
                    getRoute1.execute();
                    bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));


                    CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                    CalculationByDistance(__global_yourCoordinate_exist2, endl);
                    CalculationByDistance4(endl, endx);
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

                    if(siangkotnya.equals("ST Hall - Gedebage")){
                        posi=12;
                        nmank="12";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                        posi=13;
                        nmank="13";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                        posi=21;
                        nmank="21";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cimbuleuit - ST Hall")){
                        posi=11;
                        nmank="11";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Sederhana - Buah Batu")){
                        posi=22;
                        nmank="22";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Dago - ST Hall")){
                        posi=9;
                        nmank="09";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                        posi=14;
                        nmank="14";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Margahayu - Ledeng")){
                        posi=15;
                        nmank="15";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                        posi=7;
                        nmank="07";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                        posi=31;
                        nmank="34";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Caringin - Dago")){
                        posi=17;
                        nmank="17";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cicaheum - Ledeng]")){
                        posi=5;
                        nmank="05";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                        posi=20;
                        nmank="20";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Ciroyom - Sarijadi")){
                        posi=19;
                        nmank="19";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cisitu - Tegalega")){
                        posi=25;
                        nmank="26";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Abdulmuis - Elang")){
                        posi=4;
                        nmank="04";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                        posi=6;
                        nmank="06";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                        posi=8;
                        nmank="08";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                        posi=26;
                        nmank="27";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cibiru - Cicadas")){
                        posi=29;
                        nmank="32";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                        posi=32;
                        nmank="35";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Cibogo - Elang")){
                        posi=33;
                        nmank="36";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("Antapani - Ciroyom")){
                        posi=28;
                        nmank="31";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("[Abdulmuis - Cicaheum]")){
                        posi=0;
                        nmank="01B";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }
//                else{
//                    posi=28;
//                    nmank="31";
//                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
//                }
                    textView14.setText("Nama Trayek :" + siangkotnya);
                    textView15.setText("Jarak :" + jarakkm);
                    textView19.setText("Ongkos :" + hargarute);

                    ///adress1
                    String filterAddressh = "";
                    Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                        if (addresses.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                        }
                        address[0] = String.valueOf(filterAddressh);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
//                 String a=String.valueOf(mAutocompleteTextView.getText().toString());
//
//                 address[1] = String.valueOf(a);
//                 address[2] = String.valueOf(a);
//                 address[3] = String.valueOf(a);

//                 /adress2
                    String filterAddress2 = "";
                    Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                        if (addresses2.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                        }

                        address1[0] = String.valueOf(filterAddress2);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
//                 String b=String.valueOf(filterAddress2);
//                 address[1] = String.valueOf(b);
//
                    ///adress3
                    String filterAddress3= "";
                    Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                        if (addresses3.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                        }

                        address[1] = String.valueOf(filterAddress3);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
//                 String c=String.valueOf(filterAddress3);
//                 address[2] = String.valueOf(c);
//
//                 ///adress4
                    String filterAddress4= "";
                    Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses4 = geoCoder4.getFromLocation(latUser1, lngUser1, 1);

                        if (addresses4.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                        }

                        address1[1] = String.valueOf(filterAddress4);
                        address[2] = String.valueOf(filterAddress4);
                        address1[2] = String.valueOf(filterAddress4);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
//                 String d=String.valueOf(filterAddress4);
//                 address[3] = String.valueOf(d);

                    CustomListRute customList = new CustomListRute(Tes2.this,address , address1, imageid,jarak);
                    lvdt = (ListView) findViewById(R.id.lvdt);
                    lvdt.setAdapter(customList);

                    fab3.setVisibility(View.VISIBLE);
                    fab5.setVisibility(View.GONE);*/
                    /*bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                    String[] angkot1 = siangkotnya.split(",");
                    String angkott = angkot1[0];

                    CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                    CalculationByDistance(__global_yourCoordinate_exist2, __global_yourCoordinate_exist3);
                    CalculationByDistance4(__global_yourCoordinate_exist3,endl);
                    CalculationByDistance5(endl, endx);
                    TextView textView14= (TextView) findViewById(R.id.textView14);
                    TextView textView15= (TextView) findViewById(R.id.textView15);
                    TextView textView19= (TextView) findViewById(R.id.textView19);
                    Button satu= (Button) findViewById(R.id.button);
                    Button dua= (Button) findViewById(R.id.button2);
                    Button tiga= (Button) findViewById(R.id.button3);
                    // ListView lvdt= (ListView) findViewById(R.id.lvdt);
                    //textView14.setText(angkott);
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


                    if(angkott.equals("[ST Hall - Gedebage")){
                        posi=12;
                        nmank="12";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Sarijadi")){
                        posi=13;
                        nmank="13";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cikudapateh - Ciroyom")){
                        posi=21;
                        nmank="21";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cimbuleuit - ST Hall")){
                        posi=11;
                        nmank="11";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Sederhana - Buah Batu")){
                        posi=22;
                        nmank="22";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Dago - ST Hall")){
                        posi=9;
                        nmank="09";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Gunung Batu")){
                        posi=14;
                        nmank="14";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Margahayu - Ledeng")){
                        posi=15;
                        nmank="15";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("[Abdulmuis - Ledeng")){
                        posi=3;
                        nmank="03";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ciwastra")){
                        posi=7;
                        nmank="07";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Caringin - Sadang Serang")){
                        posi=31;
                        nmank="34";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Caringin - Dago")){
                        posi=17;
                        nmank="17";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ledeng]")){
                        posi=5;
                        nmank="05";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("Bumi Asri - Ciroyom")){
                        posi=20;
                        nmank="20";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Ciroyom - Sarijadi")){
                        posi=19;
                        nmank="19";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cisitu - Tegalega")){
                        posi=25;
                        nmank="26";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Elang")){
                        posi=4;
                        nmank="04";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ciroyom")){
                        posi=6;
                        nmank="06";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Cibaduyut")){
                        posi=8;
                        nmank="08";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cijerah - Ciwastra")){
                        posi=26;
                        nmank="27";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cibiru - Cicadas")){
                        posi=29;
                        nmank="32";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cibaduyut - Karang Setra")){
                        posi=32;
                        nmank="35";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Cibogo - Elang")){
                        posi=33;
                        nmank="36";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Antapani - Ciroyom")){
                        posi=28;
                        nmank="31";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(siangkotnya.equals("[Riung Bandung - Dago")){
                        posi=16;
                        nmank="16";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Cicaheum")){
                        posi=0;
                        nmank="01B";
                        satu.setBackgroundResource(gambar[posi]);
                        satu.setText(nmank);
                    }
//                else{
//                    posi=28;
//                    nmank="31";
//                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
//                }
                    textView14.setText("Nama Trayek :" + angkott+"]");
                    textView15.setText("Jarak :" + jarakkm);
                    textView19.setText("Ongkos :" + hargarute);


                    ///adress1
                    String filterAddressh = "";
                    Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                        if (addresses.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                        }
                        address2[0] = String.valueOf(filterAddressh);
                        //address3[0] = String.valueOf(filterAddressh);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }



//                 /adress2
                    String filterAddress2 = "";
                    Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                        if (addresses2.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                        }

                        // address2[1] = String.valueOf(filterAddress2);
                        address3[0] = String.valueOf(filterAddress2);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }


                    ///adress3
                    String filterAddress3= "";
                    Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                        if (addresses3.size() > 0) {

                            for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                        }

                        address2[1] = String.valueOf(filterAddress3);
                        ///address3[2] = String.valueOf(filterAddress3);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }

                    ///adress4
                    String filterAddress4= "";
                    Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses4 = geoCoder4.getFromLocation(llat2, lngUser1, 1);

                        if (addresses4.size() > 0) {

                            for (int i = 0; i <1;i++)
                                filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                        }

                        // address2[3] = String.valueOf(filterAddress4);
                        address3[1] = String.valueOf(filterAddress4);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                    ///adress5
                    String filterAddress5= "";
                    Geocoder geoCoder5 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses5 = geoCoder5.getFromLocation(latUser1, lngUser1, 1);

                        if (addresses5.size() > 0) {

                            for (int i = 0; i <1;i++)
                                filterAddress5 += addresses5.get(0).getAddressLine(i) + " ";
                        }


                        address2[2] = String.valueOf(filterAddress5);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }

                    address3[2] = "Selanjutnya naik angkot "+siangkotnya2+"\ndengan ongkos sebesar Rp."+hargarute3;
                    address2[3] = "Selanjutnya naik angkot "+siangkotnya2+"\ndengan ongkos sebesar Rp."+hargarute3;
                    address3[3] = "Selanjutnya naik angkot "+siangkotnya2+"\ndengan ongkos sebesar Rp."+hargarute3;


                    CustomListRute2 customList = new CustomListRute2(Tes2.this,address2 , address3, imageid7,jarak22);
                    lvdt = (ListView) findViewById(R.id.lvdt);
                    lvdt.setAdapter(customList);*/
                    bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                    String[] angkot1 = siangkotnya.split(",");
                    String angkott = angkot1[0];

//                    CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                    CalculationByDistance3(lokasiuserpertama, __global_yourCoordinate_exist2);
                    CalculationByDistance(__global_yourCoordinate_exist2, __global_yourCoordinate_exist3);
                    CalculationByDistance4(__global_yourCoordinate_exist3, endl);
//                    CalculationByDistance5(endl, endx);
                    CalculationByDistance5(endl, lokasitujuanpertama);
                    TextView textView14 = (TextView) findViewById(R.id.textView14);
                    TextView textView15 = (TextView) findViewById(R.id.textView15);
                    TextView textView19 = (TextView) findViewById(R.id.textView19);
                    Button satu = (Button) findViewById(R.id.button);
                    Button dua = (Button) findViewById(R.id.button2);
                    Button tiga = (Button) findViewById(R.id.button3);
                    // ListView lvdt= (ListView) findViewById(R.id.lvdt);
                    //textView14.setText(angkott);
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


                    if(angkott.equals("[Abdulmuis - Cicaheum via Binong") || angkott.equals("[Abdulmuis - Cicaheum via Binong]")){
                        posi=0;
                        nmank="01A";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);

                    }
                    else if(angkott.equals("[Abdulmuis - Cicaheum via Aceh") || angkott.equals("[Abdulmuis - Cicaheum via Aceh]")){
                        posi=1;
                        nmank="01B";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Dago") || angkott.equals("[Abdulmuis - Dago]")){
                        posi=2;
                        nmank="02";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Ledeng") || angkott.equals("[Abdulmuis - Ledeng]")){
                        posi=3;
                        nmank="03";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Elang") || angkott.equals("[Abdulmuis - Elang]")){
                        posi=4;
                        nmank="04";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ledeng") || angkott.equals("[Cicaheum - Ledeng]")){
                        posi=5;
                        nmank="05";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ciroyom") || angkott.equals("[Cicaheum - Ciroyom]")){
                        posi=6;
                        nmank="06";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Ciwastra") || angkott.equals("[Cicaheum - Ciwastra]")){
                        posi=7;
                        nmank="07";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cicaheum - Cibaduyut") || angkott.equals("[Cicaheum - Cibaduyut]")){
                        posi=8;
                        nmank="08";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Dago - ST Hall") || angkott.equals("[Dago - ST Hall]")){
                        posi=9;
                        nmank="09";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Sadang Serang") || angkott.equals("[ST Hall - Sadang Serang]")){
                        posi=10;
                        nmank="10";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cimbuleuit - ST Hall via Eykman") || angkott.equals("[Cimbuleuit - ST Hall via Eykman]")){
                        posi=11;
                        nmank="11A";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cimbuleuit - ST Hall via Cihampelas") || angkott.equals("[Cimbuleuit - ST Hall via Cihampelas]")){
                        posi=12;
                        nmank="11B";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Gedebage") || angkott.equals("[ST Hall - Gedebage]")){
                        posi=13;
                        nmank="12";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Sarijadi") || angkott.equals("[ST Hall - Sarijadi]")){
                        posi=14;
                        nmank="13";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[ST Hall - Gunung Batu") || angkott.equals("[ST Hall - Gunung Batu]")){
                        posi=15;
                        nmank="14";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Margahayu - Ledeng") || angkott.equals("[Margahayu - Ledeng]")){
                        posi=16;
                        nmank="15";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Riung Bandung - Dago") || angkott.equals("[Riung Bandung - Dago]")){
                        posi=17;
                        nmank="16";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Caringin - Dago") || angkott.equals("[Caringin - Dago]")){
                        posi=18;
                        nmank="17";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Panghegar - Dipatiukur") || angkott.equals("[Panghegar - Dipatiukur]")){
                        posi=19;
                        nmank="18";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Ciroyom - Sarijadi via Sukajadi") || angkott.equals("[Ciroyom - Sarijadi via Sukajadi]")){
                        posi=20;
                        nmank="19A";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Ciroyom - Sarijadi via Setrasari") || angkott.equals("[Ciroyom - Sarijadi via Setrasari]")){
                        posi=21;
                        nmank="19B";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Bumi Asri - Ciroyom") || angkott.equals("[Bumi Asri - Ciroyom]")){
                        posi=22;
                        nmank="20";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cikudapateh - Ciroyom") || angkott.equals("[Cikudapateh - Ciroyom]")){
                        posi=23;
                        nmank="21";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")){
                        posi=24;
                        nmank="22";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cijerah - Sederhana") || angkott.equals("[Cijerah - Sederhana]")){
                        posi=25;
                        nmank="23";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Sederhana - Cimindi") || angkott.equals("[Sederhana - Cimindi]")){
                        posi=26;
                        nmank="24";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Ciwastra - Ujungberung") || angkott.equals("[Ciwastra - Ujungberung]")){
                        posi=27;
                        nmank="25";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cisitu - Tegalega") || angkott.equals("[Cisitu - Tegalega]")){
                        posi=28;
                        nmank="26";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cijerah - Ciwastra") || angkott.equals("[Cijerah - Ciwastra]")){
                        posi=29;
                        nmank="27";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Elang - Gedebage") || angkott.equals("[Elang - Gedebage]")){
                        posi=30;
                        nmank="28";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Abdulmuis - Mengger") || angkott.equals("[Abdulmuis - Mengger]")){
                        posi=31;
                        nmank="29";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("Cicadas - Elang")){
                        posi=32;
                        nmank="30";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Antapani - Ciroyom") || angkott.equals("[Antapani - Ciroyom]")){
                        posi=33;
                        nmank="31";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cibiru - Cicadas") || angkott.equals("[Cibiru - Cicadas]")){
                        posi=34;
                        nmank="32";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Sekemirung - Panyileukan") || angkott.equals("[Sekemirung - Panyileukan]")){
                        posi=35;
                        nmank="33";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Caringin - Sadang Serang") || angkott.equals("[Caringin - Sadang Serang]")){
                        posi=36;
                        nmank="34";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cibaduyut - Karang Setra") || angkott.equals("[Cibaduyut - Karang Setra]")){
                        posi=37;
                        nmank="35";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }else if(angkott.equals("[Cibogo - Elang") || angkott.equals("[Cibogo - Elang]")){
                        posi=38;
                        nmank="36";
                        satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                    }
//            textView14.setText("Nama Trayek :" + angkott);
                    textView14.setText("Nama Trayek :" + siangkotnya + " -> " + siangkotnya2);
//            textView15.setText("Jarak :" + jarakkm);
                    textView15.setText(" ");
                    textView19.setText("Total Ongkos :" + hargarute);


                    ///adress1
                    String filterAddressh = "";
                    Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                        if (addresses.size() > 0) {

                            for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                        }
                        address2[0] = String.valueOf(filterAddressh);
                        //address3[0] = String.valueOf(filterAddressh);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }


//                 /adress2
                    String filterAddress2 = "";
                    Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                        if (addresses2.size() > 0) {

                            for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                        }

                        // address2[1] = String.valueOf(filterAddress2);
                        address3[0] = String.valueOf(filterAddress2);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }


                    ///adress3
                    String filterAddress3 = "";
                    Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                        if (addresses3.size() > 0) {

                            for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                        }

                        address2[1] = String.valueOf(filterAddress3);
                        ///address3[2] = String.valueOf(filterAddress3);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }

                    ///adress4
                    String filterAddress4 = "";
                    Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses4 = geoCoder4.getFromLocation(llat2, lngUser1, 1);

                        if (addresses4.size() > 0) {

                            for (int i = 0; i < 1; i++)
                                filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                        }

                        // address2[3] = String.valueOf(filterAddress4);
                        address3[1] = String.valueOf(filterAddress4);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                    ///adress5
                    String filterAddress5 = "";
                    Geocoder geoCoder5 = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        List<Address> addresses5 = geoCoder5.getFromLocation(latUser1, lngUser1, 1);

                        if (addresses5.size() > 0) {

                            for (int i = 0; i < 1; i++)
                                filterAddress5 += addresses5.get(0).getAddressLine(i) + " ";
                        }


                        address2[2] = String.valueOf(filterAddress5);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }

                    address3[2] = "Selanjutnya naik Angkot " + siangkotnya2 + "<br/>sejauh <i>" + jarak22[2] + " km</i> \n<br/>Ongkos <i>biasanya sih</i> Rp." + hargarute3 + " (?)";
                    address2[3] = siangkotnya + " <br/>sejauh <i>" + jarak22[1] + " km</i>\n<br/>Ongkos <i>biasanya sih</i> Rp." + hargarute + " (?)";
                    address3[3] = "Selanjutnya naik angkot     " + siangkotnya2 + "\ndengan ongkos sebesar Rp." + hargarute3;


                    CustomListRute2 customList = new CustomListRute2(Tes2.this, address2, address3, imageid7, jarak22);
                    lvdt = (ListView) findViewById(R.id.lvdt);
                    lvdt.setAdapter(customList);

                    fab5.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.GONE);
                } catch (Exception e) {

                }
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tr == 0) {

                    fab4.setImageResource(R.drawable.ijo1);
                    mMap.setTrafficEnabled(true);
                    tr = tr + 1;
                } else if (tr == 1) {
                    fab4.setImageResource(R.drawable.merah1);
                    mMap.setTrafficEnabled(false);
                    tr = tr - 1;
                }
            }
        });

       // zoom();
        mMap.setOnMapClickListener(this);
        mAutocompleteTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                idid.setText("0");
                fab5.setVisibility(View.GONE);
                fab3.setVisibility(View.GONE);
            }
        });
        mAutocompleteTextView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                idid.setText("1");
                fab5.setVisibility(View.GONE);
                fab3.setVisibility(View.GONE);
            }
        });

        mAutocompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                    idid.setText("0");
                    fab5.setVisibility(View.GONE);
                    fab3.setVisibility(View.GONE);
                }
            }
        });
        mAutocompleteTextView2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
                    idid.setText("1");
                    fab5.setVisibility(View.GONE);
                    fab3.setVisibility(View.GONE);
                }
            }
        });

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.915315, 107.603846), 12.0f));



        mAutocompleteTextView2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() == 0) {
                    btproses.setVisibility(View.GONE);
                }
//                else {
//                    button.setEnabled(true);
//                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        btproses.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                lat_lng_gbr.clear();
                dialog = new ProgressDialog(Tes2.this); // this = YourActivity
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Loading. Please wait...");
                dialog.setIndeterminate(true);
//                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String lokasi = mAutocompleteTextView.getText().toString();
                                String tujuan = mAutocompleteTextView2.getText().toString();
                                Geocoder coder = new Geocoder(Tes2.this);
                                try {
                                    ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(lokasi, 50);
                                    for (Address add : adresses) {
                                        lngUser = add.getLongitude();
                                        latUser = add.getLatitude();

                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(tujuan, 50);
                                    for (Address add : adresses) {

                                        lngUser1 = add.getLongitude();
                                        latUser1 = add.getLatitude();

                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                if (lokasi != null && !lokasi.equals("") && tujuan != null && !tujuan.equals("")) {
                                    mMap.clear();
                                    endx = new LatLng(latUser1, lngUser1);
                                    jlat = latUser1;
                                    jlong = lngUser1;
//                endy = new LatLng(latitudee, longitudeu);
                                    // get coordinate tujuan from field koordinat
                                    __global_endposition = String.valueOf(latUser1 + "," + lngUser1);
                                    __global_yourCoordinate_exist = new LatLng(latUser, lngUser);

                                    try {
                                        startingScript(latUser, lngUser, latUser1, lngUser1);

                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
//                new GeocoderLokasi().execute(lokasi);
//                new GeocoderTujuan().execute(tujuan);
                                } else if (lokasi.equals("") && tujuan != null && !tujuan.equals("")) {
                                    String k = String.valueOf(latitude + "," + longitude);
                                    mMap.clear();
                                    endx = new LatLng(latUser1, lngUser1);
                                    endy = new LatLng(latitude, longitude);
                                    // get coordinate tujuan from field koordinat
                                    __global_endposition = String.valueOf(latUser1 + "," + lngUser1);
                                    __global_yourCoordinate_exist = new LatLng(latitude, longitude);

                                    try {
                                        startingScript(latitude, longitude, latUser1, lngUser1);

                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
//                new GeocoderLokasi().execute(k);
//                new GeocoderTujuan().execute(tujuan);
                                    imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
                                    imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
                                    btproses.setVisibility(View.GONE);
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

                                }
                            }
                        }, 1000);
                       // dialog.dismiss();
                    }
                });


//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        final Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                 if (dialog != null) {
//                                    if (dialog.isShowing()) {
//                                        dialog.dismiss();
//                                    }
//                                }
//                            }
//                        }, 30000);
//
//                    }
//                });





                // dialog.dismiss();
                   /* new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {


                        }
                    }, 2000);*/

//                alert();
              /*  int kmn=0;
                ProgressDialog dialog = new ProgressDialog(Tes2.this); // this = YourActivity
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Loading. Please wait...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                if (dialog.isShowing()) {
                kmn=1;
                }
                if(kmn==1) {
                    String lokasi = mAutocompleteTextView.getText().toString();
                    String tujuan = mAutocompleteTextView2.getText().toString();
                    Geocoder coder = new Geocoder(Tes2.this);
                    try {
                        ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(lokasi, 50);
                        for (Address add : adresses) {
                            lngUser = add.getLongitude();
                            latUser = add.getLatitude();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(tujuan, 50);
                        for (Address add : adresses) {

                            lngUser1 = add.getLongitude();
                            latUser1 = add.getLatitude();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (lokasi != null && !lokasi.equals("") && tujuan != null && !tujuan.equals("")) {
                        mMap.clear();
                        endx = new LatLng(latUser1, lngUser1);
                        jlat = latUser1;
                        jlong = lngUser1;
//                endy = new LatLng(latitudee, longitudeu);
                        // get coordinate tujuan from field koordinat
                        __global_endposition = String.valueOf(latUser1 + "," + lngUser1);
                        __global_yourCoordinate_exist = new LatLng(latUser, lngUser);

                        try {
                            startingScript(latUser, lngUser, latUser1, lngUser1);

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                new GeocoderLokasi().execute(lokasi);
//                new GeocoderTujuan().execute(tujuan);
                    } else if (lokasi.equals("") && tujuan != null && !tujuan.equals("")) {
                        String k = String.valueOf(latitude + "," + longitude);
                        mMap.clear();
                        endx = new LatLng(latUser1, lngUser1);
                        endy = new LatLng(latitude, longitude);
                        // get coordinate tujuan from field koordinat
                        __global_endposition = String.valueOf(latUser1 + "," + lngUser1);
                        __global_yourCoordinate_exist = new LatLng(latitude, longitude);

                        try {
                            startingScript(latitude, longitude, latUser1, lngUser1);

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                new GeocoderLokasi().execute(k);
//                new GeocoderTujuan().execute(tujuan);
                    }

                    imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
                    btproses.setVisibility(View.GONE);
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
                    // dialog.dismiss();
                }*/

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
            Geocoder coder = new Geocoder(Tes2.this);
//            double longitudeu = 0;
//            double latitudee = 0;
//            double longitudeu1 = 0;
//            double latitudee1 = 0;
          /*  try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(lokasi, 50);
                for(Address add : adresses){
                    lngUser= add.getLongitude();
                    latUser = add.getLatitude();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(tujuan, 50);
                for(Address add : adresses){

                    lngUser1= add.getLongitude();
                    latUser1 = add.getLatitude();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(lokasi!=null && !lokasi.equals("")&&tujuan!=null && !tujuan.equals("")){
                mMap.clear();
                endx = new LatLng(latUser1,lngUser1 );
                jlat=latUser1;
                jlong=lngUser1;
//                endy = new LatLng(latitudee, longitudeu);
                // get coordinate tujuan from field koordinat
                __global_endposition = String.valueOf(latUser1+","+lngUser1);
                __global_yourCoordinate_exist=new LatLng(latUser,lngUser);

                try {
                    startingScript(latUser, lngUser, latUser1, lngUser1);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


//                new GeocoderLokasi().execute(lokasi);
//                new GeocoderTujuan().execute(tujuan);

            }*/
            if(lokasi!=null && !lokasi.equals("")&&tujuan!=null && !tujuan.equals("")) {
                btproses.setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
            }


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
            Geocoder coder = new Geocoder(Tes2.this);
//            double longitudeu = 0;
//            double latitudee = 0;
//            double longitudeu1 = 0;
//            double latitudee1 = 0;
           /* try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(lokasi, 50);
                for(Address add : adresses){
                    lngUser= add.getLongitude();
                    latUser = add.getLatitude();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(tujuan, 50);
                for(Address add : adresses){

                    lngUser1= add.getLongitude();
                    latUser1 = add.getLatitude();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(lokasi!=null && !lokasi.equals("")&&tujuan!=null && !tujuan.equals("")){
                mMap.clear();
                endx = new LatLng(latUser1,lngUser1 );
                jlat=latUser1;
                jlong=lngUser1;
//                endy = new LatLng(latitudee, longitudeu);
                // get coordinate tujuan from field koordinat
                __global_endposition = String.valueOf(latUser1+","+lngUser1);
                __global_yourCoordinate_exist=new LatLng(latUser,lngUser);

                try {
                    startingScript(latUser, lngUser, latUser1, lngUser1);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                new GeocoderLokasi().execute(lokasi);
//                new GeocoderTujuan().execute(tujuan);
            }else if(lokasi.equals("") && tujuan!=null && !tujuan.equals("")){
                String k = String.valueOf(latitude + "," + longitude);
                mMap.clear();
                endx = new LatLng(latUser1, lngUser1);
                endy = new LatLng(latitude, longitude);
                // get coordinate tujuan from field koordinat
                __global_endposition = String.valueOf(latUser1+","+lngUser1);
                __global_yourCoordinate_exist=new LatLng(latitude, longitude);

                try {
                    startingScript(latitude, longitude, latUser1, lngUser1);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                new GeocoderLokasi().execute(k);
//                new GeocoderTujuan().execute(tujuan);
            }*/
            if(lokasi!=null && !lokasi.equals("")&&tujuan!=null && !tujuan.equals("")) {
                btproses.setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
            }else if(lokasi.equals("") && tujuan!=null && !tujuan.equals("")){
                btproses.setVisibility(View.VISIBLE);
                imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
            }

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
//            Bitmap bitmap = takeScreenshot();
//            saveBitmap(bitmap);
//            View rootView = findViewById(android.R.id.content).getRootView();
//            Bitmap bitmap = getBitmapOfView(rootView);
            //imageViewPreview.setImageBitmap(bitmap);
//            createImageFromBitmap(bitmap);
        }else if(v==delete2){
//            mMap.setTrafficEnabled(false);
            mAutocompleteTextView2.setText("");
        }
    }


    public Bitmap getBitmapOfView(View v)
    {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bmp = rootview.getDrawingCache();
        return bmp;
    }
    public void createImageFromBitmap(Bitmap bmp)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File( Environment.getExternalStorageDirectory() +"/screenshot_angkot.png");
        try
        {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            ostream.write(bytes.toByteArray());
            ostream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    ///

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/Pictures/Screenshots/screenshot_angkot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
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

        mMap.clear();
        fab5.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);



        imm.hideSoftInputFromWindow(mAutocompleteTextView.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mAutocompleteTextView2.getWindowToken(), 0);
        String filterAddress = "";

        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses =
                    geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addresses.size() > 0) {

                for (int i = 0; i <4;i++)
                    filterAddress += addresses.get(0).getAddressLine(i) + " ";


                if(idid.getText().equals("1")) {
                    latUser1 =latLng.latitude;
                    lngUser1 =latLng.longitude;

                    mAutocompleteTextView2.setText(filterAddress);
                    String lokasi=mAutocompleteTextView.getText().toString();
                    String tujuan=mAutocompleteTextView2.getText().toString();
                    lokasitujuanpertama = new LatLng(latLng.latitude,latLng.longitude);
                    mMap.addMarker(new MarkerOptions()
                            .position(lokasitujuanpertama)
                            .title("Tujuan Anda")
                            .snippet(tujuan)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52)));
                    mAutocompleteTextView2.clearFocus();
                    mAutocompleteTextView.setNextFocusDownId(mAutocompleteTextView.getId());
                    idid.setText("0");








                    if (lokasi.equals("") && !tujuan.equals("")) {
//
                        try {
                            List<Address> addressList = geoCoder.getFromLocationName(tujuan, 1);
                            if (addressList != null && addressList.size() > 0) {
                                double lat = addressList.get(0).getLatitude();
                                double lng = addressList.get(0).getLongitude();

                                LatLng user = new LatLng(lat,lng);
                                mMap.addMarker(new MarkerOptions()
                                        .position(lokasitujuanpertama)
//                                        .position(user)
                                        .title("Tujuan Anda")
                                        .snippet(tujuan)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        btproses.setVisibility(View.VISIBLE);
                        mAutocompleteTextView2.clearFocus();
                        mAutocompleteTextView.clearFocus();
                    }
                    else if (!lokasi.equals("") && !tujuan.equals("")) {

                        try {
                            List<Address> addressList = geoCoder.getFromLocationName(lokasi, 1);
                            if (addressList != null && addressList.size() > 0) {
                                double lat = addressList.get(0).getLatitude();
                                double lng = addressList.get(0).getLongitude();

                                LatLng user = new LatLng(lat,lng);
                                 mMap.addMarker(new MarkerOptions()
//                                        .position(user)
                                         .position(lokasiuserpertama)
                                         .title("Lokasi Anda")
                                        .snippet(lokasi)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            List<Address> addressList = geoCoder.getFromLocationName(tujuan, 1);
                            if (addressList != null && addressList.size() > 0) {
                                double lat = addressList.get(0).getLatitude();
                                double lng = addressList.get(0).getLongitude();

                                LatLng user = new LatLng(lat,lng);
                                String nama="";
                                mMap.addMarker(new MarkerOptions()
                                        .position(lokasitujuanpertama)
//                                        .position(user)
                                        .title("Tujuan Anda")
                                        .snippet(tujuan)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        btproses.setVisibility(View.VISIBLE);

                        mAutocompleteTextView2.clearFocus();
                        mAutocompleteTextView.clearFocus();
                    }

                }
                else if(idid.getText().equals("0")){
                    mAutocompleteTextView.setText(filterAddress);
                    String lokasi=mAutocompleteTextView.getText().toString();
                    String tujuan=mAutocompleteTextView2.getText().toString();
                    latUser =latLng.latitude;
                    lngUser =latLng.longitude;

                    lokasiuserpertama = new LatLng(latLng.latitude,latLng.longitude);
                    String nama = "";
                    mMap.addMarker(new MarkerOptions()
                            .position(lokasiuserpertama)
                            .title("Lokasi Anda")
                            .snippet(lokasi)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));
                    mAutocompleteTextView.clearFocus();
                    mAutocompleteTextView2.setNextFocusDownId(mAutocompleteTextView2.getId());
                    idid.setText("1");


                  if (!lokasi.equals("") && !tujuan.equals("")) {

                        try {
                            List<Address> addressList = geoCoder.getFromLocationName(lokasi, 1);
                            if (addressList != null && addressList.size() > 0) {
                                double lat = addressList.get(0).getLatitude();
                                double lng = addressList.get(0).getLongitude();

                                LatLng user1 = new LatLng(lat,lng);
                                mMap.addMarker(new MarkerOptions()
                                        .position(lokasiuserpertama)
//                                        .position(user1)
                                        .title("Lokasi Anda")
                                        .snippet(lokasi)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            List<Address> addressList = geoCoder.getFromLocationName(tujuan, 1);
                            if (addressList != null && addressList.size() > 0) {
                                double lat = addressList.get(0).getLatitude();
                                double lng = addressList.get(0).getLongitude();

                                LatLng user3 = new LatLng(lat,lng);
                                mMap.addMarker(new MarkerOptions()
                                        .position(lokasitujuanpertama)
//                                        .position(user3)
                                        .title("Tujuan Anda")
                                        .snippet(tujuan)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        btproses.setVisibility(View.VISIBLE);

                        mAutocompleteTextView2.clearFocus();
                        mAutocompleteTextView.clearFocus();
                    }

                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }

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

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_place) {
//            Intent openStep = new Intent(this, MainActivity.class);
//            startActivity(openStep);
//        }else if (id == R.id.nav_camera) {
//            Intent openStep = new Intent(this, Tes2.class);
//            startActivity(openStep);
//        } else if (id == R.id.nav_gallery) {
//            Intent openStep = new Intent(this, Info_jalan.class);
//            startActivity(openStep);
//        } else if (id == R.id.nav_slideshow) {
//            Intent openStep = new Intent(this, Info_angkot.class);
//            startActivity(openStep);
//        } else if (id == R.id.nav_manage) {
//            //Intent openStep = new Intent(this, MainActivitySheet.class);
////            Intent openStep = new Intent(this, MainActivitys.class);
////            Intent openStep = new Intent(this, mapfrag.class);
//            //Intent openStep = new Intent(this, time.class);
//            //Intent openStep = new Intent(this, PathGoogleMapActivity.class);
////            Intent openStep = new Intent(this, mode.class);
////            Intent openStep = new Intent(this, MainActivitydirect.class);
//
////            Intent openStep = new Intent(this,Home.class);
//
//
//            // Intent openStep = new Intent(this,ExpandablelistActivity.class);
//            Intent openStep = new Intent(this,about.class);
////            Intent openStep = new Intent(this,MainActivitydj.class);
//            startActivity(openStep);
//        } /*else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }*/
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


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
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.m50));

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
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.amj1));

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
            lat_lng.clear();
            lat_lng_gbr.clear();
            Arrays.fill(__global_graphArray, null);

            new AmbilData1().execute();
            new AmbilData2().execute();




        }

    }


    public class AmbilData1 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Tes2.this);
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
                AlertDialog alertDialog = new AlertDialog.Builder(Tes2.this).create();
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
                                .fromResource(R.drawable.jma1));
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
                AlertDialog alertDialog = new AlertDialog.Builder(Tes2.this).create();
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
                                .fromResource(R.drawable.ank1));
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
//            Toast.makeText(getApplicationContext(), hj+""+hj2+" id"+id_latlong_pergi_sama1+"w"+simpul_lokasi+"&simpul2="+simpul_tujuan, Toast.LENGTH_LONG).show();
            if(id_latlong_pergi_sama1==0){
//                 Toast.makeText(getApplicationContext(), "dua arrah", Toast.LENGTH_LONG).show();
               ///
               // try{new AmbilLineDua1().execute();}catch(Exception e){}
            }
            else if(id_latlong_pergi_sama1!=0){
                //satu arrah
                try{new AmbilData3().execute();}catch(Exception e){}
                // try{new AmbilLine().execute();}catch(Exception e){}
//                Toast.makeText(getApplicationContext(), "satu arrah", Toast.LENGTH_LONG).show();
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
//                  Toast.makeText(getApplicationContext(),rows[0]+"1"+rows[1]+" 2 "+id_latlong_pergi_sama1+" 3 "+ id_latlong_pergi_lokasi2status+"satu arrah"+id_latlong_pergi_lokasi2, Toast.LENGTH_LONG).show();
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
//                    Toast.makeText(getApplicationContext(), "satuine", Toast.LENGTH_LONG).show();

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
                adapter = new ViewPagerAdapterRute2(Tes2.this, filterAddress , filterAddress2,jarak);
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
                if(siangkotnya.equals("Abdulmuis - Cicaheum via Binong")){
                    posi=0;
                    nmank="01A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
                else if(siangkotnya.equals("Abdulmuis - Cicaheum via Aceh")){
                    posi=1;
                    nmank="01B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Dago")){
                    posi=2;
                    nmank="02";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Ledeng")){
                    posi=3;
                    nmank="03";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sadang Serang")){
                    posi=10;
                    nmank="10";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Eykman")){
                    posi=11;
                    nmank="11A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Cihampelas")){
                    posi=12;
                    nmank="11B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gedebage")){
                    posi=13;
                    nmank="12";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                    posi=14;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                    posi=15;
                    nmank="14";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Margahayu - Ledeng")){
                    posi=16;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Riung Bandung - Dago")){
                    posi=17;
                    nmank="16";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Dago")){
                    posi=18;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Panghegar - Dipatiukur")){
                    posi=19;
                    nmank="18";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Sukajadi")){
                    posi=20;
                    nmank="19A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Setrasari")){
                    posi=21;
                    nmank="19B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                    posi=22;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                    posi=23;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                    posi=24;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Sederhana")){
                    posi=25;
                    nmank="23";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sederhana - Cimindi")){
                    posi=26;
                    nmank="24";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciwastra - Ujungberung")){
                    posi=27;
                    nmank="25";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cisitu - Tegalega")){
                    posi=28;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                    posi=29;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Elang - Gedebage")){
                    posi=30;
                    nmank="28";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Mengger")){
                    posi=31;
                    nmank="29";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicadas - Elang")){
                    posi=32;
                    nmank="30";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Antapani - Ciroyom")){
                    posi=33;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibiru - Cicadas")){
                    posi=34;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sekemirung - Panyileukan")){
                    posi=35;
                    nmank="33";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                    posi=36;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                    posi=37;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibogo - Elang")){
                    posi=38;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
                ///////////////
//                Integer imageid[] = {
//                        R.drawable.or,
//                        R.drawable.jma,
//                        R.drawable.ank,
//                        R.drawable.amj
//                };
                CustomListRute customList = new CustomListRute(Tes2.this, filterAddress , filterAddress2, imageid,jarak);
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
        String jarak2= String.valueOf(kmInDec);
        String jarak3= String.valueOf(meterInDec);
        jarak[1]=jarak2;
        jarak22[1]=jarak3;


        double ambiljarak=kmInDec-2;
        double jarak_bagi=ambiljarak/2;
       // double hitung=jarak_bagi*500;
        //double hasil=hitung+2000;
        if(kmInDec==2 ||kmInDec==1 ||kmInDec==0){
            hargarute="2000";
            // hrg.setText("Rp.2000");
        }else if(kmInDec>=2 && kmInDec<=jarak_bagi){

            double ambiljarak1=kmInDec-2;
            double jarak_bagi1=ambiljarak1;
            double hitung=jarak_bagi1*500;
            double hasil=hitung+2000;
            hargarute="Rp."+String.valueOf(hasil);
        }else if(kmInDec>jarak_bagi){
            hargarute="Rp.4900";
        }


//        else{
//            double ambiljarak=kmInDec-2;
//            double jarak_bagi=ambiljarak;
//            double hitung=jarak_bagi*500;
//            double hasil=hitung+2000;
//            //hrg.setText("Rp."+String.valueOf(hasil));
//            hargarute="Rp."+String.valueOf(hasil);
//        }


        return Radius * c;
    }

    public double CalculationByDistance3(LatLng StartP, LatLng EndP) {
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
        String jarak2= String.valueOf(meterInDec);
        jarak[0]=jarak2;
        jarak22[0]=jarak2;
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

//
        return Radius * c;
    }

    public double CalculationByDistance4(LatLng StartP, LatLng EndP) {
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

        String jarak2= String.valueOf(meterInDec);
        String jarak3= String.valueOf(kmInDec);
        jarak[2]=jarak2;
        jarak22[2]=jarak3;
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

//

        /*if(kmInDec==2 ||kmInDec==1 ||kmInDec==0){
            hargarute3="2000";
            // hrg.setText("Rp.2000");
        }else{
            double ambiljarak=kmInDec-2;
            double jarak_bagi=ambiljarak;
            double hitung=jarak_bagi*500;
            double hasil=hitung+2000;
            //hrg.setText("Rp."+String.valueOf(hasil));
            hargarute3="Rp."+String.valueOf(hasil);
        }*/
        double ambiljarak=kmInDec-2;
        double jarak_bagi=ambiljarak/2;
        // double hitung=jarak_bagi*500;
        //double hasil=hitung+2000;
        if(kmInDec==2 ||kmInDec==1 ||kmInDec==0){
            hargarute3="2000";
            // hrg.setText("Rp.2000");
        }else if(kmInDec>=2 && kmInDec<=jarak_bagi){

            double ambiljarak1=kmInDec-2;
            double jarak_bagi1=ambiljarak1;
            double hitung=jarak_bagi1*500;
            double hasil=hitung+2000;
            hargarute3="Rp."+String.valueOf(hasil);
        }else if(kmInDec>jarak_bagi){
            hargarute3="Rp.4900";
        }
        return Radius * c;
    }

    public double CalculationByDistance5(LatLng StartP, LatLng EndP) {
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

        String jarak2= String.valueOf(meterInDec);

        jarak22[3]=jarak2;
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

//

        return Radius * c;
    }
    ///////////////DJIKSTRA
    public void startingScript(double latUser, double lngUser, double lat_endposition, double lng_endposition) throws JSONException{
//        Toast.makeText(Tes2.this,latUser +" "+ lngUser+ "atas 1 bawah 1 " + lat_endposition+""+lng_endposition, Toast.LENGTH_SHORT).show();


        // delete temporary record DB
        deleteTemporaryRecord();

        // reset google map
        mMap.clear();

        // convert graph from DB to Array; graph[][]
        GraphToArray DBGraph = new GraphToArray();
        __global_graphArray = DBGraph.convertToArray(this); // return graph[][] Array

        // get max++ row temporary DB
        maxRowDB();

        // GET COORDINATE AWAL DI SEKITAR SIMPUL
        // coordinate awal lalu di konversi ke simpul awal
        // return __global_simpul_awal, __global_graphArray[][]
        // ==========================================
        Get_koordinat_awal_akhir start_coordinate_jalur = new Get_koordinat_awal_akhir();
        getSimpulAwalAkhirJalur(start_coordinate_jalur, latUser, lngUser, "awal");

        // GET COORDINATE AKHIR DI SEKITAR SIMPUL
        // coordinate akhir lalu di konversi ke simpul akhir
        // return __global_simpul_akhir, __global_graphArray[][]
        // ==========================================
        Get_koordinat_awal_akhir destination_coordinate_jalur = new Get_koordinat_awal_akhir();
        getSimpulAwalAkhirJalur(destination_coordinate_jalur, lat_endposition, lng_endposition, "akhir");

        // ALGORITMA DIJKSTRA
        // ==========================================
        dijkstra algo = new dijkstra();
        algo.jalurTerpendek(__global_graphArray, __global_simpul_awal, __global_simpul_akhir);

       // Toast.makeText(Tes2.this,algo.jalur_terpendek1+"", Toast.LENGTH_SHORT).show();

        // no result for algoritma dijkstra
       if(algo.status == "die"){

            AlertDialog alertDialog = new AlertDialog.Builder(Tes2.this).create();
            alertDialog.setTitle("Informasi");
            alertDialog.setMessage("Lokasi sudah dekat dengan lokasi anda.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }else{
            // return jalur terpendek; example 1->5->6->7
            String[] exp = algo.jalur_terpendek1.split("->");

            // DRAW JALUR ANGKUTAN UMUM
            // =========================================
            drawJalur(algo.jalur_terpendek1, exp);
        }

    }




    public void deleteTemporaryRecord(){

        // delete DB
        final SQLiteDatabase dbDelete = dbHelper.getWritableDatabase();

        // delete temporary record DB
        for(int i = 0; i < 4; i++){
            //hapus simpul awal tambahan, mulai dr id 401,402,403,404
            String deleteQuery_ = "DELETE FROM graph where id ='"+ (901+i) +"'";
            dbDelete.execSQL(deleteQuery_);

            //hapus simpul tujuan tambahan, mulai dr id 501,502,503,504
            String deleteQuery = "DELETE FROM graph where id ='"+ (1001+i) +"'";
            dbDelete.execSQL(deleteQuery);
        }
    }

    public void maxRowDB(){

        dbHelper = new SQLHelper(this);
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        cursor = dbRead.rawQuery("SELECT max(simpul_awal), max(simpul_tujuan) FROM graph", null);
        cursor.moveToFirst();
        int max_simpul_db		= 0;
        int max_simpulAwal_db 	= Integer.parseInt(cursor.getString(0).toString());
        int max_simpulTujuan_db = Integer.parseInt(cursor.getString(1).toString());

        if(max_simpulAwal_db >= max_simpulTujuan_db){
            max_simpul_db = max_simpulAwal_db;
        }else{
            max_simpul_db = max_simpulTujuan_db;
        }

        // return
        __global_maxRow0 = (max_simpul_db+1);
        __global_maxRow1 = (max_simpul_db+2);
    }


    public void drawJalur(String alg, String[] exp) throws JSONException{
       // Toast.makeText(getApplicationContext(), exp+"spank", Toast.LENGTH_LONG).show();
        int start = 0;

        // GAMBAR JALURNYA
        // ======================
        dbHelper = new SQLHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        for(int i = 0; i < exp.length-1; i++){

            //ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();

            cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal ="+exp[start]+" and simpul_tujuan ="+exp[(++start)], null);
            cursor.moveToFirst();


            // dapatkan koordinat Lat,Lng dari field koordinat (3)
            String json = cursor.getString(0).toString();

            // get JSON
            JSONObject jObject = new JSONObject(json);
            JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

            // get coordinate JSON
            for(int w = 0; w < jArrCoordinates.length(); w++){

                JSONArray latlngs = jArrCoordinates.getJSONArray(w);
                Double lats = latlngs.getDouble(0);
                Double lngs = latlngs.getDouble(1);


                lat_lng_gbr.add( new LatLng(lats, lngs) );
                if(w==jArrCoordinates.length()-1){
                    endl=new LatLng(lats, lngs);
                    klat=lats;
                    klong=lngs;

                }
            }

//            // buat rute
//            PolylineOptions jalurBiasa = new PolylineOptions();
//            jalurBiasa.addAll(lat_lng_gbr).width(5).color(0xff00ff00).geodesic(true);
//            mMap.addPolyline(jalurBiasa);




        }


        LatLng amj= new LatLng(klat, klong);
        mMap.addMarker(new MarkerOptions()
                .position(amj)
                .title("Pemberhentian Akhir")
                .snippet("Pemberhentian Akhir")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.amj1))).showInfoWindow();

        // BUAT MARKER UNTUK YOUR POSITION AND DESTINATION POSITION
        // ======================
        // your position

        mMap.addMarker(new MarkerOptions()
//                .position(__global_yourCoordinate_exist)
                .position(lokasiuserpertama)
                .title("Lokasi Anda")
                .snippet("Lokasi Anda")
                        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.jma1))).showInfoWindow();

        String[] exp_endCoordinate = __global_endposition.split(",");
        double lat_endPosition = Double.parseDouble(exp_endCoordinate[0]);
        double lng_endPosition = Double.parseDouble(exp_endCoordinate[1]);
        LatLng endx = new LatLng(lat_endPosition, lng_endPosition);

        // destination position
        mMap.addMarker(new MarkerOptions()
//                .position(endx)
                .position(lokasitujuanpertama)
                .title(endx + "Tujuan Anda")
                .snippet("Tujuan Anda")
//        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.m50))).showInfoWindow();
//        .showInfoWindow();

        // TENTUKAN JENIS ANGKUTAN UMUM YANG MELEWATI JALUR TERSEBUT
        // ========================================F==================
        // misal exp[] = 1->5->6->7
        int m = 0;


        String[] awal = __global_old_simpul_awal.split("-"); // misal 4-5
        String[] akhir = __global_old_simpul_akhir.split("-"); // misal 8-7

        int ganti_a = 0;
        int ganti_b = 0;
        int simpulAwalDijkstra = Integer.parseInt(exp[0]);

        String gabungSimpul_all = "";
        Map<String, ArrayList> listAngkutanUmum = new HashMap<String, ArrayList>();
        ArrayList<Integer> listSimpulAngkot = new ArrayList<Integer>();

        // cari simpul_old sebelum koordinat dipecah
        // misal 4-5 dipecah menjadi 4-6-5, berarti simpul_old awal = 5, simpul_old akhir = 4
        for(int e = 0; e < (exp.length - 1); e++){

            if(e == 0){ // awal

                // dijalankan jika hasil algo hanya 2 simpul, example : 4->5
                if(exp.length == 2 /* 2 simpul (4-5)*/){

                    // ada simpul baru di awal (10) dan di akhir (11), example 10->11
                    if( exp[0].equals(String.valueOf(__global_maxRow0)) && exp[1].equals(String.valueOf(__global_maxRow1)) ){

                        if(String.valueOf(__global_maxRow0).equals(akhir[0])){
                            ganti_b = Integer.parseInt(akhir[1]);
                        }else{
                            ganti_b = Integer.parseInt(akhir[0]);
                        }

                        if(String.valueOf(ganti_b).equals(awal[0])){
                            ganti_a = Integer.parseInt(awal[1]);
                        }else{
                            ganti_a = Integer.parseInt(awal[0]);
                        }
                    }
                    else{
                        // ada simpul baru di awal (10), example 10->5
                        // maka cari simpul awal yg oldnya
                        if( exp[0].equals(String.valueOf(__global_maxRow0)) ){

                            if(exp[1].equals(awal[1])){
                                ganti_a = Integer.parseInt(awal[0]);
                            }else{
                                ganti_a = Integer.parseInt(awal[1]);
                            }
                            ganti_b = Integer.parseInt(exp[1]);
                        }
                        // ada simpul baru di akhir (10), example 5->10
                        // maka cari simpul akhir yg oldnya
                        else if( exp[1].equals(String.valueOf(__global_maxRow0)) ){

                            if(exp[0].equals(akhir[0])){
                                ganti_b = Integer.parseInt(akhir[1]);
                            }else{
                                ganti_b = Integer.parseInt(akhir[0]);
                            }
                            ganti_a = Integer.parseInt(exp[0]);
                        }
                        // tidak ada penambahan simpul sama sekali
                        else{
                            ganti_a = Integer.parseInt(exp[0]);
                            ganti_b = Integer.parseInt(exp[1]);
                        }
                    }

        			/*
        			// 4 == 4
        			if(exp[0].equals(awal[0])){
            			ganti_a = Integer.parseInt(awal[0]);
            			//ganti_b = Integer.parseInt(awal[1]);
        			}else{
            			ganti_a = Integer.parseInt(awal[1]);
            			//ganti_b = Integer.parseInt(awal[0]);
        			}

        			if(String.valueOf(ganti_a).equals(akhir[0])){
            			ganti_b = Integer.parseInt(akhir[1]);
            			//ganti_b = Integer.parseInt(awal[1]);
        			}else{
            			ganti_b = Integer.parseInt(akhir[0]);
            			//ganti_b = Integer.parseInt(awal[0]);
        			}
        			*/

        			/*
        			 *         			// 4 == 4
        			if(exp[0].equals(awal[0])){
            			ganti_a = Integer.parseInt(akhir[0]);
            			ganti_b = Integer.parseInt(awal[1]);
        			}else{
            			ganti_a = Integer.parseInt(awal[1]);
            			ganti_b = Integer.parseInt(akhir[0]);
        			}
        			 */

                }
                // hasil algo lebih dr 2 : 4->5->8->7-> etc ..
                else{
                    if(exp[1].equals(awal[1])){ // 5 == 5
                        ganti_a = Integer.parseInt(awal[0]); // hasil 4
                    }else{
                        ganti_a = Integer.parseInt(awal[1]); // hasil 5
                    }

                    ganti_b = Integer.parseInt( exp[++m] );
                }
            }
            else if(e == (exp.length - 2)){ // akhir

                if(exp[ (exp.length - 2) ].equals(akhir[1])){ // 7 == 7
                    ganti_b = Integer.parseInt(akhir[0]); // hasil 8
                }else{
                    ganti_b = Integer.parseInt(akhir[1]); // hasil 7
                }

                ganti_a = Integer.parseInt( exp[m] );

            }else{ // tengah tengah
                ganti_a = Integer.parseInt( exp[m] );
                ganti_b = Integer.parseInt( exp[++m] );
            }

            gabungSimpul_all += "," + ganti_a + "-" + ganti_b + ","; // ,1-5,
            String gabungSimpul = "," + ganti_a + "-" + ganti_b + ","; // ,1-5,

            cursor = db.rawQuery("SELECT * FROM angkutan_umum where simpul like '%" + gabungSimpul + "%'", null);
            cursor.moveToFirst();

            ArrayList<String> listAngkutan = new ArrayList<String>();

            for(int ae = 0; ae < cursor.getCount(); ae++){
                cursor.moveToPosition(ae);
                listAngkutan.add( cursor.getString(1).toString() );
            }

            listAngkutanUmum.put("angkutan" + e, listAngkutan);

            // add simpul angkot
            listSimpulAngkot.add(Integer.parseInt(exp[e]));

        }


        String replace_jalur = gabungSimpul_all.replace(",,", ","); //  ,1-5,,5-6,,6-7, => ,1-5,5-6,6-7,
        Toast.makeText(getApplicationContext(), replace_jalur+"lurnya", Toast.LENGTH_LONG).show();
        dialog.dismiss();


    ////dalam array//////
        String[] ff = new String[3];
        cursor = db.rawQuery("SELECT * FROM angkutan_umum where simpul like '%" + replace_jalur + "%'", null);
        cursor.moveToFirst();

        String temp_index_baris = "";
        int index_kolom = 0;
        int jml_baris = cursor.getCount();

        for(int i = 0; i < jml_baris; i++){

            // baris
            cursor.moveToPosition(i);

            // Cari index kolom
            int simpulAwalDB = Integer.parseInt(cursor.getString(1)); // simpul_tujuan

            /*if(temp_index_baris == ""){
                temp_index_baris = String.valueOf(simpulAwalDB);
            }else{
                // simpul_awal berikutnya tidak sama dgn sebelumnya, reset index_kolom = 0
                if(Integer.parseInt(temp_index_baris) != simpulAwalDB){
                    index_kolom = 0;
                    temp_index_baris = String.valueOf(simpulAwalDB);
                }
            }
*/
            // masukkan ke graph array
            String simpulTujuan_dan_Bobot = "";
            if(cursor.getString(2).equals("")){ //tidak ada derajat keluar
                simpulTujuan_dan_Bobot = ";";
            }
            // ada derajat keluar
            else{

                // example output : 2->789.98
                simpulTujuan_dan_Bobot = cursor.getString(2).toString()+"->"+cursor.getString(4).toString(); //simpul_tujuan dan bobot
            }

            ff[simpulAwalDB]= simpulTujuan_dan_Bobot;
            index_kolom++;
        }
        ////dalam array//////






        cursor = db.rawQuery("SELECT * FROM angkutan_umum where simpul like '%" + replace_jalur + "%'", null);
        cursor.moveToFirst();
        //cursor.moveToPosition(0);

        // ada 1 angkot yg melewati jalur dari awal sampek akhir
        if(cursor.getCount() > 0){

            String id = cursor.getString(0).toString();
            String siAngkot = cursor.getString(1).toString();
            String smpul = cursor.getString(2).toString();
            hargaasli = cursor.getString(3).toString();
            hargaasli=tarif_;
            tarif_= cursor.getString(3).toString();
            jarak_ = cursor.getString(4).toString();
            armada_ = cursor.getString(5).toString();
            //warna2_ = cursor.getString(6).toString();
            warna_=cursor.getInt(6);
            siangkotnya=siAngkot;


            /*if(siangkotnya.equals("ST Hall - Gedebage")){
                ank1="12";
                posis=11;
            }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                ank1="13";
                posis=12;
            }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                ank1="21";
                posis=20;
            }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Eykman")){
                ank1="11";
                posis=10;
            }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                ank1="22";
                posis=21;
            }else if(siangkotnya.equals("Dago - ST Hall")){
                ank1="09";
                posis=8;
            }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                ank1="14";
                posis=13;
            }else if(siangkotnya.equals("Margahayu - Ledeng")){
                ank1="15";
                posis=14;
            }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                ank1="07";
                posis=6;
            }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                ank1="34";
                posis=28;

            }else if(siangkotnya.equals("Caringin - Dago")){
                ank1="17";
                posis=16;
            }else if(siangkotnya.equals("Cicaheum - Ledeng]")){
                ank1="05";
                posis=4;
            }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                ank1="20";
                posis=19;
            }else if(siangkotnya.equals("Ciroyom - Sarijadi via Sukajadi")){
                ank1="19";
                posis=18;
            }else if(siangkotnya.equals("Cisitu - Tegalega")){
                ank1="26";
                posis=23;
            }else if(siangkotnya.equals("Abdulmuis - Elang")){
                ank1="04";
                posis=3;
            }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                ank1="06";
                posis=5;
            }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                ank1="08";
                posis=7;
            }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                ank1="27";
                posis=24;
            }else if(siangkotnya.equals("Cibiru - Cicadas")){
                ank1="32";
                posis=27;
            }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                ank1="35";
                posis=29;
            }else if(siangkotnya.equals("Cibogo - Elang")){
                ank1="36";
                posis=30;
            }else if(siangkotnya.equals("Antapani - Ciroyom")){
                ank1="31";
                posis=26;
            }else if(siangkotnya.equals("Riung Bandung - Dago")){
                ank1="16";
                posis=15;
            }else if(siangkotnya.equals("Abdulmuis - Cicaheum via Aceh")){
                ank1="01B";
                posis=0;
            }else if(siangkotnya.equals("Abdulmuis - Ledeng")){
                ank1="03";
                posis=2;
            }else if(siangkotnya.equals("ST Hall - Sadang Serang")){
                ank1="10";
                posis=9;
            }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                ank1="22";
                posis=21;
            }else if(siangkotnya.equals("Abdulmuis - Dago")){
                ank1="02";
                posis=1;
            }else if(siangkotnya.equals("Panghegar - Dipatiukur")){
                ank1="19A";
                posis=17;
            }else if(siangkotnya.equals("Cijerah - Sederhana")){
                ank1="22";
                posis=21;
            }else if(siangkotnya.equals("Cicadas - Elang")){
                ank1="30";
                posis=25;
            }*/
            if(siangkotnya.equals("Abdulmuis - Cicaheum via Binong")){
                posis=0;
                ank1="01A";


            }
            else if(siangkotnya.equals("Abdulmuis - Cicaheum via Aceh")){
                posis=1;
                ank1="01B";


            }else if(siangkotnya.equals("Abdulmuis - Dago")){
                posis=2;
                ank1="02";


            }else if(siangkotnya.equals("Abdulmuis - Ledeng")){
                posis=3;
                ank1="03";


            }else if(siangkotnya.equals("Abdulmuis - Elang")){
                posis=4;
                ank1="04";


            }else if(siangkotnya.equals("Cicaheum - Ledeng")){
                posis=5;
                ank1="05";


            }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                posis=6;
                ank1="06";


            }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                posis=7;
                ank1="07";


            }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                posis=8;
                ank1="08";


            }else if(siangkotnya.equals("Dago - ST Hall")){
                posis=9;
                ank1="09";


            }else if(siangkotnya.equals("ST Hall - Sadang Serang")){
                posis=10;
                ank1="10";


            }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Eykman")){
                posis=11;
                ank1="11A";


            }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Cihampelas")){
                posis=12;
                ank1="11B";


            }else if(siangkotnya.equals("ST Hall - Gedebage")){
                posis=13;
                ank1="12";


            }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                posis=14;
                ank1="13";


            }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                posis=15;
                ank1="14";


            }else if(siangkotnya.equals("Margahayu - Ledeng")){
                posis=16;
                ank1="15";


            }else if(siangkotnya.equals("Riung Bandung - Dago")){
                posis=17;
                ank1="16";


            }else if(siangkotnya.equals("Caringin - Dago")){
                posis=18;
                ank1="17";


            }else if(siangkotnya.equals("Panghegar - Dipatiukur")){
                posis=19;
                ank1="18";


            }else if(siangkotnya.equals("Ciroyom - Sarijadi via Sukajadi")){
                posis=20;
                ank1="19A";


            }else if(siangkotnya.equals("Ciroyom - Sarijadi via Setrasari")){
                posis=21;
                ank1="19B";


            }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                posis=22;
                ank1="20";


            }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                posis=23;
                ank1="21";


            }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                posis=24;
                ank1="22";


            }else if(siangkotnya.equals("Cijerah - Sederhana")){
                posis=25;
                ank1="23";


            }else if(siangkotnya.equals("Sederhana - Cimindi")){
                posis=26;
                ank1="24";


            }else if(siangkotnya.equals("Ciwastra - Ujungberung")){
                posis=27;
                ank1="25";


            }else if(siangkotnya.equals("Cisitu - Tegalega")){
                posis=28;
                ank1="26";


            }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                posis=29;
                ank1="27";


            }else if(siangkotnya.equals("Elang - Gedebage")){
                posis=30;
                ank1="28";


            }else if(siangkotnya.equals("Abdulmuis - Mengger")){
                posis=31;
                ank1="29";


            }else if(siangkotnya.equals("Cicadas - Elang")){
                posis=32;
                ank1="30";


            }else if(siangkotnya.equals("Antapani - Ciroyom")){
                posis=33;
                ank1="31";


            }else if(siangkotnya.equals("Cibiru - Cicadas")){
                posis=34;
                ank1="32";


            }else if(siangkotnya.equals("Sekemirung - Panyileukan")){
                posis=35;
                ank1="33";


            }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                posis=36;
                ank1="34";


            }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                posis=37;
                ank1="35";


            }else if(siangkotnya.equals("Cibogo - Elang")){
                posis=38;
                ank1="36";


            }
////

            /////gambar jalur
            /*for(int i = 0; i < exp.length-1; i++){

                ArrayList<LatLng> lat_lng = new ArrayList<LatLng>();

                cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal ="+exp[start]+" and simpul_tujuan ="+exp[(++start)], null);
                cursor.moveToFirst();


                // dapatkan koordinat Lat,Lng dari field koordinat (3)
                String json = cursor.getString(0).toString();

                // get JSON
                JSONObject jObject = new JSONObject(json);
                JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

                // get coordinate JSON
                for(int w = 0; w < jArrCoordinates.length(); w++){

                    JSONArray latlngs = jArrCoordinates.getJSONArray(w);
                    Double lats = latlngs.getDouble(0);
                    Double lngs = latlngs.getDouble(1);


                    lat_lng.add( new LatLng(lats, lngs) );
                    if(w==jArrCoordinates.length()-1){
                        endl=new LatLng(lats, lngs);
                        klat=lats;
                        klong=lngs;

                    }
                }



                // buat rute
                PolylineOptions jalurBiasa = new PolylineOptions();
                jalurBiasa.addAll(lat_lng).width(5).color(0xff00ff00).geodesic(true);
                mMap.addPolyline(jalurBiasa);

            }*/
            /////////////////


            // get coordinate
            cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal = '" + simpulAwalDijkstra + "'", null);
            cursor.moveToFirst();
            String json_coordinate = cursor.getString(0).toString();

            // manipulating JSON
            JSONObject jObject = new JSONObject(json_coordinate);
            JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");
            JSONArray latlngs = jArrCoordinates.getJSONArray(0);

            // first latlng
            Double lats = latlngs.getDouble(0);
            Double lngs = latlngs.getDouble(1);
            __global_yourCoordinate_exist2=new LatLng(lats, lngs);
            llat=lats; llong=lngs;

            /*mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lats, lngs))
                    .title(ank1)
                    .snippet(siAngkot)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ank1)));*/

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lats, lngs))
                    .title(ank1)
                    .snippet(warna_+siAngkot)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ank1)));


//            marker.showInfoWindow();
//            mMap.setInfoWindowAdapter();
//            mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
            // die()

            // buat rute
            PolylineOptions jalurBiasa = new PolylineOptions();
            jalurBiasa.addAll(lat_lng_gbr).width(15).color(warna_).geodesic(true);
            mMap.addPolyline(jalurBiasa);

            try {
//                 Toast.makeText(getApplicationContext(), __global_yourCoordinate_exist+"1"+__global_yourCoordinate_exist2+"status", Toast.LENGTH_LONG).show();

                v2GetRouteDirection = new GMapV2GetRouteDirection();

                GetRouteTask3 getRoute = new GetRouteTask3();
                getRoute.execute();
//                GetRouteTask4 getRoute1 = new GetRouteTask4();
//                getRoute1.execute();
//                String c[] = {
////            R.drawable.or,
//                        "90",
//                        "190",
//                        "90",
//                };
//                String a[] = {
////            R.drawable.or,
//                        "90",
//                        "190",
//                        "90",
//                        "90"
//                };
//                String b[] = {
////            R.drawable.or,
//                        "90",
//                        "190",
//                        "90",
//                        "90"
//                };
//
//              //  viewPager.setVisibility(View.VISIBLE);
//                adapter = new ViewPagerAdapterRute2(Tes2.this, a,b,c);
//                viewPager.setAdapter(adapter);
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));


//                CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                CalculationByDistance3(lokasiuserpertama, __global_yourCoordinate_exist2);
                 CalculationByDistance(__global_yourCoordinate_exist2, endl);
                 CalculationByDistance4(endl, lokasitujuanpertama);
//                CalculationByDistance4(endl, endx);
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

                if(siangkotnya.equals("Abdulmuis - Cicaheum via Binong")){
                    posi=0;
                    nmank="01A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
                else if(siangkotnya.equals("Abdulmuis - Cicaheum via Aceh")){
                    posi=1;
                    nmank="01B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Dago")){
                    posi=2;
                    nmank="02";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Ledeng")){
                    posi=3;
                    nmank="03";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Elang")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ledeng")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciroyom")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Ciwastra")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicaheum - Cibaduyut")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Dago - ST Hall")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sadang Serang")){
                    posi=10;
                    nmank="10";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Eykman")){
                    posi=11;
                    nmank="11A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cimbuleuit - ST Hall via Cihampelas")){
                    posi=12;
                    nmank="11B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gedebage")){
                    posi=13;
                    nmank="12";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Sarijadi")){
                    posi=14;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("ST Hall - Gunung Batu")){
                    posi=15;
                    nmank="14";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Margahayu - Ledeng")){
                    posi=16;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Riung Bandung - Dago")){
                    posi=17;
                    nmank="16";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Dago")){
                    posi=18;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Panghegar - Dipatiukur")){
                    posi=19;
                    nmank="18";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Sukajadi")){
                    posi=20;
                    nmank="19A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciroyom - Sarijadi via Setrasari")){
                    posi=21;
                    nmank="19B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Bumi Asri - Ciroyom")){
                    posi=22;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cikudapateh - Ciroyom")){
                    posi=23;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Buah Batu - Sederhana")){
                    posi=24;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Sederhana")){
                    posi=25;
                    nmank="23";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sederhana - Cimindi")){
                    posi=26;
                    nmank="24";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Ciwastra - Ujungberung")){
                    posi=27;
                    nmank="25";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cisitu - Tegalega")){
                    posi=28;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cijerah - Ciwastra")){
                    posi=29;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Elang - Gedebage")){
                    posi=30;
                    nmank="28";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Abdulmuis - Mengger")){
                    posi=31;
                    nmank="29";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cicadas - Elang")){
                    posi=32;
                    nmank="30";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Antapani - Ciroyom")){
                    posi=33;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibiru - Cicadas")){
                    posi=34;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Sekemirung - Panyileukan")){
                    posi=35;
                    nmank="33";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Caringin - Sadang Serang")){
                    posi=36;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibaduyut - Karang Setra")){
                    posi=37;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(siangkotnya.equals("Cibogo - Elang")){
                    posi=38;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }


                textView14.setText("Nama Trayek :" + siangkotnya);
                textView15.setText("Jarak :" + jarakkm);

//                int jrk= Integer.parseInt(jarakkm);
//                int jrkkrg2=jrk-2;
////                int jrkkrg22=jrk-2;
//                int jrkbagi2=jrkkrg2/2;
//                int jrkbagi22=jrkkrg2/2;
//
//                if(jrk<=2){
//                    harganya1="Rp.2000";
//                }
////                else if(jrk>=2 && jrkbagi2<=jrkbagi22 ){
////                    int grp=Integer.parseInt(hargaasli)-2000;
////                    harganya1= "Rp."+String.valueOf(grp);
////                }
//                else if(jrkbagi2>=jrk){
//                    harganya1= "Rp."+String.valueOf(Integer.parseInt(hargaasli));
//                }
//
//                textView19.setText("Ongkos :" + harganya1);


                textView19.setText("Ongkos :" + hargarute);

                 ///adress1
                 String filterAddressh = "";
                 Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                 try {
                     List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                     if (addresses.size() > 0) {

                         for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                             filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                     }
                     address[0] = String.valueOf(filterAddressh);

                 } catch (IOException ex) {
                     ex.printStackTrace();
                 } catch (Exception e2) {
                     // TODO: handle exception
                     e2.printStackTrace();
                 }
//                 String a=String.valueOf(mAutocompleteTextView.getText().toString());
//
//                 address[1] = String.valueOf(a);
//                 address[2] = String.valueOf(a);
//                 address[3] = String.valueOf(a);

//                 /adress2
                 String filterAddress2 = "";
                 Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                 try {
                     List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                     if (addresses2.size() > 0) {

                         for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                             filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                     }

                     address1[0] = String.valueOf(filterAddress2);

                 } catch (IOException ex) {
                     ex.printStackTrace();
                 } catch (Exception e2) {
                     // TODO: handle exception
                     e2.printStackTrace();
                 }
//                 String b=String.valueOf(filterAddress2);
//                 address[1] = String.valueOf(b);
//
                 ///adress3
                 String filterAddress3= "";
                 Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                 try {
                     List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                     if (addresses3.size() > 0) {

                         for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                             filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                     }

                    // address[1] = String.valueOf(filterAddress3);
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 } catch (Exception e2) {
                     // TODO: handle exception
                     e2.printStackTrace();
                 }
//                 String c=String.valueOf(filterAddress3);
//                 address[2] = String.valueOf(c);
//
//                 ///adress4
                 String filterAddress4= "";
                 Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                 try {
                     List<Address> addresses4 = geoCoder4.getFromLocation(latUser1, lngUser1, 1);

                     if (addresses4.size() > 0) {

                         for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                             filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                     }

                     address1[1] = String.valueOf(filterAddress4);
                     address[2] = String.valueOf(filterAddress4);

                 } catch (IOException ex) {
                     ex.printStackTrace();
                 } catch (Exception e2) {
                     // TODO: handle exception
                     e2.printStackTrace();
                 }
//                 String d=String.valueOf(filterAddress4);
//                 address[3] = String.valueOf(d);
                address1[2] = String.valueOf(hargarute);
                address[1] = String.valueOf(siangkotnya);
                 CustomListRute customList = new CustomListRute(Tes2.this,address , address1, imageid,jarak);
                 lvdt = (ListView) findViewById(R.id.lvdt);
                 lvdt.setAdapter(customList);

                 fab3.setVisibility(View.VISIBLE);
                 fab5.setVisibility(View.GONE);

            }catch(Exception e){

            }

            return;

        }


        // ada 2 atau lebih angkot yg melewati jalur dari awal sampek akhir
        int banyakAngkot = 0;
        int indexUrut = 0;
        int indexSimpulAngkot = 1;
        int lengthAngkutan = listAngkutanUmum.size();
        Map<String, ArrayList> angkotFix = new HashMap<String, ArrayList>();

        for(int en = 0; en < lengthAngkutan; en++ ){

            // temporary sementara sebelum di retainAll()
            ArrayList<String> temps = new ArrayList<String>();
            for(int u = 0; u < listAngkutanUmum.get("angkutan0").size(); u++){
                temps.add( listAngkutanUmum.get("angkutan0").get(u).toString() );
            }

            if(en > 0 ){
                ArrayList listSekarang1 = listAngkutanUmum.get("angkutan0");
                ArrayList listSelanjutnya1 = listAngkutanUmum.get("angkutan" + en);

                // intersection
                listSekarang1.retainAll(listSelanjutnya1);

                if(listSekarang1.size() > 0){

                    listSimpulAngkot.remove(indexSimpulAngkot);
                    --indexSimpulAngkot;

                    listAngkutanUmum.remove("angkutan" + en);

                    if(en == (lengthAngkutan - 1)){

                        ArrayList<String> tempDalam = new ArrayList<String>();
                        for(int es = 0; es < listSekarang1.size(); es++){
                            tempDalam.add( listSekarang1.get(es).toString() );
                        }

                        angkotFix.put("angkutanFix" + indexUrut, tempDalam);
                        ++indexUrut;
                    }
                }
                else if(listSekarang1.size() == 0){

                    angkotFix.put("angkutanFix" + indexUrut, temps);

                    ArrayList<String> tempDalam = new ArrayList<String>();
                    for(int es = 0; es < listSelanjutnya1.size(); es++){
                        tempDalam.add( listSelanjutnya1.get(es).toString() );
                    }

                    //if(en == 1) break;
                    listAngkutanUmum.get("angkutan0").clear();
                    listAngkutanUmum.put("angkutan0", tempDalam);

                    //if(en != (listAngkutanUmum.size() - 1)){
                    listAngkutanUmum.remove("angkutan" + en);
                    //}

                    ++indexUrut;

                    if(en == (lengthAngkutan - 1)){

                        ArrayList<String> tempDalam2 = new ArrayList<String>();
                        for(int es = 0; es < listSelanjutnya1.size(); es++){
                            tempDalam2.add( listSelanjutnya1.get(es).toString() );
                        }

                        angkotFix.put("angkutanFix" + indexUrut, tempDalam2);
                        ++indexUrut;
                    }
                }

                ++indexSimpulAngkot;
            }
        }

        for(int r = 0; r < listSimpulAngkot.size(); r++){
            String simpulx = listSimpulAngkot.get(r).toString();
            // get coordinate simpulAngkutan
            cursor = db.rawQuery("SELECT jalur FROM graph where simpul_awal = '" + simpulx + "'", null);
            cursor.moveToPosition(0);

            // dapatkan koordinat Lat,Lng dari field koordinat (3)
            String json = cursor.getString(0).toString();

            // get JSON
            JSONObject jObject = new JSONObject(json);
            JSONArray jArrCoordinates = jObject.getJSONArray("coordinates");

            // get first coordinate JSON
            JSONArray latlngs = jArrCoordinates.getJSONArray(0);
            Double lats = latlngs.getDouble(0);
            Double lngs = latlngs.getDouble(1);

            LatLng simpulAngkot = new LatLng(lats, lngs);
            String siAngkot = angkotFix.get("angkutanFix" + r).toString();

            if(r == 0){
                siangkotnya=siAngkot;
                String[] angkot1 = siangkotnya.split(",");
                String angkott = angkot1[0];
                if(siangkotnya.equals("[Abdulmuis - Cicaheum via Binong]")){
                    posis2=0;
                    ank2="01A";


                }
                else if(siangkotnya.equals("[Abdulmuis - Cicaheum via Aceh]")){
                    posis2=1;
                    ank2="01B";


                }else if(siangkotnya.equals("[Abdulmuis - Dago]")){
                    posis2=2;
                    ank2="02";


                }else if(siangkotnya.equals("[Abdulmuis - Ledeng]")){
                    posis2=3;
                    ank2="03";


                }else if(siangkotnya.equals("[Abdulmuis - Elang]")){
                    posis2=4;
                    ank2="04";


                }else if(siangkotnya.equals("[Cicaheum - Ledeng]")){
                    posis2=5;
                    ank2="05";


                }else if(siangkotnya.equals("[Cicaheum - Ciroyom]")){
                    posis2=6;
                    ank2="06";


                }else if(siangkotnya.equals("[Cicaheum - Ciwastra]")){
                    posis2=7;
                    ank2="07";


                }else if(siangkotnya.equals("[Cicaheum - Cibaduyut]")){
                    posis2=8;
                    ank2="08";


                }else if(siangkotnya.equals("[Dago - ST Hall]")){
                    posis2=9;
                    ank2="09";


                }else if(siangkotnya.equals("[ST Hall - Sadang Serang]")){
                    posis2=10;
                    ank2="10";


                }else if(siangkotnya.equals("[Cimbuleuit - ST Hall via Eykman]")){
                    posis2=11;
                    ank2="11A";


                }else if(siangkotnya.equals("[Cimbuleuit - ST Hall via Cihampelas]")){
                    posis2=12;
                    ank2="11B";


                }else if(siangkotnya.equals("[ST Hall - Gedebage]")){
                    posis2=13;
                    ank2="12";


                }else if(siangkotnya.equals("[ST Hall - Sarijadi]")){
                    posis2=14;
                    ank2="13";


                }else if(siangkotnya.equals("[ST Hall - Gunung Batu]")){
                    posis2=15;
                    ank2="14";


                }else if(siangkotnya.equals("[Margahayu - Ledeng]")){
                    posis2=16;
                    ank2="15";


                }else if(siangkotnya.equals("[Riung Bandung - Dago]")){
                    posis2=17;
                    ank2="16";


                }else if(siangkotnya.equals("[Caringin - Dago]")){
                    posis2=18;
                    ank2="17";


                }else if(siangkotnya.equals("[Panghegar - Dipatiukur]")){
                    posis2=19;
                    ank2="18";


                }else if(siangkotnya.equals("[Ciroyom - Sarijadi via Sukajadi]")){
                    posis2=20;
                    ank2="19A";


                }else if(siangkotnya.equals("[Ciroyom - Sarijadi via Setrasari]")){
                    posis2=21;
                    ank2="19B";


                }else if(siangkotnya.equals("[Bumi Asri - Ciroyom]")){
                    posis2=22;
                    ank2="20";


                }else if(siangkotnya.equals("[Cikudapateh - Ciroyom]")){
                    posis2=23;
                    ank2="21";


                }else if(siangkotnya.equals("[Buah Batu - Sederhana]")){
                    posis2=24;
                    ank2="22";


                }else if(siangkotnya.equals("[Cijerah - Sederhana]")){
                    posis2=25;
                    ank2="23";


                }else if(siangkotnya.equals("[Sederhana - Cimindi]")){
                    posis2=26;
                    ank2="24";


                }else if(siangkotnya.equals("[Ciwastra - Ujungberung]")){
                    posis2=27;
                    ank2="25";


                }else if(siangkotnya.equals("[Cisitu - Tegalega]")){
                    posis2=28;
                    ank2="26";


                }else if(siangkotnya.equals("[Cijerah - Ciwastra]")){
                    posis2=29;
                    ank2="27";


                }else if(siangkotnya.equals("[Elang - Gedebage]")){
                    posis2=30;
                    ank2="28";


                }else if(siangkotnya.equals("[Abdulmuis - Mengger]")){
                    posis2=31;
                    ank2="29";


                }else if(siangkotnya.equals("[Cicadas - Elang]")){
                    posis2=32;
                    ank2="30";


                }else if(siangkotnya.equals("[Antapani - Ciroyom]")){
                    posis2=33;
                    ank2="31";


                }else if(siangkotnya.equals("[Cibiru - Cicadas]")){
                    posis2=34;
                    ank2="32";


                }else if(siangkotnya.equals("[Sekemirung - Panyileukan]")){
                    posis2=35;
                    ank2="33";


                }else if(siangkotnya.equals("[Caringin - Sadang Serang]")){
                    posis2=36;
                    ank2="34";


                }else if(siangkotnya.equals("[Cibaduyut - Karang Setra]")){
                    posis2=37;
                    ank2="35";


                }else if(siangkotnya.equals("[Cibogo - Elang]")){
                    posis2=38;
                    ank2="36";


                }
                __global_yourCoordinate_exist2=new LatLng(lats, lngs);
                llat=lats; llong=lngs;
                mMap.addMarker(new MarkerOptions()
                        .position(simpulAngkot)
                        .title(ank2)
                        .snippet(warna_+siAngkot)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ank1)));
//                mMap.setInfoWindowAdapter(new MyInfoWindowAdapter2());

                PolylineOptions jalurBiasa = new PolylineOptions();
                //jalurBiasa.addAll(lat_lng_gbr).width(15).color(0xff444444).geodesic(true);
                jalurBiasa.addAll(lat_lng_gbr).width(15).color(warna_).geodesic(true);
                mMap.addPolyline(jalurBiasa);

            }else if(r == 1){
//                __global_yourCoordinate_exist2=new LatLng(lats, lngs);
                llat2=lats; llong2=lngs;
                __global_yourCoordinate_exist3=new LatLng(lats, lngs);
                siangkotnya2=siAngkot;

                if(siangkotnya2!="[]"){

                    if(siangkotnya2.equals("[Abdulmuis - Cicaheum via Binong]")){
                        posis3=0;
                        ank3="01A";


                    }
                    else if(siangkotnya2.equals("[Abdulmuis - Cicaheum via Aceh]")){
                        posis3=1;
                        ank3="01B";


                    }else if(siangkotnya2.equals("[Abdulmuis - Dago]")){
                        posis3=2;
                        ank3="02";


                    }else if(siangkotnya2.equals("[Abdulmuis - Ledeng]")){
                        posis3=3;
                        ank3="03";


                    }else if(siangkotnya2.equals("[Abdulmuis - Elang]")){
                        posis3=4;
                        ank3="04";


                    }else if(siangkotnya2.equals("[Cicaheum - Ledeng]")){
                        posis3=5;
                        ank3="05";


                    }else if(siangkotnya2.equals("[Cicaheum - Ciroyom]")){
                        posis3=6;
                        ank3="06";


                    }else if(siangkotnya2.equals("[Cicaheum - Ciwastra]")){
                        posis3=7;
                        ank3="07";


                    }else if(siangkotnya2.equals("[Cicaheum - Cibaduyut]")){
                        posis3=8;
                        ank3="08";


                    }else if(siangkotnya2.equals("[Dago - ST Hall]")){
                        posis3=9;
                        ank3="09";


                    }else if(siangkotnya2.equals("[ST Hall - Sadang Serang]")){
                        posis3=10;
                        ank3="10";


                    }else if(siangkotnya2.equals("[Cimbuleuit - ST Hall via Eykman]")){
                        posis3=11;
                        ank3="11A";


                    }else if(siangkotnya2.equals("[Cimbuleuit - ST Hall via Cihampelas]")){
                        posis3=12;
                        ank3="11B";


                    }else if(siangkotnya2.equals("[ST Hall - Gedebage]")){
                        posis3=13;
                        ank3="12";


                    }else if(siangkotnya2.equals("[ST Hall - Sarijadi]")){
                        posis3=14;
                        ank3="13";


                    }else if(siangkotnya2.equals("[ST Hall - Gunung Batu]")){
                        posis3=15;
                        ank3="14";


                    }else if(siangkotnya2.equals("[Margahayu - Ledeng]")){
                        posis3=16;
                        ank3="15";


                    }else if(siangkotnya2.equals("[Riung Bandung - Dago]")){
                        posis3=17;
                        ank3="16";


                    }else if(siangkotnya2.equals("[Caringin - Dago]")){
                        posis3=18;
                        ank3="17";


                    }else if(siangkotnya2.equals("[Panghegar - Dipatiukur]")){
                        posis3=19;
                        ank3="18";


                    }else if(siangkotnya2.equals("[Ciroyom - Sarijadi via Sukajadi]")){
                        posis3=20;
                        ank3="19A";


                    }else if(siangkotnya2.equals("[Ciroyom - Sarijadi via Setrasari]")){
                        posis3=21;
                        ank3="19B";


                    }else if(siangkotnya2.equals("[Bumi Asri - Ciroyom]")){
                        posis3=22;
                        ank3="20";


                    }else if(siangkotnya2.equals("[Cikudapateh - Ciroyom]")){
                        posis3=23;
                        ank3="21";


                    }else if(siangkotnya2.equals("[Buah Batu - Sederhana]")){
                        posis3=24;
                        ank3="22";


                    }else if(siangkotnya2.equals("[Cijerah - Sederhana]")){
                        posis3=25;
                        ank3="23";


                    }else if(siangkotnya2.equals("[Sederhana - Cimindi]")){
                        posis3=26;
                        ank3="24";


                    }else if(siangkotnya2.equals("[Ciwastra - Ujungberung]")){
                        posis3=27;
                        ank3="25";


                    }else if(siangkotnya2.equals("[Cisitu - Tegalega]")){
                        posis3=28;
                        ank3="26";


                    }else if(siangkotnya2.equals("[Cijerah - Ciwastra]")){
                        posis3=29;
                        ank3="27";


                    }else if(siangkotnya2.equals("[Elang - Gedebage]")){
                        posis3=30;
                        ank3="28";


                    }else if(siangkotnya2.equals("[Abdulmuis - Mengger]")){
                        posis3=31;
                        ank3="29";


                    }else if(siangkotnya2.equals("[Cicadas - Elang]")){
                        posis3=32;
                        ank3="30";


                    }else if(siangkotnya2.equals("[Antapani - Ciroyom]")){
                        posis3=33;
                        ank3="31";


                    }else if(siangkotnya2.equals("[Cibiru - Cicadas]")){
                        posis3=34;
                        ank3="32";


                    }else if(siangkotnya2.equals("[Sekemirung - Panyileukan]")){
                        posis3=35;
                        ank3="33";


                    }else if(siangkotnya2.equals("[Caringin - Sadang Serang]")){
                        posis3=36;
                        ank3="34";


                    }else if(siangkotnya2.equals("[Cibaduyut - Karang Setra]")){
                        posis3=37;
                        ank3="35";


                    }else if(siangkotnya2.equals("[Cibogo - Elang]")){
                        posis3=38;
                        ank3="36";


                    }

                    mMap.addMarker(new MarkerOptions()
                            .position(simpulAngkot)
                            .title(ank3)
                            .snippet(warna_+siangkotnya2)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pindah1)));
//                    mMap.setInfoWindowAdapter(new MyInfoWindowAdapter3());

                    PolylineOptions jalurBiasa = new PolylineOptions();
                    //jalurBiasa.addAll(lat_lng_gbr).width(15).color(0xffff00ff).geodesic(true);
                    jalurBiasa.addAll(lat_lng_gbr).width(15).color(warna_).geodesic(true);
                    mMap.addPolyline(jalurBiasa);
                }

            }


        }




        ///siangkot 2 kosong
             if(siangkotnya2!="[]"){

            try {

                v2GetRouteDirection = new GMapV2GetRouteDirection();

                GetRouteTask3 getRoute = new GetRouteTask3();
                getRoute.execute();
//            GetRouteTask4 getRoute1 = new GetRouteTask4();
//            getRoute1.execute();
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                String[] angkot1 = siangkotnya.split(",");
                String angkott = angkot1[0];

//                CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                CalculationByDistance3(lokasiuserpertama, __global_yourCoordinate_exist2);
                CalculationByDistance(__global_yourCoordinate_exist2, __global_yourCoordinate_exist3);
                CalculationByDistance4(__global_yourCoordinate_exist3, endl);
//                CalculationByDistance5(endl, endx);
                CalculationByDistance5(endl, lokasitujuanpertama);
                TextView textView14 = (TextView) findViewById(R.id.textView14);
                TextView textView15 = (TextView) findViewById(R.id.textView15);
                TextView textView19 = (TextView) findViewById(R.id.textView19);
                Button satu = (Button) findViewById(R.id.button);
                Button dua = (Button) findViewById(R.id.button2);
                Button tiga = (Button) findViewById(R.id.button3);
                // ListView lvdt= (ListView) findViewById(R.id.lvdt);
                //textView14.setText(angkott);
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


                /*if (angkott.equals("[ST Hall - Gedebage") || angkott.equals("[ST Hall - Gedebage]")) {
                    posi = 12;
                    nmank = "12";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[ST Hall - Sarijadi") || angkott.equals("[ST Hall - Sarijadi]")) {
                    posi = 13;
                    nmank = "13";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cikudapateh - Ciroyom") || angkott.equals("[Cikudapateh - Ciroyom]")) {
                    posi = 21;
                    nmank = "21";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cimbuleuit - ST Hall via Eykman") || angkott.equals("[Cimbuleuit - ST Hall via Eykman]")) {
                    posi = 11;
                    nmank = "11";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")) {
                    posi = 22;
                    nmank = "22";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Dago - ST Hall") || angkott.equals("[Dago - ST Hall]")) {
                    posi = 9;
                    nmank = "09";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[ST Hall - Gunung Batu") || angkott.equals("[ST Hall - Gunung Batu]")) {
                    posi = 14;
                    nmank = "14";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Margahayu - Ledeng") || angkott.equals("[Margahayu - Ledeng]")) {
                    posi = 15;
                    nmank = "15";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (siangkotnya.equals("[Abdulmuis - Ledeng") || siangkotnya.equals("[Abdulmuis - Ledeng]")) {
                    posi = 3;
                    nmank = "03";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cicaheum - Ciwastra") || angkott.equals("[Cicaheum - Ciwastra]")) {
                    posi = 7;
                    nmank = "07";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Caringin - Sadang Serang") || angkott.equals("[Caringin - Sadang Serang]")) {
                    posi = 31;
                    nmank = "34";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Caringin - Dago") || angkott.equals("[Caringin - Dago]")) {
                    posi = 17;
                    nmank = "17";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cicaheum - Ledeng") || angkott.equals("[Cicaheum - Ledeng]")) {
                    posi = 5;
                    nmank = "05";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Bumi Asri - Ciroyom") || angkott.equals("[Bumi Asri - Ciroyom]")) {
                    posi = 20;
                    nmank = "20";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Ciroyom - Sarijadi via Sukajadi") || angkott.equals("[Ciroyom - Sarijadi via Sukajadi]")) {
                    posi = 19;
                    nmank = "19";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Cisitu - Tegalega") || angkott.equals("[Cisitu - Tegalega]")) {
                    posi = 25;
                    nmank = "26";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Abdulmuis - Elang") || angkott.equals("[Abdulmuis - Elang]")) {
                    posi = 4;
                    nmank = "04";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cicaheum - Ciroyom") || angkott.equals("[Cicaheum - Ciroyom]")) {
                    posi = 6;
                    nmank = "06";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cicaheum - Cibaduyut") || angkott.equals("[Cicaheum - Cibaduyut]")) {
                    posi = 8;
                    nmank = "08";
                    satu.setBackgroundResource(gambar[posi]);
                    // satu.setText(nmank);
                } else if (angkott.equals("[Cijerah - Ciwastra") || angkott.equals("[Cijerah - Ciwastra]")) {
                    posi = 26;
                    nmank = "27";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cibiru - Cicadas") || angkott.equals("[Cibiru - Cicadas]")) {
                    posi = 29;
                    nmank = "32";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cibaduyut - Karang Setra") || angkott.equals("[Cibaduyut - Karang Setra]")) {
                    posi = 32;
                    nmank = "35";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Cibogo - Elang") || angkott.equals("[Cibogo - Elang]")) {
                    posi = 33;
                    nmank = "36";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Antapani - Ciroyom") || angkott.equals("[Antapani - Ciroyom]")) {
                    posi = 28;
                    nmank = "31";
                    satu.setBackgroundResource(gambar[posi]);
                    //satu.setText(nmank);
                } else if (angkott.equals("[Riung Bandung - Dago") || angkott.equals("[Riung Bandung - Dago]")) {
                    posi = 16;
                    nmank = "16";
                    satu.setBackgroundResource(gambar[posi]);
                    ///satu.setText(nmank);
                } else if (angkott.equals("[Abdulmuis - Cicaheum via Aceh") || angkott.equals("[Abdulmuis - Cicaheum via Aceh]")) {
                    posi = 0;
                    nmank = "01B";
                    satu.setBackgroundResource(gambar[posi]);
                    ///satu.setText(nmank);
                } else if (angkott.equals("[Abdulmuis - Ledeng") || angkott.equals("[Abdulmuis - Ledeng]")) {
                    posi = 3;
                    nmank = "03";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[ST Hall - Sadang Serang") || angkott.equals("[ST Hall - Sadang Serang]")) {
                    posi = 10;
                    nmank = "10";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")) {
                    posi = 22;
                    nmank = "22";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[Abdulmuis - Dago") || angkott.equals("[Abdulmuis - Dago]")) {
                    posi = 2;
                    nmank = "02";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[Panghegar - Dipatiukur") || angkott.equals("[Panghegar - Dipatiukur]")) {
                    posi = 18;
                    nmank = "19A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[Cijerah - Sederhana") || angkott.equals("[Cijerah - Sederhana]")) {
                    posi = 23;
                    nmank = "23";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                } else if (angkott.equals("[Cicadas - Elang") || angkott.equals("[Cicadas - Elang]")) {
                    posi = 27;
                    nmank = "30";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }*/

                if(angkott.equals("[Abdulmuis - Cicaheum via Binong") || angkott.equals("[Abdulmuis - Cicaheum via Binong]")){
                    posi=0;
                    nmank="01A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);

                }
                else if(angkott.equals("[Abdulmuis - Cicaheum via Aceh") || angkott.equals("[Abdulmuis - Cicaheum via Aceh]")){
                    posi=1;
                    nmank="01B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Abdulmuis - Dago") || angkott.equals("[Abdulmuis - Dago]")){
                    posi=2;
                    nmank="02";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Abdulmuis - Ledeng") || angkott.equals("[Abdulmuis - Ledeng]")){
                    posi=3;
                    nmank="03";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Abdulmuis - Elang") || angkott.equals("[Abdulmuis - Elang]")){
                    posi=4;
                    nmank="04";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cicaheum - Ledeng") || angkott.equals("[Cicaheum - Ledeng]")){
                    posi=5;
                    nmank="05";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cicaheum - Ciroyom") || angkott.equals("[Cicaheum - Ciroyom]")){
                    posi=6;
                    nmank="06";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cicaheum - Ciwastra") || angkott.equals("[Cicaheum - Ciwastra]")){
                    posi=7;
                    nmank="07";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cicaheum - Cibaduyut") || angkott.equals("[Cicaheum - Cibaduyut]")){
                    posi=8;
                    nmank="08";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Dago - ST Hall") || angkott.equals("[Dago - ST Hall]")){
                    posi=9;
                    nmank="09";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[ST Hall - Sadang Serang") || angkott.equals("[ST Hall - Sadang Serang]")){
                    posi=10;
                    nmank="10";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cimbuleuit - ST Hall via Eykman") || angkott.equals("[Cimbuleuit - ST Hall via Eykman]")){
                    posi=11;
                    nmank="11A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cimbuleuit - ST Hall via Cihampelas") || angkott.equals("[Cimbuleuit - ST Hall via Cihampelas]")){
                    posi=12;
                    nmank="11B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[ST Hall - Gedebage") || angkott.equals("[ST Hall - Gedebage]")){
                    posi=13;
                    nmank="12";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[ST Hall - Sarijadi") || angkott.equals("[ST Hall - Sarijadi]")){
                    posi=14;
                    nmank="13";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[ST Hall - Gunung Batu") || angkott.equals("[ST Hall - Gunung Batu]")){
                    posi=15;
                    nmank="14";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Margahayu - Ledeng") || angkott.equals("[Margahayu - Ledeng]")){
                    posi=16;
                    nmank="15";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Riung Bandung - Dago") || angkott.equals("[Riung Bandung - Dago]")){
                    posi=17;
                    nmank="16";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Caringin - Dago") || angkott.equals("[Caringin - Dago]")){
                    posi=18;
                    nmank="17";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Panghegar - Dipatiukur") || angkott.equals("[Panghegar - Dipatiukur]")){
                    posi=19;
                    nmank="18";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Ciroyom - Sarijadi via Sukajadi") || angkott.equals("[Ciroyom - Sarijadi via Sukajadi]")){
                    posi=20;
                    nmank="19A";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Ciroyom - Sarijadi via Setrasari") || angkott.equals("[Ciroyom - Sarijadi via Setrasari]")){
                    posi=21;
                    nmank="19B";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Bumi Asri - Ciroyom") || angkott.equals("[Bumi Asri - Ciroyom]")){
                    posi=22;
                    nmank="20";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cikudapateh - Ciroyom") || angkott.equals("[Cikudapateh - Ciroyom]")){
                    posi=23;
                    nmank="21";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")){
                    posi=24;
                    nmank="22";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cijerah - Sederhana") || angkott.equals("[Cijerah - Sederhana]")){
                    posi=25;
                    nmank="23";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Sederhana - Cimindi") || angkott.equals("[Sederhana - Cimindi]")){
                    posi=26;
                    nmank="24";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Ciwastra - Ujungberung") || angkott.equals("[Ciwastra - Ujungberung]")){
                    posi=27;
                    nmank="25";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cisitu - Tegalega") || angkott.equals("[Cisitu - Tegalega]")){
                    posi=28;
                    nmank="26";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cijerah - Ciwastra") || angkott.equals("[Cijerah - Ciwastra]")){
                    posi=29;
                    nmank="27";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Elang - Gedebage") || angkott.equals("[Elang - Gedebage]")){
                    posi=30;
                    nmank="28";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Abdulmuis - Mengger") || angkott.equals("[Abdulmuis - Mengger]")){
                    posi=31;
                    nmank="29";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("Cicadas - Elang")){
                    posi=32;
                    nmank="30";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Antapani - Ciroyom") || angkott.equals("[Antapani - Ciroyom]")){
                    posi=33;
                    nmank="31";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cibiru - Cicadas") || angkott.equals("[Cibiru - Cicadas]")){
                    posi=34;
                    nmank="32";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Sekemirung - Panyileukan") || angkott.equals("[Sekemirung - Panyileukan]")){
                    posi=35;
                    nmank="33";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Caringin - Sadang Serang") || angkott.equals("[Caringin - Sadang Serang]")){
                    posi=36;
                    nmank="34";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cibaduyut - Karang Setra") || angkott.equals("[Cibaduyut - Karang Setra]")){
                    posi=37;
                    nmank="35";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }else if(angkott.equals("[Cibogo - Elang") || angkott.equals("[Cibogo - Elang]")){
                    posi=38;
                    nmank="36";
                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                }
//                else{
//                    posi=28;
//                    nmank="31";
//                    satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
//                }
//            textView14.setText("Nama Trayek :" + angkott);
                textView14.setText("Nama Trayek :" + siangkotnya + " -> " + siangkotnya2);
//            textView15.setText("Jarak :" + jarakkm);
                textView15.setText(" ");
                textView19.setText("Total Ongkos :" + hargarute);


                ///adress1
                String filterAddressh = "";
                Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                    if (addresses.size() > 0) {

                        for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                    }
                    address2[0] = String.valueOf(filterAddressh);
                    //address3[0] = String.valueOf(filterAddressh);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }


//                 /adress2
                String filterAddress2 = "";
                Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                    if (addresses2.size() > 0) {

                        for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                    }

                    // address2[1] = String.valueOf(filterAddress2);
                    address3[0] = String.valueOf(filterAddress2);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }


                ///adress3
                String filterAddress3 = "";
                Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                    if (addresses3.size() > 0) {

                        for (int i = 0; i < 1; i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                            filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                    }

                    address2[1] = String.valueOf(filterAddress3);
                    ///address3[2] = String.valueOf(filterAddress3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }

                ///adress4
                String filterAddress4 = "";
                Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses4 = geoCoder4.getFromLocation(llat2, lngUser1, 1);

                    if (addresses4.size() > 0) {

                        for (int i = 0; i < 1; i++)
                            filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                    }

                    // address2[3] = String.valueOf(filterAddress4);
                    address3[1] = String.valueOf(filterAddress4);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
                ///adress5
                String filterAddress5 = "";
                Geocoder geoCoder5 = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses5 = geoCoder5.getFromLocation(latUser1, lngUser1, 1);

                    if (addresses5.size() > 0) {

                        for (int i = 0; i < 1; i++)
                            filterAddress5 += addresses5.get(0).getAddressLine(i) + " ";
                    }


                    address2[2] = String.valueOf(filterAddress5);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }

                address3[2] = "Selanjutnya naik Angkot " + siangkotnya2 + "<br/>sejauh <i>" + jarak22[2] + " km</i> \n<br/>Ongkos <i>biasanya sih</i>" + hargarute3 + " (?)";
                address2[3] = siangkotnya + " <br/>sejauh <i>" + jarak22[1] + " m</i>\n<br/>Ongkos <i>biasanya sih</i>" + hargarute + " (?)";
                address3[3] = "Selanjutnya naik angkot     " + siangkotnya2 + "\ndengan ongkos sebesar" + hargarute3 + " (?)";


                CustomListRute2 customList = new CustomListRute2(Tes2.this, address2, address3, imageid7, jarak22);
                lvdt = (ListView) findViewById(R.id.lvdt);
                lvdt.setAdapter(customList);

                fab5.setVisibility(View.VISIBLE);
                fab3.setVisibility(View.GONE);
            } catch (Exception e) {
            }

             }
             else if(siangkotnya2=="[]"){
                 try {
//                 Toast.makeText(getApplicationContext(), __global_yourCoordinate_exist+"1"+__global_yourCoordinate_exist2+"status", Toast.LENGTH_LONG).show();

                     v2GetRouteDirection = new GMapV2GetRouteDirection();

                     GetRouteTask3 getRoute = new GetRouteTask3();
                     getRoute.execute();
                     bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));


//                     CalculationByDistance3(__global_yourCoordinate_exist, __global_yourCoordinate_exist2);
                     CalculationByDistance3(lokasiuserpertama, __global_yourCoordinate_exist2);
                     CalculationByDistance(__global_yourCoordinate_exist2, endl);
//                     CalculationByDistance4(endl, endx);
                     CalculationByDistance4(endl, lokasitujuanpertama);
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
                     String[] angkot1 = siangkotnya.split(",");
                     String angkott = angkot1[0];
                     /*if (angkott.equals("[ST Hall - Gedebage") || angkott.equals("[ST Hall - Gedebage]")) {
                         posi = 12;
                         nmank = "12";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[ST Hall - Sarijadi") || angkott.equals("[ST Hall - Sarijadi]")) {
                         posi = 13;
                         nmank = "13";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cikudapateh - Ciroyom") || angkott.equals("[Cikudapateh - Ciroyom]")) {
                         posi = 21;
                         nmank = "21";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cimbuleuit - ST Hall via Eykman") || angkott.equals("[Cimbuleuit - ST Hall via Eykman]")) {
                         posi = 11;
                         nmank = "11";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")) {
                         posi = 22;
                         nmank = "22";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Dago - ST Hall") || angkott.equals("[Dago - ST Hall]")) {
                         posi = 9;
                         nmank = "09";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[ST Hall - Gunung Batu") || angkott.equals("[ST Hall - Gunung Batu]")) {
                         posi = 14;
                         nmank = "14";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Margahayu - Ledeng") || angkott.equals("[Margahayu - Ledeng]")) {
                         posi = 15;
                         nmank = "15";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (siangkotnya.equals("[Abdulmuis - Ledeng") || siangkotnya.equals("[Abdulmuis - Ledeng]")) {
                         posi = 3;
                         nmank = "03";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cicaheum - Ciwastra") || angkott.equals("[Cicaheum - Ciwastra]")) {
                         posi = 7;
                         nmank = "07";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Caringin - Sadang Serang") || angkott.equals("[Caringin - Sadang Serang]")) {
                         posi = 31;
                         nmank = "34";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Caringin - Dago") || angkott.equals("[Caringin - Dago]")) {
                         posi = 17;
                         nmank = "17";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cicaheum - Ledeng") || angkott.equals("[Cicaheum - Ledeng]")) {
                         posi = 5;
                         nmank = "05";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Bumi Asri - Ciroyom") || angkott.equals("[Bumi Asri - Ciroyom]")) {
                         posi = 20;
                         nmank = "20";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Ciroyom - Sarijadi via Sukajadi") || angkott.equals("[Ciroyom - Sarijadi via Sukajadi]")) {
                         posi = 19;
                         nmank = "19";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Cisitu - Tegalega") || angkott.equals("[Cisitu - Tegalega]")) {
                         posi = 25;
                         nmank = "26";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Abdulmuis - Elang") || angkott.equals("[Abdulmuis - Elang]")) {
                         posi = 4;
                         nmank = "04";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cicaheum - Ciroyom") || angkott.equals("[Cicaheum - Ciroyom]")) {
                         posi = 6;
                         nmank = "06";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cicaheum - Cibaduyut") || angkott.equals("[Cicaheum - Cibaduyut]")) {
                         posi = 8;
                         nmank = "08";
                         satu.setBackgroundResource(gambar[posi]);
                         // satu.setText(nmank);
                     } else if (angkott.equals("[Cijerah - Ciwastra") || angkott.equals("[Cijerah - Ciwastra]")) {
                         posi = 26;
                         nmank = "27";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cibiru - Cicadas") || angkott.equals("[Cibiru - Cicadas]")) {
                         posi = 29;
                         nmank = "32";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cibaduyut - Karang Setra") || angkott.equals("[Cibaduyut - Karang Setra]")) {
                         posi = 32;
                         nmank = "35";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Cibogo - Elang") || angkott.equals("[Cibogo - Elang]")) {
                         posi = 33;
                         nmank = "36";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Antapani - Ciroyom") || angkott.equals("[Antapani - Ciroyom]")) {
                         posi = 28;
                         nmank = "31";
                         satu.setBackgroundResource(gambar[posi]);
                         //satu.setText(nmank);
                     } else if (angkott.equals("[Riung Bandung - Dago") || angkott.equals("[Riung Bandung - Dago]")) {
                         posi = 16;
                         nmank = "16";
                         satu.setBackgroundResource(gambar[posi]);
                         ///satu.setText(nmank);
                     } else if (angkott.equals("[Abdulmuis - Cicaheum via Aceh") || angkott.equals("[Abdulmuis - Cicaheum via Aceh]")) {
                         posi = 0;
                         nmank = "01B";
                         satu.setBackgroundResource(gambar[posi]);
                         ///satu.setText(nmank);
                     } else if (angkott.equals("[Abdulmuis - Ledeng") || angkott.equals("[Abdulmuis - Ledeng]")) {
                         posi = 3;
                         nmank = "03";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[ST Hall - Sadang Serang") || angkott.equals("[ST Hall - Sadang Serang]")) {
                         posi = 10;
                         nmank = "10";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")) {
                         posi = 22;
                         nmank = "22";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[Abdulmuis - Dago") || angkott.equals("[Abdulmuis - Dago]")) {
                         posi = 2;
                         nmank = "02";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[Panghegar - Dipatiukur") || angkott.equals("[Panghegar - Dipatiukur]")) {
                         posi = 18;
                         nmank = "19A";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[Cijerah - Sederhana") || angkott.equals("[Cijerah - Sederhana]")) {
                         posi = 23;
                         nmank = "23";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     } else if (angkott.equals("[Cicadas - Elang") || angkott.equals("[Cicadas - Elang]")) {
                         posi = 27;
                         nmank = "30";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }*/


                     if(angkott.equals("[Abdulmuis - Cicaheum via Binong") || angkott.equals("[Abdulmuis - Cicaheum via Binong]")){
                         posi=0;
                         nmank="01A";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);

                     }
                     else if(angkott.equals("[Abdulmuis - Cicaheum via Aceh") || angkott.equals("[Abdulmuis - Cicaheum via Aceh]")){
                         posi=1;
                         nmank="01B";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Abdulmuis - Dago") || angkott.equals("[Abdulmuis - Dago]")){
                         posi=2;
                         nmank="02";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Abdulmuis - Ledeng") || angkott.equals("[Abdulmuis - Ledeng]")){
                         posi=3;
                         nmank="03";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Abdulmuis - Elang") || angkott.equals("[Abdulmuis - Elang]")){
                         posi=4;
                         nmank="04";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cicaheum - Ledeng") || angkott.equals("[Cicaheum - Ledeng]")){
                         posi=5;
                         nmank="05";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cicaheum - Ciroyom") || angkott.equals("[Cicaheum - Ciroyom]")){
                         posi=6;
                         nmank="06";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cicaheum - Ciwastra") || angkott.equals("[Cicaheum - Ciwastra]")){
                         posi=7;
                         nmank="07";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cicaheum - Cibaduyut") || angkott.equals("[Cicaheum - Cibaduyut]")){
                         posi=8;
                         nmank="08";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Dago - ST Hall") || angkott.equals("[Dago - ST Hall]")){
                         posi=9;
                         nmank="09";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[ST Hall - Sadang Serang") || angkott.equals("[ST Hall - Sadang Serang]")){
                         posi=10;
                         nmank="10";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cimbuleuit - ST Hall via Eykman") || angkott.equals("[Cimbuleuit - ST Hall via Eykman]")){
                         posi=11;
                         nmank="11A";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cimbuleuit - ST Hall via Cihampelas") || angkott.equals("[Cimbuleuit - ST Hall via Cihampelas]")){
                         posi=12;
                         nmank="11B";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[ST Hall - Gedebage") || angkott.equals("[ST Hall - Gedebage]")){
                         posi=13;
                         nmank="12";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[ST Hall - Sarijadi") || angkott.equals("[ST Hall - Sarijadi]")){
                         posi=14;
                         nmank="13";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[ST Hall - Gunung Batu") || angkott.equals("[ST Hall - Gunung Batu]")){
                         posi=15;
                         nmank="14";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Margahayu - Ledeng") || angkott.equals("[Margahayu - Ledeng]")){
                         posi=16;
                         nmank="15";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Riung Bandung - Dago") || angkott.equals("[Riung Bandung - Dago]")){
                         posi=17;
                         nmank="16";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Caringin - Dago") || angkott.equals("[Caringin - Dago]")){
                         posi=18;
                         nmank="17";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Panghegar - Dipatiukur") || angkott.equals("[Panghegar - Dipatiukur]")){
                         posi=19;
                         nmank="18";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Ciroyom - Sarijadi via Sukajadi") || angkott.equals("[Ciroyom - Sarijadi via Sukajadi]")){
                         posi=20;
                         nmank="19A";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Ciroyom - Sarijadi via Setrasari") || angkott.equals("[Ciroyom - Sarijadi via Setrasari]")){
                         posi=21;
                         nmank="19B";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Bumi Asri - Ciroyom") || angkott.equals("[Bumi Asri - Ciroyom]")){
                         posi=22;
                         nmank="20";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cikudapateh - Ciroyom") || angkott.equals("[Cikudapateh - Ciroyom]")){
                         posi=23;
                         nmank="21";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Buah Batu - Sederhana") || angkott.equals("[Buah Batu - Sederhana]")){
                         posi=24;
                         nmank="22";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cijerah - Sederhana") || angkott.equals("[Cijerah - Sederhana]")){
                         posi=25;
                         nmank="23";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Sederhana - Cimindi") || angkott.equals("[Sederhana - Cimindi]")){
                         posi=26;
                         nmank="24";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Ciwastra - Ujungberung") || angkott.equals("[Ciwastra - Ujungberung]")){
                         posi=27;
                         nmank="25";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cisitu - Tegalega") || angkott.equals("[Cisitu - Tegalega]")){
                         posi=28;
                         nmank="26";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cijerah - Ciwastra") || angkott.equals("[Cijerah - Ciwastra]")){
                         posi=29;
                         nmank="27";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Elang - Gedebage") || angkott.equals("[Elang - Gedebage]")){
                         posi=30;
                         nmank="28";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Abdulmuis - Mengger") || angkott.equals("[Abdulmuis - Mengger]")){
                         posi=31;
                         nmank="29";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("Cicadas - Elang")){
                         posi=32;
                         nmank="30";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Antapani - Ciroyom") || angkott.equals("[Antapani - Ciroyom]")){
                         posi=33;
                         nmank="31";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cibiru - Cicadas") || angkott.equals("[Cibiru - Cicadas]")){
                         posi=34;
                         nmank="32";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Sekemirung - Panyileukan") || angkott.equals("[Sekemirung - Panyileukan]")){
                         posi=35;
                         nmank="33";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Caringin - Sadang Serang") || angkott.equals("[Caringin - Sadang Serang]")){
                         posi=36;
                         nmank="34";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cibaduyut - Karang Setra") || angkott.equals("[Cibaduyut - Karang Setra]")){
                         posi=37;
                         nmank="35";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }else if(angkott.equals("[Cibogo - Elang") || angkott.equals("[Cibogo - Elang]")){
                         posi=38;
                         nmank="36";
                         satu.setBackgroundResource(gambar[posi]);
//                    satu.setText(nmank);
                     }


                     textView14.setText("Nama Trayek :" + siangkotnya);
                     textView15.setText("Jarak :" + jarakkm);

//                int jrk= Integer.parseInt(jarakkm);
//                int jrkkrg2=jrk-2;
////                int jrkkrg22=jrk-2;
//                int jrkbagi2=jrkkrg2/2;
//                int jrkbagi22=jrkkrg2/2;
//
//                if(jrk<=2){
//                    harganya1="Rp.2000";
//                }
////                else if(jrk>=2 && jrkbagi2<=jrkbagi22 ){
////                    int grp=Integer.parseInt(hargaasli)-2000;
////                    harganya1= "Rp."+String.valueOf(grp);
////                }
//                else if(jrkbagi2>=jrk){
//                    harganya1= "Rp."+String.valueOf(Integer.parseInt(hargaasli));
//                }
//
//                textView19.setText("Ongkos :" + harganya1);


                     textView19.setText("Ongkos :" + hargarute);

                     ///adress1
                     String filterAddressh = "";
                     Geocoder geoCoder1 = new Geocoder(getBaseContext(), Locale.getDefault());
                     try {
                         List<Address> addresses = geoCoder1.getFromLocation(latUser, lngUser, 1);

                         if (addresses.size() > 0) {

                             for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                 filterAddressh += addresses.get(0).getAddressLine(i) + " ";
                         }
                         address[0] = String.valueOf(filterAddressh);

                     } catch (IOException ex) {
                         ex.printStackTrace();
                     } catch (Exception e2) {
                         // TODO: handle exception
                         e2.printStackTrace();
                     }
//                 String a=String.valueOf(mAutocompleteTextView.getText().toString());
//
//                 address[1] = String.valueOf(a);
//                 address[2] = String.valueOf(a);
//                 address[3] = String.valueOf(a);

//                 /adress2
                     String filterAddress2 = "";
                     Geocoder geoCoder2 = new Geocoder(getBaseContext(), Locale.getDefault());
                     try {
                         List<Address> addresses2 = geoCoder2.getFromLocation(llat, llong, 1);

                         if (addresses2.size() > 0) {

                             for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                 filterAddress2 += addresses2.get(0).getAddressLine(i) + " ";
                         }

                         address1[0] = String.valueOf(filterAddress2);

                     } catch (IOException ex) {
                         ex.printStackTrace();
                     } catch (Exception e2) {
                         // TODO: handle exception
                         e2.printStackTrace();
                     }
//                 String b=String.valueOf(filterAddress2);
//                 address[1] = String.valueOf(b);
//
                     ///adress3
                     String filterAddress3= "";
                     Geocoder geoCoder3 = new Geocoder(getBaseContext(), Locale.getDefault());
                     try {
                         List<Address> addresses3 = geoCoder3.getFromLocation(klat, klong, 1);

                         if (addresses3.size() > 0) {

                             for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                 filterAddress3 += addresses3.get(0).getAddressLine(i) + " ";
                         }

                         // address[1] = String.valueOf(filterAddress3);
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     } catch (Exception e2) {
                         // TODO: handle exception
                         e2.printStackTrace();
                     }
//                 String c=String.valueOf(filterAddress3);
//                 address[2] = String.valueOf(c);
//
//                 ///adress4
                     String filterAddress4= "";
                     Geocoder geoCoder4 = new Geocoder(getBaseContext(), Locale.getDefault());
                     try {
                         List<Address> addresses4 = geoCoder4.getFromLocation(latUser1, lngUser1, 1);

                         if (addresses4.size() > 0) {

                             for (int i = 0; i <1;i++) //addresses.get(0).getMaxAddressLineIndex(); i++)
                                 filterAddress4 += addresses4.get(0).getAddressLine(i) + " ";
                         }

                         address1[1] = String.valueOf(filterAddress4);
                         address[2] = String.valueOf(filterAddress4);

                     } catch (IOException ex) {
                         ex.printStackTrace();
                     } catch (Exception e2) {
                         // TODO: handle exception
                         e2.printStackTrace();
                     }
//                 String d=String.valueOf(filterAddress4);
//                 address[3] = String.valueOf(d);
                     address1[2] = String.valueOf(hargarute);
                     address[1] = String.valueOf(siangkotnya);
                     CustomListRute customList = new CustomListRute(Tes2.this,address , address1, imageid,jarak);
                     lvdt = (ListView) findViewById(R.id.lvdt);
                     lvdt.setAdapter(customList);

                     fab3.setVisibility(View.VISIBLE);
                     fab5.setVisibility(View.GONE);

                 }catch(Exception e){

                 }
             }



    }

    public void getSimpulAwalAkhirJalur(Get_koordinat_awal_akhir objects, double latx, double lngx, String statusObject) throws JSONException{
//        Toast.makeText(Tes2.this,latx +" gggg"+ lngx, Toast.LENGTH_SHORT).show();


        // return JSON index posisi koordinat, nodes0, nodes1
        JSONObject jStart = objects.Get_simpul(latx, lngx, this);

        // index JSON
        String status = jStart.getString("status");
        int node_simpul_awal0 = jStart.getInt("node_simpul_awal0");
        int node_simpul_awal1 = jStart.getInt("node_simpul_awal1");
        int index_coordinate_json = jStart.getInt("index_coordinate_json");
//        Toast.makeText(getApplicationContext(), status+"status", Toast.LENGTH_LONG).show();

        int fix_simpul_awal = 0;

        // jika koordinat tepat di atas posisi simpul/node
        // maka tidak perlu menambahkan simpul baru
        if(status.equals("jalur_none")){

            //tentukan simpul awal atau akhir yg dekat dgn posisi user
            if(index_coordinate_json == 0){ // awal
                fix_simpul_awal = node_simpul_awal0;
            }else{ // akhir
                fix_simpul_awal = node_simpul_awal1;
            }

            if(statusObject == "awal"){

                // return
                __global_old_simpul_awal = node_simpul_awal0 + "-" + node_simpul_awal1;
                __global_simpul_awal = fix_simpul_awal; // misal 0
            }else{

                // return
                __global_old_simpul_akhir = node_simpul_awal0 + "-" + node_simpul_awal1;
                __global_simpul_akhir = fix_simpul_awal; // misal 0
            }


        }
        // jika koordinat berada diantara simpul 5 dan simpul 4 atau simpul 4 dan simpul 5
        // maka perlu menambahkan simpul baru
        else if(status.equals("jalur_double")){

            // return
            if(statusObject == "awal"){

                // cari simpul (5,4) dan (4-5) di Tambah_simpul.java
                Tambah_simpul obj_tambah = new Tambah_simpul();
                obj_tambah.dobelSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json,
                        this, __global_graphArray, 901
                ); // 401 : row id yg baru


                // return
                __global_old_simpul_awal = obj_tambah.simpul_lama;
                __global_simpul_awal = obj_tambah.simpul_baru; // misal 6
                __global_graphArray = obj_tambah.modif_graph; // graph[][]

            }else{

                // cari simpul (5,4) dan (4-5) di Tambah_simpul.java
                Tambah_simpul obj_tambah = new Tambah_simpul();
                obj_tambah.dobelSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json,
                        this, __global_graphArray, 1001
                ); // 501 : row id yg baru


                // return
                __global_old_simpul_akhir = obj_tambah.simpul_lama;
                __global_simpul_akhir = obj_tambah.simpul_baru; // misal 4
                __global_graphArray = obj_tambah.modif_graph; // graph[][]

            }

        }
        // jika koordinat hanya berada diantara simpul 5 dan simpul 4
        // maka perlu menambahkan simpul baru
        else if(status.equals("jalur_single")){

            if(statusObject == "awal"){

                // cari simpul (5,4) di Tambah_simpul.java
                Tambah_simpul obj_tambah1 = new Tambah_simpul();
                obj_tambah1.singleSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json,
                        this, __global_graphArray, 901
                ); // 401 : row id yg baru


                // return
                __global_old_simpul_awal = obj_tambah1.simpul_lama;
                __global_simpul_awal = obj_tambah1.simpul_baru; // misal 6
                __global_graphArray = obj_tambah1.modif_graph; // graph[][]

            }else{

                // cari simpul (5,4) di Tambah_simpul.java
                Tambah_simpul obj_tambah1 = new Tambah_simpul();
                obj_tambah1.singleSimpul(node_simpul_awal0, node_simpul_awal1, index_coordinate_json,
                        this, __global_graphArray, 1001
                ); // 501 : row id yg baru


                // return
                __global_old_simpul_akhir = obj_tambah1.simpul_lama;
                __global_simpul_akhir = obj_tambah1.simpul_baru; // misal 4
                __global_graphArray = obj_tambah1.modif_graph; // graph[][]
            }
        }
    }

    private class GetRouteTask3 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";

        @Override
        protected String doInBackground(String... urls) {
           // if(__global_yourCoordinate_exist!=null && !__global_yourCoordinate_exist2.equals("")){
//            LatLng a= new LatLng(-6.942286, 107.659405);
//            LatLng b= new LatLng(-6.940539, 107.657989);
//            document = v2GetRouteDirection.getDocument(__global_yourCoordinate_exist2, __global_yourCoordinate_exist, GMapV2GetRouteDirection.MODE_WALKING);
            document = v2GetRouteDirection.getDocument(__global_yourCoordinate_exist2, lokasiuserpertama, GMapV2GetRouteDirection.MODE_WALKING);
//            document2 = v2GetRouteDirection.getDocument(endx, endl, GMapV2GetRouteDirection.MODE_WALKING);
            document2 = v2GetRouteDirection.getDocument(lokasitujuanpertama, endl, GMapV2GetRouteDirection.MODE_WALKING);

           // }
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


                ArrayList<LatLng> directionPoint2 = v2GetRouteDirection.getDirection(document2);
                PolylineOptions rectLine2 = new PolylineOptions().width(10).color(
                        Color.BLACK);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                // Adding route on the map
                mMap.addPolyline(rectLine2);
                GetRouteTask4 getRoute = new GetRouteTask4();
                getRoute.execute();
            }else{
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
            }

           /* Dialog.dismiss();*/
        }
    }

    private class GetRouteTask4 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";

        @Override
        protected String doInBackground(String... urls) {
           // if(__global_yourCoordinate_exist!=null && !__global_yourCoordinate_exist2.equals("")){

            LatLng a = new LatLng(-6.942286, 107.659405);
            LatLng b = new LatLng(-6.940539, 107.657989);
//            document = v2GetRouteDirection.getDocument(endx, endl, GMapV2GetRouteDirection.MODE_WALKING);
            document = v2GetRouteDirection.getDocument(lokasitujuanpertama, endl, GMapV2GetRouteDirection.MODE_WALKING);
          //  }
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

            }else{
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
            }

           /* Dialog.dismiss();*/
        }
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
//            Intent openStep = new Intent(this, Info_jalan.class);
            Intent openStep = new Intent(this,ExpandablelistActivity.class);
            startActivity(openStep);
        } else if (id == R.id.nav_slideshow) {
            Intent openStep = new Intent(this, Info_angkot.class);
            startActivity(openStep);
        } else if (id == R.id.nav_manage) {
            //Intent openStep = new Intent(this, MainActivitySheet.class);
//            Intent openStep = new Intent(this, MainActivitys.class);
//            Intent openStep = new Intent(this, mapfrag.class);
            //Intent openStep = new Intent(this, time.class);
            //Intent openStep = new Intent(this, PathGoogleMapActivity.class);
//            Intent openStep = new Intent(this, mode.class);
//            Intent openStep = new Intent(this, MainActivitydirect.class);

//            Intent openStep = new Intent(this,Home.class);


//            Intent openStep = new Intent(this,ExpandablelistActivity.class);
            Intent openStep = new Intent(this,about.class);
//            Intent openStep = new Intent(this,MainActivitydj.class);
            startActivity(openStep);
        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Do you want to Exit?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user pressed "yes", then he is allowed to exit from application
////                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
////                System.exit(0);
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user select "No", just cancel this dialog and continue with app
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//
////        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());
//			TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
//			tvTitle.setText(nama_trayek[posis]);
//			TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
//			tvSnippet.setText(marker.getSnippet());
            TextView tarif = ((TextView) myContentsView.findViewById(R.id.tarif));
            tarif.setText("Tarif :Rp. "+tarif_);
            TextView jarak = ((TextView) myContentsView.findViewById(R.id.jarak));
            jarak.setText("Jarak :"+jarak_+" Km");
            TextView armada = ((TextView) myContentsView.findViewById(R.id.armada));
            armada.setText("Armada :"+armada_);

            TextView judull = ((TextView) myContentsView.findViewById(R.id.judul));
            judull.setText(angkot_);
            ImageView badge=(ImageView)myContentsView.findViewById(R.id.badge);
            badge.setBackgroundResource(gambar2[posis]);

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    class MyInfoWindowAdapter2 implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter2(){
            myContentsView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());
//			TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
//			tvTitle.setText(nama_trayek[posis]);
//			TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
//			tvSnippet.setText(marker.getSnippet());
            TextView tarif = ((TextView) myContentsView.findViewById(R.id.tarif));
            tarif.setText("Tarif :Rp. "+tarif_);
            TextView jarak = ((TextView) myContentsView.findViewById(R.id.jarak));
            jarak.setText("Jarak :"+jarak_+" Km");
            TextView armada = ((TextView) myContentsView.findViewById(R.id.armada));
            armada.setText("Armada :"+armada_);

            TextView judull = ((TextView) myContentsView.findViewById(R.id.judul));
            judull.setText(angkot_);
            ImageView badge=(ImageView)myContentsView.findViewById(R.id.badge);
            badge.setBackgroundResource(gambar2[posis2]);

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    class MyInfoWindowAdapter3 implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter3(){
            myContentsView = getLayoutInflater().inflate(R.layout.layout_popup, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());
//			TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
//			tvTitle.setText(nama_trayek[posis]);
//			TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
//			tvSnippet.setText(marker.getSnippet());
            TextView tarif = ((TextView) myContentsView.findViewById(R.id.tarif));
            tarif.setText("Tarif :Rp. "+tarif_);
            TextView jarak = ((TextView) myContentsView.findViewById(R.id.jarak));
            jarak.setText("Jarak :"+jarak_+" Km");
            TextView armada = ((TextView) myContentsView.findViewById(R.id.armada));
            armada.setText("Armada :"+armada_);

            TextView judull = ((TextView) myContentsView.findViewById(R.id.judul));
            judull.setText(angkot_);
            ImageView badge=(ImageView)myContentsView.findViewById(R.id.badge);
            badge.setBackgroundResource(gambar2[posis3]);

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    // Setting a custom info window adapter for the google map

}
