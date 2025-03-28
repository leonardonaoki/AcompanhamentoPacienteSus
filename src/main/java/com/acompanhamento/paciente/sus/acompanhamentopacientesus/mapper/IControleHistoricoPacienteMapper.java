package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;

public interface IControleHistoricoPacienteMapper {
    ControleHistoricoDTO toDTO(ControleHistoricoPacienteEntity controleHistorico);
    ControleHistoricoPacienteDomain toDomain(InsertControleHistoricoDTO dto);
    ControleHistoricoPacienteEntity toEntity(ControleHistoricoPacienteDomain domain);
}
