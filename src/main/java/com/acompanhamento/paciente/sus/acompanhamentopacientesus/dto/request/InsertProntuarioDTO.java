package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record InsertProntuarioDTO(
        @NotBlank
        String especialidadeMedico,
        LocalDateTime dataValidade,
        String solicitacao,
        @NotBlank
        String statusSolicitacaoProntuario
) {
}
