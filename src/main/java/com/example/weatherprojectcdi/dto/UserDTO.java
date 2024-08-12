package com.example.weatherprojectcdi.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public Boolean isNotEmpty(){
        return !email.isEmpty() && !password.isEmpty();
    }



}
