package com.rpgbackpack.rpgbackpack.services;

import com.rpgbackpack.rpgbackpack.domain.User;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;

public interface UserService {

    User validateUser(String email, String password) throws RpgAuthException;

    User registerUser(String email, String name, String password, Boolean subscription) throws RpgAuthException;

}
