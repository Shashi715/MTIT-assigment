package com.mtit.assignment.microservice.productservice.model;

public class StockRequest {

    private int productId;
    private int quantity;

    public StockRequest() {
        super();
    }

    public StockRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
