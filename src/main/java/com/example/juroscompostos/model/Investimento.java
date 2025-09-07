package com.example.juroscompostos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double capitalInicial;
    private Double taxaJuros; //em decimal (ex: 0.05 para 5%)
    private Integer tempo; //número de períodos (meses ou anos)]

    @Enumerated(EnumType.STRING)
    private PeriodoTempo unidadeTempo;
}
