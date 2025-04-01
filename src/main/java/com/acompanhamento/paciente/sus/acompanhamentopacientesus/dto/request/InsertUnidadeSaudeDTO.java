package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

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
