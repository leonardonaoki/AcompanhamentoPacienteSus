package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso para listar todas as unidades de saúde.
 * <p>
 * Esta classe é responsável por orquestrar o processo de listagem de todas as unidades de saúde,
 * delegando a recuperação dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ListarTodasUnidadesUseCase {

    /**
     * Gateway utilizado para recuperar os dados das unidades de saúde.
     */
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    /**
     * Lista todas as unidades de saúde.
     * <p>
     * Este método recupera todas as unidades de saúde, utilizando paginação com limite máximo.
     * </p>
     *
     * @return Uma lista de objetos {@link UnidadeSaudeDTO} contendo os dados das unidades de saúde.
     * @throws SomeException Caso ocorra algum erro ao tentar recuperar os dados das unidades de saúde,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IUnidadeSaudeGateway}.
     */
    public List<UnidadeSaudeDTO> listarTodasUnidades() {
        return unidadeSaudeGateway.listarUnidadesSaude(0, Integer.MAX_VALUE); // Paginação com limite máximo
    }
}