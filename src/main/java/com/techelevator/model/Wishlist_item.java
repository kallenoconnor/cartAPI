package com.techelevator.model;

public class Wishlist_item {
    private int wishlist_item_id;

    private int wishlist_id;

    private int product_id;

    public Wishlist_item(int wishlist_item_id, int wishlist_id, int product_id) {
        this.wishlist_item_id = wishlist_item_id;
        this.wishlist_id = wishlist_id;
        this.product_id = product_id;
    }

    public Wishlist_item() {
    }

    public int getWishlist_item_id() {
        return wishlist_item_id;
    }

    public void setWishlist_item_id(int wishlist_item_id) {
        this.wishlist_item_id = wishlist_item_id;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
