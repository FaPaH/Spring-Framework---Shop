package org.lab2.services;

import org.lab2.model.Category;

import java.util.List;

public interface UserServiceCategory {

    void addCategory(String name);

    void updateCategory(int categoryId, String categoryName);

    void removeCategory(int categoryId);

    List<Category> getAllCategory();
}
