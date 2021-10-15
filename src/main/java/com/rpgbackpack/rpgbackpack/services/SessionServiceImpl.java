package com.rpgbackpack.rpgbackpack.services;

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
        int sessionId = sessionRepository.create(name, password, maxAttributes, image);
        return sessionRepository.findById(sessionId);
    }

    @Override
    public void updateSession(Integer sessionID, Session session) throws RpgBadRequestException {
        sessionRepository.update(sessionID, session);
    }

    @Override
    public void removeSessionWithAllPlayers(Integer sessionID) throws RpgResourceNotFoundException {

    }

    @Override
    public Session joinSession(Integer sessionID, String password) throws RpgAuthException {
        return sessionRepository.authorizeByIdAndPassword(sessionID, password);
    }
}
