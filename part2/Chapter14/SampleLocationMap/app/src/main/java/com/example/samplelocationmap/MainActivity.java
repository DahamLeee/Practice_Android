package com.example.samplelocationmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;
            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch(Exception e){
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
        }
        catch(SecurityException e){
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            showCurrentLocation(latitude, longitude);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

    private void showCurrentLocation(Double latitude, Double longitude){
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "permissions denied : " + strings.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "permissions granted : " + strings.length, Toast.LENGTH_LONG).show();
    }
}
