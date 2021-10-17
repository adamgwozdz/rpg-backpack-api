package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;

import java.util.List;

public interface CharacterRepository {

    List<Character> findAll() throws RpgResourceNotFoundException;

    List<Character> findBySessionId(Integer sessionID) throws RpgResourceNotFoundException;

    Character findByCharacterId(Integer characterID) throws RpgResourceNotFoundException;

    Integer create(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    void update(Integer characterID, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    void removeById(Integer characterID) throws RpgResourceNotFoundException;

    Integer getCountByUserAndSessionID(Integer userID, Integer sessionID);
}
