package br.ufrn.imd.e_commerce.controller;

import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.model.Product;
import br.ufrn.imd.e_commerce.service.BuyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class BuyController {

	private final BuyService service;

	public BuyController(BuyService service) {
		this.service = service;
	}
	
    @PostMapping
    public Product buyProduct(@RequestBody BuyDTO buyDTO) {
    	Product product = service.buy(buyDTO);
        return product;
    }

    @GetMapping
    public String get(){
        return "peguei";
    }
}

