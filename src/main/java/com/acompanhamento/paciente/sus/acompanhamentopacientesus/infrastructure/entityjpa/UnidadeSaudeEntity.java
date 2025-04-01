package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UnidadeSaudeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeUnidade;
    private String endereco;
    private String tipoUnidade; // Ex: Hospital, Posto de Saúde, Clínica, etc.
    private String telefone;
    private LocalTime horaAbertura; // Exemplo: Hora de abertura da unidade
    private LocalTime horaFechamento; // Exemplo: Hora de fechamento da unidade

    // Construtores adicionais, caso necessário
    public UnidadeSaudeEntity(String nomeUnidade, String endereco, String tipoUnidade, String telefone, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.nomeUnidade = nomeUnidade;
        this.endereco = endereco;
        this.tipoUnidade = tipoUnidade;
        this.telefone = telefone;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }
}
