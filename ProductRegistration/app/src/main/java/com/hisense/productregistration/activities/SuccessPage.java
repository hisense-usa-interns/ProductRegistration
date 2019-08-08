package com.hisense.productregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hisense.productregistration.R;
import com.hisense.productregistration.deviceInfo.GlobalVarManager;

public class SuccessPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);

        final GlobalVarManager gvm = (GlobalVarManager) getApplicationContext();

        TextView firstNameText = findViewById(R.id.firstText);
        String firstInit = firstNameText.getText().toString();
        firstNameText.setText(firstInit + " " + gvm.getFirstName());

        TextView lastNameText = findViewById(R.id.lastText);
        String lastInit = lastNameText.getText().toString();
        lastNameText.setText(lastInit + " " + gvm.getLastName());

        TextView modelText = findViewById(R.id.modelText);
        String modelInit = modelText.getText().toString();
        modelText.setText(modelInit + " " + gvm.getModel());

        TextView serialText = findViewById(R.id.serialText);
        String serialInit = serialText.getText().toString();
        serialText.setText(serialInit + " " + gvm.getSerial());

        TextView emailText = findViewById(R.id.emailText);
        String emailInit = emailText.getText().toString();
        emailText.setText(emailInit + " " + gvm.getEmail());

        TextView zipText = findViewById(R.id.zipText);
        String zipInit = zipText.getText().toString();
        zipText.setText(zipInit + " " + gvm.getZip());

        //UI Code
        Button exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implement code to exit the app
                finishAffinity();
                System.exit(0);
            }
        });

        exit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Button button= v.findViewById(R.id.exit);
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
        Toast.makeText(getApplicationContext(), "Registration completed, please click on \"Exit\"", Toast.LENGTH_LONG).show();
    }
}
