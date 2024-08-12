package com.example.weatherprojectcdi.dao;

import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.inject.*;
import jakarta.persistence.*;
@Singleton
public class AuthDao extends BaseDao{


    public User findByEmail(String email){


        String hql = "FROM User u WHERE u.email = :email";

        try {
            return this.em.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }



    }



    public User saveAndReturn(User newUser) throws InternalServerErrorException {
        this.save(newUser);
        return newUser;
    }
}
