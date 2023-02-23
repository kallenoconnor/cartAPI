package com.techelevator.dao;

import com.techelevator.model.Wishlist_item;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface WishlistItemDao {


    List<Wishlist_item> getWishlistItems(int wishlistID);
}
