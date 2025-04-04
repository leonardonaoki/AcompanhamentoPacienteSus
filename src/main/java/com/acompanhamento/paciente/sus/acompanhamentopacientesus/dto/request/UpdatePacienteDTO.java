package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO para atualização de um paciente.
 * <p>
 * Esta classe é utilizada para transferir os dados necessários para a atualização de um paciente existente.
 * </p>
 *
 * @param nome Nome do paciente.
 * @param cpf CPF do paciente.
 * @param endereco Endereço do paciente.
 * @param dataNascimento Data de nascimento do paciente.
 */
@Getter
@Setter
public class UpdatePacienteDTO {

    /**
     * Nome do paciente.
     */
    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;

    /**
     * CPF do paciente.
     */
    @NotBlank(message = "CPF não pode ser vazio.")
    private String cpf;

    /**
     * Endereço do paciente.
     */
    @NotBlank(message = "Endereço não pode ser vazio.")
    private String endereco;

    /**
     * Data de nascimento do paciente.
     */
    @NotNull(message = "Data de nascimento não pode ser nula.")
    private LocalDateTime dataNascimento;

}