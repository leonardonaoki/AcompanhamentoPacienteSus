package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;

import java.time.LocalDateTime;
public record ControleHistoricoDTO
(
    long idHistoricoPaciente,
    long idPaciente,
    long idUnidade,
    LocalDateTime dataCadastro,
    LocalDateTime dataAtualizacao,
    StatusHistoricoPaciente statusControle
) {}