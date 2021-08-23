package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/app/session")
public class SessionResource {

    @Autowired
    SessionService sessionService;

    @GetMapping("")
    public String getAllSessions(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated UserId " + userId;
    }

    @PostMapping("/add")
    public ResponseEntity<Session> addSession(HttpServletRequest request, @RequestBody Map<String, Object> sessionMap) {
        String name = (String) sessionMap.get("name");
        String password = (String) sessionMap.get("password");
        Integer maxAttributes = (Integer) sessionMap.get("maxAttributes");
        String image = (String) sessionMap.get("image");
        Session session = sessionService.addSession(name, password, maxAttributes, image);
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }
}
