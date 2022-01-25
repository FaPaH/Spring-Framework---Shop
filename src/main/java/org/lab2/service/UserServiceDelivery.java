package org.lab2.service;

import org.lab2.model.Delivery;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserServiceDelivery {

    void addDelivery(int userId, String username, List<Integer> productIdsList, String deliveryAddress, int numberPhone, int totalPrise);

    void removeDelivery(int deliveryId);

    List<Delivery> getAllDelivery();

    List<Delivery> findDeliveryByUserId(int userId);
}
