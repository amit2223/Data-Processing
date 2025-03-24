package com.example.demo.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tracking_data")
@Data
public class TrackingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long timestamp;
    private Double posX;
    private Double posY;
    private Double velX;
    private Double velY;
    private Integer confidence;

    public TrackingData() {
    }

    public TrackingData(Long timestamp, Double posX, Double posY, Double velX, Double velY, Integer confidence) {
        this.timestamp = timestamp;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.confidence = confidence;
    }
}
