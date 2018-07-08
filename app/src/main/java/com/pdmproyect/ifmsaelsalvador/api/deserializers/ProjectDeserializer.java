package com.pdmproyect.ifmsaelsalvador.api.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.pdmproyect.ifmsaelsalvador.models.Project;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjectDeserializer implements JsonDeserializer<Project> {
    @Override
    public Project deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object=json.getAsJsonObject();
        Project aux=new Project();
        aux.setId(object.get("_id").getAsString());
        aux.setName(object.get("name").getAsString());
        aux.setCommittee(object.get("committee").getAsString());
        aux.setPlace(object.get("place").getAsString());
        aux.setDescription(object.get("description").getAsString());
        aux.setVacancies(object.get("vacancies").getAsInt());
        return aux;
    }
}
