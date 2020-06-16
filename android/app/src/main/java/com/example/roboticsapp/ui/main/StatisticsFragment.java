package com.example.roboticsapp.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.roboticsapp.R;
import com.example.roboticsapp.ui.main.data.Data;
import com.example.roboticsapp.ui.main.data.Statistics;

import java.util.List;

import at.grabner.circleprogress.CircleProgressView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsFragment extends Fragment {

    private Spinner days;
    private Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private TextView waterLabel;
    private CircleProgressView humidityProgress;
    private CircleProgressView waterProgress;
    private CircleProgressView lightProgress;

    WebService webService;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.statistics_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.waterLabel = view.findViewById(R.id.waterLabel);
        this.humidityProgress = view.findViewById(R.id.humidityBar);
        this.waterProgress = view.findViewById(R.id.waterBar);
        this.lightProgress = view.findViewById(R.id.lightBar);

        InitDaysSpinner(view);

        retrieveData((Integer) days.getSelectedItem());
    }

    private void InitDaysSpinner(@NonNull View view) {
        days = (Spinner) view.findViewById(R.id.days);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, this.nums);
        days.setAdapter(adapter);
        days.setSelection(6);
        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer days = (Integer) adapterView.getItemAtPosition(i);
                retrieveData(days);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void retrieveData(Integer days) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://mysterious-brushlands-97617.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webService = retrofit.create(WebService.class);

        System.out.println("Retrieving statistics for " + days + " days...");
        Call<Statistics> statistics = webService.getStatistics(days);

        statistics.enqueue(new Callback<Statistics>() {
            @Override
            public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    return;
                }

                Statistics statistics = response.body();

                if (statistics != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        System.out.println(statistics);
                        populateUI(statistics);
                    }
                }
            }

            @Override
            public void onFailure(Call<Statistics> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void populateUI(Statistics statistics) {
        Integer waterLeft = (statistics.getLeftWaterInCm() * 100) / statistics.getTankDepth();
        this.humidityProgress.setValue(100 - (statistics.getAvgHumidity() * 100) / 1024);
        this.waterLabel.setText("Water will be enough for " + waterLeft / statistics.getAvgWater() + " days");
        this.waterProgress.setValue(statistics.getAvgWater());
        this.lightProgress.setValue((statistics.getAvgLight() * 100) / 1024);
    }
}
