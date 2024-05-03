/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.java;

import java.sql.*;

/**
 *class to call database
 * @author Clint Amos
 */
public class DataBase {
    
    public String url = "jdbc:mysql://localhost:3306/item";
    public String user = "root";
    public String password = "";
    
    public Connection connectDb() throws ClassNotFoundException{
         Class.forName("com.mysql.cj.jdbc.Driver");
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                return connection;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
        
    }
    
}
