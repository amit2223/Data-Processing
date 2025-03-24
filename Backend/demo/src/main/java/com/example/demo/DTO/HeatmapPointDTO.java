package com.example.demo.DTO;

public class HeatmapPointDTO {
    private Double posX;
    private Double posY;
    private Long intensity;
    private Long timestamp; // Add the timestamp field

    // Constructor
    public HeatmapPointDTO(Double posX, Double posY, Long intensity, Long timestamp) {
        this.posX = posX;
        this.posY = posY;
        this.intensity = intensity;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public Long getIntensity() {
        return intensity;
    }

    public void setIntensity(Long intensity) {
        this.intensity = intensity;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
