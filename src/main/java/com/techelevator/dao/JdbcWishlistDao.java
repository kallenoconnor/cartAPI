package com.techelevator.dao;

import com.techelevator.model.Wishlist;
import com.techelevator.model.Wishlist_item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWishlistDao implements WishlistDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWishlistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wishlist> getAllWishlists(int userID) {
        List<Wishlist> allWishLists = new ArrayList<>();
        String sql = "SELECT * FROM wishlist WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userID);

        while(results.next()) {
            Wishlist wishlist = mapRowToWishlist(results);
            allWishLists.add(wishlist);
        }
        return allWishLists;

    }

    @Override
    public Wishlist getSingleWishlist(int wishlistID, int userID){
        String sql = "SELECT * FROM wishlist WHERE wishlist_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,wishlistID,userID);
        if (results.next()) {
            return mapRowToWishlist(results);
        } else {
            return null;
        }
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist, int userID) {
        String sql = "INSERT INTO wishlist (name,user_id) values (?,?) RETURNING wishlist_id;";
        int newWishlistID = jdbcTemplate.queryForObject(sql,int.class,wishlist.getName(),userID);
        wishlist.setWishlist_id(newWishlistID);
        return wishlist;
    }

    @Override
    public String deleteWishlist(int wishlistID, int userID) {
        String deleteFromWishlist_item = "DELETE FROM wishlist_item WHERE wishlist_id = ?;";
        jdbcTemplate.update(deleteFromWishlist_item,wishlistID);
        String sql = "DELETE FROM wishlist WHERE wishlist_id = ? AND user_id = ?;";
        int successful = jdbcTemplate.update(sql, wishlistID, userID);
        if (successful == 1) {
            return "Wishlist successfully deleted";
        } else {
            return "Unable to delete wishlist";
        }

    }

    @Override
    public void addProductToWishlist(int wishListID, int productID) {
        String insert = "INSERT INTO wishlist_item (wishlist_id,product_id) values (?,?) RETURNING wishlist_item_id;";
        Wishlist_item newEntry = new Wishlist_item();
        int id = jdbcTemplate.queryForObject(insert, int.class, wishListID, productID);
        newEntry.setWishlist_id(id);
        newEntry.setWishlist_id(wishListID);
        newEntry.setProduct_id(productID);
    }

    @Override
    public void removeProductFromWishlist(int wishlistID, int productID) {
        String delete = "DELETE FROM wishlist_item WHERE product_id = ?;";
        int successful = jdbcTemplate.update(delete,productID);


    }









    private Wishlist mapRowToWishlist(SqlRowSet rs) {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlist_id(rs.getInt("wishlist_id"));
        wishlist.setName(rs.getString("name"));
        wishlist.setUser_id(rs.getInt("user_id"));
        return wishlist;
    }



}
