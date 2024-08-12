package com.example.weatherprojectcdi.dto.api;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDto {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("weather")
    private List<Weather> weatherList;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("visibility")
    private int visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private long dt;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("timezone")
    private long timezone;
    @JsonProperty("name")
    private String city;
    @JsonProperty("locId")
    private Long location;

    public void setLocationId(Long id) {
        this.location = id;
    }
}
