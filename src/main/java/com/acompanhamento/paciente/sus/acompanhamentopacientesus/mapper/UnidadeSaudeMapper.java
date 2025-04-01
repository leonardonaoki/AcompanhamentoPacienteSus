package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import org.springframework.stereotype.Component;

@Component
public class UnidadeSaudeMapper implements IUnidadeSaudeMapper {

    @Override
    public UnidadeSaudeDTO toDTO(UnidadeSaudeEntity unidadeSaudeEntity) {
        return new UnidadeSaudeDTO(
                unidadeSaudeEntity.getId(),
                unidadeSaudeEntity.getNomeUnidade(),
                unidadeSaudeEntity.getEndereco(),
                unidadeSaudeEntity.getTipoUnidade(),
                unidadeSaudeEntity.getTelefone(),
                unidadeSaudeEntity.getHoraAbertura(),
                unidadeSaudeEntity.getHoraFechamento()
        );
    }

    @Override
    public UnidadeSaudeDomain toDomain(UnidadeSaudeDTO dto) {
        UnidadeSaudeDomain domain = new UnidadeSaudeDomain();
        domain.setId(dto.id());
        domain.setNomeUnidade(dto.nomeUnidade());
        domain.setEndereco(dto.endereco());
        domain.setTipoUnidade(dto.tipoUnidade());
        domain.setTelefone(dto.telefone());
        domain.setHoraAbertura(dto.horaAbertura());
        domain.setHoraFechamento(dto.horaFechamento());
        return domain;
    }

    @Override
    public UnidadeSaudeEntity toEntity(UnidadeSaudeDomain domain) {
        UnidadeSaudeEntity entity = new UnidadeSaudeEntity();
        entity.setId(domain.getId());
        entity.setNomeUnidade(domain.getNomeUnidade());
        entity.setEndereco(domain.getEndereco());
        entity.setTipoUnidade(domain.getTipoUnidade());
        entity.setTelefone(domain.getTelefone());
        entity.setHoraAbertura(domain.getHoraAbertura());
        entity.setHoraFechamento(domain.getHoraFechamento());
        return entity;
    }

    @Override
    public void updateEntityFromDomain(UnidadeSaudeDomain domain, UnidadeSaudeEntity unidadeSaudeEntity) {
        unidadeSaudeEntity.setNomeUnidade(domain.getNomeUnidade());
        unidadeSaudeEntity.setEndereco(domain.getEndereco());
        unidadeSaudeEntity.setTipoUnidade(domain.getTipoUnidade());
        unidadeSaudeEntity.setTelefone(domain.getTelefone());
        unidadeSaudeEntity.setHoraAbertura(domain.getHoraAbertura());
        unidadeSaudeEntity.setHoraFechamento(domain.getHoraFechamento());
    }
}
