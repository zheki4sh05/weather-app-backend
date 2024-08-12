package com.example.weatherprojectcdi.service;


import com.example.weatherprojectcdi.dao.*;
import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import jakarta.inject.*;

import java.util.*;

public class AuthorizationServiceImpl implements IAuthorizationService {

    @Inject
    @Named("Auth")
    private AuthDao authDao;

    @Override
    public User create(UserDTO userDTO) throws NotUniqueEmail,InternalServerErrorException{

        User isExist = authDao.findByEmail(userDTO.getEmail());

        if (isExist == null) {
            User newUser  = User.builder().email(userDTO.getEmail()).password(userDTO.getPassword()).build();

            User savedUser = authDao.saveAndReturn(newUser);

            return savedUser;

        }else{
            throw new NotUniqueEmail("Such email already exist");
        }
    }

    @Override
    public Optional<User> find(UserDTO userDTO) {

        User isExist = authDao.findByEmail(userDTO.getEmail());

        return Optional.of(isExist);

    }

}
