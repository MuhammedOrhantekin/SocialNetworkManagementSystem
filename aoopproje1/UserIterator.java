/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

import java.util.List;

public class UserIterator {
    private List<User> users;
    private int currentPosition = 0;

    public UserIterator(List<User> users) {
        this.users = users;
    }

    public boolean hasNext() {
        return currentPosition < users.size();
    }

    public User next() {
        return users.get(currentPosition++);
    }
}


