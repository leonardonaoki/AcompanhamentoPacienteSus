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

class AtualizarUnidadeSaudeUseCaseTest {

    @Mock
    private IUnidadeSaudeGateway unidadeSaudeGateway;

    @InjectMocks
    private AtualizarUnidadeSaudeUseCase atualizarUnidadeSaudeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarUnidadeComSucesso() {
        // Arrange
        long unidadeId = 1L;
        UnidadeSaudeDomain unidadeAtualizada = new UnidadeSaudeDomain(
                unidadeId,
                "Unidade de Saúde Central",
                "Rua Exemplo, 123",
                "Hospital",
                "11987654321",
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );

        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade de saúde atualizada com sucesso!");

        when(unidadeSaudeGateway.atualizarUnidade(unidadeId, unidadeAtualizada)).thenReturn(mensagemEsperada);

        // Act
        InsertMessageDTO resultado = atualizarUnidadeSaudeUseCase.atualizarUnidade(unidadeId, unidadeAtualizada);

        // Assert
        assertNotNull(resultado);
        assertEquals(mensagemEsperada, resultado);
        verify(unidadeSaudeGateway, times(1)).atualizarUnidade(unidadeId, unidadeAtualizada);
    }

    @Test
    void deveLancarExcecaoQuandoFalhaNaAtualizacao() {
        // Arrange
        long unidadeId = 2L;
        UnidadeSaudeDomain unidadeAtualizada = new UnidadeSaudeDomain(
                unidadeId,
                "Unidade de Saúde Norte",
                "Avenida Principal, 456",
                "Posto de Saúde",
                "11912345678",
                LocalTime.of(7, 30),
                LocalTime.of(17, 30)
        );

        when(unidadeSaudeGateway.atualizarUnidade(unidadeId, unidadeAtualizada))
                .thenThrow(new RuntimeException("Erro ao atualizar unidade"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            atualizarUnidadeSaudeUseCase.atualizarUnidade(unidadeId, unidadeAtualizada);
        });

        assertEquals("Erro ao atualizar unidade", exception.getMessage());
        verify(unidadeSaudeGateway, times(1)).atualizarUnidade(unidadeId, unidadeAtualizada);
    }
}
