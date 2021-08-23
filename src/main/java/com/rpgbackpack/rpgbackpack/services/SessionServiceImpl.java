package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;
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
        return null;
    }

    @Override
    public Session fetchSessionById(Integer sessionId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Session addSession(String name, String password, Integer maxAttributes, String image) throws EtBadRequestException {
        int sessionId = sessionRepository.create(name, password, maxAttributes, image);
        return sessionRepository.findById(sessionId);
    }

    @Override
    public void updateSession(Integer sessionId, Session session) throws EtBadRequestException {

    }

    @Override
    public void removeSessionWithAllPlayers(Integer sessionId) throws EtResourceNotFoundException {

    }
}
