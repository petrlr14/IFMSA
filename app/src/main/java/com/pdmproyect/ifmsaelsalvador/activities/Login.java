package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.InstanceIdResult;
import com.pdmproyect.ifmsaelsalvador.R;

public class Login extends AppCompatActivity {

    Button log_in;
    TextView register;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();


    }


    public void login() {
        log_in = findViewById(R.id.boton_login);
        register = findViewById(R.id.register_login);
        forgot = findViewById(R.id.forgot_pass_login);

        register.setOnClickListener((v) -> startActivity(new Intent(Login.this, SignUpActivity.class)));

    }
}
