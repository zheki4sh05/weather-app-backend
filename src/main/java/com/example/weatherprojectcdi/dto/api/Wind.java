package com.example.weatherprojectcdi.dto.api;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

    @JsonProperty("speed")
    private float speed;
    @JsonProperty("deg")
    private int deg;
    @JsonProperty("gust")
    private float gust;
}