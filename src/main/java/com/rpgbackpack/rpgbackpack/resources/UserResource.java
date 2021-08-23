package com.rpgbackpack.rpgbackpack.resources;

import com.rpgbackpack.rpgbackpack.Constants;
import com.rpgbackpack.rpgbackpack.domain.User;
import com.rpgbackpack.rpgbackpack.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("usr_id", user.getUserId())
                .claim("usr_email", user.getEmail())
                .claim("usr_name", user.getName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUserSub(@RequestBody Map<String ,Object> userMap) {
        String email = (String) userMap.get("email");
        String name = (String) userMap.get("name");
        String password = (String) userMap.get("password");
        Boolean subscription = (Boolean) userMap.get("subscription");
        User user = userService.registerUser(email, name, password, subscription);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }


}
