package br.ufrn.imd.e_commerce.controller;

import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.service.BuyService;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class BuyController {

	private final BuyService service;

	public BuyController(BuyService service) {
		this.service = service;
	}
	
    @PostMapping
    public ResponseEntity<UUID> buyProduct(@RequestBody BuyDTO buyDTO) throws Exception {
    	
        return service.buy(buyDTO);
    }

    @GetMapping
    public String get(){
        return "peguei";
    }
}

