package com.techelevator.model;

public class Wishlist {
    private int wishlist_id;

    private int user_id;

    private String name;

    public Wishlist() {
    }

    public Wishlist(int wishlist_id, int user_id, String name) {
        this.wishlist_id = wishlist_id;
        this.user_id = user_id;
        this.name = name;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
