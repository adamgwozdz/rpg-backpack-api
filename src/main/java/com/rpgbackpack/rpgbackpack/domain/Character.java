package com.rpgbackpack.rpgbackpack.domain;

import java.sql.Timestamp;

public class Character {

    private Integer characterId;
    private Integer userID;
    private Integer sessionID;
    private Boolean gameMaster;
    private Timestamp dateJoined;
    private Timestamp dateLeft;
    private String image;

    public Character(Integer characterId, Integer userID, Integer sessionID, Boolean gameMaster, Timestamp dateJoined, Timestamp dateLeft, String image) {
        this.characterId = characterId;
        this.userID = userID;
        this.sessionID = sessionID;
        this.gameMaster = gameMaster;
        this.dateJoined = dateJoined;
        this.dateLeft = dateLeft;
        this.image = image;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Boolean getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(Boolean gameMaster) {
        this.gameMaster = gameMaster;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Timestamp getDateLeft() {
        return dateLeft;
    }

    public void setDateLeft(Timestamp dateLeft) {
        this.dateLeft = dateLeft;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
