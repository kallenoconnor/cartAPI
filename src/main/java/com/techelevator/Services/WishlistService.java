package com.techelevator.Services;

import com.techelevator.dao.ProductDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.WishlistDao;
import com.techelevator.dao.WishlistItemDao;
import com.techelevator.model.Wishlist;
import com.techelevator.model.Wishlist_item;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class WishlistService {
    private WishlistDao wishlistDao;
    private WishlistItemDao wishlistItemDao;

    private ProductDao productDao;

    private UserDao userDao;

    public WishlistService(WishlistDao wishlistDao, WishlistItemDao wishlistItemDao, UserDao userDao, ProductDao productDao) {
        this.wishlistDao = wishlistDao;
        this.wishlistItemDao = wishlistItemDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public List<Wishlist> getAllWishlists(Principal principal) {
        int userID = getUserID(principal);
        return wishlistDao.getAllWishlists(userID);

    }

    public List<Wishlist_item> getSingleWishlist(int wishlistID) {
        return wishlistItemDao.getWishlistItems(wishlistID);

    }

    public Wishlist createWishlist(Wishlist wishlist, Principal principal) {
        int userID = getUserID(principal);
        wishlist.setUser_id(userID);
        return wishlistDao.createWishlist(wishlist,userID);

    }

    public String deleteWishlist(int wishListID, Principal principal) {
        int userID = getUserID(principal);
        return wishlistDao.deleteWishlist(wishListID,userID);

    }


    public void addProductToWishlist(int wishListID, int productID) {
        List<Wishlist_item> items = wishlistItemDao.getWishlistItems(wishListID);
        if (productDao.getProductByID(productID) == null) {
            throw new NullPointerException("Product does not exist");
        } else {
            int toggle = 0;
            for (Wishlist_item entry : items) {
                if (productID == entry.getProduct_id()) {
                    toggle = 1;
                    break;
                }
            }
            if (toggle == 0) {
                wishlistDao.addProductToWishlist(wishListID, productID);
            }
        }

    }

    public void removeProductFromWishlist(int wishlistID, int productID){
         wishlistDao.removeProductFromWishlist(wishlistID,productID);

    }

    //Helper method to extract UserID from Principal
    private int getUserID(Principal principal) {
        String username = principal.getName();
        return userDao.findIdByUsername(username);
    }


}
