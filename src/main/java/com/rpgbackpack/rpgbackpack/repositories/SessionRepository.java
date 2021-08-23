package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface SessionRepository {

    List<Session> findAll() throws EtResourceNotFoundException;

    Session findById(Integer sessionId) throws EtResourceNotFoundException;

    Integer create(String name, String password, Integer maxAttributes, String image) throws EtBadRequestException;

    void update(Integer sessionId, String name, String password, Integer maxAttributes, String image) throws EtBadRequestException;

    void removeById(Integer sessionId) throws EtResourceNotFoundException;
}
