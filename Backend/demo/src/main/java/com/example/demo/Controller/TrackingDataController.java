package com.example.demo.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DataPointDTO;
import com.example.demo.DTO.HeatmapPointDTO;
import com.example.demo.Repository.TrackingDataRepository;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*") // Allow cross-origin requests from any domain
public class TrackingDataController {

    @Autowired
    private TrackingDataRepository repository;

    // Endpoint for getting the graph data: timestamp, cumulative users, avgX, avgY
    @GetMapping("/graph")
    public List<DataPointDTO> getGraphData() {
        List<Object[]> results = repository.getGraphData();
        
        // Mapping the query results to DataPointDTO
        return results.stream()
            .map(row -> new DataPointDTO(
                    (Long) row[0],            // Timestamp
                    (Long) row[1],            // Cumulative Users
                    (Double) row[2],          // Average X Position
                    (Double) row[3]))         // Average Y Position
            .toList();
    }

    // Endpoint for getting the heatmap data: posX, posY, intensity
    @GetMapping("/heatmap")
    public List<HeatmapPointDTO> getHeatmapData() {
        List<Object[]> results = repository.getHeatmapData();
        return results.stream()
                .map(row -> new HeatmapPointDTO(
                        (Double) row[0],  // posX
                        (Double) row[1],  // posY
                        (Long) row[2],    // intensity
                        (Long) row[3]     // timestamp
                ))
                .collect(Collectors.toList());
    }
    
}
