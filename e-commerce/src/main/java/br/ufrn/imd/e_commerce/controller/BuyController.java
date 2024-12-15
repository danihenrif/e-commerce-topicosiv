package br.ufrn.imd.e_commerce.controller;

import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.model.Product;
import br.ufrn.imd.e_commerce.service.BuyService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/buy")
public class BuyController {

	private final BuyService service;

	public BuyController(BuyService service) {
		this.service = service;
	}
	
    @PostMapping
    public Product buyProduct(@RequestBody BuyDTO buyDTO) {
    	service.buy(buyDTO);
        return null;
    }

    @GetMapping
    public String get(){
        return "peguei";
    }
}

