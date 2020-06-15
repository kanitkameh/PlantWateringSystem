package com.project.ksur.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long data_id;
    @Column(name = "left_water_in_cm")
    private Integer leftWaterInCm;
    @Column(name = "light")
    private Integer light;
    @Column(name = "humidity")
    private Integer humidity;
    @Column(name = "threshold")
    private Integer threshold;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public Data() {
    }

    public Data(Integer leftWaterInCm, Integer light, Integer humidity, Integer threshold, Date date) {
        this.leftWaterInCm = leftWaterInCm;
        this.light = light;
        this.humidity = humidity;
        this.threshold = threshold;
        this.date = date;
    }

    public Long getData_id() {
        return data_id;
    }

    public void setData_id(Long data_id) {
        this.data_id = data_id;
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
                "data_id=" + data_id +
                ", leftWaterInCm=" + leftWaterInCm +
                ", light=" + light +
                ", humidity=" + humidity +
                ", threshold=" + threshold +
                ", date=" + date +
                '}';
    }
}
