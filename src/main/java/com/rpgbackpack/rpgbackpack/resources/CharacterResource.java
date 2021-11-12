package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app/session/characters")
public class CharacterResource {

    @Autowired
    CharacterService characterService;

    @GetMapping("/all-characters")
    public String getAllCharacters(HttpServletRequest request) {
        int userID = (Integer) request.getAttribute("userID");
        return "Authenticated UserId " + userID;
    }

    @GetMapping("/{sessionID}")
    public ResponseEntity<List<Character>> getSessionCharacters(@PathVariable("sessionID") Integer sessionID) {
        List<Character> characters = characterService.fetchAllSessionCharacters(sessionID);
        return new ResponseEntity<>(characters, HttpStatus.OK);
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

    @DeleteMapping("/remove/{characterID}")
    public ResponseEntity<String> removeCharacter(@PathVariable("characterID") Integer characterID) {
        characterService.removeCharacter(characterID);
        return new ResponseEntity<>("Character deleted", HttpStatus.OK);
    }
}
