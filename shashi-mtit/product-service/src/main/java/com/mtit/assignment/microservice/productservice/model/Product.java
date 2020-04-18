package com.mtit.assignment.microservice.productservice.model;

public class Product {

    private int id;
    private String productid;
    private String productName;
    private double unitPrice;

    public Product(int id, String productid, String productName, double unitPrice) {
        this.id = id;
        this.productid = productid;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public Product() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productid='" + productid + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
