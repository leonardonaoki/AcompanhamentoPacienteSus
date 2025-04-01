package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * DTO para inserção de uma nova unidade de saúde.
 * <p>
 * Esta classe é utilizada para transferir os dados necessários para a criação de uma nova unidade de saúde.
 * </p>
 *
 * @param nomeUnidade Nome da unidade de saúde.
 * @param endereco Endereço da unidade de saúde.
 * @param tipoUnidade Tipo da unidade de saúde (ex: Hospital, Posto de Saúde, Clínica).
 * @param telefone Telefone de contato da unidade de saúde.
 * @param horaAbertura Hora de abertura da unidade de saúde.
 * @param horaFechamento Hora de fechamento da unidade de saúde.
 */
public record InsertUnidadeSaudeDTO(
        @NotBlank(message = "O nome da unidade é obrigatório.")
        String nomeUnidade,

        @NotBlank(message = "O endereço da unidade é obrigatório.")
        String endereco,

        @NotBlank(message = "O tipo de unidade é obrigatório.")
        String tipoUnidade, // Ex: Hospital, Posto de Saúde, Clínica

        @NotBlank(message = "O telefone da unidade é obrigatório.")
        String telefone,

        @NotNull(message = "A hora de abertura é obrigatória.")
        LocalTime horaAbertura,

        @NotNull(message = "A hora de fechamento é obrigatória.")
        LocalTime horaFechamento
) {
}