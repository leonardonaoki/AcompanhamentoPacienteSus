package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime ;
import java.time.Period;

import org.joda.time.LocalDate;
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
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificaPacienteGateway {


	public static final String ACCOUNT_SID = "AC3b3ae4d8c6d3963b0cd88519eafd10db";
	public static final String AUTH_TOKEN = "f279234097ed4cf88873d52dd8f65bf7";
	public static final String TELEFONE_ENVIO = "whatsapp:+14155238886";
	private final IControleHistoricoPacienteRepository controleHistoricoRepository;
	private final IProntuarioRepository prontuarioRepository;
	private final IPacienteRepository pacienteRepository;


	private void criaNotificacao(String mensagem, String telefoneEnvio) {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber("whatsapp:+553185056436"),
				new com.twilio.type.PhoneNumber(telefoneEnvio),
				mensagem)
				.create();
		System.out.println(message.getSid());
	}

	@Scheduled(cron = "0 30 12 * * *")
	private void notificaPacienteVencimentoRequisicao(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.statusHistoricoEmAberto());
		LocalDateTime dataAtual = LocalDateTime.now();
		String Mensagem = new String();
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			ProntuarioPacienteEntity prontuario = prontuarioRepository.findOne(ProntuarioSpecification.equalsIdHistoricoPaciente(historiciP.getIdHistoricoPaciente())).get();
			if(prontuario.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.SOLICITADO.name())) {
				PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
				if(prontuario.getDataValidade().isAfter(dataAtual)) {
					Mensagem = "A data para realização do/a ".concat(prontuario.getSolicitacao().concat(" venceu, agende outra consulta para renovar o seu pedido"));
				}else if(prontuario.getDataValidade().equals(dataAtual)){
					Mensagem = "A data para realização do/a ".concat(prontuario.getSolicitacao().concat(" vence hoje,realize o procendimento antes do vencimento ou sera necesssario outra consulta"));
				}else {
					Period periodo = Period.between(prontuario.getDataValidade().toLocalDate(),dataAtual.toLocalDate());

					Mensagem = "A data para realização do/a ".concat(prontuario.getSolicitacao().concat(" vence em ").concat(String.valueOf(periodo.getDays())).concat(" dia(s),realize o procendimento antes do vencimento ou sera necesssario outra consulta"));
				}
				criaNotificacao(Mensagem, paciente.getTelefoneCelular());

			}	
		}
	}

	@Scheduled(cron = "0 30 12 * * *")
	private void notificaPacienteAusente(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.equalsStatusHistorico(StatusHistoricoPaciente.ENCERRADO));
		String Mensagem = new String();
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			LocalDateTime dataRetorno = historiciP.getDataEncerramento().plusMonths(6);
			PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
			if(historiciP.getDataEncerramento().isAfter(dataRetorno)||historiciP.getDataEncerramento().equals(dataRetorno)) {
				Mensagem = "já faz mais de 6 meses da sua ultima consulta agende outra consulta para manter a sua saude em dia!";
			}else if(historiciP.getDataEncerramento().equals(historiciP.getDataEncerramento().plusDays(15))){
				Mensagem = "Já faz 15 dias que da sua ultima consulta certifique de estar seguindo as orientaçoes medicas e ao sinal de qualquer sintoma diferente se encaminha upa mais proxima ou marque uma nova consulta";
			}
			criaNotificacao(Mensagem, paciente.getTelefoneCelular());
		}
	}
	
	@Scheduled (cron = "0 30 12 * * *")
	private void notificaPacienteRetorno(){

		List<ControleHistoricoPacienteEntity> historicoPacienteList = controleHistoricoRepository.findAll(ControleSpecification.buscaUltimoHistoricoPaciente(StatusHistoricoPaciente.RETORNO));
		LocalDateTime dataAtual = LocalDateTime.now();
		String Mensagem = new String();
		for(ControleHistoricoPacienteEntity historiciP :historicoPacienteList ) {
			PacienteEntity paciente = pacienteRepository.findById(historiciP.getIdPaciente()).get();
			ProntuarioPacienteEntity prontuario = prontuarioRepository.findOne(ProntuarioSpecification.equalsIdHistoricoPaciente(historiciP.getIdHistoricoPaciente())).get();
			if(prontuario.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.EXAME_REALIZADO.name())) {
				if(prontuario.getDataValidade().isAfter(dataAtual)) {
					Mensagem = "A data do seu exame expirou, será necessario marcar uma nova consulta e realizar o exame novamente";
				}else if(prontuario.getDataValidade().equals(dataAtual)){
					Mensagem = "A data do seu exame vence hoje,se voce não for ao medico hoje irá perder o exame e será necessario marcar outra consulta para fazer outro pedido";
				}else {
					Period periodo = Period.between(prontuario.getDataValidade().toLocalDate(),dataAtual.toLocalDate());

					Mensagem = "A data do seu exame vence em ".concat(String.valueOf(periodo.getDays())).concat(" dia(s),retorne ao medico antes do vencimento ou sera necesssario outra consulta");
				}

			}else if(historiciP.getDataAtualizacao().plusDays(30).isBefore(dataAtual)) {
				Mensagem = "Lembre - se, depois de 30 dias o seu retorno perde a validade e será necessario marcar outra consulta. Se voce está recebendo essa mensagem, o seu retorno ainda não venceu";
			}
			
			criaNotificacao(Mensagem, paciente.getTelefoneCelular());
		}
	}
	
}
