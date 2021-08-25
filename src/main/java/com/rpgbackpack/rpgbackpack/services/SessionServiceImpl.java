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
    public List<Session> fetchAllUserSessions(Integer userId) throws RpgResourceNotFoundException {
        return sessionRepository.findByUserId(userId);
    }

    @Override
    public Session fetchSessionById(Integer sessionId) throws RpgResourceNotFoundException {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Session addSession(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException {
        int sessionId = sessionRepository.create(name, password, maxAttributes, image);
        return sessionRepository.findById(sessionId);
    }

    @Override
    public void updateSession(Integer sessionId, Session session) throws RpgBadRequestException {
        sessionRepository.update(sessionId, session);
    }

    @Override
    public void removeSessionWithAllPlayers(Integer sessionId) throws RpgResourceNotFoundException {

    }

    @Override
    public Session validateSession(Integer sessionId, String name, String password) throws RpgAuthException {
        return sessionRepository.findByIdNameAndPassword(sessionId, name, password);
    }
}
