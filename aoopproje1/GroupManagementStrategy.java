package com.mycompany.aoopproje1;

import java.util.List;
// Strategy Design Pattern'i oluşturuyoruz
public interface GroupManagementStrategy {
    void manageGroups(List<User> users, List<Group> groups);
}


