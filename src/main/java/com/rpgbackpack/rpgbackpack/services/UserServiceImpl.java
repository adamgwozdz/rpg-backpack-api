package com.rpgbackpack.rpgbackpack.services;

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
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new RpgAuthException("Email already in use");
        Integer userId = userRepository.create(email, name, password, subscription);
        return userRepository.findById(userId);
    }
}
