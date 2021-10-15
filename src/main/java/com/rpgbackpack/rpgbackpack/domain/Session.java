package com.rpgbackpack.rpgbackpack.domain;

import java.sql.Timestamp;

public class Session {

    private Integer sessionID;
    private String name;
    private String password;
    private Integer maxAttributes;
    private Timestamp dateCreated;
    private Timestamp dateModified;
    private Timestamp dateRemoved;
    private String image;

    public Session(Integer sessionID, String name, String password, Integer maxAttributes, Timestamp dateCreated, Timestamp dateModified, Timestamp dateRemoved, String image) {
        this.sessionID = sessionID;
        this.name = name;
        this.password = password;
        this.maxAttributes = maxAttributes;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.dateRemoved = dateRemoved;
        this.image = image;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxAttributes() {
        return maxAttributes;
    }

    public void setMaxAttributes(Integer maxAttributes) {
        this.maxAttributes = maxAttributes;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public Timestamp getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Timestamp dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
