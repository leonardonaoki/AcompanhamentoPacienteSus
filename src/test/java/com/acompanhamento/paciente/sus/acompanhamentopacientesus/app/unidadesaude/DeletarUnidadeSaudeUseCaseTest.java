package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DeletarUnidadeSaudeUseCaseTest {

    @Mock
    private IUnidadeSaudeGateway unidadeSaudeGateway;

    @InjectMocks
    private DeletarUnidadeSaudeUseCase deletarUnidadeSaudeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarUnidadeComSucesso() {
        // Arrange
        long unidadeId = 1L;

        // Act
        deletarUnidadeSaudeUseCase.deletarUnidade(unidadeId);

        // Assert
        verify(unidadeSaudeGateway, times(1)).deletarUnidade(unidadeId);
    }

    @Test
    void deveLancarExcecaoQuandoErroNaDelecao() {
        // Arrange
        long unidadeId = 2L;
        doThrow(new RuntimeException("Erro ao deletar unidade")).when(unidadeSaudeGateway).deletarUnidade(unidadeId);

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            deletarUnidadeSaudeUseCase.deletarUnidade(unidadeId);
        });

        org.junit.jupiter.api.Assertions.assertEquals("Erro ao deletar unidade", exception.getMessage());
        verify(unidadeSaudeGateway, times(1)).deletarUnidade(unidadeId);
    }
}
