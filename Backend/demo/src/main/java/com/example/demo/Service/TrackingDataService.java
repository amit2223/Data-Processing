package com.example.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.TrackingData;
import com.example.demo.Repository.TrackingDataRepository;

@Service
public class TrackingDataService {

    @Autowired
    private TrackingDataRepository repository;
    
    public void saveData(TrackingData data) {
        repository.save(data);
    }
}


