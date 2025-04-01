package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record InsertPacienteDTO(
        @Positive long idPaciente,

        @NotNull @Size(min = 3, max = 100)
        String nome,

        @NotNull @Size(min = 11, max = 11)
        String cpf,

        @Size(max = 255)
        String endereco,

        @NotNull
        LocalDateTime dataNascimento
) {
}
