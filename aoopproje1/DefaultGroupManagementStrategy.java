package com.mycompany.aoopproje1;

import java.util.List;

public class DefaultGroupManagementStrategy implements GroupManagementStrategy {
    // Strategy Design Pattern'in uygulanması
    @Override
    public void manageGroups(List<User> users, List<Group> groups) {
        for (int i = 0; i < groups.size(); i++) {
            Group mainGroup = groups.get(i);
            for (int j = 10 * i; j < 10 * i + 10; j++) {
                User user = EntityFactory.createUser("admin" + (j + 1), "password");
                users.add(user);
                user.getGroups().add(mainGroup);
                mainGroup.addMember(user);
            }
        }

        for (Group mainGroup : groups) {
            for (int k = 0; k < mainGroup.getSubGroups().size(); k++) {
                Group subGroup = mainGroup.getSubGroups().get(k);
                for (int j = k * 4; j < k * 4 + 4; j++) {
                    User user = mainGroup.getMembers().get(j);
                    subGroup.addMember(user);
                }
            }
        }
    }
}

