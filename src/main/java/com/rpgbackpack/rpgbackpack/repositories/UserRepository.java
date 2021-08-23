package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.User;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;

public interface UserRepository {

    Integer create(String email, String name, String password, Boolean subscription) throws RpgAuthException;

    User findByEmailAndPassword(String email, String password) throws RpgAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
