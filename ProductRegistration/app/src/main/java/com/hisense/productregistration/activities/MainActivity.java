package com.hisense.productregistration.activities;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.AccountGetter;
import com.hisense.productregistration.deviceInfo.DataGetter;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;

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

        DataGetter dataGetter = new DataGetter(context);
        String serial = dataGetter.retrieveSerialNumber();
        String model = dataGetter.retrieveModel();

        final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
        gvm.setSerial(serial);
        gvm.setModel(model);

        String postalCode = retrievePostalCode();
        String productType = "televisions";

        AccountGetter accountGetter = new AccountGetter(context);
        AccountManager manager = accountGetter.getManager();
        Intent intent = manager.newChooseAccountIntent(null, null, new String[] {"com.google"}, null, null, null, null);
        startActivityForResult(intent, 23);

        Account account = accountGetter.retrieveAccount();
        String email = accountGetter.retrieveEmail(account);

        gvm.setEmail(email);

        //UI Code
        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstName.class));
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implement code to exit the app
                finishAffinity();
                System.exit(0);
            }
        });

        yes.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.yes);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });

        no.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.no);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }

    /**
     * retrieve the postal code
     * @return postal code of most recent location
     */
    public String retrievePostalCode() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission denied");
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION} ,1);
            Log.d(TAG, "permission requested");
        }
        String postalCode = "";
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
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
        return "";
    }

}
