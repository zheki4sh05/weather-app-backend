package com.example.weatherprojectcdi.dao;


import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.inject.*;
import jakarta.persistence.*;

import java.util.function.*;

public abstract class BaseDao {

    protected EntityManager em;

    public BaseDao(){};

    public BaseDao(EntityManager em) {
        System.out.println("start");
        this.em = em;
        System.out.println("finish");
    }

//    public BaseDao() {
//        this.em = PersistUtil.getEntityManager();
//    }
    public <T> void merge(T entity) {
        transaction(em -> em.merge(entity));
    }
   public <T> void refresh(T entity) {
        em.refresh(entity);
    }

    protected void transaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }


    public void save(Object entity) throws InternalServerErrorException {
        transaction(em -> em.persist(entity));
    }
    public void delete(Object entity) {
        transaction(em -> em.remove(entity));
    }
    public void deleteById(Long id) {

        Location location = em.find(Location.class, id);

        delete(location);

    }

}
