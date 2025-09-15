package com.example.juroscompostos.service;

import com.example.juroscompostos.model.Investimento;
import com.example.juroscompostos.model.PeriodoTempo;
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

    public ResultadoJuros calcular(Investimento investimento) {
        double capitalInicial = investimento.getCapitalInicial();
        double taxa = investimento.getTaxaJuros();
        int tempo = investimento.getTempo();
        double aporteMensal = investimento.getAporteMensal() != null ? investimento.getAporteMensal() : 0.0;

        // Montante do capital inicial
        double montanteCapital = capitalInicial * Math.pow(1 + taxa, tempo);

        // Montante dos aportes
        double montanteAportes = 0.0;
        if (aporteMensal > 0) {
            montanteAportes = aporteMensal * ((Math.pow(1 + taxa, tempo) - 1) / taxa);
        }

        double montanteFinal = montanteCapital + montanteAportes;
        double totalInvestido = capitalInicial + (aporteMensal * tempo);
        double juros = montanteFinal - totalInvestido;
        double retornoPercentual = (juros / totalInvestido) * 100;

        repository.save(investimento);

        return new ResultadoJuros(montanteFinal, juros, retornoPercentual, totalInvestido);
    }


    // Listar todos os investimentos já salvos
    public List<Investimento> listarInvestimentos() {
        return repository.findAll();
    }
}
