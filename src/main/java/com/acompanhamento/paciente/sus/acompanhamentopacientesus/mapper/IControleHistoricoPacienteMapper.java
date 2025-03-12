package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;

public interface IControleHistoricoPacienteMapper {
    ControleHistoricoDTO toDTO(ControleHistoricoPacienteEntity controleHistorico);
    ControleHistoricoPacienteDomain toDomain(InsertControleHistoricoDTO dto);
    ControleHistoricoPacienteEntity toEntity(ControleHistoricoPacienteDomain domain);
}
