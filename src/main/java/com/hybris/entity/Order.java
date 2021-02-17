package com.hybris.entity;

public class Order {
    private int id;
    private int userId;
    private String status;
    private String createdAt;

    public Order() {
    }

    public Order(int userId, String status, String createdAt) {
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order(int id, int userId, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
