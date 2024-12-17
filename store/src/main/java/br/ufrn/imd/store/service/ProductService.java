package br.ufrn.imd.store.service;

import br.ufrn.imd.store.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final static List<Product> products = new ArrayList<>();

    static {
        for (int i = 0; i <= 100; i++){
            products.add(new Product(
               i,
               "Produto" + i,
               Math.round((Math.random() * 900 + 100) * 100.0)/100
            ));
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: "+ id));
    }
}
