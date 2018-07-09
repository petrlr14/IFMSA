package com.pdmproyect.ifmsaelsalvador.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.api.ClientRequest;
import com.pdmproyect.ifmsaelsalvador.models.Login;
import com.pdmproyect.ifmsaelsalvador.models.Project;

import org.w3c.dom.Text;


public class CreateProjectsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String[] options = {"A", "B", "C"};

    private Spinner comite;
    private TextView name;
    private TextView date;
    private TextView place;
    private TextView description;
    private TextView vacancies;
    private Button createProject;

    private LinearLayout linearLayout;


    public CreateProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateProjectsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateProjectsFragment newInstance(String param1, String param2) {
        CreateProjectsFragment fragment = new CreateProjectsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.from(container.getContext()).inflate(R.layout.crear_proyecto, container, false);
        comite = (Spinner) rootView.findViewById(R.id.comite_spinner);
        name = (TextView) rootView.findViewById(R.id.EditText_Nombre_CrearProyeto);
        place = (TextView) rootView.findViewById(R.id.EditText_Lugar_CrearProyecto);
        description = (TextView) rootView.findViewById(R.id.edit_text_descrption);
        vacancies = (TextView) rootView.findViewById(R.id.edit_text_vacancies);
        createProject = (Button) rootView.findViewById(R.id.button_crear_evento);

        final String[] comiteName = new String[1];
        final Boolean[] optionSelected = new Boolean[1];
        optionSelected[0] = true;

        comite.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, options));
        comite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                comiteName[0] = options[position];
                optionSelected[0] = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                optionSelected[0] = true;
            }
        });

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionSelected[0] ||
                        name.getText().toString().equals("") ||
                        date.getText().toString().equals("") ||
                        place.getText().toString().equals("") ||
                        description.getText().toString().equals("") ||
                        vacancies.getText().toString().equals("")) {
                    Snackbar.make(rootView, getString(R.string.field_empty_message), Snackbar.LENGTH_LONG).show();
                } else {
                    Project project = new Project(comiteName[0], name.getText().toString(),
                            place.getText().toString(), description.getText().toString(),
                            date.getText().toString(), Integer.parseInt(vacancies.getText().toString()));

                    ClientRequest.createProject(project, getToken(rootView.getContext()));
                }

            }
        });


        return rootView;
    }

    private String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(getString(R.string.sharedpreferences_name), Context.MODE_PRIVATE);
        String token = preferences.getString(getString(R.string.sharedpreferences_key), "");
        return token;
    }


}
