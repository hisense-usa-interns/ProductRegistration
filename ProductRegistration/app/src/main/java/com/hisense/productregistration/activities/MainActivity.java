package com.hisense.productregistration.activities;

import android.Manifest;
import android.accounts.Account;
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
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.AccountGetter;
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
        webview.loadUrl("https://www.hisense-usa.com/support/register");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        DataGetter dataGetter = new DataGetter(context);
        String serial = dataGetter.retrieveSerialNumber();
        String model = dataGetter.retrieveModel();
        String postalCode = retrievePostalCode();
        String productType = "televisions";

        AccountGetter accountGetter = new AccountGetter(context);
        Account account = accountGetter.retrieveAccount();
        String email = accountGetter.retrieveEmail(account);

        final String js = "javascript:document.getElementById('Form_RegisterProductForm_ModelNumber').value = '" + model + "';document.getElementById('Form_RegisterProductForm_SerialNumber').value='" + serial + "';document.getElementById('Form_RegisterProductForm_PostalCode').value='" + postalCode + "';document.getElementById('Form_RegisterProductForm_ProductCategory').value='" + productType + "';document.getElementById('Form_RegisterProductForm_Email').value='" + email + "';";

        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {

                        }
                    });
                } else {
                    view.loadUrl(js);
                }
            }
        });

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
