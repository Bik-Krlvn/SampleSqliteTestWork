package com.cheise_proj.presentation.model;

/**
 * Contact Object
 */
public class Contact {
    private int id;
    private int userId;
    private String name;
    private String contact;
    private String email;

    public Contact() {
    }

    public Contact(int id, int userId, String name, String contact, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
