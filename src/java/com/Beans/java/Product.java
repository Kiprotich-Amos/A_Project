/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Beans.java;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Clint Amos
 */

@Named(value = "product")
@SessionScoped 
public class Product implements Serializable {

    /**
     * Creates a new instance of Product
     */
    
    private int ProID;
    private String ProName;
    private String ProCity;
    private int ProPrice;
    
    public Product() {
    }

    public int getProID() {
        return ProID;
    }

    public void setProID(int ProID) {
        this.ProID = ProID;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String ProName) {
        this.ProName = ProName;
    }

    public String getProCity() {
        return ProCity;
    }

    public void setProCity(String ProCity) {
        this.ProCity = ProCity;
    }

    public int getProPrice() {
        return ProPrice;
    }

    public void setProPrice(int ProPrice) {
        this.ProPrice = ProPrice;
    }
    
}
