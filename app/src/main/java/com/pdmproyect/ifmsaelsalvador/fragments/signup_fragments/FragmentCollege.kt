package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.pdmproyect.ifmsaelsalvador.R
import com.pdmproyect.ifmsaelsalvador.api.ClientRequest
import com.pdmproyect.ifmsaelsalvador.utils.SignUpData
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.Step
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.sing_up_college_layout.*
import java.io.Serializable

class FragmentCollege:Fragment(),BlockingStep{

    lateinit var spinner_college: Spinner
    lateinit var spinner_year: Spinner
    lateinit var adapter_college: ArrayAdapter<CharSequence>
    lateinit var adapter_year: ArrayAdapter<CharSequence>
    lateinit var phone_nomber: TextInputEditText
    var mContext: Context?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext=context

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val vista =  LayoutInflater.from(context).inflate(R.layout.sing_up_college_layout, container, false)

        spinner_college = vista.findViewById(R.id.spinner_college)
        spinner_year = vista.findViewById(R.id.spinner_year)
        phone_nomber=vista.findViewById(R.id.edit_text_phone_number)

        adapter_college = ArrayAdapter.createFromResource(context, R.array.colleges_array, android.R.layout.simple_spinner_item)
        adapter_college.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        adapter_year = ArrayAdapter.createFromResource(context, R.array.years_array, android.R.layout.simple_spinner_item)
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_year.adapter = adapter_year
        spinner_college.adapter = adapter_college

        return vista
    }

    override fun onSelected() {
    }

    override fun verifyStep(): VerificationError? {
        if(edit_text_phone_number.text.equals("")){
            return VerificationError("Phone number must not be empty")
        }
        return null
    }

    override fun onError(error: VerificationError) {
        Toast.makeText(mContext, error.errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        SignUpData.getInstance().setThirdStepInfo(spinner_college.selectedItem.toString(), spinner_year.selectedItem.toString(), phone_nomber.text.toString())
        if (ClientRequest.signUp(SignUpData.getInstance(), mContext)){
            println("Bien")
        }else{
            println("Bad")
        }
    }


    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

}