package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProntuarioPacienteDomain {
    private long idControle;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;
    public ProntuarioPacienteDomain(String especialidadeMedico,
                             LocalDateTime dataInicio,
                             LocalDateTime dataValidade,
                             String solicitacao,
                             StatusSolicitacaoProntuario statusSolicitacaoProntuario){
        this.especialidadeMedico = especialidadeMedico;
        this.dataInicio = dataInicio;
        this.dataValidade = dataValidade;
        this.solicitacao = solicitacao;
        this.statusSolicitacaoProntuario = statusSolicitacaoProntuario;
    }
}