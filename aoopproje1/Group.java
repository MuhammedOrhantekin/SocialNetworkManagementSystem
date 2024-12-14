package com.mycompany.aoopproje1;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<User> members;
    private List<Post> wall;
    private List<Group> subGroups;

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.wall = new ArrayList<>();
        this.subGroups = new ArrayList<>();
    }

    
    
    public String getName() {
        return name;
    }

    public List<Post> getWall() {
        return wall;
    }

    public List<User> getMembers() {
        return members;
    }
    
    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWall(List<Post> wall) {
        this.wall = wall;
    }
    
        public List<Group> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<Group> subGroups) {
        this.subGroups = subGroups;
    }
    

    @Override
    public String toString() {
        return super.toString();
    }

    public void addMember(User user) {
        members.add(user);
    }
    
    public void removeUser(User user) {
        // Kullanıcıyı grup üyeleri listesinden çıkar
        members.remove(user);

        // Alt grupları kontrol et
        for (Group subGroup : subGroups) {
            // Eğer alt grupta kullanıcı varsa, kullanıcıyı alt gruptan çıkar
            if (subGroup.getMembers().contains(user)) {
                subGroup.removeUser(user);
            }
        }
    }

}

