package com.hisense.productregistration.deviceInfo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

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
