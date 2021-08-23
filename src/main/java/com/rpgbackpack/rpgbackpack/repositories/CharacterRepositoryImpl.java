package com.rpgbackpack.rpgbackpack.repositories;


import com.rpgbackpack.rpgbackpack.domain.Character;
import com.rpgbackpack.rpgbackpack.exceptions.EtBadRequestException;
import com.rpgbackpack.rpgbackpack.exceptions.EtResourceNotFoundException;
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

    private static final String SQL_FIND_BY_ID = "SELECT cha_id, cha_usr_id, cha_ses_id, cha_game_master," +
            "cha_audit_joined, cha_audit_left, cha_image " +
            "FROM characters " +
            "WHERE cha_id = ?";

    private static final String SQL_CREATE = "INSERT INTO characters (cha_usr_id, cha_ses_id, cha_game_master, " +
            "cha_image) VALUES (?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Character> findAll(Integer sessionId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Character findById(Integer sessionId, Integer characterId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{characterId}, characterRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid request");
        }
    }

    @Override
    public Integer create(Integer userID, Integer sessionID, Boolean gameMaster, String image) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userID);
                ps.setInt(2, sessionID);
                ps.setBoolean(3, gameMaster);
                ps.setString(4, image);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("cha_id");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer characterId, Boolean gameMaster, String image) throws EtBadRequestException {

    }

    @Override
    public void removeById(Integer characterId) throws EtResourceNotFoundException {

    }

    private RowMapper<Character> characterRowMapper = ((rs, rowNum) -> {
        return new Character(rs.getInt("cha_id"),
                rs.getInt("cha_usr_id"),
                rs.getInt("cha_ses_id"),
                rs.getBoolean("cha_game_master"),
                rs.getTimestamp("cha_audit_joined"),
                rs.getTimestamp("cha_audit_left"),
                rs.getString("cha_image"));
    });
}
