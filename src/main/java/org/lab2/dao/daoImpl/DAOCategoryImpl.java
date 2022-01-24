package org.lab2.dao.daoImpl;

import org.apache.log4j.Logger;
import org.lab2.dao.DAOCategory;
import org.lab2.dao.DAOConnection;
import org.lab2.model.Category;
import org.lab2.model.dbParsers.CategoryParser;
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
public class DAOCategoryImpl implements DAOCategory {

    private static Logger logger = Logger.getLogger(DAOCategoryImpl.class);

    private DAOConnection daoConnection;

    private List<Category> categoryList = new ArrayList<>();
    private ResultSet resultSet;
    private PreparedStatement statement;

    private CategoryParser categoryParser;

    @Autowired
    public void setCategoryParser(CategoryParser categoryParser) {
        this.categoryParser = categoryParser;
    }

    @Autowired
    public void setDaoConnection(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }

    @Override
    public void addCategory(String name) {
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("INSERT INTO LAB2_CATEGORY (CATEGORY_ID, CATEGORY_NAME) " +
                    "VALUES (LAB2_CATEGORY_SEQ.nextval, ?)");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in addProduct() ", e);
        } finally {
            close();
        }
    }

    @Override
    public void removeCategory(int categoryId) {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("DELETE FROM LAB2_CATEGORY WHERE CATEGORY_ID = ?");
            statement.setInt(1, categoryId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in getAllCategory() ", e);
        } finally {
            close();
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try (Connection connection = daoConnection.getConnection()) {
            statement = connection.prepareStatement("SELECT * FROM LAB2_CATEGORY");
            resultSet = statement.executeQuery();
            categoryList = categoryParser.parseAllCategory(resultSet);
        } catch (SQLException e) {
            logger.error("SQLException in getAllCategory() ", e);
        } finally {
            close();
        }
        return categoryList;
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
