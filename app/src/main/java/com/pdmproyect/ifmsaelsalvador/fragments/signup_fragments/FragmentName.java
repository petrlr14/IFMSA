package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.utils.SignUpData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.regex.Pattern;

public class FragmentName extends android.support.v4.app.Fragment implements BlockingStep {

    TextInputEditText fname, lname, email;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_name_layout, container, false);
        fname = view.findViewById(R.id.edit_text_first_name);
        lname = view.findViewById(R.id.edit_text_last_name);
        email = view.findViewById(R.id.edit_text_email);
        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (fname.getText().toString().equals("") || lname.getText().toString().equals("")
                || email.getText().toString().equals("")) {
            return new VerificationError("Field must not be empty");
        }
        if (!validateEmail(email.getText().toString())) {
            return new VerificationError("Email must be valid");
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
    @UiThread
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(() -> onNext(callback), 500);
    }

    private void onNext(StepperLayout.OnNextClickedCallback callback) {
        SignUpData.getInstance().
                setFirstStepInfo(
                        fname.getText().toString()
                                + " " + lname.getText().toString(),
                        email.getText().toString()
                );
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }


    /**
     * @param email email to be validated
     * @return true if is valid, false otherwise
     */
    private boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}

