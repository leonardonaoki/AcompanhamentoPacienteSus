package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProntuarioPacienteDomain {
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;
}