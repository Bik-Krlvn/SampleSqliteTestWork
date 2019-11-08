package com.cheise_proj.local_sqlite.model;

/**
 * UserLocal Object
 */
public class UserLocal {
    private int id;
    private String username;
    private String email;
    private String password;
    private String imagePath;

    public UserLocal() {
    }

    public UserLocal(int id, String username, String email, String password, String imagePath) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
