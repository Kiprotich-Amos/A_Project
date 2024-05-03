/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.java;

/**
 *
 * @author Clint Amos
 */

import com.Beans.java.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {
    
    
    /**
     * Creates a new instance of addProduct
     */

    private Product product; // Set from the UI
    private String responseMessage;

    public ProductController() {
        product = new Product(); // Initialize the product object
    }

    public String addNewProduct() {
        try (Connection connect = new DataBase().connectDb();
             PreparedStatement pst = connect.prepareStatement(
                  "INSERT INTO product (ProductID, ProductName, ProductCity, ProductPrice) VALUES (?, ?, ?, ?)")
        ) {
            if (product == null) {
                responseMessage = "No product data provided.";
                return null;
            }

            pst.setInt(1, product.getProID());
            pst.setString(2, product.getProName());
            pst.setString(3, product.getProCity());
            pst.setInt(4, product.getProPrice());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                responseMessage = "Product added successfully!";
            } else {
                responseMessage = "Failed to add product.";
            }
        } catch (SQLException ex) {
            responseMessage = "Database error: " + ex.getMessage();
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            responseMessage = "Unexpected error: " + ex.getMessage();
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; // Returning null keeps the same view
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
