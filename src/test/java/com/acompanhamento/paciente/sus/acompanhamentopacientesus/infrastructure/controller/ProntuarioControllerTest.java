package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdControleUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.ListarProntuarioPorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.RegistrarProntuarioPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProntuarioControllerTest {

    @Mock
    private ListarProntuarioPorIdUseCase listarProntuarioPorIdUseCase;

    @Mock
    private RegistrarProntuarioPacienteUseCase registrarProntuarioPacienteUseCase;

    @Mock
    private ListarHistoricoPacientePorIdControleUseCase listarHistoricoPacientePorIdControleUseCase;

    @Mock
    private IProntuarioMapper prontuarioMapper;

    @InjectMocks
    private ProntuarioController prontuarioController;

    private InsertProntuarioDTO insertProntuarioDTO;
    private InsertMessageDTO insertMessageDTO;
    private ProntuarioDTO prontuarioDTO;

    @BeforeEach
    void setUp() {
        insertProntuarioDTO = new InsertProntuarioDTO("CARDIOLOGIA", LocalDateTime.now(), "Solicitação de Exame", "SOLICITADO");
        insertMessageDTO = new InsertMessageDTO("Prontuário registrado com sucesso");
        prontuarioDTO = new ProntuarioDTO(1,"CARDIOLOGIA",LocalDateTime.now(),LocalDateTime.now(),"Solicitação de Exame", "SOLICITADO");
    }

    @Test
    void testListarProntuarioPacientePorIdControle_Sucesso() {
        when(listarProntuarioPorIdUseCase.listarProntuarioPacientePorIdControle(anyLong(), any(), any(), any(), any(), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(prontuarioDTO));

        ResponseEntity<List<ProntuarioDTO>> response = prontuarioController.listarProntuarioPacientePorIdControle(1L, "CARDIOLOGIA", LocalDateTime.now(), "Solicitação", "SOLICITADO", 0, 10);

        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
    }
    @Test
    void testListarProntuarioPacientePorIdControle_Erro() {
        assertThrows(IllegalArgumentException.class, () ->
                prontuarioController.listarProntuarioPacientePorIdControle(
                        1L, "CARDIOLOGIA", null, "Solicitação", "INVALIDO", 0, 10));
    }
    @ParameterizedTest
    @ValueSource(strings = { "","   "} )
    @NullAndEmptySource
    void testListarProntuarioPacientePorIdControle_StatusNull(String args) {
        ResponseEntity<List<ProntuarioDTO>> response = prontuarioController.listarProntuarioPacientePorIdControle(1L, "Cardiologia", LocalDateTime.now(), "Solicitação", args, 0, 10);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testRegistrarHistoricoPaciente_Sucesso() {
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();

        when(listarHistoricoPacientePorIdControleUseCase.listarPacientePorIdControle(anyLong())).thenReturn(null);
        when(prontuarioMapper.toDomain(anyLong(), any())).thenReturn(domain);
        when(registrarProntuarioPacienteUseCase.registrarProntuarioPaciente(any())).thenReturn(insertMessageDTO);

        ResponseEntity<InsertMessageDTO> response = prontuarioController.registrarHistoricoPaciente(1L, insertProntuarioDTO);

        assertNotNull(response);
        assertEquals("Prontuário registrado com sucesso", response.getBody().message());
    }

    @Test
    void testRegistrarHistoricoPaciente_StatusInvalido() {
        InsertProntuarioDTO dtoInvalido = new InsertProntuarioDTO("CARDIOLOGIA", LocalDateTime.now(), "Solicitação", "INVALIDO");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                prontuarioController.registrarHistoricoPaciente(1L, dtoInvalido)
        );

        assertTrue(exception.getMessage().contains("Valor inválido para statusSolicitacaoProntuario"));
    }
}
