package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InsertProntuarioDTO(
        @NotBlank
        @NotNull
        String especialidadeMedico,
        LocalDateTime dataValidade,
        String solicitacao,
        @NotBlank
        @NotNull
        String statusSolicitacaoProntuario
) {
}
