package com.pdmproyect.ifmsaelsalvador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*
        * para probar directamente el signUpActivity
        * startActivity(new Intent(this, SignUpActivity.class));
        */
    }
}
