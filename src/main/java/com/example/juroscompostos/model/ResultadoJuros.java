package com.example.juroscompostos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultadoJuros {
    private String montanteFinal;
    private String juros;
    private String retornoPercentual;
    private String totalInvestido;
}
