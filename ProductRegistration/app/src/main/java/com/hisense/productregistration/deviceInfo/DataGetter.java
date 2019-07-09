package com.hisense.productregistration.deviceInfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * class for getting data needed for device registration
 */
public class DataGetter {

    private Context context;
    private static final String TAG = "DataGetter";

    /**
     * constructor of DataGetter, can be called as new DataGetter(this)
     * @param context of class calling this method
     */
    public DataGetter(Context context) {
        this.context = context;
    }

    /**
     * retrieve the serial number from device
     * @return serial number
     */
    public String retrieveSerialNumber() {
        String serial = "";

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission denied");
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.READ_PHONE_STATE} ,1);
            Log.d(TAG, "permission requested");
        }

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            serial = android.os.Build.getSerial();
        } else if (android.os.Build.VERSION.SDK_INT <= 25) {
            serial = android.os.Build.SERIAL;
        }

        Log.d(TAG, "serial number got: " + serial);
        return serial;
    }

    /**
     * retrieve the model
     * @return model of the device
     */
    public String retrieveModel() {
        String model = Build.MODEL;
        model = model.toUpperCase();
        Log.d(TAG, "model got: " + model);
        return model;
    }

}
