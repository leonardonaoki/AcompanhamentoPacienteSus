package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ListarHistoricoPacientePorIdControleUseCaseTest {

    @Mock
    private IControleHistoricoGateway controleHistoricoGateway;

    @InjectMocks
    private ListarHistoricoPacientePorIdControleUseCase useCase;

    private Long idControle;

    @BeforeEach
    void setUp() {
        idControle = 1L;
    }

    @Test
    void deveRetornarHistoricoPacienteQuandoIdControleExistir() {
        ControleHistoricoDTO historicoEsperado =
                new ControleHistoricoDTO(1,1,1, LocalDateTime.now(),LocalDateTime.now(), StatusHistoricoPaciente.RETORNO);
        when(controleHistoricoGateway.listarHistoricoPacientePorIdControle(idControle)).thenReturn(historicoEsperado);

        ControleHistoricoDTO resultado = useCase.listarPacientePorIdControle(idControle);

        assertNotNull(resultado);
        assertEquals(historicoEsperado, resultado);
        verify(controleHistoricoGateway, times(1)).listarHistoricoPacientePorIdControle(idControle);
    }
}

