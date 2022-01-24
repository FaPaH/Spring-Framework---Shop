package org.lab2.model.dbParsers;

import org.lab2.model.Category;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryParser {

    private static Logger logger = Logger.getLogger(CategoryParser.class);

    private Category category;

    public List<Category> parseAllCategory(ResultSet resultSet) {
        List<Category> personals = new ArrayList<>();
        try {
            while (resultSet.next()) {
                personals.add(getCategory(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in parseAllCategory() ", e);
        }
        return personals;
    }

    private Category getCategory(ResultSet resultSet) {
        try {
            int categoryId = resultSet.getInt("CATEGORY_ID");
            String categoryName = resultSet.getString("CATEGORY_NAME");

            category = new Category(categoryId, categoryName);
        } catch (SQLException e) {
            logger.error("SQLException in getCategory() ", e);
        }
        return category;
    }
}
