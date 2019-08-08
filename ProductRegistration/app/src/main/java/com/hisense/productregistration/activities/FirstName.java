package com.hisense.productregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;

public class FirstName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_name);

        //UI Code
        Button proceedFirstName = (Button) findViewById(R.id.proceedFirstName);
        Button backFirstName = (Button) findViewById(R.id.backFirstName);
        EditText firstName = (EditText) findViewById(R.id.fname);

        firstName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Log.d("FirstName", "Enter button was pressed");
                    nextClicked();
                    return true;
                }
                return false;
            }
        });

        proceedFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClicked();
            }
        });

        backFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        proceedFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.proceedFirstName);
                if(hasFocus){
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_selected));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_button_selected));
                } else {
                    button.setTextColor(ContextCompat.getColor(getApplication(), R.color.my_text_default));
                    button.setBackgroundColor(ContextCompat.getColor(getApplication(),R.color.my_background_dark));
                }
            }
        });

        backFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.backFirstName);
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
        EditText editFirstName = findViewById(R.id.fname);
        String firstName = editFirstName.getText().toString().trim();

        if (firstName.length() == 0 || firstName.equals("") || firstName == null) {
            Toast.makeText(getApplicationContext(), "First name cannot be empty", Toast.LENGTH_LONG).show();
        } else {

            final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
            gvm.setFirstName(firstName);
            startActivity(new Intent(FirstName.this, LastName.class));
        }
    }
}
