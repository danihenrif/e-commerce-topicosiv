package br.ufrn.imd.store.controller;

import br.ufrn.imd.store.model.Product;
import br.ufrn.imd.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        return product;
    }
}
