package com.techelevator.dao;

import com.techelevator.model.Wishlist_item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWishlistItemDao implements WishlistItemDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcWishlistItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wishlist_item> getWishlistItems(int wishlistID) {
        List<Wishlist_item> itemsOnList = new ArrayList<>();
        String sql = "SELECT * FROM wishlist_item WHERE wishlist_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,wishlistID);
        while (results.next()) {
            Wishlist_item wishlist_item = mapRowToWishlist_item(results);
            itemsOnList.add(wishlist_item);
        }
        return itemsOnList;

    }


    private Wishlist_item mapRowToWishlist_item(SqlRowSet rs){
        Wishlist_item wishlist_item = new Wishlist_item();
        wishlist_item.setWishlist_item_id(rs.getInt("wishlist_item_id"));
        wishlist_item.setWishlist_id(rs.getInt("wishlist_id"));
        wishlist_item.setProduct_id(rs.getInt("product_id"));
        return wishlist_item;
    }
}
