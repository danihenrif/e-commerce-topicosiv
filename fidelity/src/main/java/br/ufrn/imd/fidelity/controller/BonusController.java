package br.ufrn.imd.fidelity.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bonus")


public class BonusController {
    @PostMapping("/{id}/{bonus}")
    public String bonus(@PathVariable int id, @PathVariable int bonus) {
        return "seu bonus é nenhum :)";
    }
}
