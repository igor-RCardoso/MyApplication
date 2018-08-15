package com.example.usuario.myapplication.connection;

import com.example.usuario.myapplication.model.employeeModelRest;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public class RegistryAPI {

    public static final Retrofit retrofitRX = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://projectmunch.herokuapp.com/")
            .build();

    public static final RegistryAPI.ApiService serviceRX = retrofitRX.create(ApiService.class);

    public interface ApiService {
        @GET("employees/")
        Observable<ArrayList<employeeModelRest>> getEmployees();
    }
}
