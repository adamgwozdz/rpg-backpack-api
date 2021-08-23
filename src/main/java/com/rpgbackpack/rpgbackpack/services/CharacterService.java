package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CharacterService {

    List<Character> fetchAllPlayers(Integer sessionId) throws EtResourceNotFoundException;

    Character fetchCharacterById(Integer sessionId, Integer characterId) throws EtResourceNotFoundException;

    Character addCharacter(Integer userID, Integer sessionID, Boolean gameMaster, String image) throws EtBadRequestException;

    void updateCharacter(Integer characterId, Character character) throws EtBadRequestException;

    void removeCharacter(Integer characterId) throws EtResourceNotFoundException;
}
