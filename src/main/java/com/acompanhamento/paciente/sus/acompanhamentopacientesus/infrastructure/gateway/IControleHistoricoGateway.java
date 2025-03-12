package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.ControleHistoricoDTO;

public interface IControleHistoricoGateway {
    ControleHistoricoDTO listarHistoricoPacientePorId(long id);
    ControleHistoricoDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain);
}
