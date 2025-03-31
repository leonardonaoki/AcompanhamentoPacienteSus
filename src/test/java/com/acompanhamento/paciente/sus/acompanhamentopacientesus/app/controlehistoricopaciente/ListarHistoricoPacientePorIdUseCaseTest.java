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
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListarHistoricoPacientePorIdUseCaseTest {

    @Mock
    private IControleHistoricoGateway controleHistoricoGateway;

    @InjectMocks
    private ListarHistoricoPacientePorIdUseCase useCase;

    private Long idPaciente;
    private Long idUnidade;
    private LocalDateTime data;
    private StatusHistoricoPaciente statusHistoricoPaciente;
    private int offset;
    private int limit;

    @BeforeEach
    void setUp() {
        idPaciente = 1L;
        idUnidade = 2L;
        data = LocalDateTime.now();
        statusHistoricoPaciente = StatusHistoricoPaciente.PRIMEIRA_CONSULTA;
        offset = 0;
        limit = 10;
    }

    @Test
    void deveRetornarListaDeHistoricos() {
        List<ControleHistoricoDTO> historicosEsperados = Collections.singletonList(
                new ControleHistoricoDTO(1,1,1, LocalDateTime.now(),LocalDateTime.now(), StatusHistoricoPaciente.RETORNO));
        when(controleHistoricoGateway.listarHistoricoPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        )).thenReturn(historicosEsperados);

        List<ControleHistoricoDTO> resultado = useCase.listarPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        );

        assertNotNull(resultado);
        assertEquals(historicosEsperados.size(), resultado.size());
        verify(controleHistoricoGateway, times(1)).listarHistoricoPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        );
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverHistoricos() {
        when(controleHistoricoGateway.listarHistoricoPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        )).thenReturn(Collections.emptyList());

        List<ControleHistoricoDTO> resultado = useCase.listarPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        );

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(controleHistoricoGateway, times(1)).listarHistoricoPacientePorId(
                idPaciente, idUnidade, data, statusHistoricoPaciente, offset, limit
        );
    }
}
