package br.ufrn.imd.e_commerce.controller;

import br.ufrn.imd.e_commerce.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class BuyController {

    @PostMapping
    public Product buyProduct(@RequestBody Product product) {
        return product;
    }

    @GetMapping
    public String get(){
        return "peguei";
    }
}
