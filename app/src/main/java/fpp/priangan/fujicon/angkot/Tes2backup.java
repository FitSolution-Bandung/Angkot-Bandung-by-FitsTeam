package fpp.priangan.fujicon.angkot;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fpp.priangan.fujicon.angkot.rute.JSONParser;
import fpp.priangan.fujicon.angkot.sheet.GMapV2GetRouteDirection;


public class Tes2backup extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener, LocationListener,GoogleMap.OnMapClickListener {

    ProgressDialog pDialog;
    int lm = 0;
    JSONArray college = null;

    TextView lat_pergi_akhirr,lat_pergi_awalr;
    String latlong, line;
    int id_latlong_pergi;
    double latpergi,longpergi,lattujuan,longtujuan,latpergiarange,longpergiarange,latpergiarange3,longpergiarange3,latpergiarange2,longpergiarange2,latpergiarange1,longpergiarange1;
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
    ViewPager viewPager;
    double[] lat1 ;
    double[] long1;
    double lat_pergi_awal,lat_pergi_akhir;
   // private TextView km;
    /*private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mIdTextView;
    private TextView mPhoneTextView;
    private TextView mWebTextView;
    private TextView mAttTextView;*/
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(-6.917464, 107.619123), new LatLng(-6.916887, 107.618217));
    ImageView delete,delete2;
    BottomSheetLayout bottomSheetLayout;
    TextView kmm,hrg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tes2);
        //setContentView(R.layout.custom_viewsheet);
        mGoogleApiClient = new GoogleApiClient.Builder(Tes2backup.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();




        viewPager = (ViewPager) findViewById(R.id.pager);
        kmm = (TextView) findViewById(R.id.km);
        hrg = (TextView) findViewById(R.id.hrg);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
//        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        View vi = inflater.inflate(R.layout.custom_viewsheet, null); //log.xml is your file.
//        TextView tv = (TextView)vi.findViewById(R.id.hrg);
//        tv.setText("hELLLLOO");

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                viewPager.setVisibility(ImageView.GONE);

            }
        });



        mAutocompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        latt = (TextView) findViewById(R.id.latt);
        longg = (TextView) findViewById(R.id.longg);
        idid = (TextView) findViewById(R.id.idid);
        mAutocompleteTextView.setThreshold(3);
        mAutocompleteTextView2.setThreshold(3);
        /*mNameTextView = (TextView) findViewById(R.id.name);
        mAddressTextView = (TextView) findViewById(R.id.address);
        mIdTextView = (TextView) findViewById(R.id.place_id);
        mPhoneTextView = (TextView) findViewById(R.id.phone);
        mWebTextView = (TextView) findViewById(R.id.web);
        mAttTextView = (TextView) findViewById(R.id.att);*/
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mAutocompleteTextView2.setOnItemClickListener(mAutocompleteClickListener2);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
        mAutocompleteTextView2.setAdapter(mPlaceArrayAdapter);
        delete=(ImageView)findViewById(R.id.cross);
        delete2=(ImageView)findViewById(R.id.cross2);
        delete2.setOnClickListener(this);
        delete.setOnClickListener(this);

        lat_pergi_awalr=(TextView) findViewById(R.id.lat_pergi_awal);
        lat_pergi_akhirr=(TextView) findViewById(R.id.lat_pergi_akhir);
        //////////////map
        CekGPS();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_rute_angkot);
        mMap = mapFragment.getMap();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        mMap.setTrafficEnabled(true);
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
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }
           /* mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }*/
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
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback1);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
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
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }else if(lokasi.equals("") && tujuan!=null && !tujuan.equals("")){
                String k = String.valueOf(latitude + "," + longitude);
                new GeocoderLokasi().execute(k);
                new GeocoderTujuan().execute(tujuan);
            }


           /* mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }*/
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
        }else if(v==delete2){
            mAutocompleteTextView2.setText("");
        }
    }


    ////map
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
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        String filterAddress = "";
         Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses =
                    geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addresses.size() > 0) {
                for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                    filterAddress += addresses.get(0).getAddressLine(i) + " ";



                if(idid.getText().equals("1")) {
                    mAutocompleteTextView2.setText(filterAddress);
                    String lokasi=mAutocompleteTextView.getText().toString();
                    String tujuan=mAutocompleteTextView2.getText().toString();

                    // Toast.makeText(Tes2.this,lokasi + " " + tujuan, Toast.LENGTH_SHORT).show();
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

       /* if(idid.getText().equals("0")){
            mAutocompleteTextView.setText(latLng.latitude+","+latLng.longitude);
            String lokasi=mAutocompleteTextView.getText().toString();
            String tujuan=mAutocompleteTextView2.getText().toString();
            if(mAutocompleteTextView!=null && !mAutocompleteTextView.equals("")&&mAutocompleteTextView2!=null && !mAutocompleteTextView2.equals("")){
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }
        }else  if(idid.getText().equals("1")){
            mAutocompleteTextView2.setText(latLng.latitude+","+latLng.longitude);
            String lokasi=mAutocompleteTextView.getText().toString();
            String tujuan=mAutocompleteTextView2.getText().toString();
            if(mAutocompleteTextView!=null && !mAutocompleteTextView.equals("")&&mAutocompleteTextView2!=null && !mAutocompleteTextView2.equals("")){
                new GeocoderLokasi().execute(lokasi);
                new GeocoderTujuan().execute(tujuan);
            }else{ String k=String.valueOf(latitude+","+longitude);

                Toast.makeText(Tes2.this, latLng.latitude+" "+latLng.longitude, Toast.LENGTH_SHORT).show();
                new GeocoderLokasi().execute(k);
                new GeocoderTujuan().execute(tujuan);
            }
        }*/
        mMap.clear();
        LatLng user = new LatLng(latLng.latitude,latLng.longitude);
        String nama="Lokasi anda";
        mMap.addMarker(new MarkerOptions()
                .position(user)
                .title(nama)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));


    }

    // An AsyncTask class for accessing the GeoCoding Web Service
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
                latlongtujuan.setText(String.valueOf(address.getLatitude()+","+ address.getLongitude()));
                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLngt);
                markerOptions.title("Tujuan Anda : " + addressText);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_51));

                mMap.addMarker(markerOptions);

                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLngt));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLngt, 15));
            }

            try {
                v2GetRouteDirection = new GMapV2GetRouteDirection();
                if(latLngl!=null && !latLngl.equals("")&&latLngt!=null && !latLngt.equals("")){
                    LatLng latLng2 = new LatLng(latitude, longitude);
                   CalculationByDistance(latLng2,latLngt);
//                    GetRouteTask getRoute = new GetRouteTask();
//                    getRoute.execute();
                    new AmbilData1().execute();
                    new AmbilData2().execute();
                }else if(latLngl==null && latLngl.equals("")&&latLngt!=null && !latLngt.equals("")){

                   CalculationByDistance(latLngl,latLngt);
//                    GetRouteTask getRoute = new GetRouteTask();
//                    getRoute.execute();
                    new AmbilData1().execute();
                    new AmbilData2().execute();
                    //CalculationByDistance(latLngl1, latLngl2);
                }
            }catch (Exception e){

            }

        }
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
                latlonglokasi.setText(String.valueOf(address.getLatitude()+","+ address.getLongitude()));
                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLngl);
                markerOptions.title("Titik Awal : " + addressText);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52));

                mMap.addMarker(markerOptions);

                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLngl));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLngl, 15));
            }

        }

    }
    //////////////////////////////coba
    private class GetRouteTask extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";
        @Override
        protected void onPreExecute() {
            Dialog = new ProgressDialog(Tes2backup.this);
            Dialog.setMessage("Loading route...");
            Dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);

            if(latLngl==null && latLngl.equals("")){


                LatLng latLng2 = new LatLng(latitude, longitude);
                document = v2GetRouteDirection.getDocument(latLng2, latLngt, GMapV2GetRouteDirection.MODE_DRIVING);
            }else if(latLngl!=null && !latLngl.equals("")){
                document = v2GetRouteDirection.getDocument(latLngl, latLngt, GMapV2GetRouteDirection.MODE_DRIVING);

            }

            response = "Success";
            return response;

        }

        @Override
        protected void onPostExecute(String result) {
            mMap.clear();
            if(response.equalsIgnoreCase("Success")){
                ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                PolylineOptions rectLine = new PolylineOptions().width(10).color(
                        Color.GRAY);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                // Adding route on the map
                mMap.addPolyline(rectLine);
                markerOptions.position(latLngt);
                markerOptions.draggable(true);
                mMap.addMarker(markerOptions);

            }

            Dialog.dismiss();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    /////////////////////////////////coba ak
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
      // Toast.makeText(getApplicationContext(), "Radius Value"+""+valueResult+"   KM  " + kmInDec+" Meter   "+meterInDec, Toast.LENGTH_LONG).show();

       kmm.setText(kmInDec + " KM " + meterInDec + " Meter");

        if(kmInDec==1 ||kmInDec==0){

            hrg.setText("Rp.2000");
        }else{
            double ambiljarak=kmInDec-1;
            double jarak_bagi=ambiljarak;
            double hitung=jarak_bagi*500;
            double hasil=hitung+2000;
            hrg.setText("Rp."+String.valueOf(hasil));
        }


        return Radius * c;
    }



    private void addLines(String ln) {

        int gj=1,gn=0,tg=2;
        ArrayList<double[][]> array2list = new ArrayList<>();
        try {
            String arr[] = ln.split("l");
            for (int i = 0; i < arr.length; i++) {

                String k=arr[gn];
                String m=k+",k";
                String arr2[] = m.split(",");

                String a=arr2[0];
                String b=arr2[1];
                double latasal= Double.parseDouble(a);
                double longasal=Double.parseDouble(b);
/////////////////////////////////////////////
                String k2=arr[gj];
                String m2=k2+",k";
                String arr22[] = m2.split(",");

                String a2=arr22[0];
                String b2=arr22[1];
                double latasal2= Double.parseDouble(a2);
                double longasal2=Double.parseDouble(b2);
/////////////////////////////////////////////
                String k3=arr[tg];
                String m3=k3+",k";
                String arr23[] = m3.split(",");

                String a3=arr23[0];
                String b3=arr23[1];
                double latasal3= Double.parseDouble(a3);
                double longasal3=Double.parseDouble(b3);

                double[][] pos ={{latasal, longasal},{latasal2, longasal2},{latasal3, longasal3}};


                PolylineOptions rectOptions = new PolylineOptions();
                for (int ii = 0; ii < pos.length; ii++){
                    rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.RED);
                }
                Polyline polyline = mMap.addPolyline(rectOptions);
                gj=gj+2;
                gn=gn+2;
                tg=tg+2;
            }


        }catch (Exception e){

        }


    }
    public void ambil(String k){

        String m=k+",k";
        String arr2[] = m.split(",");
        String a=arr2[0];
        String b=arr2[1];
        double latasal= Double.parseDouble(a);
        double longasal=Double.parseDouble(b);
        LatLng marker = new LatLng(latasal,longasal);
        drawMarker(marker);


    }
    private void drawMarker(LatLng point){//,String nama) {
        String filterAddress = "";
        LatLng tambah = new LatLng(point.latitude, point.longitude);
        MarkerOptions options = new MarkerOptions();
        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses =
                    geoCoder.getFromLocation(point.latitude, point.longitude, 1);

            if (addresses.size() > 0) {
                for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                    filterAddress += addresses.get(0).getAddressLine(i) + " ";
            }
            options.position(tambah);
            options.title(filterAddress);
            options.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.marker_51));
            Marker marker = mMap.addMarker(options);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }





    }
    public class AmbilData1 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Tes2backup.this);
            pDialog.setMessage("Loading Data2 ...");
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
            url = "http://115.178.53.63:8010/app_angkot/search_awal.php?lat_awal="+rows[0]+"&long_awal="+rows[1];

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    latpergi = c.getDouble("lat_pergi");
                    longpergi = c.getDouble("long_pergi");
                    id_latlong_pergi= c.getInt("id_latlong_pergi");
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

