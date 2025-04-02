package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrarUnidadeSaudeUseCaseTest {

    @Mock
    private IUnidadeSaudeGateway unidadeSaudeGateway;

    @InjectMocks
    private RegistrarUnidadeSaudeUseCase registrarUnidadeSaudeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarUnidadeComSucesso() {
        // Arrange
        UnidadeSaudeDomain unidadeSaude = new UnidadeSaudeDomain(
                1L, "Unidade Teste", "Rua A, 123", "Hospital", "11999999999",
                LocalTime.of(7, 0), LocalTime.of(19, 0)
        );
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade registrada com sucesso!");

        when(unidadeSaudeGateway.registrarUnidade(unidadeSaude)).thenReturn(mensagemEsperada);

        // Act
        InsertMessageDTO resultado = registrarUnidadeSaudeUseCase.registrarUnidadeSaude(unidadeSaude);

        // Assert
        assertNotNull(resultado);
        assertEquals(mensagemEsperada, resultado);
        verify(unidadeSaudeGateway, times(1)).registrarUnidade(unidadeSaude);
    }

    @Test
    void deveLancarExcecaoQuandoFalhaNoRegistro() {
        // Arrange
        UnidadeSaudeDomain unidadeInvalida = new UnidadeSaudeDomain();

        when(unidadeSaudeGateway.registrarUnidade(unidadeInvalida))
                .thenThrow(new RuntimeException("Erro ao registrar unidade"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> registrarUnidadeSaudeUseCase.registrarUnidadeSaude(unidadeInvalida));

        assertEquals("Erro ao registrar unidade", exception.getMessage());
        verify(unidadeSaudeGateway, times(1)).registrarUnidade(unidadeInvalida);
    }
}
