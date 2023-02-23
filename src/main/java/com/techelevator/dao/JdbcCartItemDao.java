package com.techelevator.dao;

import com.techelevator.Services.TaxAPI;
import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<CartItem> viewCart(int userID) {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT * FROM cart_item WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userID);

        while(results.next()) {
            CartItem cartItem = mapRowToCartItem(results);
            items.add(cartItem);
        }
        return items;

    }

    @Override
    public CartItem getItemByID(int cartItemID, int UserID) {
        String sql = "SELECT * FROM cart_item WHERE cart_item_id = ? AND user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemID, UserID);
        if (results.next()) {
            return mapRowToCartItem(results);
        } else {
            return null;
        }
    }

    @Override
    public void addProduct(CartItem itemToAdd, int userID) {
        CartItem newItem = itemToAdd;
            String addItem = "INSERT INTO cart_item (user_id,product_id,quantity) values (?,?,?) RETURNING cart_item_id;";
            int cart_item_id = jdbcTemplate.queryForObject(addItem, int.class, userID, itemToAdd.getProduct_id(),itemToAdd.getQuantity());
            newItem.setCart_item_id(cart_item_id);
        }

    @Override
    public void updateProduct(CartItem itemToAdd, int userID) {
        String updateItem = "UPDATE cart_item SET user_id = ?, product_id = ?, quantity = ? WHERE cart_item_id = ?;";
        jdbcTemplate.update(updateItem, userID, itemToAdd.getProduct_id(), itemToAdd.getQuantity(), itemToAdd.getCart_item_id());
    }


    @Override
    public void removeProduct(int cartItemID, int userID){
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ? AND user_id = ?;";
        jdbcTemplate.update(sql,cartItemID,userID);
    }


    @Override
    public void removeAllProducts(int UserID){
        String sql = "DELETE FROM cart_item WHERE user_id = ?;";
        jdbcTemplate.update(sql,UserID);

    }


    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem cartItem = new CartItem();
        cartItem.setUser_id(rs.getInt("user_id"));
        cartItem.setProduct_id(rs.getInt("product_id"));
        cartItem.setCart_item_id(rs.getInt("cart_item_id"));
        cartItem.setQuantity(rs.getInt("quantity"));

        return cartItem;
    }







}
