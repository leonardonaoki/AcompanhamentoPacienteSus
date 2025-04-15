package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import java.time.LocalDateTime;

/**
 * DTO para resposta de informações de um paciente.
 * <p>
 * Esta classe é utilizada para transferir os dados de um paciente em respostas de API.
 * </p>
 *
 * @param idPaciente Identificador único do paciente.
 * @param nome Nome do paciente.
 * @param cpf CPF do paciente.
 * @param endereco Endereço do paciente.
 * @param telefoneCelular Telefone para contado do paciente.
 * @param dataNascimento Data de nascimento do paciente.
 * @param dataCadastro Data de cadastro do paciente.
 * @param dataAtualizacao Data da última atualização dos dados do paciente.
 */
public record PacienteDTO(
        long idPaciente,
        String nome,
        String cpf,
        String endereco,
        String telefoneCelular,
        LocalDateTime dataNascimento,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}