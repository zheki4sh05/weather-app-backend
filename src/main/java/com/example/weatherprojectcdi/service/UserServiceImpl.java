package com.example.weatherprojectcdi.service;

import com.example.weatherprojectcdi.dao.*;
import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.inject.*;
import jakarta.servlet.http.*;

import java.util.*;

public class UserServiceImpl implements IUserService {

    @Inject
    @Named("User")
    private UserDao userDao;

    @Inject
    private ISessionService sessionService;

    public List<LocationDto> findLocationsByUser(User user) {

        List<Location> locationList= userDao.findAllLocationsByUserId(user.getId());

        return mapAllFromEntity(locationList);


    }

    private List<LocationDto> mapAllFromEntity(List<Location> locationList) {

        List<LocationDto> locationDtos = new ArrayList<>(locationList.size());

        locationList.forEach(item->{
            locationDtos.add(

                    LocationDto.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .lon(item.getLon())
                            .lat(item.getLat())
                            .build()
            );
        });

        return locationDtos;

    }


    public void save(LocationDto locationDto, HttpServletRequest request) throws InternalServerErrorException {

        Optional<Cookie> cookie = HttpRequestHandler.getSessionCookie(request);

        User user = sessionService.findUserByCookie(cookie);

        Location location = mapFromDto(locationDto);
        userDao.mergeUser(user, location);



    }

    public void delete(Long id) {



        userDao.deleteById(id);

    }

    private Location mapFromDto(LocationDto locationDto){
        return Location.builder()
                .name(locationDto.getName())
                .lon(locationDto.getLon())
                .lat(locationDto.getLat())
                .build();
    }

}
