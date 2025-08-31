package com.example.juroscompostos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoJuros {
    private Double montante;
    private Double juros;
}
