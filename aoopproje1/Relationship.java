/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

/**
 *
 * @author User
 */
class Relationship {

    private User user1;
    private User user2;
    private String type;

    public Relationship(User user1, User user2, String type) {
        this.user1 = user1;
        this.user2 = user2;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void setType(String type) {
        this.type = type;
    }
}

