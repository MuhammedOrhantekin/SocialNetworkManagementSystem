/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

/**
 *
 * @author User
 */

// Factory Pattern'in Oluşturulması
public class EntityFactory {
    public static User createUser(String username, String password) {
        return new User(username, password);
    }

    public static Post createPost(String content, User author) {
        return new Post(content, author);
    }

    public static Group createGroup(String name) {
        return new Group(name);
    }
}

