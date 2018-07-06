package com.pdmproyect.ifmsaelsalvador.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdmproyect.ifmsaelsalvador.database.dao.CommitteeDao;
import com.pdmproyect.ifmsaelsalvador.database.dao.ProjectDao;
import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;
import com.pdmproyect.ifmsaelsalvador.database.entities.Project;

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
    public LiveData<List<Project>> getProjects(){
        return this.projectDao.getAllProjects();
    }
    public LiveData<List<Project>> getProjectsByCommittee(String committee){
        return this.projectDao.getProjectsByCommittee(committee);
    }
    public Project getProjectByID(String id){
        return this.projectDao.getProjectByID(id);
    }
    public void insertProject(Project project){
        new InsertProjectAsyncTask(projectDao).execute(project);
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
    public static class InsertProjectAsyncTask extends AsyncTask<Project, Void, Void>{

        private ProjectDao projectDao;

        private InsertProjectAsyncTask(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            this.projectDao.insert(projects[0]);
            return null;
        }
    }
}
