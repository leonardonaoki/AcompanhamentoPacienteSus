package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto;

import java.time.LocalDateTime;

public record PacienteDTO(
        long idPaciente,
        String nome,
        String cpf,
        String endereco,
        LocalDateTime dataNascimento,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}
