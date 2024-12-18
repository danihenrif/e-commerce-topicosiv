package br.ufrn.imd.store.controller;

import br.ufrn.imd.store.model.Product;
import br.ufrn.imd.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        if (Math.random() < 0.2) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
