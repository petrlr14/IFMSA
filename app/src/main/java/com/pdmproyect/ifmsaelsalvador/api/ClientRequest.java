package com.pdmproyect.ifmsaelsalvador.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pdmproyect.ifmsaelsalvador.activities.CompleteFormActivity;
import com.pdmproyect.ifmsaelsalvador.api.deserializers.PostProjectsDeserializer;
import com.pdmproyect.ifmsaelsalvador.api.deserializers.ProjectDeserializer;
import com.pdmproyect.ifmsaelsalvador.database.IFMSAViewModel;
import com.pdmproyect.ifmsaelsalvador.database.entities.ProjectEntity;
import com.pdmproyect.ifmsaelsalvador.models.Login;
import com.pdmproyect.ifmsaelsalvador.activities.MainActivity;
import com.pdmproyect.ifmsaelsalvador.api.deserializers.TokenDeserializer;
import com.pdmproyect.ifmsaelsalvador.models.NewUser;
import com.pdmproyect.ifmsaelsalvador.models.Project;
import com.pdmproyect.ifmsaelsalvador.utils.SignUpData;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRequest {

    static boolean result = false;

    private static IFMSAAPI getClient(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFMSAAPI.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IFMSAAPI.class);
    }

    public static boolean signUp(SignUpData user, Context context) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(String.class, new TokenDeserializer())
                .create();
        Call<String> call = getClient(gson)
                .signUP(
                        new NewUser(
                                user.getUsername(),
                                user.getName(),
                                user.getYear().charAt(0) + "",
                                user.getCollege(),
                                user.getPassword(),
                                user.getEmail(),
                                user.getPhone()
                        )
                );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 201) {
                        context.startActivity(new Intent(context, CompleteFormActivity.class));
                        ((Activity) context).finish();
                    }
                }
                System.out.println(response.code());
                System.out.println(response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    System.out.println("hola");
                }
            }
        });
        return result;
    }

    public static void login(Login login, Context context) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(String.class, new TokenDeserializer())
                .create();
        Call<String> call = getClient(gson)
                .login(login);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                if (response.code() == 200) {
                    saveToken(context, token);
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public static void createProject(Project project, String token) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(String.class, new PostProjectsDeserializer())
                .create();
        Call<String> call = getClient(gson).
                postProjects("Bearer " + token, new Project(
                        project.getCommittee(),
                        project.getName(),
                        project.getPlace(),
                        project.getDescription(),
                        project.getDate(),
                        project.getVacancies()
                ));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.i("CREATE PROJECT: ", "SUCCEFULL ");
                } else {
                    Log.i("CREATE PROJECT: ", "UNSUCCEFULL ");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("CREATE PROJECT: ", "FAILURE");
            }
        });
    }

    public static void getProjects(IFMSAViewModel viewModel, String token) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Project.class, new ProjectDeserializer())
                .create();
        System.out.println(token);
        Call<List<Project>> getProjects = getClient(gson).getProjects("Bearer " + token);
        getProjects.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                System.out.println(response.code());
                if (response.code() == 200) {
                    for (Project project : response.body()) {
                        ProjectEntity entity =
                                new ProjectEntity(
                                        project.getId(),
                                        project.getCommittee(),
                                        project.getName(),
                                        project.getPlace(),
                                        project.getDescription()
                                );
                        viewModel.insertProject(entity);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    System.out.println("holi");
                }
            }
        });

    }

    private static void saveToken(Context context, String token) {
        System.out.println(token);
        SharedPreferences preferences = context.getSharedPreferences("log", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

}
