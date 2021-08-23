package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;
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
    public List<Character> fetchAllPlayers(Integer sessionId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Character fetchCharacterById(Integer sessionId, Integer characterId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Character addCharacter(Integer userID, Integer sessionID, Boolean gameMaster, String image) throws EtBadRequestException {
        int characterId = characterRepository.create(userID, sessionID, gameMaster, image);
        return characterRepository.findById(userID, characterId);
    }

    @Override
    public void updateCharacter(Integer characterId, Character character) throws EtBadRequestException {

    }

    @Override
    public void removeCharacter(Integer characterId) throws EtResourceNotFoundException {

    }
}
