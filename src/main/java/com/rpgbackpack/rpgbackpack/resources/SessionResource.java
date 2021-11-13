package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.services.CharacterService;
import com.rpgbackpack.rpgbackpack.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app/session/sessions")
public class SessionResource {

    @Autowired
    SessionService sessionService;

    @Autowired
    CharacterService characterService;

    @GetMapping("/all-sessions")
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.fetchAllSessions();
        for (Session session : sessions) {
            session.setCharacters(characterService.fetchAllSessionCharacters(session.getSessionID()));
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/user-sessions")
    public ResponseEntity<List<Session>> getAllUserSessions(HttpServletRequest request) {
        int userID = (Integer) request.getAttribute("userID");
        List<Session> sessions = sessionService.fetchAllUserSessions(userID);
        for (Session session : sessions) {
            session.setCharacters(characterService.fetchAllSessionCharacters(session.getSessionID()));
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/{sessionID}")
    public ResponseEntity<Session> getSessionById(@PathVariable("sessionID") Integer sessionId) {
        Session session = sessionService.fetchSessionById(sessionId);
        session.setCharacters(characterService.fetchAllSessionCharacters(session.getSessionID()));
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Session> createSession(@RequestBody Map<String, Object> sessionMap) {
        String name = (String) sessionMap.get("name");
        String password = (String) sessionMap.get("password");
        Integer maxAttributes = (Integer) sessionMap.get("maxAttributes");
        String image = (String) sessionMap.get("image");
        Session session = sessionService.createSession(name, password, maxAttributes, image);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<Session> joinSession(@RequestBody Map<String, Object> sessionMap) {
        Integer sessionID = (Integer) sessionMap.get("sessionID");
        Integer userID = (Integer) sessionMap.get("userID");
        String password = (String) sessionMap.get("password");
        String name = (String) sessionMap.get("name");
        Boolean gameMaster = (Boolean) sessionMap.get("gameMaster");
        String image = (String) sessionMap.get("image");
        Session session = sessionService.joinSession(sessionID, password);
        characterService.addCharacter(userID, sessionID, name, gameMaster, image);
        session.setCharacters(characterService.fetchAllSessionCharacters(session.getSessionID()));
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @PutMapping("/{sessionID}")
    public ResponseEntity<Map<String, Boolean>> updateSession(@PathVariable("sessionID") Integer sessionId, @RequestBody Session session) {
        sessionService.updateSession(sessionId, session);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{sessionID}")
    public ResponseEntity<Map<String, Boolean>> removeSession(@PathVariable("sessionID") Integer sessionID) {
        characterService.removeSessionCharacters(sessionID);
        sessionService.removeSession(sessionID);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
