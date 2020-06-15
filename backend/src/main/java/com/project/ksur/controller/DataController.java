package com.project.ksur.controller;

import com.project.ksur.model.Data;
import com.project.ksur.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping()
    public List<DataDTO> getAllData() {
        return this.dataService.getAllData().stream().map(this::mapDataToDataDTO).collect(Collectors.toList());
    }

    @GetMapping(value = "/currentData")
    public DataDTO getCurrentData() {
        DataDTO data = mapDataToDataDTO(this.dataService.getMostCurrentData());
        data.setWaterTankDepth(this.dataService.getTankDepth());
        System.out.println(data);
        return data;
    }

    @GetMapping(value = "/currentThreshold")
    public Integer getCurrentThreshold() {
        return this.dataService.getMostCurrentData().getThreshold();
    }

    @GetMapping(value = "/statistics/{days}")
    public StatisticsDTO getStatistics(@PathVariable("days") Integer days) {
        Integer avgHumidity = this.dataService.getAvgHumidity(days);
        Integer avgLight = this.dataService.getAvgLight(days);
        List<Integer> leftWaterForDays = this.dataService.getLeftWaterForDays(7);
        Integer avgWater = calcAvgWater(leftWaterForDays);
        return new StatisticsDTO(avgHumidity, avgWater, avgLight);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addData(@RequestBody DataDTO dataDTO) {
        Data current = this.dataService.getMostCurrentData();
        Integer threshold = 1024;
        if (current != null && current.getThreshold() != null) {
            threshold = current.getThreshold();
        }
        this.dataService.addData(dataDTO.getLeftWaterInCm(), dataDTO.getLight(), dataDTO.getHumidity(), threshold);
    }

    @GetMapping(value = "/updateThreshold/{threshold}")
    public boolean updateThreshold(@PathVariable("threshold") Integer threshold) {
        System.out.println(threshold);
        this.dataService.updateThreshol(threshold);
        return true;
    }

    public DataDTO mapDataToDataDTO(Data data) {
        return new DataDTO(data.getLeftWaterInCm(), data.getLight(), data.getHumidity(),
                data.getThreshold(), data.getDate());
    }


    private Integer calcAvgWater(List<Integer> leftWaterForDays) {
        int days = 0;
        int sum = 0;

        for (int i = 0; i < leftWaterForDays.size() - 1; i++) {
            if (leftWaterForDays.get(i) != null && leftWaterForDays.get(i + 1) != null) {
                if (leftWaterForDays.get(i) < leftWaterForDays.get(i + 1)) {
                    sum += leftWaterForDays.get(i + 1) - leftWaterForDays.get(i);
                    days++;
                }
            }
        }

        return sum / days;
    }
}
