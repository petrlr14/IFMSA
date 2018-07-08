package com.pdmproyect.ifmsaelsalvador.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ProjectEntity {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    private String committee, name, place, description;

    public ProjectEntity(@NonNull String id, @NonNull String committee, @NonNull String name, @NonNull String place, @NonNull String description) {
        this.id = id;
        this.committee = committee;
        this.name = name;
        this.place = place;
        this.description = description;
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

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    public void setPlace(@NonNull String place) {
        this.place = place;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
