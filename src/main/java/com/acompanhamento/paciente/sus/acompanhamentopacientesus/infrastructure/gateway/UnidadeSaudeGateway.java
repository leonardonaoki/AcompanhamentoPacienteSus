package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IUnidadeSaudeRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IUnidadeSaudeMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnidadeSaudeGateway implements IUnidadeSaudeGateway {

    private final IUnidadeSaudeRepository unidadeSaudeRepository;
    private final IUnidadeSaudeMapper unidadeSaudeMapper;

    @Value("${controleunidade-base-path}")
    private String urlControleUnidade;

    // Método para listar as unidades de saúde com paginação
    @Override
    public List<UnidadeSaudeDTO> listarUnidadesSaude(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<UnidadeSaudeEntity> listaUnidades = unidadeSaudeRepository.findAll(pageable);

        if (listaUnidades.isEmpty()) {
            throw new EntityNotFoundException("Não foi possível encontrar unidades de saúde.");
        }

        return listaUnidades.stream()
                .map(unidadeSaudeMapper::toDTO)
                .toList();
    }

    // Método para buscar unidade por ID
    @Override
    public UnidadeSaudeDTO buscarUnidadePorId(long id) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade de saúde não encontrada com o ID: " + id));

        return unidadeSaudeMapper.toDTO(unidadeEntity);
    }

    // Método para registrar uma nova unidade de saúde
    @Override
    @Transactional
    public InsertMessageDTO registrarUnidade(UnidadeSaudeDomain domain) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeMapper.toEntity(domain);

        UnidadeSaudeEntity entitySalvo = unidadeSaudeRepository.save(unidadeEntity);

        // Se necessário, pode-se interagir com outros serviços externos via WebClient aqui, similar ao exemplo no ProntuarioGateway.
        // Caso contrário, podemos retornar diretamente uma mensagem de sucesso.

        return new InsertMessageDTO("Unidade de saúde registrada com ID: " + entitySalvo.getId());
    }

    // Método para atualizar uma unidade de saúde
    @Override
    @Transactional
    public InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain domain) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade de saúde não encontrada com o ID: " + id));

        unidadeSaudeMapper.updateEntityFromDomain(domain, unidadeEntity); // Método para atualizar os campos da entidade com os dados do domain

        unidadeSaudeRepository.save(unidadeEntity);

        return new InsertMessageDTO("Unidade de saúde com ID: " + id + " foi atualizada.");
    }

    // Método para deletar uma unidade de saúde
    @Override
    @Transactional
    public InsertMessageDTO deletarUnidade(long id) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade de saúde não encontrada com o ID: " + id));

        unidadeSaudeRepository.delete(unidadeEntity);

        return new InsertMessageDTO("Unidade de saúde com ID: " + id + " foi deletada.");
    }
}
