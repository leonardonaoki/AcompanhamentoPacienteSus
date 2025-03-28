package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;

import java.time.LocalDateTime;
import java.util.List;

public interface IProntuarioGateway {
    List<ProntuarioDTO> listarProntuarioPacientePorIdControle(long idControle, String especialidade, LocalDateTime data, String solicitacao, StatusSolicitacaoProntuario statusSolicitacaoProntuario, int offset, int limit);
    InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain);
}
