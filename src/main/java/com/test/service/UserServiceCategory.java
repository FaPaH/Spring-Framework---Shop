package com.test.service;

import com.test.model.Category;

import java.util.List;

public interface UserServiceCategory {

    void addCategory(String name);

    void updateCategory(int categoryId, String categoryName);

    void removeCategory(int categoryId);

    List<Category> getAllCategory();
}
