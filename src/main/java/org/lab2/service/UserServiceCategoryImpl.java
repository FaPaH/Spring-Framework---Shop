package org.lab2.service;

import org.lab2.dao.DAOCategory;
import org.lab2.model.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceCategoryImpl implements UserServiceCategory{


    private static Logger logger = Logger.getLogger(UserServiceCategoryImpl.class);

    private DAOCategory daoCategory;

    @Autowired
    public void setDaoCategory(DAOCategory daoCategory) {
        this.daoCategory = daoCategory;
    }

    @Override
    public void addCategory(String name) {
        logger.debug("called addCategory() method. UserServiceCategoryImpl.class");
        daoCategory.addCategory(name);
    }

    @Override
    public void removeCategory(int categoryId) {
        logger.debug("called removeCategory() method. UserServiceCategoryImpl.class");
        daoCategory.removeCategory(categoryId);
    }

    @Override
    public List<Category> getAllCategory() {
        logger.debug("called getAllCategory() method. UserServiceCategoryImpl.class");
        return daoCategory.getAllCategory();
    }
}
