package entity;

import Exceptions.StockUnavailableException;

import java.util.Objects;

public class Product {

    private int productId;
    private String productName;
    private double price;
    private int availableQuantity;

    public Product() {
    }

    public Product(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.availableQuantity = quantity;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        if (availableQuantity < 0) {
            this.availableQuantity = 0;
        } else {
            this.availableQuantity = availableQuantity;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public void reduceStock(int quantity) throws StockUnavailableException {
        if (quantity > availableQuantity) {
            throw new StockUnavailableException("Stock is insufficient to fulfill your order");
        } else {
            this.availableQuantity -= quantity;
        }
    }


    @Override
    public String toString() {
        return "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity ;

    }
}
