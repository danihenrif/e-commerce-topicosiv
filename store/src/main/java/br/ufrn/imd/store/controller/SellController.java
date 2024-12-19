package br.ufrn.imd.store.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.store.model.SellDTO;

@RestController
@RequestMapping("/sell")
public class SellController {
	
	@PostMapping()
    public ResponseEntity<UUID> sellProduct(@RequestBody SellDTO sellDTO) throws InterruptedException {
		
		if (Math.random() < 0.99) {
			Thread.sleep(5000);
        	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
        UUID sellId = UUID.randomUUID();
        return ResponseEntity.ok(sellId);
    }
}
