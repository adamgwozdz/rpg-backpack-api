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
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated UserId " + userId;
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<List<Character>> getSessionCharacters(@PathVariable("sessionId") Integer sessionId) {
        List<Character> characters = characterService.fetchAllSessionCharacters(sessionId);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @PostMapping("/{sessionId}")
    public ResponseEntity<Character> addCharacter(@PathVariable("sessionId") Integer sessionId,
                                                  @RequestBody Map<String, Object> characterMap) {
        Integer userID = (Integer) characterMap.get("userID");
        String name = (String) characterMap.get("name");
        Boolean gameMaster = (Boolean) characterMap.get("gameMaster");
        String image = (String) characterMap.get("image");
        Character character = characterService.addCharacter(userID, sessionId, name, gameMaster, image);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }
}
