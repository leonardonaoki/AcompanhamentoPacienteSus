package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidade que representa um paciente.
 * <p>
 * Esta classe contém os dados de um paciente, incluindo identificador, nome, CPF, endereço,
 * data de nascimento, telefone ,data de cadastro e data de atualização.
 * </p>
 */
@Getter
@Setter
public class PacienteDomain {
    /**
     * Identificador único do paciente.
     */
    private long idPaciente;

    /**
     * Nome do paciente.
     */
    private String nome;

    /**
     * CPF do paciente.
     */
    private String cpf;

    /**
     * Endereço do paciente.
     */
    private String endereco;

    /**
     * Data de nascimento do paciente.
     */
    private LocalDateTime dataNascimento;

    /**
     * Data de cadastro do paciente.
     */
    private LocalDateTime dataCadastro;

    /**
     * telefone para contado do paciente 
     */
    private String telefone;

    /**
     * Data da última atualização dos dados do paciente.
     */
    private LocalDateTime dataAtualizacao;
    
}