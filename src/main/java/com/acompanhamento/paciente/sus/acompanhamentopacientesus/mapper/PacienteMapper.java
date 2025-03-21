package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PacienteMapper implements IPacienteMapper {

    @Override
    public PacienteDTO toDTO(PacienteEntity pacienteEntity) {
        return new PacienteDTO(
                pacienteEntity.getIdPaciente(),
                pacienteEntity.getNome(),
                pacienteEntity.getCpf(),
                pacienteEntity.getEndereco(),
                pacienteEntity.getDataNascimento(),
                pacienteEntity.getDataCadastro(),
                pacienteEntity.getDataAtualizacao()
        );
    }

    @Override
    public PacienteEntity toEntity(PacienteDomain pacienteDomain) {
        PacienteEntity entity = new PacienteEntity();
        entity.setNome(pacienteDomain.getNome());
        entity.setCpf(pacienteDomain.getCpf());
        entity.setEndereco(pacienteDomain.getEndereco());
        entity.setDataNascimento(pacienteDomain.getDataNascimento());
        entity.setDataCadastro(pacienteDomain.getDataCadastro());
        entity.setDataAtualizacao(pacienteDomain.getDataAtualizacao());
        return entity;
    }

    @Override
    public PacienteDomain toDomain(PacienteDTO pacienteDTO) {
        PacienteDomain domain = new PacienteDomain();
        domain.setIdPaciente(pacienteDTO.idPaciente());
        domain.setNome(pacienteDTO.nome());
        domain.setCpf(pacienteDTO.cpf());
        domain.setEndereco(pacienteDTO.endereco());
        domain.setDataNascimento(pacienteDTO.dataNascimento());
        domain.setDataCadastro(pacienteDTO.dataCadastro());
        domain.setDataAtualizacao(pacienteDTO.dataAtualizacao());
        return domain;
    }

    @Override
    public List<PacienteDTO> toDTOList(List<PacienteEntity> pacienteEntities) {
        return pacienteEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
