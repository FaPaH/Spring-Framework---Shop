package org.lab2.services;

import org.lab2.model.Products;

import java.util.List;

public interface UserServiceProduct {

    void addProduct(String name, int prise, int productCategoryId);

    void updateProduct(int productId, String name, int prise, int categoryId);

    void removeProduct(int productId);

    List<Products> getAllProducts();

    Products findProductById(int productId);
}
