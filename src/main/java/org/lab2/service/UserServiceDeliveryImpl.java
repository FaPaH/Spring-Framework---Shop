package org.lab2.service;

import org.apache.log4j.Logger;
import org.lab2.dao.DAODelivery;
import org.lab2.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDeliveryImpl implements UserServiceDelivery{

    private static Logger logger = Logger.getLogger(UserServiceDeliveryImpl.class);

    private DAODelivery daoDelivery;

    @Autowired
    public void setDaoDelivery(DAODelivery daoDelivery) {
        this.daoDelivery = daoDelivery;
    }

    @Override
    public void addDelivery(int userId, String username, List<Integer> productIdsList, String deliveryAddress, int numberPhone, int totalPrise) {
        logger.debug("called addDelivery() method. UserServiceDeliveryImpl.class");
        daoDelivery.addDelivery(userId, username, productIdsList, deliveryAddress, numberPhone, totalPrise);
    }

    @Override
    public void removeDelivery(int deliveryId) {
        logger.debug("called removeDelivery() method. UserServiceDeliveryImpl.class");
        daoDelivery.removeDelivery(deliveryId);
    }

    @Override
    public List<Delivery> getAllDelivery() {
        logger.debug("called getAllDelivery() method. UserServiceDeliveryImpl.class");
        return daoDelivery.getAllDelivery();
    }

    @Override
    public List<Delivery> findDeliveryByUserId(int userId) {
        logger.debug("called findDeliveryByUserId() method. UserServiceDeliveryImpl.class");
        return daoDelivery.findDeliveryByUserId(userId);
    }
}
