package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PacienteDomain {
    private long idPaciente;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDateTime dataNascimento;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
