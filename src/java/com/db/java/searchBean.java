/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.java;

import com.Beans.java.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Clint Amos
 */
@Named(value = "searchBean")
@SessionScoped
public class searchBean implements Serializable {

    /**
     * Creates a new instance of searchBean
     */
    private String searchQuery;

    public ArrayList<Product> find() {
        ArrayList array = new ArrayList();

        try {
            Connection connect = new DataBase().connectDb();
            if (connect != null) {
                String sql = "SELECT * FROM product WHERE ProductName LIKE ?";
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setString(1, "%" + searchQuery + "%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        Product p = new Product();
                        p.setProID(rs.getInt(1));
                        p.setProName(rs.getString(2));
                        p.setProCity(rs.getString(3));
                        p.setProPrice(rs.getInt(4));

//                        add data to array
                        array.add(p);
                    }
                    return array;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public searchBean() {
    }

}
