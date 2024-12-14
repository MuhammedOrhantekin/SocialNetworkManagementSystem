package com.mycompany.aoopproje1;

public class CommonInterestStrategy implements RelationshipStrategy {
    // Strategy Design Pattern'in uygulanması
    @Override
    public void createRelationship(User user1, User user2) {
        user1.addFriend(user2, new Relationship(user1, user2, "Common Interest"));
    }
}


