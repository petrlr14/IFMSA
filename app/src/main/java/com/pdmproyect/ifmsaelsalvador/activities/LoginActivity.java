package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pdmproyect.ifmsaelsalvador.models.Login;
import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.api.ClientRequest;

public class LoginActivity extends AppCompatActivity {

    Button log_in;
    TextView register;
    TextView forgot;
    EditText editTextUser, editTextPass;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();

    }


    private void bindViews() {
        log_in = findViewById(R.id.boton_login);
        log_in.setOnClickListener(v -> login());
        register = findViewById(R.id.register_login);
        register.setOnClickListener(v -> signIn());
        forgot = findViewById(R.id.forgot_pass_login);
        editTextPass = findViewById(R.id.password_login);
        editTextUser = findViewById(R.id.Username_login);
        linearLayout = findViewById(R.id.main_layout_login);
    }

    private void login() {
        if (editTextPass.getText().toString().equals("") ||
                editTextUser.getText().toString().equals("")) {
            Snackbar.make(linearLayout, getString(R.string.field_empty_message), Snackbar.LENGTH_LONG).show();
        } else {
            Login login = new Login(editTextUser.getText().toString(), editTextPass.getText().toString());
            ClientRequest.login(login, this);
        }
    }

    private void signIn() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

}
