package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;

import java.util.List;

public interface IPacienteMapper {
    PacienteDTO toDTO(PacienteEntity pacienteEntity);
    PacienteEntity toEntity(PacienteDomain pacienteDomain);
    PacienteDomain toDomain(PacienteDTO pacienteDTO);
    List<PacienteDTO> toDTOList(List<PacienteEntity> pacienteEntities); // Para mapear lista de entidades para lista de DTOs
}
