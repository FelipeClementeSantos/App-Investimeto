package com.example.juroscompostos.controller;

import com.example.juroscompostos.model.Investimento;
import com.example.juroscompostos.model.ResultadoJuros;
import com.example.juroscompostos.service.JurosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juros")
public class JurosController {

    private final JurosService service;

    public JurosController(JurosService service) {
        this.service = service;
    }

    @PostMapping("/calcular")
    public ResultadoJuros calcular(@RequestBody Investimento investimento) {
        return service.calcular(investimento);
    }

    // GET para listar todos os investimentos cadastrados
    @GetMapping("/investimentos")
    public List<Investimento> listarTodos() {
        return service.listarInvestimentos();
    }
}
