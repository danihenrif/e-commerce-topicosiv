package br.ufrn.imd.fidelity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bonus")


public class BonusController {
    @PostMapping()
    public ResponseEntity bonus() throws InterruptedException {
    	
    	if (Math.random() < 0.1) {
			Thread.sleep(30000);
        }
    	
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
