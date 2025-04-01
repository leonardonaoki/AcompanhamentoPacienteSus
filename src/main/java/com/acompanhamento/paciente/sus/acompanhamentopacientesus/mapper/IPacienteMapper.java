package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;

import java.util.List;

/**
 * Interface responsável por realizar o mapeamento entre as camadas de domínio, DTOs e entidades JPA
 * da entidade {@link Paciente}.
 *
 * Esta interface define métodos para converter entre as representações de {@link PacienteDomain} (domínio),
 * {@link PacienteDTO} (DTO) e {@link PacienteEntity} (entidade JPA), facilitando a troca de informações
 * entre a camada de persistência e a camada de negócios ou apresentação.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IPacienteMapper {

    /**
     * Converte uma entidade {@link PacienteEntity} para um DTO {@link PacienteDTO}.
     *
     * @param pacienteEntity a entidade que será convertida.
     * @return o DTO correspondente à entidade.
     */
    PacienteDTO toDTO(PacienteEntity pacienteEntity);

    /**
     * Converte um objeto {@link PacienteDomain} para uma entidade {@link PacienteEntity}.
     *
     * @param pacienteDomain o objeto de domínio que será convertido.
     * @return a entidade correspondente ao objeto de domínio.
     */
    PacienteEntity toEntity(PacienteDomain pacienteDomain);

    /**
     * Converte um DTO {@link PacienteDTO} para um objeto de domínio {@link PacienteDomain}.
     *
     * @param pacienteDTO o DTO que será convertido.
     * @return o objeto de domínio correspondente ao DTO.
     */
    PacienteDomain toDomain(PacienteDTO pacienteDTO);

    /**
     * Converte uma lista de entidades {@link PacienteEntity} para uma lista de DTOs {@link PacienteDTO}.
     *
     * @param pacienteEntities a lista de entidades que será convertida.
     * @return a lista de DTOs correspondente às entidades.
     */
    List<PacienteDTO> toDTOList(List<PacienteEntity> pacienteEntities);
}
