package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para inserção de um novo paciente.
 * <p>
 * Esta classe é utilizada para transferir os dados necessários para a criação de um novo paciente.
 * </p>
 *
 * @param idPaciente Identificador único do paciente.
 * @param nome Nome do paciente.
 * @param cpf CPF do paciente.
 * @param endereco Endereço do paciente.
 * @param dataNascimento Data de nascimento do paciente.
 */
public record InsertPacienteDTO(
        @Positive long idPaciente,

        @NotNull @Size(min = 3, max = 100)
        String nome,

        @NotNull @Size(min = 11, max = 11)
        String cpf,

        @Size(max = 255)
        String endereco,
        
        @Size(max = 11)
        String telefoneCelular,

        @NotNull
        LocalDateTime dataNascimento
        
        
        
) {
}