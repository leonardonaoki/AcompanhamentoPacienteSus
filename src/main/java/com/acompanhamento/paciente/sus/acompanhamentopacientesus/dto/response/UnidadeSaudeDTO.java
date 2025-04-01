package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import java.time.LocalTime;

/**
 * DTO para resposta de informações de uma unidade de saúde.
 * <p>
 * Esta classe é utilizada para transferir os dados de uma unidade de saúde em respostas de API.
 * </p>
 *
 * @param id Identificador único da unidade de saúde.
 * @param nomeUnidade Nome da unidade de saúde.
 * @param endereco Endereço da unidade de saúde.
 * @param tipoUnidade Tipo da unidade de saúde (ex: Hospital, Posto de Saúde, Clínica).
 * @param telefone Telefone de contato da unidade de saúde.
 * @param horaAbertura Hora de abertura da unidade de saúde.
 * @param horaFechamento Hora de fechamento da unidade de saúde.
 */
public record UnidadeSaudeDTO(
        long id,
        String nomeUnidade,
        String endereco,
        String tipoUnidade, // Ex: Hospital, Posto de Saúde, Clínica
        String telefone,
        LocalTime horaAbertura,
        LocalTime horaFechamento
) {}