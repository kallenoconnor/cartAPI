package com.techelevator.controller;


import com.techelevator.Services.CartService;
import com.techelevator.model.CartItem;
import com.techelevator.model.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping( path = "/cart" )
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ShoppingCart viewCart(Principal principal) {
        return cartService.viewCart(principal);
    }

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public void addProductToCart(@Valid @RequestBody CartItem itemToAdd, Principal principal) {
         cartService.addItemToCart(itemToAdd,principal);
    }

    @RequestMapping(path = "/items/{itemID}", method = RequestMethod.DELETE)
    public void removeItemFromCart(@PathVariable int itemID, Principal principal) {
      if (cartService.viewSingleItem(itemID,principal) == null) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      } else {
          cartService.removeSingleItem(itemID,principal);
      }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void removeAllItemsFromCart(Principal principal){
        cartService.removeAllItemsFromCart(principal);
    }



}