try {
    v2GetRouteDirection = new GMapV2GetRouteDirection();

    latLngl1 = new LatLng(latpergi,longpergi);
    lat_pergi_awalr.setText(String.valueOf(id_latlong_pergi));
    final String s1 = latlonglokasi.getText().toString();
    final String ss1=s1+",";
    String[] rows = ss1.split(",");
    latLngt1 = new LatLng(Double.parseDouble(rows[0]),Double.parseDouble(rows[1]));
    drawMarker(latLngl1);

    GetRouteTask1 getRoute = new GetRouteTask1();
    getRoute.execute();
}catch (Exception e){}


        }
    }

    public class AmbilData2 extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Tes2backup.this);
            pDialog.setMessage("Loading Data1 ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        final String s = latlongtujuan.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://115.178.53.63:8010/app_angkot/search_awal.php?lat_awal="+rows[0]+"&long_awal="+rows[1];

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));

                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);
                    lattujuan = c.getDouble("lat_pergi");
                    longtujuan = c.getDouble("long_pergi");

                    id_latlong_pergi= c.getInt("id_latlong_pergi");
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

            try {
                v2GetRouteDirection = new GMapV2GetRouteDirection();

                latLngl2 = new LatLng(lattujuan,longtujuan);


                lat_pergi_akhirr.setText(String.valueOf(id_latlong_pergi));
                final String s1 = latlongtujuan.getText().toString();
                final String ss1=s1+",";
                String[] rows = ss1.split(",");
                latLngt2 = new LatLng(Double.parseDouble(rows[0]),Double.parseDouble(rows[1]));

                drawMarker(latLngl2);
                ///CalculationByDistance(latLngl1, latLngt1);

                GetRouteTask2 getRoute = new GetRouteTask2();
                getRoute.execute();
            }catch (Exception e){}


            new AmbilArange().execute();
        }
    }
    private class GetRouteTask1 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";
        @Override
        protected void onPreExecute() {
            Dialog = new ProgressDialog(Tes2backup.this);
            Dialog.setMessage("Loading route...");//+latLngl1+latLngt1);
            Dialog.show();
        }

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
                markerOptions.draggable(true);
                mMap.addMarker(markerOptions);

            }else{
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
            }

            Dialog.dismiss();
        }
    }
    private class GetRouteTask2 extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";
        @Override
        protected void onPreExecute() {
            Dialog = new ProgressDialog(Tes2backup.this);
            Dialog.setMessage("Loading route...");//+latLngl2+latLngt2);
            Dialog.show();
        }

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
            if(response.equalsIgnoreCase("Success")){
                ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                PolylineOptions rectLine = new PolylineOptions().width(10).color(
                        Color.BLACK);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                // Adding route on the map
                mMap.addPolyline(rectLine);
                markerOptions.position(latLngl2);
                markerOptions.draggable(true);
                mMap.addMarker(markerOptions);

            }else{
                Toast.makeText(getApplicationContext(), "Klik peta atau cari alamat.. ", Toast.LENGTH_LONG).show();
            }

            Dialog.dismiss();
        }
    }




    public class AmbilArange extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Tes2backup.this);
            pDialog.setMessage("Loading Data3 ..."+lat_pergi_akhir1);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        final String s = latlonglokasi.getText().toString();
        final String ss=s+",";
        String[] rows = ss.split(",");
        TextView lat_pergi_awal=(TextView) findViewById(R.id.lat_pergi_awal);
        String lat_pergi_awal1=lat_pergi_awal.getText().toString();
        TextView lat_pergi_akhir=(TextView) findViewById(R.id.lat_pergi_akhir);
        String lat_pergi_akhir1=lat_pergi_akhir.getText().toString();

        @Override
        protected String doInBackground(String... arg0) {
            String url;
            url = "http://115.178.53.63:8010/app_angkot/arange_rute.php?lat_pergi_awal="+lat_pergi_awal1+"&lat_pergi_akhir="+lat_pergi_akhir1;

            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("fujicon_dr_daily_report2");
                Log.e("error", json.getString("success1"));
                lat1 = new double[college .length()];
                long1 = new double[college .length()];
                for (int i = 0; i < college.length(); i++) {
                    JSONObject c = college.getJSONObject(i);

                    lat1[i] = college.getJSONObject(i).getDouble("lat_pergi");
                    long1[i] = college.getJSONObject(i).getDouble("long_pergi");
//                    latpergiarange = c.getDouble("lat_pergi");
//                    longpergiarange = c.getDouble("long_pergi");


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
            int gj=1,gn=0,tg=2;
            try{
            for (int i = 0; i < lat1.length; i++) {

                String lat11= String.valueOf(lat1[gn]);
                String long11=String.valueOf(long1[gn]);
                String lat22=String.valueOf(lat1[gj]);
                String long22=String.valueOf(long1[gj]);
                String lat33=String.valueOf(lat1[tg]);
                String long33=String.valueOf(long1[tg]);
                //Toast.makeText(getApplicationContext(), "Latitude : "+long33, Toast.LENGTH_LONG).show();
//                if((lat11!=null || !lat11.equals("")&& long11!=null || !long11.equals(""))){
//                    double[][] pos ={{lat1[gn-1], long1[gn-1]},{lat1[gj-1], long1[gj-1]}};
//                    PolylineOptions rectOptions = new PolylineOptions();
//                    for (int ii = 0; ii < pos.length; ii++){
//                        rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.YELLOW);
//                    }
//                    Polyline polyline = mMap.addPolyline(rectOptions);
//
//                }else
              if(gn!=0) {
                  if ((lat11 != null || !lat11.equals("") && long11 != null || !long11.equals(""))) {
                      double[][] pos = {{lat1[gn - 1], long1[gn - 1]}, {lat1[gj - 1], long1[gj - 1]}};
                      PolylineOptions rectOptions = new PolylineOptions();
                      for (int ii = 0; ii < pos.length; ii++) {
                          rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                      }
                      Polyline polyline = mMap.addPolyline(rectOptions);

                  }
              }
                if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))){
                    double[][] pos ={{lat1[gn], long1[gn]},{lat1[gj], long1[gj]}};
                    PolylineOptions rectOptions = new PolylineOptions();
                    for (int ii = 0; ii < pos.length; ii++){
                        rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                    }
                    Polyline polyline = mMap.addPolyline(rectOptions);

                }
                if((lat11!=null || !lat11.equals("") && long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals(""))&&(lat33!=null || !lat33.equals(""))&& long33!=null || !long33.equals("")){
                    double[][] pos ={{lat1[gn], long1[gn]},{lat1[gj], long1[gj]},{lat1[tg], long1[tg]}};
                    PolylineOptions rectOptions = new PolylineOptions();
                    for (int ii = 0; ii < pos.length; ii++){
                        rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.GREEN);
                    }
                    Polyline polyline = mMap.addPolyline(rectOptions);

                }
                /*else
                if((lat11!=null || !lat11.equals("")&& long11!=null || !long11.equals("")) && (lat22!=null || !lat22.equals("")&& long22!=null || !long22.equals("")) &&(lat33!=null || !lat33.equals(""))&& long33!=null || !long33.equals("")){
                    double[][] pos ={{lat1[gn], long1[gn]},{lat1[gj], long1[gj]},{lat1[tg], long1[tg]}};
                    PolylineOptions rectOptions = new PolylineOptions();
                    for (int ii = 0; ii < pos.length; ii++){
                        rectOptions.add(new LatLng(pos[ii][0], pos[ii][1])).color(Color.CYAN);
                    }
                    Polyline polyline = mMap.addPolyline(rectOptions);

                }*/

                    gj=gj+2;
                gn=gn+2;
                tg=tg+2;
            }

            }catch (Exception e){

            }


        }
    }
}
