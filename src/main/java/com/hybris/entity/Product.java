package com.hybris.entity;

import com.hybris.enums.ProductStatus;

import java.sql.Date;
import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private int price;
    private ProductStatus status;
    private Timestamp createdAt;

    public Product() {
    }

    public Product(String name, int price, ProductStatus status, Timestamp createdAt) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Product(int id, String name, int price, ProductStatus status, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
