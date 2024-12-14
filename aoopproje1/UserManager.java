/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aoopproje1;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    
    // Singleton Design Pattern'in Uygulanması -----------------------------------

    // Singleton özelliğini uygulamak için instance adında bir değişken tanımlıyoruz.
    private static UserManager instance;

    // Private constructor ile doğrudan sınıfın dışından örneğin oluşturulmasını engelliyoruz.
    private UserManager() {
        // Private constructor, instantiation'ı engeller
    }

    // getInstance() metodu, tek bir örnek döndüren bir statik metottur ve senkronize edilmiştir.
    public static synchronized UserManager getInstance() 
    {
        
        // Eğer instance değişkeni null ise, yalnızca bir kez çalışacak olan bu blok, yeni bir örnek oluşturur.
        if (instance == null) {
            instance = new UserManager();
        }
        
        // Oluşturulmuş olan veya zaten varolan instance'ı döndürür.
        return instance;
    }
    
    // ---------------------------------------------------------------------------

    // User login method
    public User login(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addUsersToGroup(List<User> users, Group group) {
        for (User user : users) {
            group.addMember(user);
            user.joinGroup(group);
        }
    }

    

    public User findUserByUsername(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Yeni findGroupByName fonksiyonunu ekliyoruz
    public Group findGroupByName(List<Group> groups, String groupName) {
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }
    
    // Yeni bir alt grup oluşturmak için bu metodu kullanabiliriz
    public void createSubGroup(Group parentGroup, String subGroupName) {
        Group subGroup = EntityFactory.createGroup(subGroupName);
        parentGroup.getSubGroups().add(subGroup);
    }
    
    // Kullanıcının bir gruptan ayrılmasını sağlar
    // Ayrıldığı grup alt gruplarının üyeliğinden de çıkarılır
    public void leaveGroup(User user, Group group) {
        group.removeUser(user);
        user.leaveGroup(group);
        
        // Eğer ayrılan grup bir ana grup ise, alt gruplardan da çıkış yapılmalıdır
        if (!group.getSubGroups().isEmpty()) {
            for (Group subGroup : group.getSubGroups()) {
                subGroup.removeUser(user);
                user.leaveGroup(subGroup);
            }
        }
    }
    
    public List<User> searchUsers(List<User> users, String name) {
        List<User> foundUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().equals(name) && user.isVisibleInSearch()) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    public List<Group> createFriendGroups(List<User> users) {
        List<Group> groups = new ArrayList<>();
        
        // Factory Pattern'in kullanılması

        Group group1 = EntityFactory.createGroup("Grup1");
        groups.add(group1);
        for (int i = 0; i < 2; i++) {
            Group subGroup = EntityFactory.createGroup(group1.getName() + "_Alt" + (i + 1));
            group1.getSubGroups().add(subGroup);
        }

        Group group2 = EntityFactory.createGroup("Grup2");
        groups.add(group2);
        for (int i = 0; i < 2; i++) {
            Group subGroup = EntityFactory.createGroup(group2.getName() + "_Alt" + (i + 1));
            group2.getSubGroups().add(subGroup);
        }

        Group group3 = EntityFactory.createGroup("Grup3");
        groups.add(group3);
        for (int i = 0; i < 2; i++) {
            Group subGroup = EntityFactory.createGroup(group3.getName() + "_Alt" + (i + 1));
            group3.getSubGroups().add(subGroup);
        }

        return groups;
    }

    // Strategy Design Pattern'in kullanılması
    public void setupFriendshipsAndGroups(List<User> users, List<Group> groups, GroupManagementStrategy strategy) {
        strategy.manageGroups(users, groups);
    }


   // Strategy Design Pattern'in kullanılması
    public void makeUsersFriends(User user1, User user2, RelationshipStrategy strategy) {
        strategy.createRelationship(user1, user2);
    }
    

    // Strategy Design Pattern'in kullanılması
    public void makeSomeFriendships(List<User> users) {
        makeUsersFriends(users.get(0), users.get(1), new FriendshipStrategy());
        makeUsersFriends(users.get(3), users.get(5), new FriendshipStrategy());
        makeUsersFriends(users.get(6), users.get(9), new FriendshipStrategy());
        makeUsersFriends(users.get(9), users.get(13), new FriendshipStrategy());
        makeUsersFriends(users.get(12), users.get(17), new FriendshipStrategy());
        makeUsersFriends(users.get(15), users.get(21), new FriendshipStrategy());
        makeUsersFriends(users.get(18), users.get(25), new FriendshipStrategy());
        makeUsersFriends(users.get(21), users.get(29), new FriendshipStrategy());
        makeUsersFriends(users.get(24), users.get(2), new FriendshipStrategy());

        makeUsersFriends(users.get(2), users.get(3), new CommonInterestStrategy());
        makeUsersFriends(users.get(4), users.get(9), new CommonInterestStrategy());
        makeUsersFriends(users.get(6), users.get(15), new CommonInterestStrategy());
        makeUsersFriends(users.get(8), users.get(21), new CommonInterestStrategy());
        makeUsersFriends(users.get(10), users.get(27), new CommonInterestStrategy());
        makeUsersFriends(users.get(12), users.get(4), new CommonInterestStrategy());
        makeUsersFriends(users.get(14), users.get(10), new CommonInterestStrategy());
        makeUsersFriends(users.get(16), users.get(17), new CommonInterestStrategy());
        makeUsersFriends(users.get(18), users.get(22), new CommonInterestStrategy());

        makeUsersFriends(users.get(1), users.get(19), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(3), users.get(21), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(5), users.get(23), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(7), users.get(25), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(9), users.get(27), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(11), users.get(29), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(13), users.get(17), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(15), users.get(21), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(17), users.get(15), new FamilyRelationshipStrategy());
        makeUsersFriends(users.get(17), users.get(15), new FamilyRelationshipStrategy());
    }}
