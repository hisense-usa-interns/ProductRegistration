package com.hisense.productregistration.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.AccountGetter;
import com.hisense.productregistration.deviceInfo.DataGetter;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;

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

        String postalCode = "";
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

}