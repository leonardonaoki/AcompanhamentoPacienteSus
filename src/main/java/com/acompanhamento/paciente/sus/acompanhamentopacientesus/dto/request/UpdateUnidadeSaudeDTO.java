package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateUnidadeSaudeDTO {

    @NotBlank(message = "Nome da unidade não pode ser vazio.")
    private String nomeUnidade;

    @NotBlank(message = "Endereço não pode ser vazio.")
    private String endereco;

    @NotBlank(message = "Tipo da unidade não pode ser vazio.")
    private String tipoUnidade;  // Ex: Hospital, Posto de Saúde, Clínica

    @NotBlank(message = "Telefone não pode ser vazio.")
    private String telefone;

    @NotNull(message = "Hora de abertura não pode ser nula.")
    private LocalTime horaAbertura;

    @NotNull(message = "Hora de fechamento não pode ser nula.")
    private LocalTime horaFechamento;

}
