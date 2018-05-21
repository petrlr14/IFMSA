package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdmproyect.ifmsaelsalvador.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        startActivity(new Intent(this, Login.class));
        /*
        * para probra signup
        *startActivity(new Intent(this, SignUpActivity.class));
        * */
    }

}
