package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import org.springframework.stereotype.Component;

/**
 * Implementação da interface {@link IUnidadeSaudeMapper}, responsável pelo mapeamento entre as camadas de domínio,
 * DTOs e entidades JPA para a entidade {@link UnidadeSaude}.
 *
 * Esta classe converte dados entre as camadas de persistência (entidades JPA), de apresentação (DTOs) e o domínio,
 * garantindo que as informações sejam consistentes ao longo da aplicação.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
@Component
public class UnidadeSaudeMapper implements IUnidadeSaudeMapper {

    /**
     * Converte uma entidade JPA {@link UnidadeSaudeEntity} para um DTO {@link UnidadeSaudeDTO}.
     *
     * @param unidadeSaudeEntity a entidade JPA que será convertida.
     * @return o DTO correspondente à entidade.
     */
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

    /**
     * Converte um DTO {@link UnidadeSaudeDTO} para um objeto de domínio {@link UnidadeSaudeDomain}.
     *
     * @param dto o DTO que será convertido.
     * @return o objeto de domínio correspondente ao DTO.
     */
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

    /**
     * Converte um objeto de domínio {@link UnidadeSaudeDomain} para uma entidade JPA {@link UnidadeSaudeEntity}.
     *
     * @param domain o objeto de domínio que será convertido.
     * @return a entidade correspondente ao objeto de domínio.
     */
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

    /**
     * Atualiza uma entidade {@link UnidadeSaudeEntity} com os dados de um objeto de domínio {@link UnidadeSaudeDomain}.
     *
     * @param domain o objeto de domínio com os dados atualizados.
     * @param unidadeSaudeEntity a entidade JPA que será atualizada.
     */
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
