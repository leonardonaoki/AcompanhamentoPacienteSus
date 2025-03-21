package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;

import java.time.LocalDateTime;

public record ProntuarioDTO
(
    long idHistoricoPaciente,
    String especialidadeMedico,
    LocalDateTime dataInicio,
    LocalDateTime dataValidade,
    String solicitacao,
    StatusSolicitacaoProntuario statusSolicitacaoProntuario
) {}