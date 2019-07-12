package com.hisense.productregistration.deviceInfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mediatek.twoworlds.tv.HisenseTvAPI;
import com.mediatek.twoworlds.tv.HisenseTvAPIBase;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigType;


import java.lang.reflect.Method;

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

        HisenseTvAPI mHisenseTvAPI = HisenseTvAPI.getInstance(context);
        if (mHisenseTvAPI != null) {
            serial = mHisenseTvAPI.getConfigValue(MtkTvConfigType.CFG_FACTORY_FAC_SERIAL_NEW_NUMBER);
            Log.d(TAG, "serial number got: " + serial);
        } else {
            Log.d(TAG, "HisenseTvAPI is null, cannot get serial number");
        }

        return serial;
    }

    /**
     * retrieve the model
     * @return model of the device
     */
    public String retrieveModel() {

        String model;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);

            // (?) Lenovo Tab (https://stackoverflow.com/a/34819027/1276306)
            model = (String) get.invoke(c, "ro.product.hisense.model");

            if (model.equals(""))
                model = null;
        } catch (Exception e) {
            e.printStackTrace();
            model = null;
        }

        return model;
    }

}
