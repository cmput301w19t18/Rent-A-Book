package com.example.cmput301w19t18.rent_a_book;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.cmput301w19t18.rent_a_book.HomeActivity.ADDING;

/**
 * The type Maps activity.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseReference mUserDatabase;
    FirebaseAuth mAuth;

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;

    public String uLat;
    public String uLon;

    /**
     * The constant locationLat.
     */
///////////////// Location of the marker/pin dropped /////////////////
    public static Double locationLat;
    public static Double locationLon;

    public static void setLocationLat(Double locationLat) {
        MapsActivity.locationLat = locationLat;
    }
    public static void setLocationLon(Double locationLon) {
        MapsActivity.locationLon = locationLon;
    }
    public static Double getLocationLat() {
        return locationLat;
    }
    public static Double getLocationLon() {
        return locationLon;
    }


    /**
     * On create.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO credit https://tips.androidhive.info/2013/10/android-make-activity-as-fullscreen-removing-title-bar-or-action-bar/#disqus_thread
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * @param googleMap the google map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng CSB_home = new LatLng(53.526724, -113.526483); //Location of CSB
        Float zoom = new Float (15); //default zoom level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CSB_home, zoom));

        mMap.addMarker(new MarkerOptions().position(CSB_home).title("CSB HAS CHANGED"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CSB_home));

        enableMyLocation();
        //setMapLongClick(mMap);

        String accepted = getIntent().getStringExtra("Accepted");
        if (accepted.contains("2")) {
            displayPickupMap(locationLat, locationLon);
        }
        if (accepted.contains("1")){
            setMapLongClick(mMap);
        }

    }

    /**
     * On create options menu boolean.
     *
     * @param menu the menu
     * @return the boolean
     */
//"In MapsActivity, override the onCreateOptionsMenu() method and inflate the map_options file:"
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }

    /**
     * Sets options item selected.
     *
     * @param item the item
     * @return the options item selected
     */
//"To change the map type, use the setMapType() method on the GoogleMap object, passing in one of the map-type constants. "
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    //////////////// Allows setting of pins ////////////////
    private void setMapLongClick(final GoogleMap map) {


        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng)); //adds a pin to the map on click

                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        String snippet = String.format(Locale.getDefault(),
                                "Lat: %1$.5f, Long: %2$.5f",
                                latLng.latitude,
                                latLng.longitude);

                        map.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("Dropped Pin")
                                .snippet(snippet));

                        //return data from map marker position:
                        locationLat = latLng.latitude;
                        locationLon = latLng.longitude;

                        //Accepting request
                        //FIREBASE

                        mAuth = FirebaseAuth.getInstance();
                        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");

                        String user_id = mAuth.getCurrentUser().getUid();

                        DatabaseReference accepted_ref = mUserDatabase.child(user_id);
                        accepted_ref.child("lat").setValue(locationLat);
                        accepted_ref.child("lon").setValue(locationLon);

                        Toast.makeText(getApplicationContext(), "Location Set", Toast.LENGTH_LONG).show();


                    }
                });

            }
        });


    }


    public void displayPickupMap(Double lon, Double lat){

        //String borrowed = getIntent().getStringExtra("Borrowed");
        //Toast.makeText(getApplicationContext(), borrowed, Toast.LENGTH_LONG).show();
        //if (getIntent().getStringExtra("Borrowed").contains("2")){

        String bookTitle = getIntent().getStringExtra("Book");
        final String ownerEmail = getIntent().getStringExtra("Owner");
        //Toast.makeText(getApplicationContext(), "test email", Toast.LENGTH_LONG).show();

        DatabaseReference mDatabase4;
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        mDatabase4 = FirebaseDatabase.getInstance().getReference("Users");
        Query query4 = mDatabase4.orderByChild("email");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                    for (final DataSnapshot snapshot: dataSnapshot.getChildren()){

                        User user = snapshot.getValue(User.class);
                        if (user.email.contains(ownerEmail)) {

                            String keyer = snapshot.getKey();


                            //Gets lat

                            DatabaseReference lat_ref = FirebaseDatabase.getInstance().getReference("Users").child(keyer);

                            lat_ref.getRef().addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                        //Double lat = dataSnapshot1.child("lat").getValue(Double.class);
                                        //Toast.makeText(getApplicationContext(), dataSnapshot1.getValue().toString(), Toast.LENGTH_LONG).show();
                                        String lat = dataSnapshot1.getValue().toString();
                                        uLat = lat;
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            }) ;

                            //Gets lon

                            DatabaseReference lon_ref = FirebaseDatabase.getInstance().getReference("Users").child(keyer);

                            lon_ref.getRef().addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataSnapshot2: dataSnapshot.getChildren()){
                                        //Double lat = dataSnapshot1.child("lat").getValue(Double.class);
                                        //Toast.makeText(getApplicationContext(), dataSnapshot1.getValue().toString(), Toast.LENGTH_LONG).show();
                                        String lon = dataSnapshot2.getValue().toString();
                                        uLon = lon;
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            }) ;


                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query4.addListenerForSingleValueEvent(eventListener);

        Toast.makeText(getApplicationContext(), uLat, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), uLon, Toast.LENGTH_LONG).show();


        LatLng pickup_point = new LatLng(lat, lon); //Location of CSB
        Float zoom = new Float (15); //default zoom level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pickup_point, zoom));

        mMap.addMarker(new MarkerOptions().position(pickup_point).title("PICKUP POINT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickup_point));



    }




    //////////// Current location methods ////////////
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    /**
     * On request permissions result.
     *
     * @param requestCode  the request code
     * @param permissions  the permissions
     * @param grantResults the grant results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }



}
