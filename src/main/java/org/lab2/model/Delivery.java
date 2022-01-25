package org.lab2.model;

import java.util.List;

public class Delivery {

    private int deliveryId;
    private int userId;
    private String username;
    private List<Integer> productIds;
    private String deliveryAddress;
    private int numberPhone;
    private int totalPrise;

    public Delivery(int deliveryId, int userId, String username, List<Integer> productIds, String deliveryAddress, int numberPhone, int totalPrise) {
        this.deliveryId = deliveryId;
        this.userId = userId;
        this.username = username;
        this.productIds = productIds;
        this.deliveryAddress = deliveryAddress;
        this.numberPhone = numberPhone;
        this.totalPrise = totalPrise;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public int getTotalPrise() {
        return totalPrise;
    }

    public void setTotalPrise(int totalPrise) {
        this.totalPrise = totalPrise;
    }
}
