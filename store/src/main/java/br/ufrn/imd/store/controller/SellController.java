package br.ufrn.imd.store.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellController {
	
	@PostMapping("/{id}")
    public UUID getProduct(@PathVariable int id){
		UUID sellId = UUID.randomUUID();
        return sellId;
    }
}
