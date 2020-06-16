package com.example.roboticsapp.ui.main.data;

public class Statistics {
    Integer avgHumidity;
    Integer avgWater;
    Integer avgLight;
    Integer leftWaterInCm;
    Integer tankDepth;


    public Statistics() {
    }

    public Statistics(Integer avgHumidity, Integer avgWater, Integer avgLight) {
        this.avgHumidity = avgHumidity;
        this.avgWater = avgWater;
        this.avgLight = avgLight;
    }

    public Statistics(Integer avgHumidity, Integer avgWater, Integer avgLight, Integer leftWaterInCm, Integer tankDepth) {
        this.avgHumidity = avgHumidity;
        this.avgWater = avgWater;
        this.avgLight = avgLight;
        this.leftWaterInCm = leftWaterInCm;
        this.tankDepth = tankDepth;
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

    public Integer getLeftWaterInCm() {
        return leftWaterInCm;
    }

    public void setLeftWaterInCm(Integer leftWaterInCm) {
        this.leftWaterInCm = leftWaterInCm;
    }

    public Integer getTankDepth() {
        return tankDepth;
    }

    public void setTankDepth(Integer tankDepth) {
        this.tankDepth = tankDepth;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "avgHumidity=" + avgHumidity +
                ", avgWater=" + avgWater +
                ", avgLight=" + avgLight +
                ", leftWaterInCm=" + leftWaterInCm +
                ", tankDepth=" + tankDepth +
                '}';
    }
}
