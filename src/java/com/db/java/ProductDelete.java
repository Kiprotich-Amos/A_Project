package com.db.java;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to handle product deletion operations
 * Author: Clint Amos
 */
@Named(value = "productDelete")
@SessionScoped
public class ProductDelete implements Serializable {

    // Field to capture the product ID from the UI
    private int productId;

    // Getter for productId
    public int getProductId() {
        return productId;
    }

    // Setter for productId
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Deletes a product from the database by the stored ProductID.
     *
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteProduct() throws ClassNotFoundException {
        // Get a database connection
        Connection connection = null;
        PreparedStatement pst = null;

        try {
            // Establish the database connection
            connection = new DataBase().connectDb();

            // SQL query to delete the product by ProductID
            String deleteSQL = "DELETE FROM product WHERE ProductID = ?";

            // Prepare the statement
            pst = connection.prepareStatement(deleteSQL);

            // Set the ProductID parameter
            pst.setInt(1, productId);

            // Execute the DELETE statement
            int rowsAffected = pst.executeUpdate();

            // Return true if a row was deleted, otherwise false
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Error handling
            System.err.println("Error deleting product with ID: " + productId);
            e.printStackTrace();
            return false;

        } finally {
            // Ensure resources are properly closed
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle cleanup error if it occurs
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle cleanup error if it occurs
                }
            }
        }
    }
}
