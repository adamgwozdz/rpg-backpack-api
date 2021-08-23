package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CharacterRepository {

    List<Character> findAll(Integer sessionId) throws EtResourceNotFoundException;

    Character findById(Integer sessionId, Integer characterId) throws EtResourceNotFoundException;

    Integer create(Integer userID, Integer sessionID, Boolean gameMaster, String image) throws EtBadRequestException;

    void update(Integer characterId, Boolean gameMaster, String image) throws EtBadRequestException;

    void removeById(Integer characterId) throws EtResourceNotFoundException;
}
