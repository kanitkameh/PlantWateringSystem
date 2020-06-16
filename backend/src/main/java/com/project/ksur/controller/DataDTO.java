package com.project.ksur.controller;

import java.util.Date;

public class DataDTO {
    private Integer leftWaterInCm;
    private Integer light;
    private Integer humidity;
    private Integer Threshold;
    private Integer waterTankDepth;
    private Date date;


    public DataDTO() {
    }

    public DataDTO(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold, Date date) {
        this.leftWaterInCm = leftWaterInCm;
        this.light = light;
        this.humidity = humidity;
        Threshold = threshold;
        this.date = date;
    }

    public DataDTO(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold, Integer waterTankDepth, Date date) {
        this.leftWaterInCm = leftWaterInCm;
        this.light = light;
        this.humidity = humidity;
        Threshold = threshold;
        this.waterTankDepth = waterTankDepth;
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
        return Threshold;
    }

    public void setThreshold(Integer threshold) {
        Threshold = threshold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "leftWaterInCm=" + leftWaterInCm +
                ", light=" + light +
                ", humidity=" + humidity +
                ", Threshold=" + Threshold +
                ", date=" + date +
                '}';
    }
}