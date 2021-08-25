package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;

import java.util.List;

public interface CharacterRepository {

    List<Character> findAll() throws RpgResourceNotFoundException;

    List<Character> findBySessionId(Integer sessionId) throws RpgResourceNotFoundException;

    Character findByCharacterId(Integer characterId) throws RpgResourceNotFoundException;

    Integer create(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    void update(Integer characterId, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    void removeById(Integer characterId) throws RpgResourceNotFoundException;
}
