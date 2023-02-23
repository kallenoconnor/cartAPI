package com.techelevator.Services;

import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;

@Component
public class CartService {
    private CartItemDao cartItemDao;
    private ProductDao productDao;
    private UserDao userDao;
    private TaxAPI taxAPI;

    public CartService(UserDao userDao, CartItemDao cartItemDao, ProductDao productDao, TaxAPI taxAPI) {
        this.userDao = userDao;
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
        this.taxAPI = taxAPI;

    }

    public ShoppingCart viewCart(Principal principal) {
        int userID = getUserID(principal);
        ShoppingCart cart = new ShoppingCart();
        List<CartItem> items = cartItemDao.viewCart(userID);
        cart.setItemsInCart(items);

        //calculate subtotal by multiplying price of product times quantity
        BigDecimal subtotal = new BigDecimal(0.0);
        for (CartItem entry : items) {
            Product product = productDao.getProductByID(entry.getProduct_id());
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(entry.getQuantity())));
        }
        cart.setSubtotal(subtotal);

        //get user info to obtain state for tax based on userID
        User user = userDao.getUserById(userID);
        TaxDto taxInfo = taxAPI.getTaxRate(user.getStateCode());
        BigDecimal tax = BigDecimal.valueOf(taxInfo.getSalesTaxRate() / 100).multiply(subtotal);
        cart.setTax(tax.setScale(2,RoundingMode.HALF_EVEN));
        cart.setTotal(subtotal.add(tax).setScale(2,RoundingMode.HALF_EVEN));

        return cart;

    }

    public void addItemToCart(CartItem itemToAdd, Principal principal) {
        int userID = getUserID(principal);
        itemToAdd.setUser_id(userID);
        List<CartItem> itemsInCart = cartItemDao.viewCart(userID);

        int toggle = 0;
        for (CartItem entry : itemsInCart) {
            if (itemToAdd.getProduct_id() == entry.getProduct_id()) {
                itemToAdd.setQuantity(itemToAdd.getQuantity() + entry.getQuantity());
                itemToAdd.setCart_item_id(entry.getCart_item_id());
                cartItemDao.updateProduct(itemToAdd, userID);
                toggle = 1;
                break;
            }
        }
        if (toggle == 0) {
            cartItemDao.addProduct(itemToAdd, userID);
        }
    }


    public CartItem viewSingleItem(int cartItemID, Principal principal) {
        int userID = getUserID(principal);
        return cartItemDao.getItemByID(cartItemID, userID);
    }

    public void removeSingleItem(int cartItemID, Principal principal) {
        int userID = getUserID(principal);
        cartItemDao.removeProduct(cartItemID, userID);
    }

    public void removeAllItemsFromCart(Principal principal) {
        int userID = getUserID(principal);
        cartItemDao.removeAllProducts(userID);
    }


    //Helper method to extract UserID from Principal
    private int getUserID(Principal principal) {
        String username = principal.getName();
        int userID = userDao.findIdByUsername(username);
        return userID;
    }

}