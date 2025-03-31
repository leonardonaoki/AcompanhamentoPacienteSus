package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProntuarioPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idHistoricoPaciente;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private String statusSolicitacaoProntuario;
}
