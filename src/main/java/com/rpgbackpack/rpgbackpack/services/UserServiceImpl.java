package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.FIELDS;
import com.rpgbackpack.rpgbackpack.domain.User;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;
import com.rpgbackpack.rpgbackpack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws RpgAuthException {
        if (email != null)
            email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String email, String name, String password, Boolean subscription) throws RpgAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null)
            email = email.toLowerCase();
        if (!pattern.matcher(email).matches())
            throw new RpgAuthException("Invalid email format");
        if (name.length() > FIELDS.USERNAME.maxLength)
            throw new RpgAuthException("Name too long (max " + FIELDS.USERNAME.maxLength + " characters)");
        if (name.length() < FIELDS.USERNAME.minLength)
            throw new RpgAuthException("Name too short (min " + FIELDS.USERNAME.minLength + " characters)");
        /* Will be obsolete when client side password encryption will be implemented */
//        if (password.length() > FIELDS.USER_PASSWORD.maxLength)
//            throw new RpgAuthException("Password too long (max " + FIELDS.USER_PASSWORD.maxLength + " characters)");
//        if (password.length() < FIELDS.USER_PASSWORD.minLength)
//            throw new RpgAuthException("Password too short (min " + FIELDS.USER_PASSWORD.minLength + " characters)");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new RpgAuthException("Email already in use");
        Integer userId = userRepository.create(email, name, password, subscription);
        return userRepository.findById(userId);
    }
}
