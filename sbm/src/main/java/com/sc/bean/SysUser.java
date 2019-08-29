package com.sc.bean;

import java.io.Serializable;

public class SysUser implements Serializable {
    private String id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

    private String locked;

    private static final long serialVersionUID = 1L;

    public SysUser(String id, String usercode, String username, String password, String salt, String locked) {
        this.id = id;
        this.usercode = usercode;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
    }

    public SysUser() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }
}