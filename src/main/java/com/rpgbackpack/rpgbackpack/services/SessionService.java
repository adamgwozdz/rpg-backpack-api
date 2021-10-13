package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;

import java.util.List;

public interface SessionService {

    List<Session> fetchAllSessions() throws RpgResourceNotFoundException;

    List<Session> fetchAllUserSessions(Integer userId) throws RpgResourceNotFoundException;

    Session fetchSessionById(Integer sessionId) throws RpgResourceNotFoundException;

    Session createSession(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException;

    void updateSession(Integer sessionId, Session session) throws RpgBadRequestException;

    void removeSessionWithAllPlayers(Integer sessionId) throws RpgResourceNotFoundException;

    Session validateSession(Integer sessionId, String password) throws RpgAuthException;
}
