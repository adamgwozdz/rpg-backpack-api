package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
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
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private static final String SQL_FIND_ALL = "SELECT ses_id, ses_name, ses_password, ses_max_attributes, " +
            "ses_audit_created, ses_audit_modified, ses_audit_removed, ses_image " +
            "FROM sessions ";

    private static final String SQL_FIND_ALL_BY_USER_ID = "SELECT ses_id, ses_name, ses_password, ses_max_attributes, " +
            "ses_audit_created, ses_audit_modified, ses_audit_removed, ses_image " +
            "FROM sessions " +
            "RIGHT JOIN user_characters ON ses_id = cha_ses_id " +
            "LEFT JOIN users ON usr_id = cha_usr_id " +
            "WHERE usr_id = ?";

    private static final String SQL_FIND_BY_ID = "SELECT ses_id, ses_name, ses_password, ses_max_attributes, " +
            "ses_audit_created, ses_audit_modified, ses_audit_removed, ses_image " +
            "FROM sessions " +
            "WHERE ses_id = ?";

    private static final String SQL_CREATE = "INSERT INTO sessions (ses_name, ses_password, ses_max_attributes, " +
            "ses_image) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE sessions SET ses_name = ?, ses_password = ?, ses_max_attributes = ?, " +
            "ses_audit_modified = NOW(), ses_image = ? WHERE ses_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Session> findAll() throws RpgResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, sessionRowMapper);
        } catch (Exception e) {
            throw new RpgResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public List<Session> findByUserId(Integer userId) throws RpgResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL_BY_USER_ID, new Object[]{userId}, sessionRowMapper);
        } catch (Exception e) {
            throw new RpgResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public Session findById(Integer sessionId) throws RpgResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sessionId}, sessionRowMapper);
        } catch (Exception e) {
            throw new RpgResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public Integer create(String name, String password, Integer maxAttributes, String image) throws RpgBadRequestException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, hashedPassword);
                ps.setInt(3, maxAttributes);
                ps.setString(4, image);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ses_id");
        } catch (Exception e) {
            throw new RpgBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer sessionId, Session session) throws RpgBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, session.getName(),
                    BCrypt.hashpw(session.getPassword(), BCrypt.gensalt(10)),
                    session.getMaxAttributes(),
                    session.getImage(),
                    sessionId);
        } catch (Exception e) {
            throw new RpgBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Integer sessionId) throws RpgResourceNotFoundException {

    }

    @Override
    public Session authorizeByIdAndPassword(Integer sessionId, String password) {
        try {
            Session session = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sessionId}, sessionRowMapper);
            if(!BCrypt.checkpw(password, session.getPassword()))
                throw new RpgAuthException("Invalid session ID or password");
            return session;
        } catch (EmptyResultDataAccessException e) {
            throw new RpgAuthException("Invalid session ID or password");
        }
    }

    private RowMapper<Session> sessionRowMapper = ((rs, rowNum) -> {
        return new Session(rs.getInt("ses_id"),
                rs.getString("ses_name"),
                rs.getString("ses_password"),
                rs.getInt("ses_max_attributes"),
                rs.getTimestamp("ses_audit_created"),
                rs.getTimestamp("ses_audit_modified"),
                rs.getTimestamp("ses_audit_removed"),
                rs.getString("ses_image"));
    });
}
