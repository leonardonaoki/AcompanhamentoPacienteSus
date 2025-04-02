package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.AtualizarStatusHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProntuarioGatewayTest {
    @Mock
    private IProntuarioRepository prontuarioRepository;

    @Mock
    private IProntuarioMapper prontuarioMapper;
    @Mock
    private AtualizarStatusHistoricoPacienteUseCase atualizarStatusHistoricoPacienteUseCase;
    @InjectMocks
    private ProntuarioGateway prontuarioGateway;

    @Test
    void testListarProntuarioPacientePorIdControle() {
        long idControle = 1L;
        String especialidade = "Cardiologia";
        LocalDateTime data = LocalDateTime.now();
        String solicitacao = "Exame";
        StatusSolicitacaoProntuario statusSolicitacaoProntuario = StatusSolicitacaoProntuario.SOLICITADO;
        int offset = 0;
        int limit = 10;

        ProntuarioPacienteEntity prontuarioPacienteEntity = new ProntuarioPacienteEntity();
        prontuarioPacienteEntity.setId(1L);
        prontuarioPacienteEntity.setEspecialidadeMedico("Cardiologia");

        Page<ProntuarioPacienteEntity> page = mock(Page.class);
        when(page.isEmpty()).thenReturn(false);
        when(page.stream()).thenReturn(List.of(prontuarioPacienteEntity).stream());
        when(prontuarioRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(prontuarioMapper.toDTO(any(ProntuarioPacienteEntity.class)))
                .thenReturn(new ProntuarioDTO(1L,"Cardiologia",LocalDateTime.now(),LocalDateTime.now(),"Exame",StatusSolicitacaoProntuario.SOLICITADO.toString()));

        List<ProntuarioDTO> result = prontuarioGateway.listarProntuarioPacientePorIdControle(
                idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testListarProntuarioPacientePorIdControleThrowsEntityNotFoundException() {
        long idControle = 1L;
        String especialidade = "Cardiologia";
        LocalDateTime data = LocalDateTime.now();
        String solicitacao = "Exame";
        StatusSolicitacaoProntuario statusSolicitacaoProntuario = StatusSolicitacaoProntuario.SOLICITADO;
        int offset = 0;
        int limit = 10;

        Page<ProntuarioPacienteEntity> page = mock(Page.class);
        when(page.isEmpty()).thenReturn(true);
        when(prontuarioRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        assertThrows(EntityNotFoundException.class, () ->
                prontuarioGateway.listarProntuarioPacientePorIdControle(
                        idControle, especialidade, data, solicitacao, statusSolicitacaoProntuario, offset, limit)
        );
    }

    @Test
    void registrarProntuarioPaciente_StatusSolicitadoComSolicitacao_DeveRetornarMensagemSucesso() {
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setIdControleHistorico(123L);
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO);
        domain.setSolicitacao("Solicitação de prontuário");
        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setId(1L);

        when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
        when(prontuarioRepository.save(entity)).thenReturn(entity);
        when(atualizarStatusHistoricoPacienteUseCase.atualizarStatusHistoricoPaciente(anyLong(),any()))
                .thenReturn(new ControleHistoricoDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now(),StatusHistoricoPaciente.RETORNO));
        InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

        assertNotNull(result);
        verify(prontuarioMapper).toEntity(domain);
        verify(prontuarioRepository).save(entity);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void registrarProntuarioPaciente_StatusSolicitadoSemSolicitacao_DeveLancarExcecao(String arg) {
        // Arrange
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setIdControleHistorico(123L);
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO);
        domain.setSolicitacao(arg);
        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setId(1L);
        assertThrows(IllegalArgumentException.class,
                () -> prontuarioGateway.registrarProntuarioPaciente(domain));

        verify(prontuarioRepository, never()).save(any());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"ENTREGUE","EXAME_REALIZADO"})
    void registrarProntuarioPaciente_StatusEntregueComSolicitacao_DeveAtualizarStatusParaRetorno(String status) {
        // Arrange
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.valueOf(status));
        domain.setSolicitacao("Fazer novo exame");
        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setId(1L);
        when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
        when(prontuarioRepository.save(entity)).thenReturn(entity);
        when(atualizarStatusHistoricoPacienteUseCase.atualizarStatusHistoricoPaciente(anyLong(),any()))
                .thenReturn(new ControleHistoricoDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now(),StatusHistoricoPaciente.RETORNO));
        InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

        assertNotNull(result);
        // Capturando o status passado ao método
        ArgumentCaptor<StatusHistoricoPaciente> captor = ArgumentCaptor.forClass(StatusHistoricoPaciente.class);
        verify(atualizarStatusHistoricoPacienteUseCase).atualizarStatusHistoricoPaciente(anyLong(), captor.capture());
        StatusHistoricoPaciente statusCapturado = captor.getValue();
        assertEquals(StatusHistoricoPaciente.RETORNO, statusCapturado);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"ENTREGUE","EXAME_REALIZADO"})
    void registrarProntuarioPaciente_StatusEntregueSemSolicitacao_DeveAtualizarStatusParaEncerrado(String status) {
        // Arrange
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.valueOf(status));
        domain.setSolicitacao(null);

        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setId(1L);
        when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
        when(prontuarioRepository.save(entity)).thenReturn(entity);

        InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

        // Assert
        assertNotNull(result);
        // Capturando o status passado ao método
        ArgumentCaptor<StatusHistoricoPaciente> captor = ArgumentCaptor.forClass(StatusHistoricoPaciente.class);
        verify(atualizarStatusHistoricoPacienteUseCase).atualizarStatusHistoricoPaciente(anyLong(), captor.capture());
        StatusHistoricoPaciente statusCapturado = captor.getValue();
        assertEquals(StatusHistoricoPaciente.ENCERRADO, statusCapturado);
    }
}

