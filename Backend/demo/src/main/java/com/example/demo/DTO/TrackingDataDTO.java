package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrackingDataDTO {
    
    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("pos_x")
    private Double posX;

    @JsonProperty("pos_y")
    private Double posY;

    @JsonProperty("vel_x")
    private Double velX;

    @JsonProperty("vel_y")
    private Double velY;

    @JsonProperty("confidence")
    private Integer confidence;

    
}
