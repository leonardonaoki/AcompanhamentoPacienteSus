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
class AtualizarStatusHistoricoPacienteUseCaseTest {

    @Mock
    private IControleHistoricoGateway controleHistoricoGateway;

    @InjectMocks
    private AtualizarStatusHistoricoPacienteUseCase useCase;

    private long id;
    private StatusHistoricoPaciente status;

    @BeforeEach
    void setUp() {
        id = 1L;
        status = StatusHistoricoPaciente.PRIMEIRA_CONSULTA;
    }

    @Test
    void deveAtualizarStatusHistoricoComSucesso() {
        ControleHistoricoDTO controleHistoricoEsperado =
                new ControleHistoricoDTO(1,1,1, LocalDateTime.now(),LocalDateTime.now(),StatusHistoricoPaciente.RETORNO);
        when(controleHistoricoGateway.atualizarStatusHistoricoPaciente(id, status)).thenReturn(controleHistoricoEsperado);

        ControleHistoricoDTO resultado = useCase.atualizarStatusHistoricoPaciente(id, status);

        assertNotNull(resultado);
        assertEquals(controleHistoricoEsperado, resultado);
        verify(controleHistoricoGateway, times(1)).atualizarStatusHistoricoPaciente(id, status);
    }
}
