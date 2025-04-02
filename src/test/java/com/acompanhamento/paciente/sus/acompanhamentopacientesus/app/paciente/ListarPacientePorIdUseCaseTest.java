package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarPacientePorIdUseCaseTest {

    @Mock
    private IPacienteGateway pacienteGateway;

    @InjectMocks
    private ListarPacientePorIdUseCase listarPacientePorIdUseCase;

    @Test
    void deveRetornarPacienteQuandoEncontrado() {
        // Arrange
        long pacienteId = 1L;
        PacienteDTO pacienteDTO = new PacienteDTO(
                pacienteId,
                "João Silva",
                "123.456.789-00",
                "Rua das Flores, 123",
                LocalDateTime.of(1990, 5, 20, 0, 0),
                LocalDateTime.now().minusMonths(6),
                LocalDateTime.now()
        );

        when(pacienteGateway.listarPacientePorId(pacienteId)).thenReturn(pacienteDTO);

        // Act
        PacienteDTO resultado = listarPacientePorIdUseCase.listarPacientePorId(pacienteId);

        // Assert
        assertNotNull(resultado);
        assertEquals(pacienteDTO.idPaciente(), resultado.idPaciente());
        assertEquals(pacienteDTO.nome(), resultado.nome());
        assertEquals(pacienteDTO.cpf(), resultado.cpf());
        assertEquals(pacienteDTO.endereco(), resultado.endereco());
        assertEquals(pacienteDTO.dataNascimento(), resultado.dataNascimento());
        assertEquals(pacienteDTO.dataCadastro(), resultado.dataCadastro());

        // Verifica se o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).listarPacientePorId(pacienteId);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        // Arrange
        long pacienteId = 2L;
        when(pacienteGateway.listarPacientePorId(pacienteId))
                .thenThrow(new EntityNotFoundException("Paciente não encontrado"));

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                listarPacientePorIdUseCase.listarPacientePorId(pacienteId)
        );

        assertEquals("Paciente não encontrado", exception.getMessage());

        // Verifica que o método foi chamado exatamente uma vez antes da exceção
        verify(pacienteGateway, times(1)).listarPacientePorId(pacienteId);
    }
}
