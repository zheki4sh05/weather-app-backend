package com.example.weatherprojectcdi.interfaces;


import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.servlet.http.*;
import lombok.*;

import java.util.*;

public interface ISessionService {

     UUID createByUser(User user) throws InternalServerErrorException;


     void delete(String value);



     boolean isSessionActiveBy(Optional<Cookie> cookie);

    @SneakyThrows
     User findUserByCookie(Optional<Cookie> sessionCookie) ;

}
