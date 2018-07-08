package com.pdmproyect.ifmsaelsalvador.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdmproyect.ifmsaelsalvador.database.dao.CommitteeDao;
import com.pdmproyect.ifmsaelsalvador.database.dao.ProjectDao;
import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;
import com.pdmproyect.ifmsaelsalvador.database.entities.ProjectEntity;

import java.util.List;

public class Repository {

    private CommitteeDao committeeDao;
    private ProjectDao projectDao;

    public Repository(Application application) {
        AppDatabase db=AppDatabase.getInstance(application);
        this.committeeDao=db.committeeDao();
        this.projectDao=db.projectDao();
    }

    //Committees
    public LiveData<List<Committee>> getCommittees(){
        return this.committeeDao.getAllCommittees();
    }
    public void insertCommittee(Committee committee){
        new InsertCommitteeAsyncTask(committeeDao).execute(committee);
    }

    //Projects
    public LiveData<List<ProjectEntity>> getProjects(){
        return this.projectDao.getAllProjects();
    }
    public LiveData<List<ProjectEntity>> getProjectsByCommittee(String committee){
        return this.projectDao.getProjectsByCommittee(committee);
    }
    public LiveData<ProjectEntity> getProjectByID(String id){
        return this.projectDao.getProjectByID(id);
    }
    public void insertProject(ProjectEntity projectEntity){
        new InsertProjectAsyncTask(projectDao).execute(projectEntity);
    }

    public void nukeTable(){
        new deleteTableAsyncTask(projectDao).execute();
    }

    /* *****************************************INNER CLASSES****************************** */
    //Committees
    public static class InsertCommitteeAsyncTask extends AsyncTask<Committee, Void, Void>{

        private CommitteeDao committeeDao;

        public InsertCommitteeAsyncTask(CommitteeDao committeeDao) {
            this.committeeDao = committeeDao;
        }

        @Override
        protected Void doInBackground(Committee... committees) {
            this.committeeDao.insertCommittee(committees[0]);
            return null;
        }
    }
    //Projects
    public static class InsertProjectAsyncTask extends AsyncTask<ProjectEntity, Void, Void>{

        private ProjectDao projectDao;

        private InsertProjectAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(ProjectEntity... projectEntities) {
            this.projectDao.insert(projectEntities[0]);
            return null;
        }
    }

    private static class deleteTableAsyncTask extends AsyncTask<Void, Void, Void> {

        ProjectDao projectDao;

        public deleteTableAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            projectDao.nukeTable();
            return null;
        }
    }
}
