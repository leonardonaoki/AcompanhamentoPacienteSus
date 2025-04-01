package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso para listar todos os pacientes.
 * <p>
 * Esta classe é responsável por orquestrar o processo de recuperação de todos os pacientes
 * cadastrados. A consulta é delegada ao gateway de persistência, que realiza a busca
 * no banco de dados.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ListarTodosPacientesUseCase {

    /**
     * Gateway utilizado para buscar todos os pacientes.
     */
    private final IPacienteGateway pacienteGateway;

    /**
     * Recupera todos os pacientes cadastrados no sistema.
     * <p>
     * Este método delega a busca ao gateway e retorna uma lista com os dados de todos
     * os pacientes cadastrados no sistema.
     * </p>
     *
     * @return Uma lista de objetos {@link PacienteDTO} contendo os dados de todos os pacientes.
     * @throws SomeException Caso ocorra algum erro ao tentar recuperar os dados dos pacientes,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IPacienteGateway}.
     */
    public List<PacienteDTO> listarTodosPacientes() {
        // Chama o gateway para recuperar a lista de todos os pacientes
        return pacienteGateway.listarTodosPacientes();
    }
}
