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

public class LastName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_name);

        //UI Code
        Button proceedLastName = (Button) findViewById(R.id.proceedLastName);
        Button backLastName = (Button) findViewById(R.id.backLastName);
        EditText lastName = (EditText) findViewById(R.id.lname);

        lastName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Log.d("LastName", "Enter button was pressed");
                    nextClicked();
                    return true;
                }
                return false;
            }
        });

        proceedLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClicked();
            }
        });

        backLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        proceedLastName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.proceedLastName);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });

        backLastName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.backLastName);
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
        EditText editLastName = findViewById(R.id.lname);
        String lastName = editLastName.getText().toString().trim();

        if (lastName.length() == 0 || lastName.equals("") || lastName == null) {
            Toast.makeText(getApplicationContext(), "Last name cannot be empty", Toast.LENGTH_LONG).show();
        } else {

            final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
            gvm.setLastName(lastName);
            startActivity(new Intent(LastName.this, ZipCode.class));
        }
    }
}
