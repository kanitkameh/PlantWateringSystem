package com.project.ksur.service;

import com.project.ksur.model.Data;
import com.project.ksur.repository.IDataRepository;
import com.project.ksur.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private IDataRepository dataRepository;

    public List<Data> getAllData() {
        Iterable<Data> it = this.dataRepository.findAll();

        List<Data> data = new ArrayList<>();
        it.forEach(data::add);

        return data;
    }

    public void addData(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold) {
        Data newData = new Data(leftWaterInCm, light, humidity, threshold, DateTimeUtil.getDate());
        this.dataRepository.save(newData);
    }

    public Data getMostCurrentData() {
        return this.dataRepository.getMostCurrentData();
    }

    public Integer getTankDepth() {
        return this.dataRepository.getTankDepth();
    }

    public List<Integer> getLeftWaterForDays(Integer days) {
        return this.dataRepository.getLeftWaterForDays(days);
    }

    public Integer getAvgHumidity(Integer days) {
        return this.dataRepository.getAvgHumidity(days);
    }

    public Integer getAvgLight(Integer days) {
        return this.dataRepository.getAvgLight(days);
    }

    public void updateThreshol(Integer threshold) {
        Data current = this.dataRepository.getMostCurrentData();
        this.addData(current.getLeftWaterInCm(), current.getLight(), current.getHumidity(), threshold);
    }
}
