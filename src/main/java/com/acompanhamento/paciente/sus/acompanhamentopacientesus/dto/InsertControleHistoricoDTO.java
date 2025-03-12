package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto;

import jakarta.validation.constraints.Positive;

public record InsertControleHistoricoDTO(
        @Positive long idPaciente,
        @Positive long idUnidade
) {
}
