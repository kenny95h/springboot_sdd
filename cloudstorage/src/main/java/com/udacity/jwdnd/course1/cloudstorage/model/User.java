package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public User(Integer userid, String username, String salt, String password, String firstname, String lastname) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
}
