package org.lab2.service;

import org.lab2.model.Category;

import java.util.List;

public interface UserServiceCategory {

    void addCategory(String name);

    void removeCategory(int categoryId);

    List<Category> getAllCategory();
}
