package fpp.priangan.fujicon.angkot.sheet;

<<<<<<< HEAD
=======
import android.Manifest;
>>>>>>> origin/master
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
<<<<<<< HEAD
=======
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
>>>>>>> origin/master
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fpp.priangan.fujicon.angkot.PlaceArrayAdapter;
import fpp.priangan.fujicon.angkot.R;
<<<<<<< HEAD
=======
import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapter;
import fpp.priangan.fujicon.angkot.adapters.ViewPagerAdapter2;
>>>>>>> origin/master


public class MainActivitySheet extends ActionBarActivity implements View.OnClickListener,
        OnMapReadyCallback,
        LocationListener,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,GoogleMap.OnMapClickListener {

    //////////////////////coba direct
    // List<Overlay> mapOverlays;
    //Barcode.GeoPoint point1, point2;
    LocationManager locManager;
    Drawable drawable;
    Document document;
    GMapV2GetRouteDirection v2GetRouteDirection;
    LatLng fromPosition;
    LatLng toPosition;
    GoogleMap mGoogleMap;
    Location location;
    //////////////////////akhir direct
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] no;
    String[] warna;
    String[] nama_trayek;
    String[] lintasan;
    String[] harga;
    String[] operasi;
    int[] gambar;
    ///map
    private GoogleMap mMap;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final String LOG_TAG = "Penghitungan_trayek";
    double latitude;
    double longitude;
    private GoogleApiClient mGoogleApiClient;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(-6.936674547450142, 107.69859038293362), new LatLng(-6.909851660736927, 107.61063203215599));
    MarkerOptions markerOptions;
    LatLng latLngl,latLngt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    ///map
    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();


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

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsheet);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));

            }
        });
try {
    Bundle bundle = getIntent().getExtras();
    String getlokasi = bundle.getString("setlokasi");
    String gettujuan = bundle.getString("settujuan");
    latLngl = bundle.getParcelable("setlokasil");
    latLngt= bundle.getParcelable("settujuant");
    // if(location!=null && !location.equals("")){
    new GeocoderLokasi().execute(getlokasi);
    new GeocoderTujuan().execute(gettujuan);

    //////////////map
    v2GetRouteDirection = new GMapV2GetRouteDirection();
    CekGPS();
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map_detail_perhitungan_trayek);
    mMap = mapFragment.getMap();

    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        return;
    }
    mMap.getUiSettings().setZoomControlsEnabled(true);
    mMap.getUiSettings().setCompassEnabled(true);
    mMap.getUiSettings().setMyLocationButtonEnabled(true);
    mMap.getUiSettings().setAllGesturesEnabled(true);
    mMap.setTrafficEnabled(true);
    mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    mMap.setMyLocationEnabled(true);
    mMap.getUiSettings().setMapToolbarEnabled(false);//hide direct

    if(latLngl!=null && !latLngl.equals("")&&latLngt!=null && !latLngt.equals("")){
        GetRouteTask getRoute = new GetRouteTask();
        getRoute.execute();
    }
    mapFragment.getMapAsync(this);
    if (latitude != 0 && longitude != 0) {
        Toast.makeText(getApplicationContext(), "Latitude : " + latitude + " Longitude : " + longitude , Toast.LENGTH_LONG).show();
    }
    zoom();
    ///mapak
    bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
    String[] no= new String[] {
            "01B"
    };
        /*"Ciburial","80","Lembang"*/
    String[] warna= new String[] {
            "Hijau-Merah Tua"
    };
        /*,"Hijau-Krem","Hijau-Hijau","Krem-Merah-Hitam"*/
    String[] nama_trayek = new String[] {
            "Abdulmuis - Cicaheum via Aceh"

    };

    String[] lintasan= new String[] {
            "RUTE 1"
    };

    String[] harga= new String[] {
            "Rp.000"
    };

    String[] operasi= new String[] {
            "04.00 wib "
    };

    int[] gambar=new int[]{
<<<<<<< HEAD
            R.drawable.b01
=======
            R.drawable.abdul_muis_cicaheum_via_aceh
>>>>>>> origin/master
    };

    // Locate the ViewPager in viewpager_main.xml
    viewPager = (ViewPager) findViewById(R.id.pager);
    // Pass results to ViewPagerAdapter Class
   /// adapter = new ViewPagerAdapter(MainActivitySheet.this, no, warna, nama_trayek, lintasan,harga,operasi,gambar);
    // Binds the Adapter to the ViewPager
    viewPager.setAdapter(adapter);
//    Bundle bundle = getIntent().getExtras();
    int posi = bundle.getInt("setk");
    viewPager.setCurrentItem(posi);

}catch(Exception e){
    Toast.makeText(getApplicationContext(), "as"+e, Toast.LENGTH_LONG).show();
}

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.btn_custom_view:
                bottomSheetLayout.showWithSheetView(getLayoutInflater().inflate(R.layout.custom_viewsheet, bottomSheetLayout, false));
                break;/
            case R.id.btn_expand_me:
                bottomSheetLayout.expandSheet();
                break;
            /*case R.id.btn_intent:
                final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Rhesoft tutorial");
                shareIntent.setType("text/plain");

                IntentPickerSheetView intentPickerSheetView = new IntentPickerSheetView(MainActivitySheet.this, shareIntent, "Share", new IntentPickerSheetView.OnIntentPickedListener() {
                    @Override
                    public void onIntentPicked(IntentPickerSheetView.ActivityInfo activityInfo) {
                        bottomSheetLayout.dismissSheet();
                        startActivity(activityInfo.getConcreteIntent(shareIntent));
                    }
                });

               bottomSheetLayout.showWithSheetView(intentPickerSheetView);
                break;*/
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

    public void zoom(){
        LatLng user = new LatLng(latitude, longitude);
        String nama="Lokasi anda";
//warna
//		googleMap.addMarker(new MarkerOptions()
//        .position(user)
//        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


//////gambar
        mMap.addMarker(new MarkerOptions()
                .position(user)
                .title(nama)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_50)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                user, 15));
    }

    @Override
    public void onMapClick(LatLng latLng) {

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

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLngl);
                markerOptions.title("Titik Awal : " + addressText);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_52));

                mMap.addMarker(markerOptions);

                //Toast.makeText(getBaseContext(), "No Location found"+latLngl, Toast.LENGTH_SHORT).show();
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
            Dialog = new ProgressDialog(MainActivitySheet.this);
            Dialog.setMessage("Loading route...");
            Dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            //Get All Route values
//            document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
            document = v2GetRouteDirection.getDocument(latLngl, latLngt, GMapV2GetRouteDirection.MODE_WALKING);
            response = "Success";
            return response;

        }

        @Override
        protected void onPostExecute(String result) {
           // mGoogleMap.clear();
            if(response.equalsIgnoreCase("Success")){
                ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                PolylineOptions rectLine = new PolylineOptions().width(10).color(
                        Color.BLACK);

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

}
