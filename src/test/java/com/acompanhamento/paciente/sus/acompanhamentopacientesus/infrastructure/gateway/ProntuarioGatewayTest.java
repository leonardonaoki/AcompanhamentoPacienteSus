package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
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
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    private WebClient mockWebClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private ProntuarioGateway prontuarioGateway;
    @Captor
    private ArgumentCaptor<UpdateControleHistoricoDTO> bodyCaptor;

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

        try (MockedStatic<WebClient> webClientMockedStatic = mockStatic(WebClient.class)) {
            webClientMockedStatic.when(() -> WebClient.create(null)).thenReturn(mockWebClient);
            // Arrange
            when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
            when(prontuarioRepository.save(entity)).thenReturn(entity);
            mockWebClient();

            // Act
            InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

            // Assert
            assertNotNull(result);

            // Verifica se o WebClient foi criado com a URL correta
            verify(prontuarioMapper).toEntity(domain);
            verify(prontuarioRepository).save(entity);
            verify(mockWebClient).patch();
            verify(requestBodyUriSpec).uri("/controlehistorico/123");
            verify(requestBodySpec).contentType(MediaType.APPLICATION_JSON);
            verify(requestBodySpec).bodyValue(bodyCaptor.capture());
            verify(requestHeadersSpec).retrieve();
            verify(responseSpec).bodyToMono(String.class);

            UpdateControleHistoricoDTO sentBody = bodyCaptor.getValue();
            assertEquals(StatusHistoricoPaciente.EM_CURSO.toString(), sentBody.novoStatus());
        }

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
        try (MockedStatic<WebClient> webClientMockedStatic = mockStatic(WebClient.class)) {
            webClientMockedStatic.when(() -> WebClient.create(null)).thenReturn(mockWebClient);
            // Act & Assert
            assertThrows(IllegalArgumentException.class,
                    () -> prontuarioGateway.registrarProntuarioPaciente(domain));

            verify(prontuarioRepository, never()).save(any());
            verify(mockWebClient, never()).patch();
        }
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
        try (MockedStatic<WebClient> webClientMockedStatic = mockStatic(WebClient.class)) {
            webClientMockedStatic.when(() -> WebClient.create(null)).thenReturn(mockWebClient);
            when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
            when(prontuarioRepository.save(entity)).thenReturn(entity);

            mockWebClient();

            // Act
            InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

            // Assert
            assertNotNull(result);
            verify(requestBodySpec).bodyValue(bodyCaptor.capture());
            UpdateControleHistoricoDTO sentBody = bodyCaptor.getValue();
            assertEquals(StatusHistoricoPaciente.RETORNO.toString(), sentBody.novoStatus());
        }
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
        try (MockedStatic<WebClient> webClientMockedStatic = mockStatic(WebClient.class)) {
            webClientMockedStatic.when(() -> WebClient.create(null)).thenReturn(mockWebClient);
            when(prontuarioMapper.toEntity(domain)).thenReturn(entity);
            when(prontuarioRepository.save(entity)).thenReturn(entity);

            mockWebClient();

            // Act
            InsertMessageDTO result = prontuarioGateway.registrarProntuarioPaciente(domain);

            // Assert
            assertNotNull(result);
            verify(requestBodySpec).bodyValue(bodyCaptor.capture());
            UpdateControleHistoricoDTO sentBody = bodyCaptor.getValue();
            assertEquals(StatusHistoricoPaciente.ENCERRADO.toString(), sentBody.novoStatus());
        }
    }

    private void mockWebClient() {
        when(mockWebClient.patch()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any(UpdateControleHistoricoDTO.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.empty());
    }
}

