package com.pdmproyect.ifmsaelsalvador.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Project {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    private String committee;

    public Project(@NonNull String id, @NonNull String committee) {
        this.id = id;
        this.committee = committee;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getCommittee() {
        return committee;
    }

    public void setCommittee(@NonNull String committee) {
        this.committee = committee;
    }
}
