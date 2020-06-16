package com.example.roboticsapp.ui.main;

import com.example.roboticsapp.ui.main.data.Data;
import com.example.roboticsapp.ui.main.data.Statistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("/data")
    Call<List<Data>> getData();

    @GET("/data/currentData")
    Call<Data> getMostCurrentData();

    @GET("/data/statistics/{days}")
    Call<Statistics> getStatistics(@Path("days") Integer days);

    @GET("/data/updateThreshold/{threshold}")
    Call<Boolean> updateThreshold(@Path("threshold") Integer threshold);
}
