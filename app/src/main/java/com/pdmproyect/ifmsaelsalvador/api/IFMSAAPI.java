package com.pdmproyect.ifmsaelsalvador.api;

import com.pdmproyect.ifmsaelsalvador.models.Login;
import com.pdmproyect.ifmsaelsalvador.models.NewUser;
import com.pdmproyect.ifmsaelsalvador.models.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFMSAAPI {

    String END_POINT = "https://api-proyecto-pdm.herokuapp.com/api/";

    @POST("signup")
    Call<String> signUP(
            @Body NewUser newUser
    );

    @POST("login")
    Call<String> login(@Body Login login);

    @FormUrlEncoded
    @POST("project")
    Call<String> postProjects(@Header("Authorization") String auth,
                              @Field("project") Project project);

    @GET("project")
    Call<List<Project>> getProjects(@Header("Authorization") String auth);
}
