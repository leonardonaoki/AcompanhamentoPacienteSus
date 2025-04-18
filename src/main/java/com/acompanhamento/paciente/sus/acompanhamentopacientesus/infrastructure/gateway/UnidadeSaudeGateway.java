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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementação do gateway responsável pela manipulação de dados das unidades de saúde.
 *
 * A classe {@link UnidadeSaudeGateway} implementa a interface {@link IUnidadeSaudeGateway} e é responsável por
 * realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) para a entidade Unidade de Saúde. A classe interage
 * diretamente com o repositório {@link IUnidadeSaudeRepository} e mapeia os dados utilizando o {@link IUnidadeSaudeMapper}.
 *
 * A classe também contém métodos de interação com outros serviços externos, como demonstrado no método de registro.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
@Component
@RequiredArgsConstructor
public class UnidadeSaudeGateway implements IUnidadeSaudeGateway {

    private final IUnidadeSaudeRepository unidadeSaudeRepository;
    private final IUnidadeSaudeMapper unidadeSaudeMapper;
    private static final String ERRO_UNIDADE_NAO_EXISTE = "Unidade de saúde não encontrada com o ID: ";
    /**
     * Lista todas as unidades de saúde com paginação.
     *
     * Este método retorna uma lista paginada de unidades de saúde. O número de unidades retornadas é controlado
     * pelos parâmetros de offset (posição inicial) e limit (quantidade de unidades).
     *
     * @param offset Posição inicial na lista de unidades de saúde.
     * @param limit Quantidade máxima de unidades de saúde a serem retornadas.
     * @return Uma lista de {@link UnidadeSaudeDTO} com as unidades de saúde encontradas.
     * @throws EntityNotFoundException Caso não haja unidades de saúde para a consulta.
     */
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

    /**
     * Busca uma unidade de saúde pelo ID.
     *
     * Este método localiza uma unidade de saúde com base no ID fornecido e retorna um objeto {@link UnidadeSaudeDTO}.
     * Caso a unidade não seja encontrada, uma exceção {@link EntityNotFoundException} é lançada.
     *
     * @param id O ID da unidade de saúde a ser buscada.
     * @return O {@link UnidadeSaudeDTO} com os dados da unidade de saúde encontrada.
     * @throws EntityNotFoundException Caso a unidade de saúde com o ID fornecido não seja encontrada.
     */
    @Override
    public UnidadeSaudeDTO buscarUnidadePorId(long id) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_UNIDADE_NAO_EXISTE + id));

        return unidadeSaudeMapper.toDTO(unidadeEntity);
    }

    /**
     * Registra uma nova unidade de saúde no sistema.
     *
     * Este método cria uma nova unidade de saúde no banco de dados a partir dos dados fornecidos no objeto
     * {@link UnidadeSaudeDomain}. O método retorna uma mensagem de sucesso com o ID da unidade registrada.
     *
     * @param domain Objeto {@link UnidadeSaudeDomain} contendo os dados da nova unidade de saúde.
     * @return Um {@link InsertMessageDTO} com uma mensagem de confirmação.
     */
    @Override
    public InsertMessageDTO registrarUnidade(UnidadeSaudeDomain domain) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeMapper.toEntity(domain);
        UnidadeSaudeEntity entitySalvo = unidadeSaudeRepository.save(unidadeEntity);

        // Se necessário, pode-se interagir com outros serviços externos aqui, via WebClient.
        // Caso contrário, podemos retornar diretamente uma mensagem de sucesso.

        return new InsertMessageDTO("Unidade de saúde registrada com ID: " + entitySalvo.getId());
    }

    /**
     * Atualiza os dados de uma unidade de saúde existente no sistema.
     *
     * Este método localiza uma unidade de saúde com base no ID fornecido e atualiza seus dados com as informações
     * contidas no objeto {@link UnidadeSaudeDomain}. Se a unidade de saúde não for encontrada, uma exceção
     * {@link EntityNotFoundException} é lançada. Após a atualização, uma mensagem de sucesso é retornada.
     *
     * @param id O ID da unidade de saúde a ser atualizada.
     * @param domain Objeto {@link UnidadeSaudeDomain} contendo os novos dados da unidade de saúde.
     * @return Um {@link InsertMessageDTO} com uma mensagem de sucesso.
     * @throws EntityNotFoundException Caso a unidade de saúde com o ID fornecido não seja encontrada.
     */
    @Override
    @Transactional
    public InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain domain) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_UNIDADE_NAO_EXISTE + id));

        unidadeSaudeMapper.updateEntityFromDomain(domain, unidadeEntity); // Atualiza os campos da entidade

        unidadeSaudeRepository.save(unidadeEntity);

        return new InsertMessageDTO("Unidade de saúde com ID: " + id + " foi atualizada.");
    }

    /**
     * Deleta uma unidade de saúde do sistema.
     *
     * Este método localiza uma unidade de saúde com base no ID fornecido e a deleta do sistema. Se a unidade de saúde
     * não for encontrada, uma exceção {@link EntityNotFoundException} é lançada. Após a exclusão, uma mensagem de sucesso é retornada.
     *
     * @param id O ID da unidade de saúde a ser deletada.
     * @return Um {@link InsertMessageDTO} com uma mensagem de sucesso.
     * @throws EntityNotFoundException Caso a unidade de saúde com o ID fornecido não seja encontrada.
     */
    @Override
    @Transactional
    public InsertMessageDTO deletarUnidade(long id) {
        UnidadeSaudeEntity unidadeEntity = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_UNIDADE_NAO_EXISTE + id));

        unidadeSaudeRepository.delete(unidadeEntity);

        return new InsertMessageDTO("Unidade de saúde com ID: " + id + " foi deletada.");
    }
}
