package com.rpgbackpack.rpgbackpack.domain;

import java.sql.Timestamp;

public class Character {

    private Integer characterID;
    private Integer userID;
    private Integer sessionID;
    private String name;
    private Boolean gameMaster;
    private Timestamp dateJoined;
    private Timestamp dateLeft;
    private String image;

    public Character(Integer characterID, Integer userID, Integer sessionID, String name, Boolean gameMaster, Timestamp dateJoined, Timestamp dateLeft, String image) {
        this.characterID = characterID;
        this.userID = userID;
        this.sessionID = sessionID;
        this.name = name;
        this.gameMaster = gameMaster;
        this.dateJoined = dateJoined;
        this.dateLeft = dateLeft;
        this.image = image;
    }

    public Integer getCharacterID() {
        return characterID;
    }

    public void setCharacterID(Integer characterID) {
        this.characterID = characterID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
