package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String encryptedpassword;
    private Integer userid;
    private String salt;

    public Credential(Integer credentialId, String url, String username, String encryptedpassword, Integer userid, String salt) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.encryptedpassword = encryptedpassword;
        this.url = url;
        this.credentialId = credentialId;
    }


    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String decrypt() {
        EncryptionService enc = new EncryptionService();
        return enc.decryptValue(this.encryptedpassword, this.salt);
    }

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}

