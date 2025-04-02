package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarTodosPacientesUseCaseTest {

    @Mock
    private IPacienteGateway pacienteGateway;

    @InjectMocks
    private ListarTodosPacientesUseCase listarTodosPacientesUseCase;

    @Test
    void deveRetornarListaDePacientesQuandoExistiremPacientes() {
        // Arrange
        List<PacienteDTO> pacientesMock = List.of(
                new PacienteDTO(1L, "João Silva", "123.456.789-00", "Rua das Flores, 123",
                        LocalDateTime.of(1990, 5, 20, 0, 0), LocalDateTime.now().minusMonths(6), LocalDateTime.now()),
                new PacienteDTO(2L, "Maria Santos", "987.654.321-00", "Avenida Central, 456",
                        LocalDateTime.of(1985, 10, 15, 0, 0), LocalDateTime.now().minusYears(1), LocalDateTime.now().minusDays(5))
        );

        when(pacienteGateway.listarTodosPacientes()).thenReturn(pacientesMock);

        // Act
        List<PacienteDTO> resultado = listarTodosPacientesUseCase.listarTodosPacientes();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João Silva", resultado.get(0).nome());
        assertEquals("Maria Santos", resultado.get(1).nome());

        // Verifica se o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).listarTodosPacientes();
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPacientes() {
        // Arrange
        when(pacienteGateway.listarTodosPacientes()).thenReturn(Collections.emptyList());

        // Act
        List<PacienteDTO> resultado = listarTodosPacientesUseCase.listarTodosPacientes();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        // Verifica que o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).listarTodosPacientes();
    }

    @Test
    void deveLancarExcecaoQuandoGatewayFalhar() {
        // Arrange
        when(pacienteGateway.listarTodosPacientes()).thenThrow(new RuntimeException("Erro ao buscar pacientes"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                listarTodosPacientesUseCase.listarTodosPacientes()
        );

        assertEquals("Erro ao buscar pacientes", exception.getMessage());

        // Verifica que o método foi chamado exatamente uma vez antes da falha
        verify(pacienteGateway, times(1)).listarTodosPacientes();
    }
}
