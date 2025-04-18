package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação da interface {@link IPacienteMapper}, responsável por realizar o mapeamento entre as camadas de domínio,
 * DTOs e entidades JPA da entidade .
 *
 * Esta classe converte os dados da camada de persistência (entidades JPA) para a camada de apresentação (DTOs) e vice-versa.
 * Também é responsável por mapear os dados entre o domínio e as entidades para garantir a consistência dos dados nas camadas do sistema.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
@Component
public class PacienteMapper implements IPacienteMapper {

    /**
     * Converte uma entidade {@link PacienteEntity} para um DTO {@link PacienteDTO}.
     *
     * @param pacienteEntity a entidade JPA que será convertida.
     * @return o DTO correspondente à entidade.
     */
    @Override
    public PacienteDTO toDTO(PacienteEntity pacienteEntity) {
        return new PacienteDTO(
                pacienteEntity.getIdPaciente(),
                pacienteEntity.getNome(),
                pacienteEntity.getCpf(),
                pacienteEntity.getEndereco(),
                pacienteEntity.getTelefoneCelular(),
                pacienteEntity.getDataNascimento(),
                pacienteEntity.getDataCadastro(),
                pacienteEntity.getDataAtualizacao()
        );
    }

    /**
     * Converte um objeto {@link PacienteDomain} para uma entidade {@link PacienteEntity}.
     *
     * @param pacienteDomain o objeto de domínio que será convertido.
     * @return a entidade correspondente ao objeto de domínio.
     */
    @Override
    public PacienteEntity toEntity(PacienteDomain pacienteDomain) {
        PacienteEntity entity = new PacienteEntity();
        entity.setNome(pacienteDomain.getNome());
        entity.setCpf(pacienteDomain.getCpf());
        entity.setEndereco(pacienteDomain.getEndereco());
        entity.setTelefoneCelular(pacienteDomain.getTelefone());
        entity.setDataNascimento(pacienteDomain.getDataNascimento());
        entity.setDataCadastro(pacienteDomain.getDataCadastro());
        entity.setDataAtualizacao(pacienteDomain.getDataAtualizacao());
        return entity;
    }

    /**
     * Converte um DTO {@link PacienteDTO} para um objeto de domínio {@link PacienteDomain}.
     *
     * @param pacienteDTO o DTO que será convertido.
     * @return o objeto de domínio correspondente ao DTO.
     */
    @Override
    public PacienteDomain toDomain(PacienteDTO pacienteDTO) {
        PacienteDomain domain = new PacienteDomain();
        domain.setNome(pacienteDTO.nome());
        domain.setCpf(pacienteDTO.cpf());
        domain.setEndereco(pacienteDTO.endereco());
        domain.setTelefone(pacienteDTO.telefoneCelular());
        domain.setDataNascimento(pacienteDTO.dataNascimento());
        domain.setDataCadastro(pacienteDTO.dataCadastro());
        domain.setDataAtualizacao(pacienteDTO.dataAtualizacao());
        return domain;
    }

    /**
     * Converte uma lista de entidades {@link PacienteEntity} para uma lista de DTOs {@link PacienteDTO}.
     *
     * @param pacienteEntities a lista de entidades JPA que será convertida.
     * @return a lista de DTOs correspondente às entidades.
     */
    @Override
    public List<PacienteDTO> toDTOList(List<PacienteEntity> pacienteEntities) {
        return pacienteEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
