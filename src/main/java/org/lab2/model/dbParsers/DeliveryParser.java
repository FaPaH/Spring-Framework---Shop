package org.lab2.model.dbParsers;

import org.apache.log4j.Logger;
import org.lab2.model.Delivery;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DeliveryParser {

    private static Logger logger = Logger.getLogger(DeliveryParser.class);

    private Delivery delivery;

    public List<Delivery> parseAllDelivery(ResultSet resultSet) {
        List<Delivery> deliveries = new ArrayList<>();
        try {
            while (resultSet.next()) {
                deliveries.add(getDelivery(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in parseAllDelivery() ", e);
        }
        return deliveries;
    }

    private Delivery getDelivery(ResultSet resultSet) {
        try {
            int deliveryId = resultSet.getInt("DELIVERY_ID");
            int userId = resultSet.getInt("USER_ID");
            String username = resultSet.getString("USERNAME");
            String productIds = resultSet.getString("PRODUCT_IDS");

            List<Integer> productIdsList = new ArrayList<>();
            System.out.println(productIds);
            String[] numbers = productIds.split(" ");
            System.out.println(Arrays.toString(numbers));
            for (String str : numbers) {
                productIdsList.add(Integer.valueOf(str));
            }

            String address = resultSet.getString("DELIVERY_ADDRESS");
            int phoneNumber = resultSet.getInt("DELIVERY_NUMBER_PHONE");
            int totalPrise = resultSet.getInt("TOTAL_PRISE");

            delivery = new Delivery(deliveryId, userId, username, productIdsList, address, phoneNumber, totalPrise);
        } catch (SQLException e) {
            logger.error("SQLException in getDelivery() ", e);
        }
        return delivery;
    }
}
