package br.com.awards.goldenraspberryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.awards.goldenraspberryapi.dto.ResultadoIntervaloDTO;
import br.com.awards.goldenraspberryapi.service.CalculoService;

@RestController
@RequestMapping("/api/premios")
public class PremioController {

    private final CalculoService calculoService;

    public PremioController(CalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @GetMapping("/intervalos")
    public ResultadoIntervaloDTO obterIntervalos() {
        return calculoService.calcularIntervalos();
    }
}