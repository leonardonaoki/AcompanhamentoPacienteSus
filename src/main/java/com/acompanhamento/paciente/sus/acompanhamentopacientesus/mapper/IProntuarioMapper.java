package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;

public interface IProntuarioMapper {
    ProntuarioDTO toDTO(ProntuarioPacienteEntity prontuario);
    ProntuarioPacienteDomain toDomain(InsertProntuarioDTO dto);
    ProntuarioPacienteEntity toEntity(ProntuarioPacienteDomain domain);
}
