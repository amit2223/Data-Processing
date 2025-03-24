package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.TrackingData;

public interface TrackingDataRepository extends JpaRepository<TrackingData, Long> {   

    @Query("""
    SELECT t.timestamp,
    (SELECT COUNT(t1.id) FROM TrackingData t1 WHERE t1.timestamp <= t.timestamp) AS cumulativeUsers,
    AVG(t.posX) AS avgX,
    AVG(t.posY) AS avgY
    FROM TrackingData t
    GROUP BY t.timestamp
    """)
    List<Object[]> getGraphData();

    @Query("SELECT t.posX, t.posY, COUNT(t.id) AS intensity, MAX(t.timestamp) AS timestamp " +
       "FROM TrackingData t " +
       "GROUP BY t.posX, t.posY")
    List<Object[]> getHeatmapData();
}
