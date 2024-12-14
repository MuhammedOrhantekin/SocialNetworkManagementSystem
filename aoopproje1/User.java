/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

/**
 *
 * @author User
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User implements Observer{

    private String username;
    private String password;
    private List<User> friends;
    private List<Post> wall;
    private boolean isVisibleInSearch;
    private Map<User, Relationship> relationships; // Arkadaşlık ilişkilerini saklamak için bir Map
    private List<User> blockedUsers; // Engellenen kullanıcıları saklamak için bir liste
    private List<User> userswhoBlockYou;
    private List<Group> groups; // Kullanıcının ait olduğu gruplar

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<>();
        this.wall = new ArrayList<>();
        this.isVisibleInSearch = true;
        this.relationships = new HashMap<>(); // relationships Map'ini başlatma
        this.blockedUsers = new ArrayList<>(); // blockedUsers listesini başlatma
        this.userswhoBlockYou = new ArrayList<>(); // blockedUsers listesini başlatma
        this.groups = new ArrayList<>();
    }
    
        // Diğer metotlar buraya eklenebilir
    public Iterable<Post> getWall() {
        return wall;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Map<User, Relationship> getRelationships() {
        return relationships;
    }
    
    public List<User> getBlockedUsers() {
        return blockedUsers;
    }
    
    public List<User> getUsersWhoBlockYou(){
        return userswhoBlockYou;
    }
    
    public boolean isVisibleInSearch() {
        return isVisibleInSearch;
    }
    
    public boolean isIsVisibleInSearch() {
        return isVisibleInSearch;
    }

    public void setVisibleInSearch(boolean isVisibleInSearch) {
        this.isVisibleInSearch = isVisibleInSearch;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setWall(List<Post> wall) {
        this.wall = wall;
    }

    public void setIsVisibleInSearch(boolean isVisibleInSearch) {
        this.isVisibleInSearch = isVisibleInSearch;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setRelationships(Map<User, Relationship> relationships) {
        this.relationships = relationships;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWhoBlockYouList(List<User> userwhoBlockYou) {
        this.userswhoBlockYou = userwhoBlockYou;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
    public void addFriend(User friend, Relationship relationship) {
        // Eğer arkadaşın engel listesinde değilse ekle
        if (!friend.isBlocked(this)) {
            friends.add(friend);
            friend.friends.add(this);
            relationships.put(friend, relationship);
            friend.relationships.put(this, relationship);
            System.out.println(username + " added " + friend.getUsername() + " as a friend.");
        } else {
            System.out.println(friend.getUsername() + " has blocked " + username + ". Cannot add as a friend.");
        }

    }

    public void joinGroup(Group group) {
        this.groups.add(group);

    }
    
    public void leaveGroup(Group group) {
        // Grubu kullanıcının grup listesinden çıkar
        groups.remove(group);

        // Grubun alt gruplarını kontrol et
        for (Group subGroup : group.getSubGroups()) {
            // Kullanıcı alt gruba üye ise, alt gruptan çıkar
            if (groups.contains(subGroup)) {
                groups.remove(subGroup);
            }
        }
    }

    // User sınıfına ait olduğu grupları döndüren bir yöntem ekleme

    public List<Group> getBelongingGroups() {
        return groups;
    }

    // Engelleme kontrolü
    public boolean isBlocked(User user) {
        // Kullanıcının engellediği kullanıcılar listesinde varsa true döndür
        boolean isBlocked = blockedUsers.contains(user);
        return isBlocked;
    }
    
    // Engellenme kontrolü
    public boolean DidHeBlockYou(User user){
        // Seni engelleyen kullanıcılar listesinde varsa true döndür
        boolean isYouBlocked = userswhoBlockYou.contains(user);
        return isYouBlocked;
    }

    public void blockUser(User user) {
        // Kullanıcıyı engellendi listesine ekle
        blockedUsers.add(user);
        // Engellenen kullanıcının arkadaş listesinden çıkar
        if (friends.contains(user)) {
            friends.remove(user);
        }
        
        // Engellenen kullanıcının arkadaşlık ilişkisini temizle
        relationships.remove(user);

        // Engellenen kullanıcını duvarındaki gönderileri kaldır
//        Iterator<Post> iterator = user.getWall().iterator();
//        while (iterator.hasNext()) {
//            Post post = iterator.next();
//            if (post.getAuthor().equals(user)) {
//                iterator.remove();
//            }
//        }
    }
    
    public void beBlocked(User user){
        // Kullanıcıyı engelleyen listesine ekle
        userswhoBlockYou.add(user);
        
        // Engelleyen kullanıcını arkadaş listesinden çıkar
        if (friends.contains(user)) {
            friends.remove(user);
        }
        
        // Engellenen kullanıcının arkadaşlık ilişkisini temizle
        relationships.remove(user);
        
//        Iterator<Post> iterator = user.getWall().iterator();
//        while (iterator.hasNext()) {
//            Post post = iterator.next();
//            if (post.getAuthor().equals(user)) {
//                iterator.remove();
//            }
//        }
    }



    public String getRelationship(User friend) {
        Relationship relationship = relationships.get(friend);
        return (relationship != null) ? relationship.getType() : "Bilinmiyor";
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
        friend.friends.remove(this); // Arkadaşlığı kaldırırken diğer kullanıcının arkadaş listesinden de kaldırılmasını sağlar
        relationships.remove(friend); // Arkadaşlık ilişkisini Map'ten kaldırır
    }

    public void publishPost(String content) {
        
        // Factory Pattern'in uygulanması
        Post post = EntityFactory.createPost(content, this);
        wall.add(post);
        notifyFriends(post.getContent()); // Bildirim metodunu çağır
    }

    // Observer deseni: Yeni bir gönderi yayınlandığında duvarı gören arkadaşlara bildirim gönder
    private void notifyFriends(String content) {
        for (User friend : friends) {
            friend.update("[" + username + "]: " + content);
        }
    }
    
    
    // Observer Design Pattern'ın kullanılması
    @Override
    public void update(String content) {
        System.out.println("Bildirim alındı: " + content);
    }
}

