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
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/user-sessions")
    public ResponseEntity<List<Session>> getAllUserSessions(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Session> sessions = sessionService.fetchAllUserSessions(userId);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<Session> getSessionById(@PathVariable("sessionId") Integer sessionId) {
        Session session = sessionService.fetchSessionById(sessionId);
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

    @PutMapping("/{sessionId}")
    public ResponseEntity<Map<String, Boolean>> updateSession(HttpServletRequest request, @PathVariable("sessionId") Integer sessionId,
                                                              @RequestBody Session session) {
        //int userId = (Integer) request.getAttribute("userId");
        sessionService.updateSession(sessionId, session);
        Map<String, Boolean> map = new HashMap<>();
        map.put("message", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Session> joinSession(@RequestBody Map<String, Object> sessionMap) {
        Integer sessionId = (Integer) sessionMap.get("sessionId");
        String password = (String) sessionMap.get("password");
        Session session = sessionService.validateSession(sessionId, password);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }
}
