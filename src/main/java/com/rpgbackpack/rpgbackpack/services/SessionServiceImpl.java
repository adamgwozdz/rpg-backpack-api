package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;
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
    public List<Session> fetchAllSessions() throws EtResourceNotFoundException {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> fetchAllUserSessions(Integer userId) throws EtResourceNotFoundException {
        return sessionRepository.findByUserId(userId);
    }

    @Override
    public Session fetchSessionById(Integer sessionId) throws EtResourceNotFoundException {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Session addSession(String name, String password, Integer maxAttributes, String image) throws EtBadRequestException {
        int sessionId = sessionRepository.create(name, password, maxAttributes, image);
        return sessionRepository.findById(sessionId);
    }

    @Override
    public void updateSession(Integer sessionId, Session session) throws EtBadRequestException {
        sessionRepository.update(sessionId, session);
    }

    @Override
    public void removeSessionWithAllPlayers(Integer sessionId) throws EtResourceNotFoundException {

    }

    @Override
    public Session validateSession(Integer sessionId, String name, String password) throws RpgAuthException {
        return sessionRepository.findByIdNameAndPassword(sessionId, name, password);
    }
}
