package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface SessionService {

    List<Session> fetchAllSessions() throws EtResourceNotFoundException;

    Session fetchSessionById(Integer sessionId) throws EtResourceNotFoundException;

    Session addSession(String name, String password, Integer maxAttributes, String image) throws EtBadRequestException;

    void updateSession(Integer sessionId, Session session) throws EtBadRequestException;

    void removeSessionWithAllPlayers(Integer sessionId) throws EtResourceNotFoundException;
}