package com.example.weatherprojectcdi.dto.api;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {

    @JsonProperty("country")
    private String country;
    @JsonProperty("sunrise")
    private long sunrise;
    @JsonProperty("sunset")
    private long sunset;
}