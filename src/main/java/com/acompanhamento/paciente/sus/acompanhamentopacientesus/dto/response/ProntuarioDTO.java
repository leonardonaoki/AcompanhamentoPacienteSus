package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;
import java.time.LocalDateTime;

public record ProntuarioDTO
(
    long idHistoricoPaciente,
    String especialidadeMedico,
    LocalDateTime dataInicio,
    LocalDateTime dataValidade,
    String solicitacao,
    String statusSolicitacaoProntuario
) {}