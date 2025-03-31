package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ControleHistoricoPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHistoricoPaciente;
    private long idPaciente;
    private long idUnidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private StatusHistoricoPaciente statusControle;
}


