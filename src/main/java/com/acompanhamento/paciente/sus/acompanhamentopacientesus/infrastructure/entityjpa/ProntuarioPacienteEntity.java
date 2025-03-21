package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProntuarioPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String especialidadeMedico;
    private LocalDateTime dataInicio;
    private LocalDateTime dataValidade;
    private String solicitacao;
    private StatusSolicitacaoProntuario statusSolicitacaoProntuario;
}
