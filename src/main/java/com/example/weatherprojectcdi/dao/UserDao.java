package com.example.weatherprojectcdi.dao;


import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.inject.*;
import jakarta.persistence.*;

import java.util.*;

public class UserDao extends BaseDao{


    public UserDao(EntityManager entityManager){
        super(entityManager);
    }

    public List<Location> findAllLocationsByUserId(Long id) {

        TypedQuery<Location> query = this.em.createQuery("SELECT l FROM Location l " +
                        "JOIN l.user u " +
                        "WHERE u.id = :userId",
                Location.class);
        query.setParameter("userId", id);
        return query.getResultList();

    }



    public void mergeUser(User user, Location location) throws InternalServerErrorException {
        User merged =  em.merge(user);
        location.setUser(merged);
        save(location);
    }

    public Optional<Session> findById(UUID uuid) {

        Session session = em.find(Session.class, uuid);
        return Optional.of(session);

    }
}
