package com.pdmproyect.ifmsaelsalvador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.pdmproyect.ifmsaelsalvador.R;

public class CompleteFormActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_completo);
        bindViews();
        setThings();
    }

    private void bindViews() {
        button = findViewById(R.id.Button_Regresar_formulario);
    }

    private void setThings() {
        button.setOnClickListener(v -> {
                    startActivity(new Intent(CompleteFormActivity.this, LoginActivity.class));
                    finish();
                }
        );
    }

}
