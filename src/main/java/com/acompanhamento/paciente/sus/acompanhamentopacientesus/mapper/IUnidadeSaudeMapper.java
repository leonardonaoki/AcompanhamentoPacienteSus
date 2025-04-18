package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUpdateUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;

/**
 * Interface responsável por realizar o mapeamento entre as camadas de domínio, DTOs e entidades JPA
 * da entidade
 *
 * Esta interface define métodos para converter entre as representações de {@link UnidadeSaudeDomain} (domínio),
 * {@link UnidadeSaudeDTO} (DTO) e {@link UnidadeSaudeEntity} (entidade JPA), facilitando a troca de informações
 * entre a camada de persistência e a camada de negócios ou apresentação.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IUnidadeSaudeMapper {

    /**
     * Converte uma entidade {@link UnidadeSaudeEntity} para um DTO {@link UnidadeSaudeDTO}.
     *
     * @param unidadeSaudeEntity a entidade que será convertida.
     * @return o DTO correspondente à entidade.
     */
    UnidadeSaudeDTO toDTO(UnidadeSaudeEntity unidadeSaudeEntity);

    /**
     * Converte um DTO {@link UnidadeSaudeDTO} para um objeto de domínio {@link UnidadeSaudeDomain}.
     *
     * @param dto o DTO que será convertido.
     * @return o objeto de domínio correspondente ao DTO.
     */
    UnidadeSaudeDomain toDomain(InsertUpdateUnidadeSaudeDTO dto);

    /**
     * Converte um objeto {@link UnidadeSaudeDomain} para uma entidade {@link UnidadeSaudeEntity}.
     *
     * @param domain o objeto de domínio que será convertido.
     * @return a entidade correspondente ao objeto de domínio.
     */
    UnidadeSaudeEntity toEntity(UnidadeSaudeDomain domain);

    /**
     * Atualiza os campos de uma entidade {@link UnidadeSaudeEntity} com os dados de um objeto {@link UnidadeSaudeDomain}.
     *
     * @param domain o objeto de domínio com os novos dados.
     * @param unidadeSaudeEntity a entidade que será atualizada.
     */
    void updateEntityFromDomain(UnidadeSaudeDomain domain, UnidadeSaudeEntity unidadeSaudeEntity);
}
