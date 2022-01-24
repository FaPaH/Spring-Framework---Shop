package org.lab2.model;

public class Products {

    private int productId;
    private String productName;
    private int productPrise;
    private String productCategory;
    private int productCategoryId;

    public Products(int productId, String productName, int productPrise, String productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrise = productPrise;
        this.productCategory = productCategory;
    }

    public Products(int productId, String productName, int productPrise, int productCategoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrise = productPrise;
        this.productCategoryId = productCategoryId;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrise() {
        return productPrise;
    }

    public void setProductPrise(int productPrise) {
        this.productPrise = productPrise;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
