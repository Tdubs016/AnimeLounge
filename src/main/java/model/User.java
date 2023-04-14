/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Typan
 */
public class User {
    private String username;
    private char[] password;
public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }
public String getUsername() {
        return username;
    }
public void setUsername(String username) {
        this.username = username;
    }
public char[] getPassword() {
        return password;
    }
public void setPassword(char[] password) {
        this.password = password;
    }
}
