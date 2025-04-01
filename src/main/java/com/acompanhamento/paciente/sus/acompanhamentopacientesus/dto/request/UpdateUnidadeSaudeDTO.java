package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

/**
 * DTO para atualização de uma unidade de saúde.
 * <p>
 * Esta classe é utilizada para transferir os dados necessários para a atualização de uma unidade de saúde existente.
 * </p>
 *
 * @param nomeUnidade Nome da unidade de saúde.
 * @param endereco Endereço da unidade de saúde.
 * @param tipoUnidade Tipo da unidade de saúde (ex: Hospital, Posto de Saúde, Clínica).
 * @param telefone Telefone de contato da unidade de saúde.
 * @param horaAbertura Hora de abertura da unidade de saúde.
 * @param horaFechamento Hora de fechamento da unidade de saúde.
 */
@Data
public class UpdateUnidadeSaudeDTO {

    /**
     * Nome da unidade de saúde.
     */
    @NotBlank(message = "Nome da unidade não pode ser vazio.")
    private String nomeUnidade;

    /**
     * Endereço da unidade de saúde.
     */
    @NotBlank(message = "Endereço não pode ser vazio.")
    private String endereco;

    /**
     * Tipo da unidade de saúde (ex: Hospital, Posto de Saúde, Clínica).
     */
    @NotBlank(message = "Tipo da unidade não pode ser vazio.")
    private String tipoUnidade;

    /**
     * Telefone de contato da unidade de saúde.
     */
    @NotBlank(message = "Telefone não pode ser vazio.")
    private String telefone;

    /**
     * Hora de abertura da unidade de saúde.
     */
    @NotNull(message = "Hora de abertura não pode ser nula.")
    private LocalTime horaAbertura;

    /**
     * Hora de fechamento da unidade de saúde.
     */
    @NotNull(message = "Hora de fechamento não pode ser nula.")
    private LocalTime horaFechamento;

}