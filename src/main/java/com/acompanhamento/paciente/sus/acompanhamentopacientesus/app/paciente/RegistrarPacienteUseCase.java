package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Caso de uso para registrar um novo paciente.
 * <p>
 * Esta classe é responsável por orquestrar o processo de registro de um novo paciente,
 * incluindo a definição das datas de cadastro e atualização como o momento atual,
 * e delegando a persistência dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class RegistrarPacienteUseCase {

    /**
     * Gateway utilizado para persistir os dados de um novo paciente.
     */
    private final IPacienteGateway pacienteGateway;

    /**
     * Registra um novo paciente no sistema.
     * <p>
     * Este método define a data de cadastro e a data de atualização do paciente como
     * o momento atual e delega a persistência dos dados ao gateway.
     * </p>
     *
     * @param domain O objeto {@link PacienteDomain} contendo os dados do novo paciente a ser registrado.
     * @return Um objeto {@link PacienteDTO} com os dados do paciente recém-registrado.
     * @throws SomeException Caso ocorra algum erro ao tentar registrar o paciente,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IPacienteGateway}.
     */
    public PacienteDTO registrarPaciente(PacienteDomain domain) {

        // Define as datas de cadastro e atualização como o momento atual
        domain.setDataCadastro(LocalDateTime.now());
        domain.setDataAtualizacao(LocalDateTime.now());

        // Chama o gateway para realizar o registro do paciente
        return pacienteGateway.registrarPaciente(domain);
    }
}
