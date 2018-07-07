package com.pdmproyect.ifmsaelsalvador.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.activities.CommitteeOverview;
import com.pdmproyect.ifmsaelsalvador.adapter.CommitteeAdapter;
import com.pdmproyect.ifmsaelsalvador.database.IFMSAViewModel;

public class CommitteeFragment extends Fragment {

    private IFMSAViewModel viewModel;
    private CommitteeAdapter adapter;
    private Context context;
    //widgets
    private RecyclerView recyclerViewProjects;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.committee_fragment, container, false);
        bindViews(view);
        setThings();
        return view;
    }

    /**
     * @param view view that will provide widgets references
     */
    private void bindViews(View view) {
        recyclerViewProjects = view.findViewById(R.id.recyclerview_all_committee);
    }

    private void setThings() {
        adapter = new CommitteeAdapter() {
            @Override
            public void onCommitteeClick(String name) {
                Intent transition = new Intent(context, CommitteeOverview.class);
                transition.putExtra("name", name);
                startActivity(transition);
            }
        };
        viewModel = ViewModelProviders.of(this).get(IFMSAViewModel.class);
        viewModel.getAllCommittees().observe(this, (v) -> adapter.setCommittees(v));
        recyclerViewProjects.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewProjects.setAdapter(adapter);
    }
}
