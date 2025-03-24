package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TrackingDataDTO;
import com.example.demo.Entity.TrackingData;
import com.example.demo.Service.TrackingDataService;

@RestController
@RequestMapping("/api")
public class StreamingDataController {

    @Autowired
    private TrackingDataService trackingDataService;

    @PostMapping("/publish-data")
    public ResponseEntity<String> startStreaming(@RequestBody TrackingDataDTO data) {

         // Convert DTO to Entity
        TrackingData entity = new TrackingData();
        entity.setTimestamp(data.getTimestamp());
        entity.setPosX(data.getPosX());
        entity.setPosY(data.getPosY());
        entity.setVelX(data.getVelX());
        entity.setVelY(data.getVelY());
        entity.setConfidence(data.getConfidence());
        
        // Save to database
        trackingDataService.saveData(entity);

        return ResponseEntity.ok("Data saved successfully");
    }
}

