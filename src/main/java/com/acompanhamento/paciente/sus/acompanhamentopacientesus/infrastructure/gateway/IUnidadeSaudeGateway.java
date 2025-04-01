package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;

import java.util.List;

/**
 * Interface que define os métodos para interagir com as unidades de saúde.
 *
 * Esta interface serve como um contrato para os gateways responsáveis pela
 * manipulação dos dados das unidades de saúde no sistema. Ela define operações CRUD
 * (Criar, Ler, Atualizar, Deletar) e busca de unidades de saúde, com possibilidade
 * de paginação nas listagens.
 *
 * A interface será implementada por classes responsáveis pela comunicação com
 * a camada de persistência de dados (ex: banco de dados).
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IUnidadeSaudeGateway {

    /**
     * Lista todas as unidades de saúde, com possibilidade de paginação.
     *
     * Este método retorna uma lista de unidades de saúde paginada, com base nos
     * parâmetros de offset (ponto de partida) e limit (quantidade de unidades
     * de saúde a serem retornadas).
     *
     * @param offset Posição inicial na lista de unidades de saúde.
     * @param limit Quantidade máxima de unidades de saúde a serem retornadas.
     * @return Uma lista de {@link UnidadeSaudeDTO} contendo as unidades de saúde.
     */
    List<UnidadeSaudeDTO> listarUnidadesSaude(int offset, int limit);

    /**
     * Busca uma unidade de saúde específica pelo seu ID.
     *
     * Este método busca os dados de uma unidade de saúde com base no ID fornecido.
     * Retorna um {@link UnidadeSaudeDTO} com as informações da unidade de saúde correspondente.
     *
     * @param id O ID da unidade de saúde a ser buscada.
     * @return O {@link UnidadeSaudeDTO} com os dados da unidade de saúde correspondente.
     */
    UnidadeSaudeDTO buscarUnidadePorId(long id);

    /**
     * Registra uma nova unidade de saúde no sistema.
     *
     * Este método é responsável por criar uma nova unidade de saúde no sistema,
     * com base nos dados fornecidos no objeto {@link UnidadeSaudeDomain}.
     * O método retorna um {@link InsertMessageDTO} com uma mensagem de confirmação ou erro.
     *
     * @param unidadeSaudeDomain Objeto que contém os dados da nova unidade de saúde.
     * @return Um {@link InsertMessageDTO} contendo a mensagem de sucesso ou erro.
     */
    InsertMessageDTO registrarUnidade(UnidadeSaudeDomain unidadeSaudeDomain);

    /**
     * Atualiza os dados de uma unidade de saúde existente no sistema.
     *
     * Este método é responsável por atualizar os dados de uma unidade de saúde com base no ID
     * fornecido e nas informações do objeto {@link UnidadeSaudeDomain}.
     * O método retorna um {@link InsertMessageDTO} com uma mensagem de sucesso ou erro.
     *
     * @param id O ID da unidade de saúde a ser atualizada.
     * @param unidadeSaudeDomain Objeto que contém os dados atualizados da unidade de saúde.
     * @return Um {@link InsertMessageDTO} contendo a mensagem de sucesso ou erro.
     */
    InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain unidadeSaudeDomain);

    /**
     * Deleta uma unidade de saúde do sistema com base no ID.
     *
     * Este método é responsável por excluir uma unidade de saúde do sistema utilizando o ID
     * fornecido. Retorna um {@link InsertMessageDTO} com uma mensagem de sucesso ou erro.
     *
     * @param id O ID da unidade de saúde a ser deletada.
     * @return Um {@link InsertMessageDTO} contendo a mensagem de sucesso ou erro.
     */
    InsertMessageDTO deletarUnidade(long id);
}
