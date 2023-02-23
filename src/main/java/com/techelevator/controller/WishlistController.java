package com.techelevator.controller;

import com.techelevator.Services.WishlistService;
import com.techelevator.model.Wishlist;
import com.techelevator.model.Wishlist_item;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/wishlists")
public class WishlistController {

    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Wishlist> getAllWishlists(Principal principal){
    return wishlistService.getAllWishlists(principal);
    }

    @RequestMapping(path = "/{wishlistId}", method = RequestMethod.GET)
    public List<Wishlist_item> getSingleWishlist(@PathVariable int wishlistId) {
        if (wishlistService.getSingleWishlist(wishlistId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return wishlistService.getSingleWishlist(wishlistId);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Wishlist createWishlist(@RequestBody Wishlist wishlist, Principal principal) {
       return wishlistService.createWishlist(wishlist,principal);
    }

    @RequestMapping(path = "/{wishlistId}", method = RequestMethod.DELETE)
    public String deleteWishlist(@PathVariable int wishlistId, Principal principal) {
        return wishlistService.deleteWishlist(wishlistId,principal);
    }

    @RequestMapping(path = "/{wishlistId}/products/{productId}", method = RequestMethod.POST)
    public void addProductToWishlist(@PathVariable int wishlistId, @PathVariable int productId) {
        wishlistService.addProductToWishlist(wishlistId,productId);
    }

    @RequestMapping(path = "/{wishlistId}/products/{productId}", method = RequestMethod.DELETE)
    public void removeProductFromWishList(@PathVariable int wishlistId, @PathVariable int productId) {
        wishlistService.removeProductFromWishlist(wishlistId,productId);
    }







}
