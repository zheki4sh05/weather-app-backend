package com.example.weatherprojectcdi.util.producers;

import com.example.weatherprojectcdi.dao.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.enterprise.inject.*;
import jakarta.inject.*;
import jakarta.persistence.*;

public class DaoFactory {

//    private EntityManager getEmf(){
//        return PersistUtil.getEntityManager();
//    }

    @Produces
    public EntityManager configureManager(){
        return PersistUtil.getEntityManager();
    }

    @Produces
    @Named("Auth")
    public AuthDao configureAuthDao(){
        return new AuthDao();
    }

    @Produces
    @Named("Session")
    public SessionDao configureSessionDao(){
        return new SessionDao();
    }

    @Produces
    @Named("User")
    public UserDao configureUserDao(){
        return new UserDao();
    }


}
