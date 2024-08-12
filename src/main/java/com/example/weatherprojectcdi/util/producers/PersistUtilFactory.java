package com.example.weatherprojectcdi.util.producers;

import com.example.weatherprojectcdi.util.qualifier.*;
import jakarta.enterprise.inject.*;

public class PersistUtilFactory {

    @Produces
    @DataBaseScemaQualifier
    String db1 = "org.hibernate.tutorial.jpa";

}
