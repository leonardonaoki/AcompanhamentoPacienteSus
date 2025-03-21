package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;

public interface IProntuarioGateway {
    ProntuarioDTO listarProntuarioPacientePorIdControle(long idControle);
    InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain);
}
