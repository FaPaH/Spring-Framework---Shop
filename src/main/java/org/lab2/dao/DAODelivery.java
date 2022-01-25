package org.lab2.dao;

import org.lab2.model.Delivery;
import org.lab2.model.Products;

import java.util.List;

public interface DAODelivery {

    void addDelivery(int userId, String username, List<Integer> productIdsList, String deliveryAddress, int numberPhone, int totalPrise);

    void removeDelivery(int deliveryId);

    List<Delivery> getAllDelivery();

    List<Delivery> findDeliveryByUserId(int userId);
}
