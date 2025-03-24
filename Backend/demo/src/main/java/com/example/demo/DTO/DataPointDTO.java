package com.example.demo.DTO;

public class DataPointDTO {
    private Long timestamp;
    private Long cumulativeUsers;
    private Double avgX;
    private Double avgY;

    public DataPointDTO(Long timestamp, Long cumulativeUsers, Double avgX, Double avgY) {
        this.timestamp = timestamp;
        this.cumulativeUsers = cumulativeUsers;
        this.avgX = avgX;
        this.avgY = avgY;
    }

    // Getters and setters for all fields
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public Long getCumulativeUsers() { return cumulativeUsers; }
    public void setCumulativeUsers(Long cumulativeUsers) { this.cumulativeUsers = cumulativeUsers; }

    public Double getAvgX() { return avgX; }
    public void setAvgX(Double avgX) { this.avgX = avgX; }

    public Double getAvgY() { return avgY; }
    public void setAvgY(Double avgY) { this.avgY = avgY; }
}
