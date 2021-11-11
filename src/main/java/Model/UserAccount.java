package Model;

import java.io.Serializable;

/**
 * The class used to handle user accounts and their related tasks.
 *
 * @author Quenten Calvano
 * @version 9/10/2021
 */


public class UserAccount implements Serializable{

    //Attributes...
    private String username;
    private String password;
    private Double weight;

    //Constructors...
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.weight = -1.0;
    }
    public UserAccount(String username, String password, Double weight) {
        this.username = username;
        this.password = password;
        this.weight = weight;
    }

    //Setters and Getters...
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
