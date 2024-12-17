package br.ufrn.imd.fidelity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bonus")


public class BonusController {
    @PostMapping()
    public String bonus() {
        return "seu bonus é nenhum :)";
    }
}
