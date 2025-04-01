package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Representa uma entidade de unidade de saúde no sistema.
 * <p>
 * A classe é responsável por armazenar informações sobre as unidades de saúde, como o nome da unidade,
 * endereço, tipo, telefone, horários de funcionamento e outros dados relacionados.
 * </p>
 *
 * <p>
 * Utiliza a anotação {@link Entity} para ser reconhecida como uma entidade JPA e o {@link Id} para indicar
 * o identificador único da unidade.
 * </p>
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
@Getter
@Setter
@Entity
public class UnidadeSaudeEntity {

    /**
     * Identificador único da unidade de saúde.
     * <p>
     * Utiliza a estratégia {@link GenerationType#IDENTITY} para a geração automática do ID.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Nome da unidade de saúde.
     * Exemplo: "Hospital das Clínicas", "Posto de Saúde Central", etc.
     */
    private String nomeUnidade;

    /**
     * Endereço completo da unidade de saúde.
     * Exemplo: "Rua das Flores, 123, Bairro Central, Cidade X."
     */
    private String endereco;

    /**
     * Tipo de unidade de saúde.
     * Exemplo: "Hospital", "Posto de Saúde", "Clínica", etc.
     */
    private String tipoUnidade;

    /**
     * Número de telefone para contato com a unidade de saúde.
     * Exemplo: "(11) 1234-5678"
     */
    private String telefone;

    /**
     * Hora de abertura da unidade de saúde.
     * Exemplo: 08:00.
     */
    private LocalTime horaAbertura;

    /**
     * Hora de fechamento da unidade de saúde.
     * Exemplo: 18:00.
     */
    private LocalTime horaFechamento;

    /**
     * Construtor padrão para a criação da entidade.
     */
    public UnidadeSaudeEntity() {}

    /**
     * Construtor para a criação de uma unidade de saúde com todos os parâmetros necessários.
     *
     * @param nomeUnidade   Nome da unidade de saúde.
     * @param endereco      Endereço completo da unidade.
     * @param tipoUnidade   Tipo da unidade (ex: "Hospital", "Posto de Saúde").
     * @param telefone      Número de telefone para contato.
     * @param horaAbertura  Hora de abertura da unidade.
     * @param horaFechamento Hora de fechamento da unidade.
     */
    public UnidadeSaudeEntity(String nomeUnidade, String endereco, String tipoUnidade, String telefone, LocalTime horaAbertura, LocalTime horaFechamento) {
        this.nomeUnidade = nomeUnidade;
        this.endereco = endereco;
        this.tipoUnidade = tipoUnidade;
        this.telefone = telefone;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }
}
