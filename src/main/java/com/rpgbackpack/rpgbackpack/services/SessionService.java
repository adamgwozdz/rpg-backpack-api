package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;

import java.util.List;

public interface SessionService {

    List<Session> fetchAllSessions() throws RpgResourceNotFoundException;

    List<Session> fetchAllUserSessions(Integer userID) throws RpgResourceNotFoundException;

    Session fetchSessionById(Integer sessionID) throws RpgResourceNotFoundException;

    Session createSession(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException;

    void updateSession(Integer sessionID, Session session) throws RpgBadRequestException;

    void removeSessionWithAllPlayers(Integer sessionID) throws RpgResourceNotFoundException;

    Session joinSession(Integer sessionID, String password) throws RpgAuthException;
}
