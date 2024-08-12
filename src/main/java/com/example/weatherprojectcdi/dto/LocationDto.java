package com.example.weatherprojectcdi.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LocationDto {
    private Long id;
    private String name;
    private Double lat;
    private Double lon;
}
