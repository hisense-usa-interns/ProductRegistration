package com.hisense.productregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;
import com.hisense.productregistration.register.UrlRegister;

public class EmailId extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_id);

        final GlobalVarManager gvm1 = (GlobalVarManager) getApplicationContext();
        EditText editEmailPrefilled = findViewById(R.id.email);
        editEmailPrefilled.setText(gvm1.getEmail());

        //UI Code
        Button proceedEmailId = (Button) findViewById(R.id.proceedEmailId);
        Button backEmailId = (Button) findViewById(R.id.backEmailId);

        proceedEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmailId = findViewById(R.id.email);
                String emailId = editEmailId.getText().toString().trim();
                final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
                gvm.setEmail(emailId);

                final UrlRegister r1 = new UrlRegister(getBaseContext());

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            final GlobalVarManager gvm1 = (GlobalVarManager) getApplicationContext();
                            int registerSuccess = r1.getRequestToURL("televisions",gvm1.getModel(),gvm1.getSerial(),gvm1.getFirstName(),gvm1.getLastName(),gvm1.getZip(),gvm1.getEmail());
                            if (registerSuccess == 1) {
                                startActivity(new Intent(EmailId.this, SuccessPage.class));
                            } else {
                                System.out.println("Register failed");
                                if (registerSuccess == 2) {
                                    startActivity(new Intent(EmailId.this, FirstName.class));
                                } else if (registerSuccess == 3) {
                                    startActivity(new Intent(EmailId.this, LastName.class));
                                } else if (registerSuccess == 4) {
                                    startActivity(new Intent(EmailId.this, ZipCode.class));
                                } else if (registerSuccess == 5) {

                                } else if (registerSuccess == 6) {

                                } else {

                                }
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
