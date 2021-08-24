package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Session;
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
@RequestMapping("/api/app/session")
public class SessionResource {

    @Autowired
    SessionService sessionService;

    @GetMapping("")
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.fetchAllSessions();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<Session> getSessionById(@PathVariable("sessionId") Integer sessionId) {
        Session session = sessionService.fetchSessionById(sessionId);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Session> addSession(@RequestBody Map<String, Object> sessionMap) {
        String name = (String) sessionMap.get("name");
        String password = (String) sessionMap.get("password");
        Integer maxAttributes = (Integer) sessionMap.get("maxAttributes");
        String image = (String) sessionMap.get("image");
        Session session = sessionService.addSession(name, password, maxAttributes, image);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Boolean>> loginToSession(@RequestBody Map<String, Object> sessionMap) {
        Integer sessionId = (Integer) sessionMap.get("sessionId");
        String name = (String) sessionMap.get("name");
        String password = (String) sessionMap.get("password");
        //Session session = sessionService.validateSession(sessionId, name, password);
        Map<String, Boolean> map = new HashMap<>();
        map.put("message", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/{sessionId}")
    public ResponseEntity<Map<String, Boolean>> updateSession(HttpServletRequest request, @PathVariable("sessionId") Integer sessionId,
                                                              @RequestBody Session session) {
        //int userId = (Integer) request.getAttribute("userId");
        sessionService.updateSession(sessionId, session);
        Map<String, Boolean> map = new HashMap<>();
        map.put("message", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
