package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.MensagensNotificacaoUsuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IControleHistoricoPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class NotificaPacienteGatewayTest {

    @Mock
    private IControleHistoricoPacienteRepository controleHistoricoRepository;

    @Mock
    private IProntuarioRepository prontuarioRepository;

    @Mock
    private IPacienteRepository pacienteRepository;

    @InjectMocks
    private NotificaPacienteGateway gateway;

    @Spy
    @InjectMocks
    private NotificaPacienteGateway gatewaySpy;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriaNotificacao_enviaMensagemComSucesso() {
        // Arrange
        IControleHistoricoPacienteRepository controleMock = mock(IControleHistoricoPacienteRepository.class);
        IProntuarioRepository prontuarioMock = mock(IProntuarioRepository.class);
        IPacienteRepository pacienteMock = mock(IPacienteRepository.class);

        NotificaPacienteGateway gatewayTeste = new NotificaPacienteGateway(controleMock, prontuarioMock, pacienteMock);

        String mensagem = "Mensagem de teste";
        String telefone = "whatsapp:+5511999999999";

        PhoneNumber to = new PhoneNumber("whatsapp:+553185056436");
        PhoneNumber from = new PhoneNumber(telefone);

        // Mock do Creator
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real

        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(to, from, mensagem))
                    .thenReturn(creatorMock);

            // Act
            gatewayTeste.criaNotificacao(mensagem, telefone);

            // Assert
            twilioStaticMock.verify(() ->
                    Twilio.init(NotificaPacienteGateway.ACCOUNT_SID, NotificaPacienteGateway.AUTH_TOKEN));

            messageStaticMock.verify(() ->
                    Message.creator(to, from, mensagem));

            verify(creatorMock).create();
        }
    }

    @Test
    void naoDeveNotificarPaciente_SemSolicitacao() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdHistoricoPaciente(1L);
        historico.setIdPaciente(1L);

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setStatusSolicitacaoProntuario("");
        prontuario.setDataValidade(hoje.plusDays(5));
        prontuario.setSolicitacao("Exame de sangue");

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511999999999");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteVencimentoRequisicao();

            verify(gatewaySpy,never()).criaNotificacao(
                    MensagensNotificacaoUsuario.VENCIMENTO_SOLICITACAO_EXAMES.getMensagem("Exame de sangue"),
                    "whatsapp:+5511999999999"
            );
        }

    }

    @Test
    void deveNotificarPaciente_quandoValidadeFutura() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdHistoricoPaciente(1L);
        historico.setIdPaciente(1L);

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO.name());
        prontuario.setDataValidade(hoje.minusDays(5));
        prontuario.setSolicitacao("Exame de sangue");

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511999999999");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteVencimentoRequisicao();

            verify(gatewaySpy).criaNotificacao(
                    MensagensNotificacaoUsuario.VENCIMENTO_SOLICITACAO_EXAMES.getMensagem("Exame de sangue"),
                    "whatsapp:+5511999999999"
            );
        }

    }

    @Test
    void deveNotificarPaciente_quandoValidadeHoje() {
        LocalDateTime hoje = LocalDateTime.now().plusMinutes(30);

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdHistoricoPaciente(2L);
        historico.setIdPaciente(2L);

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO.name());
        prontuario.setDataValidade(hoje);
        prontuario.setSolicitacao("Ultrassom");

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511988888888");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteVencimentoRequisicao();

            verify(gatewaySpy).criaNotificacao(
                    MensagensNotificacaoUsuario.VENCIMENTO_HOJE_SOLICITACAO_EXAMES.getMensagem("Ultrassom"),
                    "whatsapp:+5511988888888"
            );
        }

    }

    @Test
    void deveNotificarPaciente_quandoValidadeExpirada() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdHistoricoPaciente(3L);
        historico.setIdPaciente(3L);

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO.name());
        prontuario.setDataValidade(hoje.plusDays(2));
        prontuario.setSolicitacao("Raio-X");

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511977777777");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));
        when(pacienteRepository.findById(3L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteVencimentoRequisicao();

            verify(gatewaySpy).criaNotificacao(
                    MensagensNotificacaoUsuario.ANTES_VENCIMENTO_SOLICITACAO_EXAMES.getMensagem("Raio-X", "2"),
                    "whatsapp:+5511977777777"
            );
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {6,7})
    void deveNotificarComRetorno6MesesQuandoDataEncerramentoMaiorOuIgualMais6Meses(int args) {
        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        LocalDateTime dataEncerramento = LocalDateTime.now().minusMonths(args);
        historico.setIdPaciente(1L);
        historico.setDataEncerramento(dataEncerramento);

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511988888888");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteAusente();

            verify(gatewaySpy).criaNotificacao(MensagensNotificacaoUsuario.RETORNO_6MESES.getMensagem(), "whatsapp:+5511988888888");
        }

    }
    @Test
    void deveNotificarComApos15DiasQuandoDataEncerramentoIgualMais15Dias() {
        LocalDateTime dataEncerramento = LocalDateTime.now().minusDays(15);

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(2L);
        historico.setDataEncerramento(dataEncerramento);

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511977777777");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteAusente();

            verify(gatewaySpy).criaNotificacao(MensagensNotificacaoUsuario.APOS_15DIAS_MEDICO.getMensagem(), "whatsapp:+5511977777777");
        }

    }

    @Test
    void naoDeveNotificarQuandoNenhumaCondicaoDeDatasForSatisfeita() {
        LocalDateTime dataEncerramento = LocalDateTime.now().minusDays(13); // nem 6 meses, nem 15 dias

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(3L);
        historico.setDataEncerramento(dataEncerramento);

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+5511966666666");

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(3L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteAusente();

            verify(gatewaySpy,never()).criaNotificacao(MensagensNotificacaoUsuario.APOS_15DIAS_MEDICO.getMensagem(), "whatsapp:+5511977777777");
        }
    }

    @Test
    void naoDeveNotificarRetornoQuandoNenhumaCondicaoDeDatasForSatisfeita() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(1L);
        historico.setIdHistoricoPaciente(10L);
        historico.setDataAtualizacao(hoje.minusDays(10));

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+55999999999");

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setDataValidade(hoje.plusDays(3));
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.ENTREGUE.name());

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(3L)).thenReturn(Optional.of(paciente));
        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteRetorno();

            verify(gatewaySpy,never()).criaNotificacao(MensagensNotificacaoUsuario.APOS_15DIAS_MEDICO.getMensagem(), "whatsapp:+5511977777777");
        }
    }

    @Test
    void deveNotificarComExameExpiradoQuandoDataValidadeForMaiorQueHoje() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(1L);
        historico.setIdHistoricoPaciente(10L);
        historico.setDataAtualizacao(hoje.minusDays(10));

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+55999999999");

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setDataValidade(hoje.plusDays(3));
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.EXAME_REALIZADO.name());

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));

        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteRetorno();

            verify(gatewaySpy).criaNotificacao(MensagensNotificacaoUsuario.EXAME_EXPIRADO.getMensagem(), paciente.getTelefoneCelular());
        }
    }

    @Test
    void deveNotificarComExameVenceHojeQuandoDataValidadeForHoje() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(1L);
        historico.setIdHistoricoPaciente(10L);
        historico.setDataAtualizacao(hoje.minusDays(10));

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+55999999999");

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setDataValidade(hoje);
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.EXAME_REALIZADO.name());

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));

        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteRetorno();

            verify(gatewaySpy).criaNotificacao(MensagensNotificacaoUsuario.EXAME_VENCE_HOJE.getMensagem(), paciente.getTelefoneCelular());
        }
    }

    @Test
    void deveNotificarComExameVenceEmXdiasQuandoDataValidadeForAnteriorAHoje() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(1L);
        historico.setIdHistoricoPaciente(10L);
        historico.setDataAtualizacao(hoje.minusDays(10));

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+55999999999");

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setDataValidade(hoje.minusDays(2));
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.EXAME_REALIZADO.name());

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));

        String esperado = MensagensNotificacaoUsuario.EXAME_VENCE_EM_X_DIAS.getMensagem("2");

        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteRetorno();

            verify(gatewaySpy).criaNotificacao(esperado, paciente.getTelefoneCelular());
        }

    }

    @Test
    void deveNotificarComLembreteValidadeQuandoNaoForExameRealizadoEPassou30Dias() {
        LocalDateTime hoje = LocalDateTime.now();

        ControleHistoricoPacienteEntity historico = new ControleHistoricoPacienteEntity();
        historico.setIdPaciente(1L);
        historico.setIdHistoricoPaciente(10L);
        historico.setDataAtualizacao(hoje.minusDays(31));

        PacienteEntity paciente = new PacienteEntity();
        paciente.setTelefoneCelular("whatsapp:+55999999999");

        ProntuarioPacienteEntity prontuario = new ProntuarioPacienteEntity();
        prontuario.setDataValidade(hoje.plusDays(5)); // Não importa
        prontuario.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.SOLICITADO.name());

        when(controleHistoricoRepository.findAll(any(Specification.class))).thenReturn(List.of(historico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(prontuarioRepository.findOne(any(Specification.class))).thenReturn(Optional.of(prontuario));

        MessageCreator creatorMock = mock(MessageCreator.class);
        when(creatorMock.create()).thenReturn(null); // Evita chamada real
        try (MockedStatic<Twilio> twilioStaticMock = mockStatic(Twilio.class);
             MockedStatic<Message> messageStaticMock = mockStatic(Message.class)) {

            // Mock da criação do message
            messageStaticMock
                    .when(() -> Message.creator(any(), any(PhoneNumber.class), anyString()))
                    .thenReturn(creatorMock);

            gatewaySpy.notificaPacienteRetorno();

            verify(gatewaySpy).criaNotificacao(MensagensNotificacaoUsuario.LEMBRETE_VALIDADE.getMensagem(), paciente.getTelefoneCelular());
        }

    }
}
