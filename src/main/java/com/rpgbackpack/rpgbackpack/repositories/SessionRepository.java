package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;

import java.util.List;

public interface SessionRepository {

    List<Session> findAll() throws RpgResourceNotFoundException;

    List<Session> findByUserId(Integer userID) throws RpgResourceNotFoundException;

    Session findById(Integer sessionID) throws RpgResourceNotFoundException;

    Session authorizeByIdAndPassword(Integer sessionID, String password);

    Integer create(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException;

    void update(Integer sessionID, Session session) throws RpgBadRequestException;

    void removeById(Integer sessionID) throws RpgResourceNotFoundException;
}
