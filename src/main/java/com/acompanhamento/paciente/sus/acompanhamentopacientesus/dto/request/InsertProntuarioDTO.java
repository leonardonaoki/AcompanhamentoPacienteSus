package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import java.time.LocalDateTime;

public record InsertProntuarioDTO(
        long idControleHistorico,
        String especialidadeMedico,
        LocalDateTime dataInicio,
        LocalDateTime dataValidade,
        String solicitacao,
        StatusSolicitacaoProntuario statusSolicitacaoProntuario
) {
}
