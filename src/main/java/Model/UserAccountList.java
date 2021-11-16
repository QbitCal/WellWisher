/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Quenten Calvano
 * @version 11/6/2021
 */
public class UserAccountList {
    private ArrayList<UserAccount> accountList = new ArrayList<>();
    private String userListFileName = "listOfUsers.ser";
    
    public UserAccountList() {
        this.readUserListFile();
        if(accountList.isEmpty() || accountList == null) {
            this.createTestUserList();
            this.writeUserListFile();
            this.readUserListFile();
        }
        this.printUserList();
    }

    //Setters and Getters...
    public ArrayList<UserAccount> getAccountList() {
        return accountList;
    }
    
    private void printUserList() {
        System.out.println("The User Account List has these users:");
        for (int i = 0; i < accountList.size(); i++) {
            UserAccount currentUser = (UserAccount) accountList.get(i);
            System.out.println(currentUser.getUsername());
        }
    }

    //This method creates 20 test users...
    private void createTestUserList() {
        for (int i = 0; i < 20; i++) {
            accountList.add(new UserAccount("User " + i, "password"));
        }
        System.out.println("Test UserList created");
        System.out.println("The UserList is: " + accountList);
    }

    private void writeUserListFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(userListFileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(accountList);
            out.close();
        } catch (IOException ex) {
            System.out.println("ERROR: IOException occurred.");
        }
    
    }

    private void readUserListFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(userListFileName);
            in = new ObjectInputStream(fis);
            accountList = (ArrayList) in.readObject();
            in.close();
            if (!accountList.isEmpty()) {
                System.out.println("There are users in the user list.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFoundException occurred.");
        } catch (IOException e) {
            System.out.println("ERROR: IOException occurred.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: ClassNotFoundException occurred.");
        }
    }
}
