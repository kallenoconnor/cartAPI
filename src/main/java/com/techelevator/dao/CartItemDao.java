package com.techelevator.dao;

import com.techelevator.model.CartItem;
import com.techelevator.model.Product;

import java.util.List;

public interface CartItemDao {

    List<CartItem> viewCart(int UserID);

    void addProduct(CartItem cartItem, int userID);

    void updateProduct(CartItem cartItem, int userID);

    void removeProduct(int cartItemID, int UserID);

    void removeAllProducts(int UserID);

    CartItem getItemByID(int CartItemID, int UserID);


}
