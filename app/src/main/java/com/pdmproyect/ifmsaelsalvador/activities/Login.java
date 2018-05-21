package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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


    public void login(){
        log_in = findViewById(R.id.boton_login);
        register = findViewById(R.id.register_login);
        forgot = findViewById(R.id.forgot_pass_login);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pag_principal = new Intent(Login.this,ProfileActivity.class);
                startActivity(pag_principal);

            }
        });


    }
}
