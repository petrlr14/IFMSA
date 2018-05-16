package com.pdmproyect.ifmsaelsalvador.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments.FragmentName;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;


public class StepperAdapter extends AbstractFragmentStepAdapter {
    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        FragmentName fn = new FragmentName();
        Bundle b = new Bundle();
        b.putInt("CURRENT_STEP_POSITION_KEY", position);
        fn.setArguments(b);
        return fn;
    }

    @Override
    public int getCount() {
        return 1;
    }

}
