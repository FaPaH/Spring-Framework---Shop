package org.lab2.dao;

import org.lab2.model.Category;

import java.util.List;

public interface DAOCategory {

    void addCategory(String name);

    void removeCategory(int categoryId);

    List<Category> getAllCategory();
}
