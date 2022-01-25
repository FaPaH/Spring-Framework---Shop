package org.lab2.dao.daoImpl;

import org.apache.log4j.Logger;
import org.lab2.dao.DAOConnection;
import org.lab2.dao.DAODelivery;
import org.lab2.model.Delivery;
import org.lab2.model.Products;
import org.lab2.model.dbParsers.DeliveryParser;
import org.lab2.model.dbParsers.ProductParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "singleton")
public class DAODeliveryImpl implements DAODelivery {

    private static Logger logger = Logger.getLogger(DAODeliveryImpl.class);

    private DAOConnection daoConnection;

    private List<Delivery> deliveryList = new ArrayList<>();
    private ResultSet resultSet;
    private PreparedStatement statement;

    private DeliveryParser deliveryParser;

    @Autowired
    public void setDeliveryParser(DeliveryParser deliveryParser) {
        this.deliveryParser = deliveryParser;
    }

    @Autowired
    public void setDaoConnection(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }


    @Override
    public void addDelivery(int userId, String username, List<Integer> productIdsList, String deliveryAddress, int numberPhone, int totalPrise) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("INSERT INTO LAB2_DELIVERY(DELIVERY_ID, USER_ID, USERNAME, " +
                    "PRODUCT_IDS, DELIVERY_ADDRESS, DELIVERY_NUMBER_PHONE, TOTAL_PRISE) " +
                    "VALUES (LAB2_DELIVERY_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, userId);
            statement.setString(2, username);

            System.out.println("checkedList before parse in string " + productIdsList);

            StringBuilder productIds = new StringBuilder();
            for (Integer integer : productIdsList) {
                if (productIds.toString().equals("")) {
                    productIds.append(integer);
                } else {
                    productIds.append(" ").append(integer);
                }
            }

            System.out.println("checkedList after parse in string " + productIds);

            statement.setString(3, String.valueOf(productIds.toString()));
            statement.setString(4, deliveryAddress);
            statement.setInt(5, numberPhone);
            statement.setInt(6, totalPrise);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in addDelivery() ", e);
        } finally {
            close();
        }
    }

    @Override
    public void removeDelivery(int deliveryId) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("DELETE FROM LAB2_DELIVERY WHERE DELIVERY_ID = ?");
            statement.setInt(1, deliveryId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in addDelivery() ", e);
        } finally {
            close();
        }
    }

    @Override
    public List<Delivery> getAllDelivery() {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT D.DELIVERY_ID, D.USER_ID, D.USERNAME, " +
                    "D.PRODUCT_IDS, D.DELIVERY_ADDRESS, D.DELIVERY_NUMBER_PHONE, D.TOTAL_PRISE  " +
                    "FROM LAB2_DELIVERY D " +
                    "ORDER BY D.DELIVERY_ID ASC");
            resultSet = statement.executeQuery();
            deliveryList = deliveryParser.parseAllDelivery(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in getAllProducts() ", e);
        } finally {
            close();
        }
        return deliveryList;
    }

    @Override
    public List<Delivery> findDeliveryByUserId(int userId) {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT D.DELIVERY_ID, D.USER_ID, D.USERNAME, " +
                    "D.PRODUCT_IDS, D.DELIVERY_ADDRESS, D.DELIVERY_NUMBER_PHONE, D.TOTAL_PRISE  " +
                    "FROM LAB2_DELIVERY D " +
                    "WHERE D.USER_ID = ? " +
                    "ORDER BY D.DELIVERY_ID ASC");
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            deliveryList = deliveryParser.parseAllDelivery(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in getAllProducts() ", e);
        } finally {
            close();
        }
        return deliveryList;
    }

    private void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            logger.error("error in close() ", e);
        }
    }
}
