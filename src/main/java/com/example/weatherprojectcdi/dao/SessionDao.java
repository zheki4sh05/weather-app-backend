package com.example.weatherprojectcdi.dao;



import com.example.weatherprojectcdi.entity.*;
import jakarta.inject.*;
import jakarta.persistence.*;

import java.util.*;

public class SessionDao extends BaseDao{


    public SessionDao(EntityManager entityManager){
        super(entityManager);
    }


//    public void save(Session session) throws InternalServerErrorException{
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//
//        try {
//            entityManager.persist(session);
//            entityManager.flush();
//
//            transaction.commit();
//        } catch (ConstraintViolationException e) {
//            transaction.rollback();
//            throw new InternalServerErrorException("Transaction error");
//        }
//    }

//    public void delete(Session entity) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//
//            entityManager.remove(entity);
//            entityManager.flush();
//
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//            throw new RuntimeException(e);
//        }
//    }

    public Optional<Session> findById(UUID uuid) {

        Session session = this.em.find(Session.class, uuid);
        return Optional.of(session);

    }
}
