package br.ufrn.imd.e_commerce.controller;

import br.ufrn.imd.e_commerce.model.BuyDTO;
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
    public Double buyProduct(@RequestBody BuyDTO buyDTO) throws Exception {
    	// Product product = service.buy(buyDTO);
        // return product;
        Double value = service.getExchange(buyDTO);
        return value;
    }

    @GetMapping
    public String get(){
        return "peguei";
    }
}

