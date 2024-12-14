/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

/**
 *
 * @author User
 */

public class Post {
    private String content;
    private User author;

        public Post(String content, User author) {
            this.content = content;
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public User getAuthor() {
            return author;
        }
}
