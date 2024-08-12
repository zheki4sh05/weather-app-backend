package com.example.weatherprojectcdi.service;



import com.example.weatherprojectcdi.dao.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import jakarta.inject.*;
import jakarta.servlet.http.*;
import lombok.*;

import java.time.*;
import java.time.chrono.*;
import java.util.*;

public class SessionServiceImpl implements ISessionService {

    @Inject
    @Named("Session")
    private SessionDao sessionDao;

    @Override
    public UUID createByUser(User user) throws InternalServerErrorException {

        UUID newUUID = UUID.randomUUID();

        Session session = new Session(newUUID, user, LocalDateTime.now().plusHours(2));
        sessionDao.save(session);

        return newUUID;

    }

    @Override
    public void delete(String value) {

        UUID uuid = UUID.fromString(value);

        Session session = sessionDao.findById(uuid).get();

        sessionDao.delete(session);

    }


    @Override
    public boolean isSessionActiveBy(Optional<Cookie> cookie) {
        UUID uuid = UUID.fromString(cookie.get().getValue());
        Session session = null;
        try {
            session = sessionDao.findById(uuid).orElseThrow(BadRequestException::new);
        } catch (BadRequestException e) {
            return false;
        }

        Boolean isTrue =  session.getExpiresAt().isAfter(ChronoLocalDateTime.from(LocalDateTime.now()));

        return isTrue;
    }
    @Override

    @SneakyThrows
    public User findUserByCookie(Optional<Cookie> sessionCookie) {
        UUID uuid = UUID.fromString(sessionCookie.get().getValue());

        Session session = sessionDao.findById(uuid).orElseThrow(BadRequestException::new);

        return session.getUser();

    }

}
