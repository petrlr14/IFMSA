package com.pdmproyect.ifmsaelsalvador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdmproyect.ifmsaelsalvador.adapter.StepperAdapter;
import com.stepstone.stepper.StepperLayout;

public class SignUpActivity extends AppCompatActivity {

    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mStepperLayout =findViewById(R.id.stepper_layout);
        mStepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));
    }
}
