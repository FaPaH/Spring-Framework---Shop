package org.lab2.service;

import org.lab2.dao.DAOProduct;
import org.lab2.model.Products;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceProductImpl implements UserServiceProduct{

    private static Logger logger = Logger.getLogger(UserServiceProductImpl.class);

    private DAOProduct daoProduct;

    @Autowired
    public void setDaoProduct(DAOProduct daoProduct) {
        this.daoProduct = daoProduct;
    }

    @Override
    public void addProduct(String name, int prise, int productCategoryId) {
        logger.debug("called addProduct() method. UserServiceImpl.class");
        daoProduct.addProduct(name, prise, productCategoryId);
    }

    @Override
    public void updateProduct(int productId, String name, int prise, int categoryId) {
        logger.debug("called updateProduct() method. UserServiceImpl.class");
        daoProduct.updateProduct(productId, name, prise, categoryId);
    }

    @Override
    public void removeProduct(int productId) {
        logger.debug("called removeProduct() method. UserServiceImpl.class");
        daoProduct.removeProduct(productId);
    }

    @Override
    public List<Products> getAllProducts() {
        logger.debug("called getAllProducts() method. UserServiceImpl.class");
        return daoProduct.getAllProducts();
    }

    @Override
    public Products findProductById(int productId) {
        logger.debug("called findProductById() method. UserServiceImpl.class");
        return daoProduct.findProductById(productId);
    }
}
