package com.mtit.assignment.microservice.productservice.model;

public class ProductRequest {

    private String productid;
    private String productName;
    private double unitPrice;
    private int qty;

    public ProductRequest(){
        super();
    }

    public ProductRequest(String productid, String productName, double unitPrice, int qty) {
        this.productid = productid;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productid='" + productid + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
