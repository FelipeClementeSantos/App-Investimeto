package com.example.juroscompostos.service;

import com.example.juroscompostos.model.Investimento;
import com.example.juroscompostos.model.ResultadoJuros;
import com.example.juroscompostos.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurosService {

    private final InvestimentoRepository repository;

    public JurosService(InvestimentoRepository repository) {
        this.repository = repository;
    }

    // Fórmula: M = C * (1 + i)^t
    public ResultadoJuros calcular(Investimento investimento) {
        double montante = investimento.getCapitalInicial() *
                Math.pow(1 + investimento.getTaxaJuros(), investimento.getTempo());

        double juros = montante - investimento.getCapitalInicial();

        // salvar no banco
        repository.save(investimento);

        return new ResultadoJuros(montante, juros);
    }

    // Listar todos os investimentos já salvos
    public List<Investimento> listarInvestimentos() {
        return repository.findAll();
    }
}
