package com.hisense.productregistration.deviceInfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;

import com.hisense.productregistration.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private Context context = this;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataGetter dataGetter = new DataGetter(context);
        String serialNumber = dataGetter.retrieveSerialNumber();
        Log.d(TAG, "serial number got: " + serialNumber);

        String modelNumber = dataGetter.retrieveModel();
        Log.d(TAG, "model number got: " + modelNumber);

        //Postal Code
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission denied");
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,1);
            Log.d(TAG, "permission requested");
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String postalCode = addresses.get(0).getPostalCode();
            Log.d(TAG, "Postal Code: " + postalCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
