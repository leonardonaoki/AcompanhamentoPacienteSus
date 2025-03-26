package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProntuarioPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idHistoricoPaciente;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;

    public ProntuarioPacienteEntity(
            long idHistoricoPaciente,
            String especialidadeMedico,
            LocalDateTime dataInicio,
            LocalDateTime dataValidade,
            String solicitacao,
            StatusSolicitacaoProntuario statusSolicitacaoProntuario){
        this.idHistoricoPaciente = idHistoricoPaciente;
        this.especialidadeMedico = especialidadeMedico;
        this.dataInicio = dataInicio;
        this.dataValidade = dataValidade;
        this.solicitacao = solicitacao;
        this.statusSolicitacaoProntuario = statusSolicitacaoProntuario;
    }
}
