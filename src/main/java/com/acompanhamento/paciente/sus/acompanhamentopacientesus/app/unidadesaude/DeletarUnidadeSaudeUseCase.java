package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para deletar uma unidade de saúde.
 * <p>
 * Esta classe é responsável por orquestrar o processo de deleção de uma unidade de saúde,
 * delegando a remoção dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class DeletarUnidadeSaudeUseCase {

    /**
     * Gateway utilizado para deletar os dados de uma unidade de saúde.
     */
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    /**
     * Deleta uma unidade de saúde existente.
     * <p>
     * Este método recebe o identificador de uma unidade de saúde e delega a remoção ao gateway.
     * </p>
     *
     * @param id O identificador único da unidade de saúde a ser deletada.
     * @throws SomeException Caso ocorra algum erro ao tentar deletar os dados da unidade de saúde,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IUnidadeSaudeGateway}.
     */
    public void deletarUnidade(long id) {
        unidadeSaudeGateway.deletarUnidade(id);
    }
}