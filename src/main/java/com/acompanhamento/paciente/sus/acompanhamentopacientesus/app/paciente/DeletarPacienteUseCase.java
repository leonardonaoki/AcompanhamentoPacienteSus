package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para deletar um paciente.
 * <p>
 * Esta classe é responsável por orquestrar o processo de deleção de um paciente,
 * delegando a responsabilidade de remoção dos dados para o gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class DeletarPacienteUseCase {

    /**
     * Gateway utilizado para realizar a remoção de um paciente.
     */
    private final IPacienteGateway pacienteGateway;

    /**
     * Deleta um paciente com base no identificador único.
     * <p>
     * Este método recebe o identificador de um paciente e delega a ação de deleção
     * ao gateway, removendo o paciente da base de dados.
     * </p>
     *
     * @param id O identificador único do paciente a ser deletado.
     * @throws SomeException Caso ocorra algum erro ao tentar deletar o paciente,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IPacienteGateway}.
     */
    public void deletarPaciente(long id) {
        // Chama o gateway para realizar a deleção do paciente
        pacienteGateway.deletarPaciente(id);
    }
}
