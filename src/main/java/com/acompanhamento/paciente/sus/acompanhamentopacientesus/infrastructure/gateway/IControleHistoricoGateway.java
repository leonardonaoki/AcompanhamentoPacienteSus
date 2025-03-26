package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;

import java.time.LocalDateTime;
import java.util.List;

public interface IControleHistoricoGateway {
    ControleHistoricoDTO listarHistoricoPacientePorIdControle(Long idControle);
    List<ControleHistoricoDTO> listarHistoricoPacientePorId(Long idPaciente, Long idUnidade, LocalDateTime data, StatusHistoricoPaciente statusHistoricoPaciente);
    InsertMessageDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain);
    ControleHistoricoDTO atualizarStatusHistoricoPaciente(Long id, StatusHistoricoPaciente status);
}
