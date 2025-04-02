package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarUnidadePorIdUseCaseTest {

    @Mock
    private IUnidadeSaudeGateway unidadeSaudeGateway;

    @InjectMocks
    private ListarUnidadePorIdUseCase listarUnidadePorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUnidadeQuandoIdExiste() {
        // Arrange
        long idUnidade = 1L;
        UnidadeSaudeDTO unidadeEsperada = new UnidadeSaudeDTO(
                idUnidade, "Unidade A", "Rua X, 123", "Hospital", "123456789",
                LocalTime.of(8, 0), LocalTime.of(18, 0)
        );

        when(unidadeSaudeGateway.buscarUnidadePorId(idUnidade)).thenReturn(unidadeEsperada);

        // Act
        UnidadeSaudeDTO resultado = listarUnidadePorIdUseCase.listarUnidadePorId(idUnidade);

        // Assert
        assertNotNull(resultado);
        assertEquals(unidadeEsperada, resultado);
        verify(unidadeSaudeGateway, times(1)).buscarUnidadePorId(idUnidade);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        // Arrange
        long idInexistente = 99L;
        when(unidadeSaudeGateway.buscarUnidadePorId(idInexistente))
                .thenThrow(new EntityNotFoundException("Unidade não encontrada"));

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> listarUnidadePorIdUseCase.listarUnidadePorId(idInexistente));

        assertEquals("Unidade não encontrada", exception.getMessage());
        verify(unidadeSaudeGateway, times(1)).buscarUnidadePorId(idInexistente);
    }
}
