package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app/session/characters")
public class CharacterResource {

    @Autowired
    CharacterService characterService;

    @GetMapping("/all-characters")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.fetchAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/session/{sessionID}")
    public ResponseEntity<List<Character>> getSessionCharacters(@PathVariable("sessionID") Integer sessionID) {
        List<Character> characters = characterService.fetchAllSessionCharacters(sessionID);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{characterID}")
    public ResponseEntity<Character> getCharacter(@PathVariable("characterID") Integer characterID) {
        Character character = characterService.fetchCharacterById(characterID);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @PostMapping("/{sessionID}")
    public ResponseEntity<Character> addCharacter(@PathVariable("sessionID") Integer sessionID,
                                                  @RequestBody Map<String, Object> characterMap) {
        Integer userID = (Integer) characterMap.get("userID");
        String name = (String) characterMap.get("name");
        Boolean gameMaster = (Boolean) characterMap.get("gameMaster");
        String image = (String) characterMap.get("image");
        Character character = characterService.addCharacter(userID, sessionID, name, gameMaster, image);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }

    @PutMapping("/{characterID}")
    public ResponseEntity<Map<String, Boolean>> updateCharacter(@PathVariable("characterID") Integer characterID, @RequestBody Character character) {
        characterService.updateCharacter(characterID, character);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{characterID}")
    public ResponseEntity<Map<String, Boolean>> removeCharacter(@PathVariable("characterID") Integer characterID) {
        characterService.removeCharacter(characterID);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
