package com.pdmproyect.ifmsaelsalvador.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pdmproyect.ifmsaelsalvador.database.dao.CommitteeDao;
import com.pdmproyect.ifmsaelsalvador.database.dao.ProjectDao;
import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;

@Database(entities = {Committee.class}, exportSchema = false,version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static volatile AppDatabase db;
    private static final String DBNAME="ifmsa.db";

    public static AppDatabase getInstance(Context context) {
        if(db==null){
            db= Room.databaseBuilder(context, AppDatabase.class, DBNAME)
                    .build();
        }
        return db;
    }

    public abstract CommitteeDao committeeDao();
    public abstract ProjectDao projectDao();

}
