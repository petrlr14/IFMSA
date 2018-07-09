package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.pdmproyect.ifmsaelsalvador.R;

public class SplashScreen extends AppCompatActivity {
    private final int DURACION_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(this::startActivity, DURACION_SPLASH);

    }

    private void startActivity(){
        Intent intent;
        if (getLocalToken().equals("")) {
            intent = new Intent(SplashScreen.this, LoginActivity.class);
        }else{
            intent=new Intent(SplashScreen.this, MainActivity.class);
        }
        System.out.println(getLocalToken());
        startActivity(intent);
        finish();
    }

    private String getLocalToken(){
        SharedPreferences preferences=
                getSharedPreferences(getString(R.string.sharedpreferences_name), Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.sharedpreferences_key), "");
    }
}
