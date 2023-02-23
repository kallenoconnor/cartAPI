package com.techelevator.dao;

import com.techelevator.model.Wishlist;

import java.util.List;

public interface WishlistDao {
    List<Wishlist> getAllWishlists(int userID);

    Wishlist getSingleWishlist(int wishlistID, int userID);

    Wishlist createWishlist(Wishlist wishlist, int userID);

    String deleteWishlist(int wishlistID, int userID);

    void addProductToWishlist(int wishListID, int productID);

    void removeProductFromWishlist(int wishListID, int productID);

}
