package com.hisense.productregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;

public class ZipCode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);

        //UI Code
        Button proceedZipCode = (Button) findViewById(R.id.proceedZipCode);
        Button backZipCode = (Button) findViewById(R.id.backZipCode);
        EditText zipcode = (EditText) findViewById(R.id.zipcode);

        zipcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Log.d("ZipCode", "Enter button was pressed");
                    nextClicked();
                    return true;
                }
                return false;
            }
        });

        proceedZipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClicked();
            }
        });

        backZipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        proceedZipCode.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.proceedZipCode);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });

        backZipCode.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.backZipCode);
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

    public void nextClicked() {
        EditText editZipCode = findViewById(R.id.zipcode);
        String zipCode = editZipCode.getText().toString().trim();

        if (zipCode.length() == 0 || zipCode.equals("") || zipCode == null) {
            Toast.makeText(getApplicationContext(), "Zip code cannot be empty", Toast.LENGTH_LONG).show();
        } else {

            final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
            gvm.setZip(zipCode);
            startActivity(new Intent(ZipCode.this, EmailId.class));
        }
    }
}
