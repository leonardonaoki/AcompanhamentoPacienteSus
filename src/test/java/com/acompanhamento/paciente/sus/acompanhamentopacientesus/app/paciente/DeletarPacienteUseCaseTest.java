package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeletarPacienteUseCaseTest {

    @Mock
    private IPacienteGateway pacienteGateway;

    @InjectMocks
    private DeletarPacienteUseCase deletarPacienteUseCase;

    @Test
    void deveDeletarPacienteComSucesso() {
        // Arrange
        long pacienteId = 1L;

        // Act
        deletarPacienteUseCase.deletarPaciente(pacienteId);

        // Assert
        verify(pacienteGateway, times(1)).deletarPaciente(pacienteId);
    }

    @Test
    void deveLancarExcecaoQuandoGatewayFalhar() {
        // Arrange
        long pacienteId = 1L;
        doThrow(new RuntimeException("Erro ao deletar paciente")).when(pacienteGateway).deletarPaciente(pacienteId);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                deletarPacienteUseCase.deletarPaciente(pacienteId)
        );

        assertEquals("Erro ao deletar paciente", exception.getMessage());

        // Verifica que o método foi chamado corretamente antes da exceção
        verify(pacienteGateway, times(1)).deletarPaciente(pacienteId);
    }
}
