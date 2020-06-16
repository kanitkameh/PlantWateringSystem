package com.example.roboticsapp.ui.main;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roboticsapp.R;
import com.example.roboticsapp.ui.main.data.Data;
import com.example.roboticsapp.ui.main.data.Statistics;

import at.grabner.circleprogress.CircleProgressView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NowFragment extends Fragment {

    WebService webService;

    private TextView currentThreshold;
    private Button updateThreshold;
    private CircleProgressView humidityProgress;
    private CircleProgressView waterProgress;
    private CircleProgressView lightProgress;

    public static NowFragment newInstance() {
        return new NowFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.now_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.currentThreshold = view.findViewById(R.id.currentThreshold);
        this.updateThreshold = view.findViewById(R.id.btnUpdate);
        this.humidityProgress = view.findViewById(R.id.humidityBar);
        this.waterProgress = view.findViewById(R.id.waterBar);
        this.lightProgress = view.findViewById(R.id.lightBar);

        this.updateThreshold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://mysterious-brushlands-97617.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webService = retrofit.create(WebService.class);

        retrieveCurrentData();
    }

    private void retrieveCurrentData() {
        Call<Data> data = webService.getMostCurrentData();

        data.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    return;
                }

                Data data = response.body();

                if (data != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        System.out.println(data);
                        populateUI(data);
                    }
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void populateUI(Data currentData) {
        this.currentThreshold.setText("Current threshold is " + (100 - (currentData.getThreshold() * 100) / 1024));
        this.humidityProgress.setValue(100 - (currentData.getHumidity() * 100) / 1024);
        this.lightProgress.setValue((currentData.getLight() * 100) / 1024);

        if (currentData.getWaterTankDepth() != 0) {
            this.waterProgress.setValue((currentData.getLeftWaterInCm() * 100) / currentData.getWaterTankDepth());
        }
    }

    public void openDialog() {
        HumidityDialog humidityDialog = new HumidityDialog(new HumidityDialog.HumidityDialogListener() {
            @Override
            public void applyText(String newHumidityThreshold) {
                changeHumidityThreshold(newHumidityThreshold);
            }
        });
        humidityDialog.show(this.getFragmentManager(), "humidity dialog");
    }

    private void changeHumidityThreshold(String newHumidityThreshold) {
        System.out.println(newHumidityThreshold);
        Call<Boolean> statistics = webService.updateThreshold(1024 - (Integer.parseInt(newHumidityThreshold) * 1024) / 100);
        statistics.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    return;
                }

                retrieveCurrentData();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

}