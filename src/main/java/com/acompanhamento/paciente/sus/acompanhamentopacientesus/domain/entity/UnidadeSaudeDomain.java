package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Entidade que representa uma unidade de saúde.
 * <p>
 * Esta classe contém os dados de uma unidade de saúde, incluindo identificador, nome, endereço,
 * tipo de unidade, telefone, hora de abertura e hora de fechamento.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
public class UnidadeSaudeDomain {
    /**
     * Identificador único da unidade de saúde.
     */
    private long id;

    /**
     * Nome da unidade de saúde.
     */
    private String nomeUnidade;

    /**
     * Endereço da unidade de saúde.
     */
    private String endereco;

    /**
     * Tipo da unidade de saúde (ex: Hospital, Posto de Saúde, Clínica).
     */
    private String tipoUnidade;

    /**
     * Telefone de contato da unidade de saúde.
     */
    private String telefone;

    /**
     * Hora de abertura da unidade de saúde.
     */
    private LocalTime horaAbertura;

    /**
     * Hora de fechamento da unidade de saúde.
     */
    private LocalTime horaFechamento;

    /**
     * Construtor adicional para maior flexibilidade na criação de objetos.
     *
     * @param id Identificador único da unidade de saúde.
     * @param nomeUnidade Nome da unidade de saúde.
     * @param endereco Endereço da unidade de saúde.
     * @param tipoUnidade Tipo da unidade de saúde.
     * @param telefone Telefone de contato da unidade de saúde.
     * @param horaAbertura Hora de abertura da unidade de saúde.
     * @param horaFechamento Hora de fechamento da unidade de saúde.
     */
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