/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserAccount;
import Model.UserAccountList;
import java.util.ArrayList;

/**
 *
 * @author quentencalvano
 */
public class LoginController {
    
    private UserAccountList userAccounts;
    private ArrayList<UserAccount> accountList;
    
    public LoginController() {
        // Get an instance of the current UserAccountList class...
        userAccounts = new UserAccountList();
        accountList = userAccounts.getAccountList();
        // Then we would put up the LginUI window...
    }
    
    public UserAccountList getUserList(){
        return userAccounts;
    }
    
}
