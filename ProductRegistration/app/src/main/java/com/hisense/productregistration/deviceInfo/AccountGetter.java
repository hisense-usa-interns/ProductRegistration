package com.hisense.productregistration.deviceInfo;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * class for getting information from Google account
 */
public class AccountGetter {

    private Context context;
    private static final String TAG = "AccountGetter";
    private AccountManager manager;

    /**
     * constructor of Account Getter
     * @param context
     */
    public AccountGetter(Context context) {
        this.context = context;
        this.manager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
    }

    /**
     * return account manager
     * @return manager
     */
    public AccountManager getManager() {
        return this.manager;
    }

    /**
     * get the account object from Google, only the first account in the account list
     * @return account
     */
    public Account retrieveAccount() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission denied");
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.GET_ACCOUNTS} ,1);
            Log.d(TAG, "permission requested");
        }
        Log.d(TAG, "permission granted already");

        Account[] accounts = manager.getAccounts();
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    /**
     * get the email address with the given account
     * @param account
     * @return email
     */
    public String retrieveEmail(Account account) {
        String email = account.name;
        return email;
    }



}
