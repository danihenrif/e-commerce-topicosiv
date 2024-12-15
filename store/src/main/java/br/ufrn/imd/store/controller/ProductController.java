package br.ufrn.imd.store.controller;

import br.ufrn.imd.store.model.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        // TODO: recuperar um produto real
        Product product = new Product();
        product.setId(id);
        product.setName("Produto Exemplo");
        product.setValue(99.99);

        return product;
    }
}
