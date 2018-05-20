package com.pdmproyect.ifmsaelsalvador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments.SignUpCollegeActivity;

public class ProfileActivity extends AppCompatActivity {

    SignUpCollegeActivity fragmento = new SignUpCollegeActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
      // getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragmento).commit();
    }

}
