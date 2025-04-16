package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime ;
import java.time.Period;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.MensagensNotificacaoUsuario;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.controle.ControleSpecification;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.prontuario.ProntuarioSpecification;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IControleHistoricoPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificaPacienteGateway {


	public static final String ACCOUNT_SID = "AC3b3ae4d8c6d3963b0cd88519eafd10db";
	public static final String AUTH_TOKEN = "f279234097ed4cf88873d52dd8f65bf7";
	private final IControleHistoricoPacienteRepository controleHistoricoRepository;
	private final IProntuarioRepository prontuarioRepository;
	private final IPacienteRepository pacienteRepository;


	protected void criaNotificacao(String mensagem, String telefoneEnvio) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message.creator(
				new com.twilio.type.PhoneNumber("whatsapp:+553185056436"),
				new com.twilio.type.PhoneNumber(telefoneEnvio),
				mensagem)
				.create();
	}

	@Scheduled(cron = "0 30 12 * * *")
	protected void notificaPacienteVencimentoRequisicao(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.statusHistoricoEmAberto());
		LocalDateTime dataAtual = LocalDateTime.now();
		String mensagem;
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			ProntuarioPacienteEntity prontuario = prontuarioRepository.findOne(ProntuarioSpecification.equalsIdHistoricoPaciente(historiciP.getIdHistoricoPaciente())).get();

			if(prontuario.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.SOLICITADO.name())) {
				PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
				if(dataAtual.isAfter(prontuario.getDataValidade())) {
					mensagem = MensagensNotificacaoUsuario.VENCIMENTO_SOLICITACAO_EXAMES.getMensagem(prontuario.getSolicitacao());
				}else if(prontuario.getDataValidade().toLocalDate().isEqual(LocalDate.now())){
					mensagem = MensagensNotificacaoUsuario.VENCIMENTO_HOJE_SOLICITACAO_EXAMES.getMensagem(prontuario.getSolicitacao());
				}else {
					Period periodo = Period.between(dataAtual.toLocalDate(),prontuario.getDataValidade().toLocalDate());

					mensagem = MensagensNotificacaoUsuario.ANTES_VENCIMENTO_SOLICITACAO_EXAMES.getMensagem(prontuario.getSolicitacao(),String.valueOf(periodo.getDays()));
				}
				criaNotificacao(mensagem, paciente.getTelefoneCelular());
			}
		}
	}

	@Scheduled(cron = "0 30 12 * * *")
	protected void notificaPacienteAusente(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.equalsStatusHistorico(StatusHistoricoPaciente.ENCERRADO));
		String mensagem = "";
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			LocalDateTime dataRetorno = historiciP.getDataEncerramento().plusMonths(6);
			PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
			if(LocalDate.now().isAfter(dataRetorno.toLocalDate()) ||dataRetorno.toLocalDate().isEqual(LocalDate.now())) {
				mensagem = MensagensNotificacaoUsuario.RETORNO_6MESES.getMensagem();
			}else if(LocalDateTime.now().isAfter(historiciP.getDataEncerramento().plusDays(15))){
				mensagem = MensagensNotificacaoUsuario.APOS_15DIAS_MEDICO.getMensagem();
			}
			if(!mensagem.isBlank())
				criaNotificacao(mensagem, paciente.getTelefoneCelular());
		}
	}

	@Scheduled (cron = "0 30 12 * * *")
	protected void notificaPacienteRetorno(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.buscaUltimoHistoricoPaciente(StatusHistoricoPaciente.RETORNO));
		LocalDateTime dataAtual = LocalDateTime.now();
		String mensagem = "";
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
			ProntuarioPacienteEntity prontuario = prontuarioRepository.findOne(ProntuarioSpecification.equalsIdHistoricoPaciente(historiciP.getIdHistoricoPaciente())).get();
			if(prontuario.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.EXAME_REALIZADO.name())) {
				if(prontuario.getDataValidade().isAfter(dataAtual)) {
					mensagem = MensagensNotificacaoUsuario.EXAME_EXPIRADO.getMensagem();
				}else if(prontuario.getDataValidade().toLocalDate().equals(dataAtual.toLocalDate())){
					mensagem = MensagensNotificacaoUsuario.EXAME_VENCE_HOJE.getMensagem();
				}else {
					Period periodo = Period.between(prontuario.getDataValidade().toLocalDate(),dataAtual.toLocalDate());

					mensagem = MensagensNotificacaoUsuario.EXAME_VENCE_EM_X_DIAS.getMensagem(String.valueOf(periodo.getDays()));
				}

			}else if(historiciP.getDataAtualizacao().plusDays(30).isBefore(dataAtual)) {
				mensagem = MensagensNotificacaoUsuario.LEMBRETE_VALIDADE.getMensagem();
			}
			if(!mensagem.isBlank())
				criaNotificacao(mensagem, paciente.getTelefoneCelular());
		}
	}

}
