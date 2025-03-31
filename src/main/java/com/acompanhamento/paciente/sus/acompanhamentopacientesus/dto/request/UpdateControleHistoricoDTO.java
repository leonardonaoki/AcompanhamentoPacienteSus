package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateControleHistoricoDTO (
        @NotBlank
        String novoStatus
) {
}
