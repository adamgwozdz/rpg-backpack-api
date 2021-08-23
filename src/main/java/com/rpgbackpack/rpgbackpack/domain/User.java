package com.rpgbackpack.rpgbackpack.domain;

import java.sql.Timestamp;

public class User {

    private Integer userId;
    private String email;
    private String name;
    private String password;
    private Boolean emailVerified;
    private Boolean subscription;
    private Timestamp dateCreated;
    private Timestamp dateModified;
    private Timestamp dateRemoved;
    private Timestamp dateSubscribed;
    private Boolean admin;
    private String profileImage;

    public User(Integer userId, String email, String name, String password, Boolean emailVerified, Boolean subscription,
                Timestamp dateCreated, Timestamp dateModified, Timestamp dateRemoved,
                Timestamp dateSubscribed, Boolean admin, String profileImage) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.emailVerified = emailVerified;
        this.subscription = subscription;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.dateRemoved = dateRemoved;
        this.dateSubscribed = dateSubscribed;
        this.admin = admin;
        this.profileImage = profileImage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(Boolean subscription) {
        this.subscription = subscription;
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

    public Timestamp getDateSubscribed() {
        return dateSubscribed;
    }

    public void setDateSubscribed(Timestamp dateSubscribed) {
        this.dateSubscribed = dateSubscribed;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
