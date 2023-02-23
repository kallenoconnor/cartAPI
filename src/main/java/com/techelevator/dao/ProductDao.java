package com.techelevator.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> displayAll();

    List<Product> searchProducts(String searchTerm);

    Product getProductByID(int product_id);

}
