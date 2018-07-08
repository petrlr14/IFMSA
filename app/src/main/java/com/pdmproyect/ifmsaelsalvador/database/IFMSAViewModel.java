package com.pdmproyect.ifmsaelsalvador.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;
import com.pdmproyect.ifmsaelsalvador.database.entities.ProjectEntity;

import java.util.List;

public class IFMSAViewModel extends AndroidViewModel {

    private Repository repository;

    public IFMSAViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    /* ***************************Committees*/


    public LiveData<List<Committee>> getAllCommittees() {
        return this.repository.getCommittees();
    }

    public void insertCommittee(Committee committee) {
        this.repository.insertCommittee(committee);
    }

    /* *****************************Projects*/


    public LiveData<List<ProjectEntity>> getAllProjects() {
        return this.repository.getProjects();
    }

    public LiveData<List<ProjectEntity>> getProjectsByCommittee(String committee) {
        return this.repository.getProjectsByCommittee(committee);
    }

    public LiveData<ProjectEntity> getProjectByID(String id) {
        return this.repository.getProjectByID(id);
    }

    public void insertProject(ProjectEntity projectEntity) {
        this.repository.insertProject(projectEntity);
    }

    public void nukeTable(){
        this.repository.nukeTable();
    }
}
