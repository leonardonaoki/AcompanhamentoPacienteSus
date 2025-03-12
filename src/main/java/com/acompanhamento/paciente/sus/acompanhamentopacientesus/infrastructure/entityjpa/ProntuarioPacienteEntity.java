package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProntuarioPacienteEntity {
    @Id
    private long id;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;
}
