package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para listar um paciente pelo seu identificador.
 * <p>
 * Esta classe é responsável por orquestrar a busca de informações de um paciente
 * com base no seu identificador único. A busca real é delegada ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ListarPacientePorIdUseCase {

    /**
     * Gateway utilizado para buscar os dados de um paciente.
     */
    private final IPacienteGateway pacienteGateway;

    /**
     * Recupera os dados de um paciente a partir do identificador único.
     * <p>
     * Este método recebe o identificador de um paciente e delega a busca ao gateway,
     * retornando os dados do paciente caso encontrado.
     * </p>
     *
     * @param id O identificador único do paciente a ser listado.
     * @return Um objeto {@link PacienteDTO} com os dados do paciente encontrado.
     * @throws SomeException Caso ocorra algum erro ao tentar recuperar os dados do paciente,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IPacienteGateway}.
     */
    public PacienteDTO listarPacientePorId(long id) {
        // Chama o gateway para recuperar os dados do paciente
        return pacienteGateway.listarPacientePorId(id);
    }
}
