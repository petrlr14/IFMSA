package com.pdmproyect.ifmsaelsalvador.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.adapter.ProjectAdapter;
import com.pdmproyect.ifmsaelsalvador.api.ClientRequest;
import com.pdmproyect.ifmsaelsalvador.api.IFMSAAPI;
import com.pdmproyect.ifmsaelsalvador.api.deserializers.ProjectDeserializer;
import com.pdmproyect.ifmsaelsalvador.database.IFMSAViewModel;
import com.pdmproyect.ifmsaelsalvador.database.entities.ProjectEntity;
import com.pdmproyect.ifmsaelsalvador.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    private IFMSAViewModel viewModel;
    private Context context;
    private SwipeRefreshLayout refreshLayout;
    private static String token;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
        token=context.getSharedPreferences("log", Context.MODE_PRIVATE)
                .getString("token", "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.committee_fragment, container, false);
        bindViews(view);
        setThings();
        return view;
    }

    /**
     * @param view view that will provide widgets references
     */
    private void bindViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_all);
        refreshLayout=view.findViewById(R.id.swipe);
    }

    private void setThings(){
        viewModel= ViewModelProviders.of(this).get(IFMSAViewModel.class);
        adapter=new ProjectAdapter();
        viewModel.getAllProjects().observe(this, v->adapter.setProjectEntityList(v));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout.setOnRefreshListener(()->refreshProjects(viewModel, getToken(), refreshLayout));
    }

    private String getToken(){
        return token;
    }

    private static void refreshProjects(IFMSAViewModel viewModel, String token, SwipeRefreshLayout refreshLayout){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Project.class, new ProjectDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFMSAAPI.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Call<List<Project>> getProjects=retrofit.create(IFMSAAPI.class)
                .getProjects("Bearer "+token);
        getProjects.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                System.out.println(response.code());
                refreshLayout.setRefreshing(false);
                if(response.code()==200){
                    viewModel.nukeTable();
                    for (Project project : response.body()) {
                        ProjectEntity entity=
                                new ProjectEntity(
                                        project.getId(),
                                        project.getCommittee(),
                                        project.getName(),
                                        project.getPlace(),
                                        project.getDescription()
                                );
                        viewModel.insertProject(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                t.printStackTrace();
            }
        });

    }

}
