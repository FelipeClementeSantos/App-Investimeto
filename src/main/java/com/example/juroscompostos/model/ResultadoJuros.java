package com.example.juroscompostos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultadoJuros {
    private double montanteFinal;   // valor final acumulado
    private double juros;           // ganho em reais
    private double retornoPercentual; // ganho em %
    private double totalInvestido;  // total aplicado (capital + aportes)
}
