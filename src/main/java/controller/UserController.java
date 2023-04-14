/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Typan
 */
import model.DAO;
import model.User;


public class UserController {
     private DAO theDAO;
    
    public UserController() {
        this.theDAO = new DAO();
    }
    
    public boolean createUser(User theUser) {
        String encryptedPassword = theDAO.encryptPass(new String(theUser.getPassword()));
        theUser.setPassword(encryptedPassword.toCharArray());
        return theDAO.createUser(theUser);
    }
    
    public User getUser(User theUser) {
        String encryptedPassword = theDAO.encryptPass(new String(theUser.getPassword()));
        theUser.setPassword(encryptedPassword.toCharArray());
        return this.theDAO.getUser(theUser);
    }
    
    public void setUserCurrent(String username,String currentAnime){
        theDAO.setUserCurrent(username, currentAnime);
    
    }
    
    public String getUserCurrent(String username){
    
    return this.theDAO.getUserCurrent(username);
    }
    
    public void setUserFavAnime(String username, String anime1, String anime2, String anime3){
     
        this.theDAO.setUserFavAnime(username, anime1, anime2, anime3);
        
    }
     
    public String getuserFavAnime(String username, String anime){
    
     return this.theDAO.getuserFavAnime(username, anime);
    }
    
}
