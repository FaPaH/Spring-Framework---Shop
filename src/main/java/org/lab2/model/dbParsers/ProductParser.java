package org.lab2.model.dbParsers;

import org.lab2.model.Products;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductParser {

    private static Logger logger = Logger.getLogger(ProductParser.class);

    private Products products;

    public List<Products> parseAllProducts(ResultSet resultSet) {
        List<Products> personals = new ArrayList<>();
        try {
            while (resultSet.next()) {
                personals.add(getProducts(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in parseAllProducts() ", e);
        }
        return personals;
    }

    private Products getProducts(ResultSet resultSet) {
        try {
            int productId = resultSet.getInt("PRODUCT_ID");
            String productName = resultSet.getString("PRODUCT_NAME");
            int productPrise = resultSet.getInt("PRODUCT_PRISE");
            String productCategory = resultSet.getString("CATEGORY_NAME");

            products = new Products(productId, productName, productPrise, productCategory);
        } catch (SQLException e) {
            logger.error("SQLException in getProducts() ", e);
        }
        return products;
    }
}
