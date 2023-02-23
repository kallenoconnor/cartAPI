package com.techelevator.controller;


import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping( path = "/products" )
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts(@RequestParam(defaultValue = "") String sku,
                                        @RequestParam(defaultValue = "") String name) {
        if (!sku.equals("")) {
            return productDao.searchProducts(sku);
        } else if (!name.equals("")) {
            return productDao.searchProducts(name);
        }
        return productDao.displayAll();
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) {
        Product product = productDao.getProductByID(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

}
