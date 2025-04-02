package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PacienteDTOTest {

    @Test
    void deveManterImutabilidadeDoPacienteDTO() {
        // Arrange
        PacienteDTO pacienteDTO = new PacienteDTO(
                1L,
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0),
                LocalDateTime.of(2024, 3, 10, 14, 30),
                LocalDateTime.of(2024, 3, 15, 16, 45)
        );

        // Act
        PacienteDTO novoPacienteDTO = new PacienteDTO(
                pacienteDTO.idPaciente(),
                "Novo Nome", // Tentando alterar o nome
                pacienteDTO.cpf(),
                pacienteDTO.endereco(),
                pacienteDTO.dataNascimento(),
                pacienteDTO.dataCadastro(),
                pacienteDTO.dataAtualizacao()
        );

        // Assert
        assertNotEquals(pacienteDTO, novoPacienteDTO); // Confirma que um novo objeto foi criado
        assertEquals("Carlos Souza", pacienteDTO.nome()); // O objeto original permanece inalterado
    }
}
