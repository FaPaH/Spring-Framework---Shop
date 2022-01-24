package org.lab2.dao;

import org.lab2.model.Products;

import java.util.List;

public interface DAOProduct {

    void addProduct(String name, int prise, int productCategoryId);

    void updateProduct(int productId, String name, int prise, int categoryId);

    void removeProduct(int productId);

    List<Products> getAllProducts();

    Products findProductById(int productId);
}
