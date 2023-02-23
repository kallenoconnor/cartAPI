package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> itemsInCart;
    private BigDecimal subtotal;
    private BigDecimal tax;

    private BigDecimal total;


    public ShoppingCart(List<CartItem> itemsInCart, BigDecimal subtotal, BigDecimal tax, BigDecimal total) {
        this.itemsInCart = itemsInCart;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
    }

    public ShoppingCart() {
    }

    public List<CartItem> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<CartItem> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
