package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.User;
import com.rpgbackpack.rpgbackpack.exceptions.RpgAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO users(usr_email, usr_name, usr_password, usr_subscription) " +
            "VALUES(?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(usr_id) FROM USERS WHERE usr_email = ?";

    private static final String SQL_FIND_BY_ID = "SELECT usr_id, usr_sta_id, usr_email, usr_name, usr_password, usr_email_verified, usr_subscription, " +
            "usr_audit_created, usr_audit_modified, usr_audit_removed, usr_audit_subscribed, usr_admin, usr_profile_image " +
            "FROM users WHERE usr_id = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT usr_id, usr_sta_id, usr_email, usr_name, usr_password, usr_email_verified, usr_subscription, " +
            "usr_audit_created, usr_audit_modified, usr_audit_removed, usr_audit_subscribed, usr_admin, usr_profile_image " +
            "FROM users WHERE usr_email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String email, String name, String password, Boolean subscription) throws RpgAuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, hashedPassword);
                ps.setBoolean(4, subscription);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("usr_id");
        } catch (Exception e) {
            throw new RpgAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws RpgAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new RpgAuthException("Invalid email/password");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new RpgAuthException("Invalid email/password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("usr_id"),
                rs.getInt("usr_sta_id"),
                rs.getString("usr_email"),
                rs.getString("usr_name"),
                rs.getString("usr_password"),
                rs.getBoolean("usr_email_verified"),
                rs.getBoolean("usr_subscription"),
                rs.getTimestamp("usr_audit_created"),
                rs.getTimestamp("usr_audit_modified"),
                rs.getTimestamp("usr_audit_removed"),
                rs.getTimestamp("usr_audit_subscribed"),
                rs.getBoolean("usr_admin"),
                rs.getString("usr_profile_image"));
    });
}
