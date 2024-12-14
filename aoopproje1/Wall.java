/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;


import java.util.ArrayList;
import java.util.List;

class Wall {

    private User user;
    private List<Post> posts;

    public Wall(User user) {
        this.user = user;
        this.posts = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }
    
    public List<Post> getPosts() {
        return posts;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}

