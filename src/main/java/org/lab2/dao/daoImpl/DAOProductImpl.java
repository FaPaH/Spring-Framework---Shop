package org.lab2.dao.daoImpl;

import org.lab2.dao.DAOConnection;
import org.lab2.dao.DAOProduct;
import org.lab2.model.Products;
import org.lab2.model.dbParsers.ProductParser;
import org.apache.log4j.Logger;
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
public class DAOProductImpl implements DAOProduct {

    private static Logger logger = Logger.getLogger(DAOConnectionImpl.class);

    private DAOConnection daoConnection;

    private List<Products> productsList = new ArrayList<>();
    private ResultSet resultSet;
    private PreparedStatement statement;

    private ProductParser productParser;

    @Autowired
    public void setProductParse(ProductParser productParser) {
        this.productParser = productParser;
    }

    @Autowired
    public void setDaoConnection(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }

    @Override
    public void addProduct(String name, int prise, int productCategoryId) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("INSERT INTO LAB2_PRODUCTS(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRISE, CATEGORY_ID) " +
                        "VALUES (LAB2_PRODUCTS_SEQ.nextval, ?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, prise);
            statement.setInt(3, productCategoryId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in addProduct() ", e);
        } finally {
            close();
        }
    }

    @Override
    public void updateProduct(int productId, String name, int prise, int categoryId) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("UPDATE LAB2_PRODUCTS " +
                    "SET PRODUCT_NAME = ?, PRODUCT_PRISE = ?, CATEGORY_ID = ? " +
                    "WHERE PRODUCT_ID = ?");
            statement.setString(1, name);
            statement.setInt(2, prise);
            statement.setInt(3, categoryId);
            statement.setInt(4, productId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in updateProduct() ", e);
        } finally {
            close();
        }
    }

    @Override
    public void removeProduct(int productId) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("DELETE FROM LAB2_PRODUCTS WHERE PRODUCT_ID = ?");
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in removeProduct() ", e);
        } finally {
            close();
        }
    }

    @Override
    public List<Products> getAllProducts() {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRISE, C.CATEGORY_NAME " +
                    "FROM LAB2_PRODUCTS P " +
                    "LEFT OUTER JOIN LAB2_CATEGORY C " +
                    "ON P.CATEGORY_ID = C.CATEGORY_ID " +
                    "ORDER BY P.PRODUCT_ID ASC");
            resultSet = statement.executeQuery();
            productsList = productParser.parseAllProducts(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in getAllProducts() ", e);
        } finally {
            close();
        }
        return productsList;
    }

    @Override
    public Products findProductById(int productId) {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRISE, C.CATEGORY_NAME " +
                    "FROM LAB2_PRODUCTS P " +
                    "LEFT OUTER JOIN LAB2_CATEGORY C " +
                    "ON P.CATEGORY_ID = C.CATEGORY_ID " +
                    "WHERE P.PRODUCT_ID = ?");
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
            productsList = productParser.parseAllProducts(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in findProductById() ", e);
        } finally {
            close();
        }
        return productsList.get(0);
    }

    @Override
    public List<Products> findProductByName(String searchName){
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRISE, C.CATEGORY_NAME " +
                    "FROM LAB2_PRODUCTS P " +
                    "LEFT OUTER JOIN LAB2_CATEGORY C " +
                    "ON P.CATEGORY_ID = C.CATEGORY_ID " +
                    "WHERE P.PRODUCT_NAME LIKE ? " +
                    "ORDER BY P.PRODUCT_ID ASC");
            statement.setString(1, "%" + searchName + "%");
            resultSet = statement.executeQuery();
            productsList = productParser.parseAllProducts(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in getAllProducts() ", e);
        } finally {
            close();
        }
        return productsList;
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
