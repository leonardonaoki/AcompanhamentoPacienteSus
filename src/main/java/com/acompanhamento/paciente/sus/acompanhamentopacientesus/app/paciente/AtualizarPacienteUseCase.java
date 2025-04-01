package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Caso de uso para atualizar as informações de um paciente.
 * <p>
 * Esta classe é responsável por orquestrar o processo de atualização de um paciente,
 * incluindo a definição do momento da atualização e delegando a atualização real
 * para o gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AtualizarPacienteUseCase {

    /**
     * Gateway utilizado para persistir as atualizações de um paciente.
     */
    private final IPacienteGateway pacienteGateway;

    /**
     * Atualiza as informações de um paciente.
     * <p>
     * Este método recebe um identificador único do paciente e os novos dados do paciente,
     * define a data de atualização como o momento atual e chama o gateway para persistir
     * as alterações no banco de dados.
     * </p>
     *
     * @param id     O identificador único do paciente a ser atualizado.
     * @param domain O objeto {@link PacienteDomain} com os novos dados do paciente.
     * @return Um objeto {@link PacienteDTO} com os dados atualizados do paciente.
     * @throws SomeException Caso ocorra algum erro ao tentar atualizar o paciente,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IPacienteGateway}.
     */
    public PacienteDTO atualizarPaciente(long id, PacienteDomain domain) {
        // Define a data de atualização como o momento atual
        domain.setDataAtualizacao(LocalDateTime.now());

        // Chama o gateway para realizar a atualização
        return pacienteGateway.atualizarPaciente(id, domain);
    }
}
