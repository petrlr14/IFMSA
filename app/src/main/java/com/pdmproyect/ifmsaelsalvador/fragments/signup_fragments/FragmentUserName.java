package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmproyect.ifmsaelsalvador.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

public class FragmentUserName extends Fragment implements Step {

    TextInputEditText username, password1, password2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sign_up_username_layout, container, false);
        username=view.findViewById(R.id.edit_text_username);
        password1=view.findViewById(R.id.edit_text_password);
        password2=view.findViewById(R.id.edit_text_password2);
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
