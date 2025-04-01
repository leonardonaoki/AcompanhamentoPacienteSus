package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatePacienteDTO {

    @NotBlank(message = "Nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "CPF não pode ser vazio.")
    private String cpf;

    @NotBlank(message = "Endereço não pode ser vazio.")
    private String endereco;

    @NotNull(message = "Data de nascimento não pode ser nula.")
    private LocalDateTime dataNascimento;

}
