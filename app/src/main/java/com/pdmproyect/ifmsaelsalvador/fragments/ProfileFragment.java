package com.pdmproyect.ifmsaelsalvador.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.pdmproyect.ifmsaelsalvador.R;

public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    ImageButton imageButtonUserPhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_proyectos_perfil);
        imageButtonUserPhoto = view.findViewById(R.id.imagebutton_foto_perfil);
        return view;
    }
}
