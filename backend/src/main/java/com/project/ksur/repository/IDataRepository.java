package com.project.ksur.repository;

import com.project.ksur.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDataRepository extends JpaRepository<Data, Long> {

    @Query(value = "select * from data order by date desc limit 1", nativeQuery = true)
    public Data getMostCurrentData();

    @Query(value = "select max(left_water_in_cm) from data", nativeQuery = true)
    public Integer getTankDepth();

    @Query(value = "select left_water_in_cm from data where date > (CURRENT_DATE - INTERVAL '?1 days');", nativeQuery = true)
    public List<Integer> getLeftWaterForDays(Integer days);

    @Query(value = "select avg(humidity) from data where date > (CURRENT_DATE - INTERVAL '?1 days');",
            nativeQuery = true)
    public Integer getAvgHumidity(Integer days);

    @Query(value = "select avg(light) from data where date > (CURRENT_DATE - INTERVAL '?1 days');",
            nativeQuery = true)
    public Integer getAvgLight(Integer days);

}
