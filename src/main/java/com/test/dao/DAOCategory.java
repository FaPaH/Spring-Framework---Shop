package com.test.dao;

import com.test.model.Category;

import java.util.List;

public interface DAOCategory {

    void addCategory(String name);

    void removeCategory(int categoryId);

    List<Category> getAllCategory();
}
