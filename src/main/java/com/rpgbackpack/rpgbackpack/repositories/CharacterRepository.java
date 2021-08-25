package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CharacterRepository {

    List<Character> findAll(Integer sessionId) throws EtResourceNotFoundException;

    Character findByCharacterId(Integer characterId) throws EtResourceNotFoundException;

    Integer create(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws EtBadRequestException;

    void update(Integer characterId, String name, Boolean gameMaster, String image) throws EtBadRequestException;

    void removeById(Integer characterId) throws EtResourceNotFoundException;
}
