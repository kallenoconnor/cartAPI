package com.techelevator.dao;

import com.techelevator.model.Product;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> displayAll(){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;

    }
    @Override
    public List<Product> searchProducts(String searchTerm) {
        List<Product> products = new ArrayList<>();
        String searchTermWithTags = "%" + searchTerm + "%";
        String sql = "SELECT * FROM product\n" +
                "WHERE name ILIKE ? OR product_sku ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,searchTermWithTags,searchTermWithTags);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductByID(int productID) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productID);
        if (results.next()) {
            return mapRowToProduct(results);
        } else {
            return null;
        }
    }


    private Product mapRowToProduct(SqlRowSet rowSet) {
        Product product = new Product();
        product.setProduct_id(rowSet.getInt("product_id"));
        product.setProduct_sku(rowSet.getString("product_sku"));
        product.setName(rowSet.getString("name"));
        product.setDescription(rowSet.getString("description"));
        product.setPrice(rowSet.getBigDecimal("price"));
        product.setImage_name(rowSet.getString("image_name"));
        return product;
    }




}
