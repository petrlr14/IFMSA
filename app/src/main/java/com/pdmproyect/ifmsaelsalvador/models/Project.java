package com.pdmproyect.ifmsaelsalvador.models;

public class Project {
    private String id, committee, name, place, description, date;
    private int vacancies;

    public Project() {
    }

    public Project(String committee, String name, String place, String description, String date, int vacancies) {
        this.committee = committee;
        this.name = name;
        this.place = place;
        this.description = description;
        this.date = date;
        this.vacancies = vacancies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
