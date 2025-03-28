package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProntuarioPacienteDomain {
    private long idControleHistorico;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private String statusSolicitacaoProntuario;
}