package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class UnidadeSaudeDomain {
    private long id;
    private String nomeUnidade;
    private String endereco;
    private String tipoUnidade; // Ex: Hospital, Posto de Saúde, Clínica
    private String telefone;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;

    // Construtor adicional, caso precise de mais flexibilidade
    public UnidadeSaudeDomain(long id, String nomeUnidade, String endereco, String tipoUnidade, String telefone, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.id = id;
        this.nomeUnidade = nomeUnidade;
        this.endereco = endereco;
        this.tipoUnidade = tipoUnidade;
        this.telefone = telefone;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }
}
