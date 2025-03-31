package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrarHistoricoPacienteUseCaseTest {

    @Mock
    private IControleHistoricoGateway controleHistoricoGateway;

    @InjectMocks
    private RegistrarHistoricoPacienteUseCase useCase;

    private ControleHistoricoPacienteDomain domain;

    @BeforeEach
    void setUp() {
        domain = new ControleHistoricoPacienteDomain();
    }

    @Test
    void deveRegistrarHistoricoPacienteComSucesso() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Histórico registrado com sucesso");
        when(controleHistoricoGateway.registrarHistoricoPaciente(domain)).thenReturn(mensagemEsperada);

        InsertMessageDTO resultado = useCase.registrarHistoricoPaciente(domain);

        assertNotNull(resultado);
        assertEquals(mensagemEsperada.message(), resultado.message());
        assertNotNull(domain.getDataCadastro());
        assertNotNull(domain.getDataAtualizacao());
        assertEquals(StatusHistoricoPaciente.PRIMEIRA_CONSULTA, domain.getStatusControle());
        verify(controleHistoricoGateway, times(1)).registrarHistoricoPaciente(domain);
    }
}
