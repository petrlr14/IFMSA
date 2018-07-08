package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.utils.SignUpData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class FragmentUserName extends Fragment implements BlockingStep {

    TextInputEditText username, password1, password2;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

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
        if (username.getText().toString().equals("")
                ||password1.getText().toString().equals("")
                ||password2.getText().toString().equals("")
                ) {
            return new VerificationError("Field must not be empty");
        }
        if(!password1.getText().toString().equals(password2.getText().toString())){
            return new VerificationError("Passwords must match");
        }
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(context, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(()->onNext(callback), 500);
    }

    private void onNext(StepperLayout.OnNextClickedCallback callback){
        SignUpData.getInstance().
                setSecondStepInfo(
                        username.getText().toString(),
                        password1.getText().toString()
                );
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
