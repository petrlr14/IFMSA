package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pdmproyect.ifmsaelsalvador.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentName extends android.support.v4.app.Fragment implements Step {

    TextInputEditText fname, lname, email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sign_up_name_layout, container, false);
        fname=view.findViewById(R.id.edit_text_first_name);
        lname=view.findViewById(R.id.edit_text_last_name);
        email=view.findViewById(R.id.edit_text_email);
        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
