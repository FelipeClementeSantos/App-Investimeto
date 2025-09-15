package com.example.juroscompostos.service;

import com.example.juroscompostos.model.Investimento;
import com.example.juroscompostos.model.ResultadoJuros;
import com.example.juroscompostos.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
public class JurosService {

    private final InvestimentoRepository repository;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private final NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("pt", "BR"));

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
        double retornoPercentual = (juros / totalInvestido);

        repository.save(investimento);

        return new ResultadoJuros(
                currencyFormat.format(montanteFinal),
                currencyFormat.format(juros),
                percentFormat.format(retornoPercentual),
                currencyFormat.format(totalInvestido)
        );
    }

    // Listar todos os investimentos já salvos
    public List<Investimento> listarInvestimentos() {
        return repository.findAll();
    }
}
