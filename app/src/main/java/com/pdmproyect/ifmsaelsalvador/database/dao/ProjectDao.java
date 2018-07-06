package com.pdmproyect.ifmsaelsalvador.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pdmproyect.ifmsaelsalvador.database.entities.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    /**
     * @param project project that will be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);

    @Query("SELECT*FROM Project")
    LiveData<List<Project>> getAllProjects();

    /**
     * @param committe committee to be filtered
     * @return {@link LiveData} list of projects filtered
     */
    @Query("SELECT*FROM Project WHERE committee=:committe")
    LiveData<List<Project>> getProjectsByCommittee(String committe);

    /**
     * @param id id to be filtered
     * @return filtered project
     */
    @Query("SELECT*FROM Project WHERE id=:id")
    Project getProjectByID(String id);
}
