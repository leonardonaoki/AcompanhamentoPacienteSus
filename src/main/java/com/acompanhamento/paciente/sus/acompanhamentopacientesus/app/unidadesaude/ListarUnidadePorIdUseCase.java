package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para listar uma unidade de saúde por ID.
 * <p>
 * Esta classe é responsável por orquestrar o processo de listagem de uma unidade de saúde específica,
 * delegando a recuperação dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ListarUnidadePorIdUseCase {

    /**
     * Gateway utilizado para recuperar os dados de uma unidade de saúde.
     */
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    /**
     * Lista uma unidade de saúde por ID.
     * <p>
     * Este método recupera uma unidade de saúde específica com base no seu identificador único.
     * </p>
     *
     * @param id O identificador único da unidade de saúde a ser listada.
     * @return Um objeto {@link UnidadeSaudeDTO} contendo os dados da unidade de saúde.
     * @throws SomeException Caso ocorra algum erro ao tentar recuperar os dados da unidade de saúde,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IUnidadeSaudeGateway}.
     */
    public UnidadeSaudeDTO listarUnidadePorId(long id) {
        return unidadeSaudeGateway.buscarUnidadePorId(id);
    }
}