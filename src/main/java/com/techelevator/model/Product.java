package com.techelevator.model;

import java.math.BigDecimal;

public class Product {
    private int product_id;

    private String product_sku;

    private String name;

    private String description;

    private BigDecimal price;

    private String image_name;

    public Product() {
    }

    public Product(int product_id, String product_sku, String name, String description, BigDecimal price, String image_name) {
        this.product_id = product_id;
        this.product_sku = product_sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_name = image_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
