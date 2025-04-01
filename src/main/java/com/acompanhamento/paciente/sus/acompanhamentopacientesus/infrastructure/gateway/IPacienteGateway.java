package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;

import java.util.List;

/**
 * Interface que define os métodos para interagir com os dados do paciente.
 *
 * Esta interface serve como um contrato para os gateways responsáveis pela
 * manipulação dos dados dos pacientes no sistema. Ela define operações CRUD
 * (Criar, Ler, Atualizar, Deletar) e busca de pacientes, tanto individualmente
 * quanto em listas.
 *
 * A interface será implementada por classes responsáveis pela comunicação com
 * a camada de persistência de dados (ex: banco de dados).
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IPacienteGateway {

    /**
     * Registra um novo paciente no sistema.
     *
     * Este método é responsável por criar um novo registro de paciente com base
     * nos dados fornecidos no objeto {@link PacienteDomain}. O método retorna
     * um {@link PacienteDTO} contendo os dados do paciente recém-criado.
     *
     * @param domain Objeto que contém os dados do paciente a ser registrado.
     * @return O {@link PacienteDTO} com os dados do paciente registrado.
     */
    PacienteDTO registrarPaciente(PacienteDomain domain); // Create

    /**
     * Atualiza os dados de um paciente existente no sistema.
     *
     * Este método é responsável por atualizar as informações de um paciente
     * com base no ID fornecido e nos dados do paciente no objeto {@link PacienteDomain}.
     * O método retorna um {@link PacienteDTO} com os dados atualizados do paciente.
     *
     * @param id O ID do paciente que será atualizado.
     * @param domain Objeto que contém os dados atualizados do paciente.
     * @return O {@link PacienteDTO} com os dados do paciente atualizado.
     */
    PacienteDTO atualizarPaciente(long id, PacienteDomain domain); // Update

    /**
     * Lista os dados de um paciente específico com base no ID.
     *
     * Este método busca os dados de um paciente no sistema usando o ID fornecido
     * e retorna um {@link PacienteDTO} com as informações do paciente correspondente.
     *
     * @param id O ID do paciente a ser buscado.
     * @return O {@link PacienteDTO} com os dados do paciente correspondente.
     */
    PacienteDTO listarPacientePorId(long id); // Read (Single)

    /**
     * Lista todos os pacientes registrados no sistema.
     *
     * Este método retorna uma lista de todos os pacientes no sistema, representados
     * por uma lista de objetos {@link PacienteDTO}.
     *
     * @return Uma lista de {@link PacienteDTO} com os dados de todos os pacientes.
     */
    List<PacienteDTO> listarTodosPacientes(); // Read (All)

    /**
     * Deleta um paciente do sistema com base no ID.
     *
     * Este método é responsável por excluir um paciente do sistema utilizando o ID
     * fornecido. Não retorna nenhum valor após a execução.
     *
     * @param id O ID do paciente a ser deletado.
     */
    void deletarPaciente(long id); // Delete
}
