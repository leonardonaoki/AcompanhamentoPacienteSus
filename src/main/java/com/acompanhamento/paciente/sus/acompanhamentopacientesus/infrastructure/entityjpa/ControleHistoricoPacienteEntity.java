package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ControleHistoricoPacienteEntity {
    @Id
    private long idHistoricoPaciente;
    private long idPaciente;
    private long idUnidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private StatusHistoricoPaciente statusControle;
}


