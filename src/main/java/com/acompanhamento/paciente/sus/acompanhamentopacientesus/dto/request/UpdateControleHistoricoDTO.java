package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateControleHistoricoDTO (
        @NotBlank
        @NotNull
        StatusHistoricoPaciente novoStatus
) {
}
