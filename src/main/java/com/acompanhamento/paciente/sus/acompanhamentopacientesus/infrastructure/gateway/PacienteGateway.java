package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementação do gateway responsável pela manipulação de dados dos pacientes no sistema.
 *
 * A classe {@link PacienteGateway} implementa a interface {@link IPacienteGateway} e é responsável
 * por fornecer a implementação das operações CRUD (Criar, Ler, Atualizar, Deletar) para a entidade paciente.
 *
 * A classe realiza a interação com o repositório de dados {@link IPacienteRepository}, mapeando as entidades
 * para objetos DTO através do {@link IPacienteMapper}.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
@Component
@RequiredArgsConstructor
public class PacienteGateway implements IPacienteGateway {

    private static final String ERRO_ID_NAO_ENCONTRADO = "Não foi possível identificar o paciente com o ID ";

    private final IPacienteRepository pacienteRepository;
    private final IPacienteMapper pacienteMapper;

    /**
     * Registra um novo paciente no sistema.
     *
     * Este método cria um novo paciente no sistema, salvando seus dados no banco de dados.
     * Os dados do paciente são convertidos de um objeto {@link PacienteDomain} para um {@link PacienteEntity},
     * e após o salvamento, o paciente é retornado como um {@link PacienteDTO}.
     *
     * @param domain Objeto {@link PacienteDomain} contendo as informações do paciente a ser registrado.
     * @return O {@link PacienteDTO} com os dados do paciente recém-criado.
     */
    @Override
    public PacienteDTO registrarPaciente(PacienteDomain domain) {
        PacienteEntity entitySalvo = pacienteRepository.save(pacienteMapper.toEntity(domain));
        return pacienteMapper.toDTO(entitySalvo);
    }

    /**
     * Atualiza os dados de um paciente existente no sistema.
     *
     * Este método localiza um paciente pelo ID e, em seguida, atualiza os dados do paciente com base nas informações
     * fornecidas no objeto {@link PacienteDomain}. Caso o paciente não seja encontrado, uma exceção {@link EntityNotFoundException}
     * é lançada. Após a atualização, o paciente é retornado como um {@link PacienteDTO}.
     *
     * @param id O ID do paciente a ser atualizado.
     * @param domain O objeto {@link PacienteDomain} contendo as informações atualizadas do paciente.
     * @return O {@link PacienteDTO} com os dados do paciente atualizado.
     * @throws EntityNotFoundException Caso o paciente com o ID fornecido não seja encontrado.
     */
    @Override
    public PacienteDTO atualizarPaciente(long id, PacienteDomain domain) {
        PacienteEntity pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));

        pacienteExistente.setNome(domain.getNome());
        pacienteExistente.setCpf(domain.getCpf());
        pacienteExistente.setEndereco(domain.getEndereco());
        pacienteExistente.setDataNascimento(domain.getDataNascimento());
        pacienteExistente.setDataAtualizacao(domain.getDataAtualizacao());

        PacienteEntity entityAtualizado = pacienteRepository.save(pacienteExistente);
        return pacienteMapper.toDTO(entityAtualizado);
    }

    /**
     * Lista os dados de um paciente específico com base no ID.
     *
     * Este método localiza um paciente pelo ID e retorna os dados do paciente como um {@link PacienteDTO}.
     * Caso o paciente não seja encontrado, uma exceção {@link EntityNotFoundException} é lançada.
     *
     * @param id O ID do paciente a ser buscado.
     * @return O {@link PacienteDTO} com os dados do paciente encontrado.
     * @throws EntityNotFoundException Caso o paciente com o ID fornecido não seja encontrado.
     */
    @Override
    public PacienteDTO listarPacientePorId(long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));
    }

    /**
     * Lista todos os pacientes registrados no sistema.
     *
     * Este método retorna uma lista de todos os pacientes no sistema, representados por uma lista de objetos {@link PacienteDTO}.
     *
     * @return Uma lista de {@link PacienteDTO} com os dados de todos os pacientes.
     */
    @Override
    public List<PacienteDTO> listarTodosPacientes() {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toDTOList(pacientes);
    }

    /**
     * Deleta um paciente do sistema com base no ID.
     *
     * Este método localiza um paciente pelo ID e, se encontrado, o deleta do sistema. Caso o paciente não seja encontrado,
     * uma exceção {@link EntityNotFoundException} é lançada.
     *
     * @param id O ID do paciente a ser deletado.
     * @throws EntityNotFoundException Caso o paciente com o ID fornecido não seja encontrado.
     */
    @Override
    public void deletarPaciente(long id) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));

        pacienteRepository.delete(paciente);
    }
}
