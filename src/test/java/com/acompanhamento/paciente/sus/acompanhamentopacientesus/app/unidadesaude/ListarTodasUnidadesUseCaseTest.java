package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarTodasUnidadesUseCaseTest {

    @Mock
    private IUnidadeSaudeGateway unidadeSaudeGateway;

    @InjectMocks
    private ListarTodasUnidadesUseCase listarTodasUnidadesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeUnidadesComSucesso() {
        // Arrange
        List<UnidadeSaudeDTO> unidadesEsperadas = Arrays.asList(
                new UnidadeSaudeDTO(1L, "Unidade A", "Rua X, 123", "Hospital", "123456789",
                        LocalTime.of(8, 0), LocalTime.of(18, 0)),
                new UnidadeSaudeDTO(2L, "Unidade B", "Avenida Y, 456", "Posto de Saúde", "987654321",
                        LocalTime.of(7, 0), LocalTime.of(17, 0))
        );

        when(unidadeSaudeGateway.listarUnidadesSaude(0, Integer.MAX_VALUE)).thenReturn(unidadesEsperadas);

        // Act
        List<UnidadeSaudeDTO> resultado = listarTodasUnidadesUseCase.listarTodasUnidades();

        // Assert
        assertEquals(unidadesEsperadas.size(), resultado.size());
        assertEquals(unidadesEsperadas, resultado);
        verify(unidadeSaudeGateway, times(1)).listarUnidadesSaude(0, Integer.MAX_VALUE);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHaUnidades() {
        // Arrange
        when(unidadeSaudeGateway.listarUnidadesSaude(0, Integer.MAX_VALUE)).thenReturn(List.of());

        // Act
        List<UnidadeSaudeDTO> resultado = listarTodasUnidadesUseCase.listarTodasUnidades();

        // Assert
        assertTrue(resultado.isEmpty());
        verify(unidadeSaudeGateway, times(1)).listarUnidadesSaude(0, Integer.MAX_VALUE);
    }

    @Test
    void deveLancarExcecaoQuandoGatewayFalha() {
        // Arrange
        when(unidadeSaudeGateway.listarUnidadesSaude(0, Integer.MAX_VALUE))
                .thenThrow(new RuntimeException("Erro ao listar unidades"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> listarTodasUnidadesUseCase.listarTodasUnidades());

        assertEquals("Erro ao listar unidades", exception.getMessage());
        verify(unidadeSaudeGateway, times(1)).listarUnidadesSaude(0, Integer.MAX_VALUE);
    }
}
