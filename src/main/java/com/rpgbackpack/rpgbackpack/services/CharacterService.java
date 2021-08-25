package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;

import java.util.List;

public interface CharacterService {

    List<Character> fetchAllCharacters() throws RpgResourceNotFoundException;

    List<Character> fetchAllSessionCharacters(Integer sessionId) throws RpgResourceNotFoundException;

    Character fetchCharacterById(Integer sessionId, Integer characterId) throws RpgResourceNotFoundException;

    Character addCharacter(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    void updateCharacter(Integer characterId, Character character) throws RpgBadRequestException;

    void removeCharacter(Integer characterId) throws RpgResourceNotFoundException;
}
