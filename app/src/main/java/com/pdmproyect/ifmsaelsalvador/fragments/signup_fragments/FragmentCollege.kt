package com.pdmproyect.ifmsaelsalvador.fragments.signup_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.pdmproyect.ifmsaelsalvador.R
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import java.io.Serializable

class FragmentCollege:Fragment(),Step{

    lateinit var spinner_college: Spinner
    lateinit var spinner_year: Spinner
    lateinit var adapter_college: ArrayAdapter<CharSequence>
    lateinit var adapter_year: ArrayAdapter<CharSequence>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var vista =  LayoutInflater.from(context).inflate(R.layout.sing_up_college_layout, container, false)

        spinner_college = vista.findViewById(R.id.spinner_college)
        spinner_year = vista.findViewById(R.id.spinner_year)

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
        return null
    }

    override fun onError(error: VerificationError) {

    }

}