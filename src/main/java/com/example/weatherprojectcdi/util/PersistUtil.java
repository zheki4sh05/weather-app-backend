package com.example.weatherprojectcdi.util;

import com.example.weatherprojectcdi.util.qualifier.*;
import jakarta.inject.*;
import jakarta.persistence.*;

public class PersistUtil {

    private static final EntityManagerFactory EMF;

    @Inject
    @DataBaseScemaQualifier
    private String nameDefault;

    static {
//        Map<String, String> configOverrides = new HashMap<>();
//
//        Configuration configuration = new Configuration().configure();
//
//
//        Properties props =configuration.getProperties();
//
//        String url =props.getProperty("connection.url");
//        String user = props.getProperty("connection.username");
//        String password = props.getProperty("connection.password");
//        String driver = props.getProperty("connection.driver_class");
////        String dialect = props.getProperty("hibernate.dialect");
//
//        configOverrides.put("jakarta.persistence.jdbc.url", url);
//        configOverrides.put("jakarta.persistence.jdbc.user", user);
//        configOverrides.put("jakarta.persistence.jdbc.password", password);
//        configOverrides.put("jakarta.persistence.jdbc.driver", driver);
////        configOverrides.put("hibernate.dialect", dialect);

        EMF =  Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

}
