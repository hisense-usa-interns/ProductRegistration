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

public class FirstName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_name);

        //UI Code
        Button proceedFirstName = (Button) findViewById(R.id.proceedFirstName);
        Button backFirstName = (Button) findViewById(R.id.backFirstName);

        proceedFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editFirstName = findViewById(R.id.fname);
                String firstName = editFirstName.getText().toString().trim();
                final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();
                gvm.setFirstName(firstName);
                startActivity(new Intent(FirstName.this, LastName.class));
            }
        });

        backFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstName.this, MainActivity.class));
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
}
