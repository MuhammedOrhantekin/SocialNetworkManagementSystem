package com.mycompany.aoopproje1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserInterface extends JFrame {

    private final List<User> users;
    private final List<Group> groups;
    private Scanner scanner;
    private final UserManager userManager;

    public UserInterface(List<User> users, Scanner scanner, List<Group> groups) {
        
        
        User admin = new User("admin", "admin");
        users.add(admin);
        users.add(admin);
        
        this.users = users;
        this.groups = groups;
        this.scanner = scanner;
        
        // Singleton yapısının kullanılması
        this.userManager = UserManager.getInstance(); // Singleton örneği

        setTitle("Sosyal Ağ Uygulaması");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton loginUserButton = new JButton("1. Kullanıcıya Giriş Yap");
        JButton searchUserButton = new JButton("2. Kullanıcı Ara");
        JButton createUserButton = new JButton("3. Kullanıcı Hesabı Oluştur");
        JButton exitButton = new JButton("4. Çıkış");

        panel.add(loginUserButton);
        panel.add(searchUserButton);
        panel.add(createUserButton);
        panel.add(exitButton);

        loginUserButton.addActionListener((ActionEvent e) -> {
            loginUser();
        });

        searchUserButton.addActionListener((ActionEvent e) -> {
            searchUser();
        });

        createUserButton.addActionListener((ActionEvent e) -> {
            createUserAndJoinGroups();
        });

        exitButton.addActionListener((ActionEvent e) -> {
            dispose();
            scanner.close();
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loginUser() {
        String username = JOptionPane.showInputDialog("Kullanıcı adı:");
        String password = JOptionPane.showInputDialog("Parola:");

        User currentUser = userManager.login(users, username, password);

        if (currentUser != null) {
            JOptionPane.showMessageDialog(null, "Giriş başarılı! Hoş geldiniz, " + currentUser.getUsername() + "!");
            showUserMenu(currentUser);
        } else {
            JOptionPane.showMessageDialog(null, "Giriş başarısız! Kullanıcı adı veya parola yanlış.");
        }
    }

    private void showUserMenu(User currentUser) {
        JFrame menuFrame = new JFrame("Kullanıcı Menüsü");
        menuFrame.setSize(300, 400);
        menuFrame.setLayout(new GridLayout(0, 1));

        JButton addFriendButton = new JButton("Arkadaş Ekle");
        addFriendButton.addActionListener((ActionEvent e) -> {
            addFriendOperation(currentUser);
        });

        JButton shareTextButton = new JButton("Metinsel Bilgi Paylaş");
        shareTextButton.addActionListener((ActionEvent e) -> {
            shareText(currentUser);
        });

        JButton changeVisibilityButton = new JButton("Görünürlük Ayarları");
        changeVisibilityButton.addActionListener((ActionEvent e) -> {
            changeVisibility(currentUser);
        });

        JButton blockUserButton = new JButton("Engelleme Ayarları");
        blockUserButton.addActionListener((ActionEvent e) -> {
            blockUser(currentUser);
        });

        JButton printFriendsButton = new JButton("Arkadaş Listesini Görüntüle");
        printFriendsButton.addActionListener((ActionEvent e) -> {
            printUserFriendsAndRelationships(currentUser);
        });

        JButton explorePostsButton = new JButton("Metinsel Bilgileri ve Keşfeti Gör");
        explorePostsButton.addActionListener((ActionEvent e) -> {
            displayPostsAndExplore(currentUser);
        });

        JButton createGroupButton = new JButton("Yeni Grup Ekle");
        createGroupButton.addActionListener((ActionEvent e) -> {
            createNewGroup();
        });

        JButton viewGroupsButton = new JButton("Grupları Görüntüle");
        viewGroupsButton.addActionListener((ActionEvent e) -> {
            viewGroups();
        });
        
        JButton joinExistingGroupsButton = new JButton("Var olan Gruplara Katıl");
        joinExistingGroupsButton.addActionListener((ActionEvent e) -> {
            joinExistingGroups(currentUser);
        });
        
        JButton joinSubGroupsButton = new JButton("Alt-Gruplara katıl");
        joinSubGroupsButton.addActionListener((ActionEvent e) -> {
            joinSubGroups(currentUser);
        });

        JButton exitButton = new JButton("Çıkış");
        exitButton.addActionListener((ActionEvent e) -> {
            menuFrame.dispose();
        });
        
        JButton createSubGroupButton = new JButton("Alt Grup Oluştur");
        createSubGroupButton.addActionListener((ActionEvent e) -> {
            createNewSubGroup(currentUser);
        });

        JButton leaveGroupButton = new JButton("Grup/Alt Grup Ayrıl");
        leaveGroupButton.addActionListener((ActionEvent e) -> {
            leaveGroup(currentUser);
        });

        menuFrame.add(addFriendButton);
        menuFrame.add(shareTextButton);
        menuFrame.add(changeVisibilityButton);
        menuFrame.add(blockUserButton);
        menuFrame.add(printFriendsButton);
        menuFrame.add(explorePostsButton);
        menuFrame.add(createGroupButton);
        menuFrame.add(viewGroupsButton);
        menuFrame.add(joinExistingGroupsButton);
        menuFrame.add(joinSubGroupsButton);
        menuFrame.add(createSubGroupButton);        
        menuFrame.add(leaveGroupButton);
        menuFrame.add(exitButton);

        menuFrame.setVisible(true);
    }

    private void createNewGroup() {
        String groupName = JOptionPane.showInputDialog("Yeni grup adı:");
        Group newGroup = new Group(groupName);
        groups.add(newGroup);
        JOptionPane.showMessageDialog(null, "Yeni grup oluşturuldu: " + groupName);
    }
    
    private void createNewSubGroup(User currentUser) {
    // Kullanıcının üye olduğu grupları al
    List<Group> userGroups = currentUser.getGroups();
        System.out.println(userGroups.isEmpty());
    // Kullanıcının alt grup oluşturabileceği bir ana grup var mı kontrol et
    if (!userGroups.isEmpty()) {
        // Kullanıcıya hangi ana gruba alt grup eklemek istediğini sormak için bir dizi seçenek oluştur
        String[] groupNames = new String[userGroups.size()];
        for (int i = 0; i < userGroups.size(); i++) {
            groupNames[i] = userGroups.get(i).getName();
        }

        // Kullanıcıya seçim yapması için bir pencere göster
        String selectedGroupName = (String) JOptionPane.showInputDialog(
                null,
                "Hangi ana gruba alt grup eklemek istiyorsunuz?",
                "Alt Grup Oluşturma",
                JOptionPane.QUESTION_MESSAGE,
                null,
                groupNames,
                groupNames[0]); // Varsayılan olarak ilk grubu seç

        // Kullanıcı bir seçim yaptıysa devam et
        if (selectedGroupName != null && !selectedGroupName.isEmpty()) {
            // Seçilen ana grubu bul
            Group selectedGroup = null;
            for (Group group : userGroups) {
                if (group.getName().equals(selectedGroupName)) {
                    selectedGroup = group;
                    break;
                }
            }

            if (selectedGroup != null) {
                // Kullanıcıdan alt grup adını al
                String subGroupName = JOptionPane.showInputDialog("Oluşturulacak alt grup adını girin:");
                if (subGroupName != null && !subGroupName.isEmpty()) {
                    // Seçilen ana gruba alt grup olarak ekler
                    userManager.createSubGroup(selectedGroup, subGroupName);
                    JOptionPane.showMessageDialog(null, "Alt grup oluşturuldu: " + subGroupName);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seçilen grup bulunamadı.");
            }
        }
    } else {
        // Kullanıcı üye olduğu bir ana grup olmadığı için alt grup oluşturamaz
        JOptionPane.showMessageDialog(null, "Alt grup oluşturmak için önce bir ana gruba üye olmalısınız.");
    }
}

// rapora ekle
    // Iterator design pattern'in kullanılması 
    private void viewGroups() {
        StringBuilder groupList = new StringBuilder("Groups:\n");
        Iterator<Group> groupIterator = groups.iterator();

        while (groupIterator.hasNext()) {
            Group group = groupIterator.next();
            groupList.append(group.getName()).append("\n");

            Iterator<User> userIterator = group.getMembers().iterator();
            while (userIterator.hasNext()) {
                User user = userIterator.next();
                groupList.append("  - ").append(user.getUsername()).append("\n");
            }

            Iterator<Group> subGroupIterator = group.getSubGroups().iterator();
            while (subGroupIterator.hasNext()) {
                Group subGroup = subGroupIterator.next();
                groupList.append("  * ").append(subGroup.getName()).append("\n");

                Iterator<User> subGroupUserIterator = subGroup.getMembers().iterator();
                while (subGroupUserIterator.hasNext()) {
                    User subGroupUser = subGroupUserIterator.next();
                    groupList.append("    - ").append(subGroupUser.getUsername()).append("\n");
                }
            }
        }

        JTextArea textArea = new JTextArea(groupList.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Group List", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Iterator Kullanımı
    private void joinExistingGroups(User currentUser) {
        StringBuilder groupList = new StringBuilder("Mevcut Gruplar:\n");
        Iterator<Group> groupIterator = groups.iterator();

        while (groupIterator.hasNext()) {
            Group group = groupIterator.next();
            if (userManager.findGroupByName(currentUser.getGroups(), group.getName()) != null){
                continue;
            }
                System.out.println(group.getName());
            groupList.append(group.getName()).append("\n");
        }

        String groupName = JOptionPane.showInputDialog(groupList.toString() + "\nKatılmak istediğiniz grup adını girin:");
        Group selectedGroup = userManager.findGroupByName(groups, groupName);

        if (selectedGroup != null) {
            selectedGroup.addMember(currentUser);
            currentUser.joinGroup(selectedGroup);
            JOptionPane.showMessageDialog(null, "Gruba başarıyla katıldınız: " + selectedGroup.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Grup bulunamadı.");
        }
    }
    
    // Iterator Kullanımı
    private void joinSubGroups(User currentUser) {
        StringBuilder groupList = new StringBuilder("Katıldığınız Grupların Alt Grupları:\n");
        Iterator<Group> groupIterator = currentUser.getGroups().iterator();

        while (groupIterator.hasNext()) {
            Group group = groupIterator.next();
            groupList.append(group.getName()).append(":\n");

            Iterator<Group> subGroupIterator = group.getSubGroups().iterator();
            while (subGroupIterator.hasNext()) {
                Group subGroup = subGroupIterator.next();
                if (userManager.findUserByUsername(subGroup.getMembers(), currentUser.getUsername()) != null) continue;
                groupList.append("  - ").append(subGroup.getName()).append("\n");
            }
        }

        String subGroupName = JOptionPane.showInputDialog(groupList.toString() + "\nKatılmak istediğiniz alt grup adını girin:");
        Group selectedSubGroup = null;

        Iterator<Group> userGroupIterator = currentUser.getGroups().iterator();
        while (userGroupIterator.hasNext()) {
            Group userGroup = userGroupIterator.next();
            selectedSubGroup = userManager.findGroupByName(userGroup.getSubGroups(), subGroupName);
            if (selectedSubGroup != null) {
                break;
            }
        }

        if (selectedSubGroup != null) {
            selectedSubGroup.addMember(currentUser);
            currentUser.joinGroup(selectedSubGroup);
            JOptionPane.showMessageDialog(null, "Alt gruba başarıyla katıldınız: " + selectedSubGroup.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Alt grup bulunamadı.");
        }
    }
// rapora ekle burada bitiyor
    
    private void leaveGroup(User currentUser) {
        // Kullanıcının üye olduğu grupları al
        List<Group> userGroups = currentUser.getGroups();

        // Eğer kullanıcının üye olduğu grup yoksa bilgilendirme mesajı göster
        if (userGroups.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Henüz bir gruba üye değilsiniz.");
            return;
        }

        // Üye olduğu grupları listele
        StringBuilder groupListMessage = new StringBuilder("Üye Olduğunuz Gruplar:\n");
        for (Group group : userGroups) {
            groupListMessage.append(group.getName()).append("\n");
        }

        // Kullanıcıdan ayrılmak istediği grubu seçmesini iste
        String groupName = JOptionPane.showInputDialog(groupListMessage.toString() + "\nAyrılmak istediğiniz grup/alt grubun adını girin:");

        // Kullanıcı bir grup adı girdiyse işlemi yap
        if (groupName != null && !groupName.isEmpty()) {
            Group group = userManager.findGroupByName(userGroups, groupName);
            if (group != null) {
                userManager.leaveGroup(currentUser, group);
                JOptionPane.showMessageDialog(null, "Grupdan/Alt gruptan ayrıldınız: " + groupName);
            } else {
                JOptionPane.showMessageDialog(null, "Girilen adla bir grup/alt grup bulunamadı.");
            }
        }
    }


    private void leaveSubGroup(){
        
    }

    // Iterator design pattern'in kullanılması 
    private void printUserFriendsAndRelationships(User currentUser) {
        StringBuilder friendInfoText = new StringBuilder();
        friendInfoText.append("Arkadaşlar ve İlişkileri:\n");

        Iterator<User> friendIterator = currentUser.getFriends().iterator();
        while (friendIterator.hasNext()) {
            User friend = friendIterator.next();
            friendInfoText.append("Arkadaş: ").append(friend.getUsername()).append("\n");
            friendInfoText.append("İlişki: ").append(currentUser.getRelationship(friend)).append("\n");
        }

        JOptionPane.showMessageDialog(null, friendInfoText.toString());
    }

    // Iterator design pattern'in kullanılması 
    private void displayPostsAndExplore(User currentUser) {
        StringBuilder postText = new StringBuilder();
        postText.append("Kullanıcı Gönderileri:\n");

        Iterator<Post> postIterator = currentUser.getWall().iterator();
        while (postIterator.hasNext()) {
            Post post = postIterator.next();
            postText.append("- ").append(post.getContent()).append("\n");
        }

        postText.append("\nKeşfedilen Gönderiler:\n");

        Iterator<User> friendIterator = currentUser.getFriends().iterator();
        while (friendIterator.hasNext()) {
            User friend = friendIterator.next();
            Iterator<Post> friendPostIterator = friend.getWall().iterator();
            while (friendPostIterator.hasNext()) {
                Post post = friendPostIterator.next();
                postText.append("- [").append(friend.getUsername()).append("] ").append(post.getContent()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, postText.toString());
    }

    // Iterator design pattern'in kullanılması 
    private void displayFriendPosts(User currentUser) {
        StringBuilder friendPostText = new StringBuilder();
        friendPostText.append("Arkadaşların Gönderileri:\n");

        Iterator<User> friendIterator = currentUser.getFriends().iterator();
        while (friendIterator.hasNext()) {
            User friend = friendIterator.next();
            friendPostText.append("[").append(friend.getUsername()).append("\n");

            Iterator<Post> postIterator = friend.getWall().iterator();
            while (postIterator.hasNext()) {
                Post post = postIterator.next();
                friendPostText.append("- ").append(post.getContent()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, friendPostText.toString());
    }
    
   public void UnblockUserInterface(User currentUser, User friend) {
        int choice = JOptionPane.showOptionDialog(null, "Kullanıcının engelini kaldırmak istiyor musunuz?", "Kullanıcı Engeli Kaldırma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Evet", "Hayır"}, "Evet");

        if (choice == JOptionPane.YES_OPTION) {
            // Evet'e tıklandığında yapılacak işlemler
            JOptionPane.showMessageDialog(null, "Kullanıcının engeli kaldırıldı!");
            addFriend(currentUser, friend);
        } else {
            // Hayır'a tıklandığında yapılacak işlemler
            JOptionPane.showMessageDialog(null, "Kullanıcının engeli kaldırılmadı!");
        }
    }
    
    private void addFriendOperation(User currentUser) {
        String friendUsername = JOptionPane.showInputDialog("Eklenecek arkadaşın kullanıcı adı:");
        User friend = userManager.findUserByUsername(users, friendUsername);
        if (friend != null && currentUser != friend) {
            if (currentUser.getBlockedUsers().contains(friend)) {
                JOptionPane.showMessageDialog(null,"Siz "+ friend.getUsername() + " adlı kullanıcıyı engellediğiniz için arkadaş ekleyemezsiniz.");
                UnblockUserInterface(currentUser, friend);
            } else if (friend.getBlockedUsers().contains(currentUser)) {
                JOptionPane.showMessageDialog(null, "Siz " + friend.getUsername() + " tarafından engellendiğiniz için arkadaş ekleyemezsiniz.");
            } else {
                addFriend(currentUser, friend);
            }
        }
        else if(currentUser != friend){
            JOptionPane.showMessageDialog(null, "Kendi kendini arkadaş ekleyemezsin.");
        }
        else {
            JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.");
        }
    }
    
    private void addFriend(User currentUser, User friend)
    {
        int relationshipChoice = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                                      Arkada\u015fl\u0131k ili\u015fkisi tipini se\u00e7in:
                                                                                      1. Friendship
                                                                                      2. Common Interests
                                                                                      3. Family Relationships""")
                );
                String relationshipType;
                switch (relationshipChoice) {
                    case 1:
                        relationshipType = "Friendship";
                        break;
                    case 2:
                        relationshipType = "Common Interests";
                        break;
                    case 3:
                        relationshipType = "Family Relationships";
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Geçersiz seçenek! Arkadaşlık eklenemedi.");
                        return;
                }
                currentUser.addFriend(friend, new Relationship(currentUser, friend, relationshipType));
                JOptionPane.showMessageDialog(null, "Arkadaş eklendi!");
    }
    
    

    private void shareText(User currentUser) {
        String message = JOptionPane.showInputDialog("Paylaşmak istediğiniz metinsel bilgi:");
        currentUser.publishPost(message);
        JOptionPane.showMessageDialog(null, "Metinsel bilgi başarıyla paylaşıldı!");
    }

    private void changeVisibility(User currentUser) {
        boolean isVisible = Boolean.parseBoolean(JOptionPane.showInputDialog("Görünürlük durumu (true/false):"));
        currentUser.setVisibleInSearch(isVisible);
        JOptionPane.showMessageDialog(null, "Görünürlük durumu başarıyla güncellendi.");
    }

    private void blockUser(User currentUser) {
        String blockedUsername = JOptionPane.showInputDialog("Engellenecek kullanıcının kullanıcı adı:");
        
        if(userManager.findUserByUsername(currentUser.getUsersWhoBlockYou(), blockedUsername) != null){
            JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı. Bu Kullanıcı sizi engellemiş ola bilir");
            return;
        }
        
        User blockedFriend = userManager.findUserByUsername(currentUser.getFriends(), blockedUsername);
        User blockedUser = userManager.findUserByUsername(users, blockedUsername);
        if (blockedFriend != null) {
            blockFriend(blockedFriend, currentUser);
        } else if(blockedUser != null) {
            blockNormalUser(blockedUser, currentUser);
        }else{
            JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.");
        }
    }
    
    private void blockFriend(User blockedFriend, User currentUser){
        if (currentUser.getBlockedUsers().contains(blockedFriend)) {
            JOptionPane.showMessageDialog(null, blockedFriend.getUsername() + " zaten engellendi.");
        } else {
            currentUser.blockUser(blockedFriend);
            blockedFriend.beBlocked(currentUser);
            currentUser.removeFriend(blockedFriend);
            JOptionPane.showMessageDialog(null, "Kullanıcı engellendi ve arkadaşlık ilişkisi kaldırıldı!");
        }
    }

    
    private void blockNormalUser(User blockedUser, User currentUser){
        if (!currentUser.getBlockedUsers().contains(blockedUser)) {
            currentUser.blockUser(blockedUser);
            blockedUser.beBlocked(currentUser);
            currentUser.removeFriend(blockedUser);
            JOptionPane.showMessageDialog(null, "Kullanıcı engellendi ve engeli kaldırmadığın sürece seninle arkadaş olamaz!");
        } else {
            JOptionPane.showMessageDialog(null, blockedUser.getUsername() + " zaten engellendi.");
        }
    }

    private void searchUser() {
        String searchName = JOptionPane.showInputDialog("Aranacak kullanıcı adını girin:");
        List<User> foundUsers = userManager.searchUsers(users, searchName);
        StringBuilder message = new StringBuilder("Eşleşen Kullanıcılar Listesi:\n");
        if (foundUsers.isEmpty()) {
            message.append("Kullanıcı bulunamadı.");
        } else {
            Iterator<User> userIterator = foundUsers.iterator();
            while (userIterator.hasNext()) {
                User user = userIterator.next();
                message.append(user.getUsername()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private void createUserAndJoinGroups() {
        
        String username;
        String password;
        
        while (true) {
            username = JOptionPane.showInputDialog("Kullanıcı adı:");
            System.out.println("username: " + username);
            
            if (username == null || "".equals(username)) {
                return; // Kullanıcı iptal ettiği veya boş bıraktığı için işlemi sonlandır
            }

            password = JOptionPane.showInputDialog("Parola:");
            if (password == null || "".equals(password)) {
                return; // Kullanıcı iptal ettiği veya boş bıraktığı için işlemi sonlandır
            }
            
            if (userManager.findUserByUsername(users, username) == null) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, username + " isimde kullanıcı var!");
            }
        } 

        User newUser = new User(username, password);
        users.add(newUser);
        
        

        // Kullanıcıya gruplara katılma seçeneği sunulsun
        int option = JOptionPane.showConfirmDialog(null, "Gruplara katılmak ister misiniz?", "Gruplara Katıl", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            Iterator<Group> groupIterator = groups.iterator();
            while (groupIterator.hasNext()) {
                Group group = groupIterator.next();
                int joinOption = JOptionPane.showConfirmDialog(null, "Gruba katılmak ister misiniz? Grup adı: " + group.getName(), "Gruba Katıl", JOptionPane.YES_NO_OPTION);
                if (joinOption == JOptionPane.YES_OPTION) {
                    group.addMember(newUser);
                    newUser.joinGroup(group);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Kullanıcı hesabı başarıyla oluşturuldu ve gruplara katılım işlemi gerçekleştirildi!");
    }
    
}
