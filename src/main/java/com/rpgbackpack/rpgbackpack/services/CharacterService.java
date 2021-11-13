package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;

import java.util.List;

public interface CharacterService {

    List<Character> fetchAllCharacters() throws RpgResourceNotFoundException;

    List<Character> fetchAllSessionCharacters(Integer sessionID) throws RpgResourceNotFoundException;

    Character fetchCharacterById(Integer characterID) throws RpgResourceNotFoundException;

    Character addCharacter(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException;

    Character updateCharacter(Integer characterID, Character character) throws RpgBadRequestException;

    void removeCharacter(Integer characterID) throws RpgResourceNotFoundException;

    void removeSessionCharacters(Integer sessionID) throws RpgResourceNotFoundException;
}
