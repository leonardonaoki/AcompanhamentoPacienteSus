package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ControleHistoricoPacienteDomain {
    private long idPaciente;
    private long idUnidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private StatusHistoricoPaciente statusControle;
}


