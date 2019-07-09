package com.hisense.productregistration.activities;

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.DataGetter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    public Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl("https://www.google.com/");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        DataGetter dataGetter = new DataGetter(context);
        String serial = dataGetter.retrieveSerialNumber();
        String model = dataGetter.retrieveModel();
        String postalCode = retrievePostalCode();
    }

    /**
     * retrieve the postal code
     * @return postal code of most recent location
     */
    public String retrievePostalCode() {
        //Postal Code
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission denied");
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,1);
            Log.d(TAG, "permission requested");
        }
        String postalCode = "";
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            postalCode = addresses.get(0).getPostalCode();
            Log.d(TAG, "Postal Code: " + postalCode);
            return postalCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postalCode;
    }

}
