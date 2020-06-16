package com.example.roboticsapp.ui.main.data;

import java.util.Date;

public class Data {
    private Integer leftWaterInCm;
    private Integer light;
    private Integer humidity;
    private Integer threshold;
    private Integer waterTankDepth;
    private Date date;

    public Data() {
    }

    public Data(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold, Integer waterTankDepth, Date date) {
        this.leftWaterInCm = leftWaterInCm;
        this.light = light;
        this.humidity = humidity;
        this.threshold = threshold;
        this.waterTankDepth = waterTankDepth;
        this.date = date;
    }

    public Data(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold, Date date) {
        this.leftWaterInCm = leftWaterInCm;
        this.light = light;
        this.humidity = humidity;
        this.threshold = threshold;
        this.date = date;
    }

    public Integer getWaterTankDepth() {
        return waterTankDepth;
    }

    public void setWaterTankDepth(Integer waterTankDepth) {
        this.waterTankDepth = waterTankDepth;
    }

    public Integer getLeftWaterInCm() {
        return leftWaterInCm;
    }

    public void setLeftWaterInCm(Integer leftWaterInCm) {
        this.leftWaterInCm = leftWaterInCm;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "leftWaterInCm=" + leftWaterInCm +
                ", light=" + light +
                ", humidity=" + humidity +
                ", Threshold=" + threshold +
                ", date=" + date +
                '}';
    }
}
