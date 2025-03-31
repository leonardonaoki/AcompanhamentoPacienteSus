package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrarProntuarioPacienteUseCaseTest {

    @Mock
    private IProntuarioGateway prontuarioGateway;

    @InjectMocks
    private RegistrarProntuarioPacienteUseCase useCase;

    private ProntuarioPacienteDomain domain;

    @BeforeEach
    void setUp() {
        domain = new ProntuarioPacienteDomain();
    }

    @Test
    void deveRegistrarProntuarioComSucesso() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Registro efetuado com sucesso");
        when(prontuarioGateway.registrarProntuarioPaciente(domain)).thenReturn(mensagemEsperada);

        InsertMessageDTO resultado = useCase.registrarProntuarioPaciente(domain);

        assertNotNull(resultado);
        assertEquals(mensagemEsperada.message(), resultado.message());
        assertNotNull(domain.getDataInicio());
        verify(prontuarioGateway, times(1)).registrarProntuarioPaciente(domain);
    }
}
