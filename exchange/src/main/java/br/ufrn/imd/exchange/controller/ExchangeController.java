package br.ufrn.imd.exchange.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @GetMapping
    public String exchange(){
        return "convertido";
    }
}
