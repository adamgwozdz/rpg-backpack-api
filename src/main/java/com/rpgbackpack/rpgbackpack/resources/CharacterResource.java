package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/app/character")
public class CharacterResource {

    @Autowired
    CharacterService characterService;

    @GetMapping("")
    public String getAllCharacters(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated UserId " + userId;
    }

    @PostMapping("/add")
    public ResponseEntity<Character> addSession(@RequestBody Map<String, Object> characterMap) {
        Integer userID = (Integer) characterMap.get("userID");
        Integer sessionID = (Integer) characterMap.get("sessionID");
        Boolean gameMaster = (Boolean) characterMap.get("gameMaster");
        String image = (String) characterMap.get("image");
        Character character = characterService.addCharacter(userID, sessionID, gameMaster, image);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }
}
