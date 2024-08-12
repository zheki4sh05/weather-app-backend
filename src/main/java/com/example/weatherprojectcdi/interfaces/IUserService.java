package com.example.weatherprojectcdi.interfaces;


import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.servlet.http.*;

import java.util.*;

public interface IUserService {

    public List<LocationDto> findLocationsByUser(User user);

    public void save(LocationDto locationDto, HttpServletRequest request) throws InternalServerErrorException;

    public void delete(Long id);


}
