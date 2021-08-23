package com.rpgbackpack.rpgbackpack.repositories;

import com.rpgbackpack.rpgbackpack.domain.Session;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String SQL_FIND_BY_ID = "SELECT ses_id, ses_name, ses_password, ses_max_attributes, " +
            "ses_audit_created, ses_audit_modified, ses_audit_removed, ses_image " +
            "FROM sessions " +
            "WHERE ses_id = 1";

    private static final String SQL_CREATE = "INSERT INTO sessions (ses_name, ses_password, ses_max_attributes, " +
            "ses_image) VALUES (?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Session> findAll() throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Session findById(Integer sessionId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sessionId}, sessionRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public Integer create(String name, String password, Integer maxAttributes, String image) throws EtBadRequestException {
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
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer sessionId, String name, String password, Integer maxAttributes, String image) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer sessionId) throws EtResourceNotFoundException {

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
