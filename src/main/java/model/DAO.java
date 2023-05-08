/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Typan
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DAO {
    
    private Connection theConnection;
    public DAO() {
            try {
                this.theConnection = Database.getDB().getConnection(); //retrieve the single instance of the database
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    public boolean createUser(User theUser) {
            try {
                //prepare the insert query
                PreparedStatement theStatement = theConnection.prepareStatement("insert into User (username,user_password,currentAnime,anime1,anime2,anime3) values (?,?,?,?,?,?)");
    //attach the values to be inserted into the database through the query
                theStatement.setString(1, theUser.getUsername());
                theStatement.setString(2, new String(theUser.getPassword()));
                theStatement.setString(3, "none");
                theStatement.setString(4, "none");
                theStatement.setString(5, "none");
                theStatement.setString(6, "none");
    //execute the query and return the boolean to denote the status
                theStatement.execute();
                return true;
            } catch (Exception ex) {
                //if error return false
                return false;
            }
        }
    public User getUser(User theUser) {
            try {
                //prepare the insert query
                PreparedStatement theStatement = theConnection.prepareStatement("select * from User where username = ? and user_password = ?");
                //attach the values to be inserted into the database through the query
                theStatement.setString(1, theUser.getUsername());
                theStatement.setString(2, new String(theUser.getPassword()));
    //execute the query to get the record for the user
                ResultSet queryResult = theStatement.executeQuery();
    if (queryResult.next()) {
                    //if query has results, return the user record 
                    return new User(queryResult.getString("username"), queryResult.getString("user_password").toCharArray());
                } else {
                    return null;
                }
            } catch (Exception ex) {
                //if error return false
                return null;
            }
        }
    public void setUserCurrent(String username, String currentAnime){
        //sets the users currently watching anime in the database 
        try{
            PreparedStatement theStatement = theConnection.prepareStatement("update User set currentAnime = ? where username = ?");
            theStatement.setString(1, currentAnime);
            theStatement.setString(2, username);
            theStatement.execute();
            
        }catch(Exception ex){
            
        }    
    }
    public void setUserFavAnime(String username, String anime1, String anime2, String anime3){
        //sets the users top three anime from the inputs 
        try{
            PreparedStatement theStatement = theConnection.prepareStatement("update User set anime1 = ?, anime2 = ?, anime3 = ? where username = ?");            
            theStatement.setString(1, anime1);
            theStatement.setString(2, anime2);
            theStatement.setString(3, anime3);
             theStatement.setString(4, username);
            theStatement.execute();
            
        }catch(Exception ex){
            
        }    
    
    
    }
    
    public String getuserFavAnime(String username, String anime){
        //gets the users top three anime from the database
    try{
            if("anime1".equals(anime)){
                PreparedStatement theStatement = theConnection.prepareStatement("select anime1 from User where username = ?");            
                theStatement.setString(1, username);
                 ResultSet queryResult = theStatement.executeQuery();
                if (queryResult.next()) {

                        return(queryResult.getString(anime));
                    } else {
                        return null;
                    }
                
            }else if("anime2".equals(anime)){
                PreparedStatement theStatement = theConnection.prepareStatement("select anime2 from User where username = ?");            
                theStatement.setString(1, username);
                 ResultSet queryResult = theStatement.executeQuery();
                if (queryResult.next()) {

                        return(queryResult.getString(anime));
                    } else {
                        return null;
                    }
            
            } else if("anime3".equals(anime)){
                PreparedStatement theStatement = theConnection.prepareStatement("select anime3 from User where username = ?");            
                theStatement.setString(1, username);
                 ResultSet queryResult = theStatement.executeQuery();
                if (queryResult.next()) {

                        return(queryResult.getString(anime));
                    } else {
                        return null;
                    }
            
            } else {
            return null;
            
            }
            
           
           
            
        }catch(Exception ex){
            return null;
        }    
    
    
    }
    
    public String getUserCurrent(String username){
        //gets the current anime from the database
        try{
            PreparedStatement theStatement = theConnection.prepareStatement("select currentAnime from User where username = ?");
            
            theStatement.setString(1, username);
            
            ResultSet queryResult = theStatement.executeQuery();
            if (queryResult.next()) {
                    
                    return(queryResult.getString("currentAnime"));
                } else {
                    return null;
                }
            
            
        }catch(Exception ex){
        
            return null;
        }
       
   
    }
    
    public String encryptPass(String password) {
            try {
                //retrieve instance of the encryptor of SHA-256
                MessageDigest digestor = MessageDigest.getInstance("SHA-256");
                //retrieve bytes to encrypt
                byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
                StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
                //perform encryption
                for (int i = 0; i < encodedhash.length; i++) {
                    String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                    if (hexVal.length() == 1) {
                        encryptionValue.append('0');
                    }
                    encryptionValue.append(hexVal);
                }
                //return encrypted value
                return encryptionValue.toString();
    } catch (Exception ex) {
                return ex.getMessage();
            }
        }

    
    
}
