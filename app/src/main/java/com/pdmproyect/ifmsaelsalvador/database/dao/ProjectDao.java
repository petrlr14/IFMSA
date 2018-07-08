package com.pdmproyect.ifmsaelsalvador.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pdmproyect.ifmsaelsalvador.database.entities.ProjectEntity;

import java.util.List;

@Dao
public interface ProjectDao {

    /**
     * @param projectEntity projectEntity that will be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProjectEntity projectEntity);

    @Query("SELECT*FROM ProjectEntity")
    LiveData<List<ProjectEntity>> getAllProjects();

    /**
     * @param committe committee to be filtered
     * @return {@link LiveData} list of projects filtered
     */
    @Query("SELECT*FROM ProjectEntity WHERE committee=:committe")
    LiveData<List<ProjectEntity>> getProjectsByCommittee(String committe);

    /**
     * @param id id to be filtered
     * @return filtered project
     */
    @Query("SELECT*FROM ProjectEntity WHERE id=:id")
    LiveData<ProjectEntity> getProjectByID(String id);

    @Query("DELETE FROM ProjectEntity")
    void nukeTable();
}
