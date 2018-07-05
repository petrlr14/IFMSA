package com.pdmproyect.ifmsaelsalvador.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;

import java.util.List;

@Dao
public interface CommitteeDao {

    /**
     * @param committee committee that will be inserted to local database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommittee(Committee committee);

    /**
     * @return will return all committees in the local database
     */
    @Query("SELECT*FROM Committee")
    LiveData<List<Committee>> getAllCommittees();
}
