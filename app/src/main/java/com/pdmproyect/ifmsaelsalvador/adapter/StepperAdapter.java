package com.pdmproyect.ifmsaelsalvador.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments.FragmentCollege;
import com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments.FragmentName;
import com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments.FragmentUserName;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;


public class StepperAdapter extends AbstractFragmentStepAdapter {

    private static final String KEY="CURRENT_STEP_POSITION_KEY";

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        Bundle b = new Bundle();
        switch (position) {
            case 0:
                FragmentName fn = new FragmentName();
                b.putInt(KEY, position);
                fn.setArguments(b);
                return fn;
            case 1:
                FragmentUserName fun = new FragmentUserName();
                b.putInt(KEY, position);
                fun.setArguments(b);
                return fun;
            default:
                FragmentCollege fc=new FragmentCollege();
                b.putInt(KEY, position);
                fc.setArguments(b);
                return fc;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
