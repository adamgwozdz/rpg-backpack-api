package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.FIELDS;
import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
import com.rpgbackpack.rpgbackpack.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public List<Character> fetchAllCharacters() throws RpgResourceNotFoundException {
        return characterRepository.findAll();
    }

    @Override
    public List<Character> fetchAllSessionCharacters(Integer sessionID) throws RpgResourceNotFoundException {
        return characterRepository.findBySessionId(sessionID);
    }

    @Override
    public Character fetchCharacterById(Integer sessionID, Integer characterID) throws RpgResourceNotFoundException {
        return null;
    }

    @Override
    public Character addCharacter(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException {
        if (name.length() > FIELDS.CHARACTER_NAME.maxLength)
            throw new RpgAuthException("Name too long (max " + FIELDS.CHARACTER_NAME.maxLength + " characters)");
        if (name.length() < FIELDS.CHARACTER_NAME.minLength)
            throw new RpgAuthException("Name too short (min " + FIELDS.CHARACTER_NAME.minLength + " characters)");
        Integer count = characterRepository.getCountByUserAndSessionID(userID, sessionID);
        if(count > 0)
            throw new RpgAuthException("You have already joined this session");
        int characterID = characterRepository.create(userID, sessionID, name, gameMaster, image);
        return characterRepository.findByCharacterId(characterID);
    }

    @Override
    public Character updateCharacter(Integer characterID, Character character) throws RpgBadRequestException {
        return characterRepository.update(characterID, character);
    }

    @Override
    public void removeCharacter(Integer characterID) throws RpgResourceNotFoundException {
        characterRepository.removeByCharacterId(characterID);
    }

    @Override
    public void removeSessionCharacters(Integer sessionID) throws RpgResourceNotFoundException {
        characterRepository.removeBySessionId(sessionID);
    }
}
