package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateControleHistoricoDTO (
        @NotBlank
        @NotNull
        String novoStatus
) {
}
