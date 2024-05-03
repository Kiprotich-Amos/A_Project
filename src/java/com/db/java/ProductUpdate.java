package com.db.java;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to handle product update operations
 * Author: Clint Amos
 */
@Named(value = "productUpdate")
@SessionScoped
public class ProductUpdate implements Serializable {

    private int productId;
    private String productName;
    private double productPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Updates the product's name and price based on the ProductID.
     * 
     * @return a navigation outcome or a success/failure message.
     */
    public String updateProduct() throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement pst = null;

        try {
            connection = new DataBase().connectDb(); // Your database connection class

            String updateSQL = "UPDATE product SET ProductName = ?, ProductPrice = ? WHERE ProductID = ?";

            pst = connection.prepareStatement(updateSQL);

            pst.setString(1, productName);
            pst.setDouble(2, productPrice);
            pst.setInt(3, productId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                return "update-success"; // Navigation outcome or success message
            } else {
                return "update-failure"; // Navigation outcome or failure message
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the SQL exception
            return "error"; // Navigation outcome or error message

        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
