package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
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
class ListarProntuarioPorIdUseCaseTest {

    @Mock
    private IProntuarioGateway prontuarioGateway;

    @InjectMocks
    private ListarProntuarioPorIdUseCase useCase;

    private long idControle;
    private String especialidade;
    private LocalDateTime data;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;
    private int offset;
    private int limit;

    @BeforeEach
    void setUp() {
        idControle = 1L;
        especialidade = "Cardiologia";
        data = LocalDateTime.now();
        solicitacao = "Consulta";
        statusSolicitacaoProntuario = StatusSolicitacaoProntuario.SOLICITADO;
        offset = 0;
        limit = 10;
    }

    @Test
    void deveRetornarListaDeProntuarios() {
        List<ProntuarioDTO> prontuariosEsperados = Collections.singletonList(new ProntuarioDTO(idControle,especialidade,data,null,solicitacao,statusSolicitacaoProntuario.toString()));
        when(prontuarioGateway.listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        )).thenReturn(prontuariosEsperados);

        List<ProntuarioDTO> resultado = useCase.listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        );

        assertNotNull(resultado);
        assertEquals(prontuariosEsperados.size(), resultado.size());
        verify(prontuarioGateway, times(1)).listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        );
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverProntuarios() {
        when(prontuarioGateway.listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        )).thenReturn(Collections.emptyList());

        List<ProntuarioDTO> resultado = useCase.listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        );

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(prontuarioGateway, times(1)).listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit
        );
    }
}
