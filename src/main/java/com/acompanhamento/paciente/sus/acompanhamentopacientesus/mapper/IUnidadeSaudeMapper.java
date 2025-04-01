package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;

public interface IUnidadeSaudeMapper {

    // Converte a entidade JPA para o DTO
    UnidadeSaudeDTO toDTO(UnidadeSaudeEntity unidadeSaudeEntity);

    // Converte o DTO para o Domain
    UnidadeSaudeDomain toDomain(UnidadeSaudeDTO dto);

    // Converte o Domain para a entidade JPA
    UnidadeSaudeEntity toEntity(UnidadeSaudeDomain domain);

    // Método para atualizar a entidade com dados do Domain (caso seja necessário)
    void updateEntityFromDomain(UnidadeSaudeDomain domain, UnidadeSaudeEntity unidadeSaudeEntity);
}
