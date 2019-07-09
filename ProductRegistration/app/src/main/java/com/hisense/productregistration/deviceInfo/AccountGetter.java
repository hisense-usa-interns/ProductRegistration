package com.hisense.productregistration.deviceInfo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

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
        this.manager = AccountManager.get(context);
    }

    /**
     * get the account object from Google, only the first account in the account list
     * @return account
     */
    public Account retrieveAccount() {
        Account[] accounts = manager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }



}
