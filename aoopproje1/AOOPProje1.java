
package com.mycompany.aoopproje1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AOOPProje1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();
        List<Group> groups = new ArrayList<>();

        // Kullanıcı işlemlerini yönetmek için UserManager sınıfından bir nesne oluşturun
        UserManager userManager = UserManager.getInstance();

        groups = userManager.createFriendGroups(users);
        userManager.setupFriendshipsAndGroups(users, groups, new DefaultGroupManagementStrategy());
        userManager.makeSomeFriendships(users);

        UserInterface ui = new UserInterface(users, scanner, groups);
    }
}





