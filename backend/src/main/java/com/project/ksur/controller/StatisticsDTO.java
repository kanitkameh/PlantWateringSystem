package com.project.ksur.controller;

public class StatisticsDTO {
    Integer avgHumidity;
    Integer avgWater;
    Integer avgLight;

    public StatisticsDTO() {
    }

    public StatisticsDTO(Integer avgHumidity, Integer avgLight) {
        this.avgHumidity = avgHumidity;
        this.avgLight = avgLight;
    }

    public StatisticsDTO(Integer avgHumidity, Integer avgWater, Integer avgLight) {
        this.avgHumidity = avgHumidity;
        this.avgWater = avgWater;
        this.avgLight = avgLight;
    }

    public Integer getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(Integer avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public Integer getAvgWater() {
        return avgWater;
    }

    public void setAvgWater(Integer avgWater) {
        this.avgWater = avgWater;
    }

    public Integer getAvgLight() {
        return avgLight;
    }

    public void setAvgLight(Integer avgLight) {
        this.avgLight = avgLight;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "avgHumidity=" + avgHumidity +
                ", avgWater=" + avgWater +
                ", avgLight=" + avgLight +
                '}';
    }
}
