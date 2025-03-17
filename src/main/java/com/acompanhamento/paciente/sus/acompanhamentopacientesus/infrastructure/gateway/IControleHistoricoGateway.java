package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;

public interface IControleHistoricoGateway {
    ControleHistoricoDTO listarHistoricoPacientePorId(long id);
    InsertMessageDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain);
    ControleHistoricoDTO atualizarStatusHistoricoPaciente(long id, StatusHistoricoPaciente status);
}
