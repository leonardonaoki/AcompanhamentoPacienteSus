package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPaciente;

    private String nome;
    private String cpf;
    private String endereco;
    private LocalDateTime dataNascimento;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
