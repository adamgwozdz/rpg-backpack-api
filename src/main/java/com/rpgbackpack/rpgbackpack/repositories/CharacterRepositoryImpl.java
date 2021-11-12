package com.rpgbackpack.rpgbackpack.repositories;


import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.RpgBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.RpgResourceNotFoundException;
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
public class CharacterRepositoryImpl implements CharacterRepository {

    private static final String SQL_FIND_BY_CHARACTER_ID = "SELECT cha_id, cha_usr_id, cha_ses_id, cha_name, cha_game_master," +
            "cha_audit_joined, cha_audit_left, cha_image " +
            "FROM user_characters " +
            "WHERE cha_id = ?";

    private static final String SQL_FIND_BY_SESSION_ID = "SELECT * FROM user_characters\n" +
            "LEFT JOIN sessions ON ses_id = cha_ses_id\n" +
            "WHERE ses_id = ?";

    private static final String SQL_CREATE = "INSERT INTO user_characters (cha_usr_id, cha_ses_id, cha_name, cha_game_master, " +
            "cha_image) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_USER_AND_SESSION_ID = "SELECT COUNT(cha_id) FROM sessions\n" +
            "LEFT JOIN user_characters ON ses_id = cha_ses_id\n" +
            "WHERE cha_usr_id = ? AND cha_ses_id = ?";

    private static final String SQL_DELETE_BY_CHARACTER_ID = "DELETE FROM user_characters WHERE cha_id = ?";

    private static final String SQL_DELETE_BY_SESSION_ID = "DELETE FROM user_characters WHERE cha_ses_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Character> findAll() throws RpgResourceNotFoundException {
        return null;
    }

    @Override
    public Character findByCharacterId(Integer characterID) throws RpgResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_CHARACTER_ID, new Object[]{characterID}, characterRowMapper);
        } catch (Exception e) {
            throw new RpgResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public List<Character> findBySessionId(Integer sessionID) throws RpgResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_SESSION_ID, new Object[]{sessionID}, characterRowMapper);
        } catch (Exception e) {
            throw new RpgResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public Integer create(Integer userID, Integer sessionID, String name, Boolean gameMaster, String image) throws RpgBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userID);
                ps.setInt(2, sessionID);
                ps.setString(3, name);
                ps.setBoolean(4, gameMaster);
                ps.setString(5, image);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("cha_id");
        } catch (Exception e) {
            throw new RpgBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer characterID, String name, Boolean gameMaster, String image) throws RpgBadRequestException {
    }

    @Override
    public void removeByCharacterId(Integer characterID) throws RpgResourceNotFoundException {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_CHARACTER_ID);
            ps.setInt(1, characterID);
            return ps;
        });
    }

    @Override
    public void removeBySessionId(Integer sessionID) throws RpgResourceNotFoundException {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_SESSION_ID);
            ps.setInt(1, sessionID);
            return ps;
        });
    }

    @Override
    public Integer getCountByUserAndSessionID(Integer userID, Integer sessionID) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_USER_AND_SESSION_ID, new Object[]{userID, sessionID}, Integer.class);
    }

    private RowMapper<Character> characterRowMapper = ((rs, rowNum) -> {
        return new Character(rs.getInt("cha_id"),
                rs.getInt("cha_usr_id"),
                rs.getInt("cha_ses_id"),
                rs.getString("cha_name"),
                rs.getBoolean("cha_game_master"),
                rs.getTimestamp("cha_audit_joined"),
                rs.getTimestamp("cha_audit_left"),
                rs.getString("cha_image"));
    });
}
