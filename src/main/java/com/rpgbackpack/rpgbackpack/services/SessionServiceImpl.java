package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.FIELDS;
import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;
import com.rpgbackpack.rpgbackpack.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public List<Session> fetchAllSessions() throws RpgResourceNotFoundException {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> fetchAllUserSessions(Integer userID) throws RpgResourceNotFoundException {
        return sessionRepository.findByUserId(userID);
    }

    @Override
    public Session fetchSessionById(Integer sessionID) throws RpgResourceNotFoundException {
        return sessionRepository.findById(sessionID);
    }

    @Override
    public Session createSession(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException {
        if (name.length() > FIELDS.SESSION_NAME.maxLength)
            throw new RpgAuthException("Name too long (max " + FIELDS.USERNAME.maxLength + " characters)");
        if (name.length() < FIELDS.SESSION_NAME.minLength)
            throw new RpgAuthException("Name too short (min " + FIELDS.USERNAME.minLength + " characters)");
        //TODO Will be obsolete when client side password encryption will be implemented
        if (password.length() > FIELDS.SESSION_PASSWORD.maxLength)
            throw new RpgAuthException("Password too long (max " + FIELDS.USER_PASSWORD.maxLength + " characters)");
        if (password.length() < FIELDS.SESSION_PASSWORD.minLength)
            throw new RpgAuthException("Password too short (min " + FIELDS.USER_PASSWORD.minLength + " characters)");
        int sessionId = sessionRepository.create(name, password, maxAttributes, image);
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Session updateSession(Integer sessionID, Session session) throws RpgBadRequestException {
        return sessionRepository.update(sessionID, session);
    }

    @Override
    public void removeSession(Integer sessionID) throws RpgResourceNotFoundException {
        sessionRepository.removeById(sessionID);
    }

    @Override
    public Session joinSession(Integer sessionID, String password) throws RpgAuthException {
        return sessionRepository.authorizeByIdAndPassword(sessionID, password);
    }
}
