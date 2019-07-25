package com.hisense.productregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;
import com.hisense.productregistration.register.UrlRegister;

public class EmailId extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_id);

        //UI Code
        Button proceedEmailId = (Button) findViewById(R.id.proceedEmailId);
        Button backEmailId = (Button) findViewById(R.id.backEmailId);

        proceedEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmailId = findViewById(R.id.email);
                String emailId = editEmailId.getText().toString();
                final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
                gvm.setEmail(emailId);

                final UrlRegister r1 = new UrlRegister(getBaseContext());
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean registerSuccess = r1.getRequestToURL("televisions",gvm.getModel(),gvm.getSerial(),gvm.getFirstName(),gvm.getLastName(),gvm.getZip(),gvm.getEmail());
                            if (registerSuccess) {
                                startActivity(new Intent(EmailId.this, SuccessPage.class));
                            } else {
                                System.out.println("Register failed");
                            }
                        } catch (Exception e) {

                        }
                    }
                });
                thread.start();

                //startActivity(new Intent(EmailId.this, SuccessPage.class));
            }
        });

        backEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailId.this, ZipCode.class));
            }
        });

        proceedEmailId.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.proceedEmailId);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });

        backEmailId.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.backEmailId);
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
}
