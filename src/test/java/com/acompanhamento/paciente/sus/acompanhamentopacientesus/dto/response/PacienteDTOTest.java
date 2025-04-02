package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDTOTest {

    @Test
    void deveCriarPacienteDTOComValoresValidos() {
        // Arrange
        long idPaciente = 1L;
        String nome = "Carlos Souza";
        String cpf = "12345678901";
        String endereco = "Rua A, 123";
        LocalDateTime dataNascimento = LocalDateTime.of(1995, 4, 15, 0, 0);
        LocalDateTime dataCadastro = LocalDateTime.of(2024, 3, 10, 14, 30);
        LocalDateTime dataAtualizacao = LocalDateTime.of(2024, 3, 15, 16, 45);

        // Act
        PacienteDTO pacienteDTO = new PacienteDTO(idPaciente, nome, cpf, endereco, dataNascimento, dataCadastro, dataAtualizacao);

        // Assert
        assertNotNull(pacienteDTO);
        assertEquals(idPaciente, pacienteDTO.idPaciente());
        assertEquals(nome, pacienteDTO.nome());
        assertEquals(cpf, pacienteDTO.cpf());
        assertEquals(endereco, pacienteDTO.endereco());
        assertEquals(dataNascimento, pacienteDTO.dataNascimento());
        assertEquals(dataCadastro, pacienteDTO.dataCadastro());
        assertEquals(dataAtualizacao, pacienteDTO.dataAtualizacao());
    }

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

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            // Tentativa de modificar um record (imutável)
            pacienteDTO.getClass().getDeclaredMethod("setNome", String.class)
                    .invoke(pacienteDTO, "Novo Nome");
        });
    }
}
