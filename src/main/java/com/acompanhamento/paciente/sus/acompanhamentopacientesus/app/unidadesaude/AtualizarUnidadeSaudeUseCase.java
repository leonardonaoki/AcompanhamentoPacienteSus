package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para atualizar uma unidade de saúde.
 * <p>
 * Esta classe é responsável por orquestrar o processo de atualização de uma unidade de saúde,
 * delegando a persistência dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AtualizarUnidadeSaudeUseCase {

    /**
     * Gateway utilizado para atualizar os dados de uma unidade de saúde.
     */
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    /**
     * Atualiza os dados de uma unidade de saúde existente.
     * <p>
     * Este método recebe o identificador de uma unidade de saúde e os novos dados a serem atualizados,
     * delegando a atualização ao gateway.
     * </p>
     *
     * @param id O identificador único da unidade de saúde a ser atualizada.
     * @param domain O objeto {@link UnidadeSaudeDomain} contendo os novos dados da unidade de saúde.
     * @return Um objeto {@link InsertMessageDTO} com a mensagem de sucesso ou falha da operação.
     * @throws SomeException Caso ocorra algum erro ao tentar atualizar os dados da unidade de saúde,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IUnidadeSaudeGateway}.
     */
    public InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain domain) {
        // Verifica se a unidade de saúde existe (opcional, dependendo da sua lógica de negócio)
        // Antes de fazer a atualização, validações podem ser feitas, como se o id é válido e se todos os campos estão presentes.

        // Atualiza a unidade de saúde via gateway
        return unidadeSaudeGateway.atualizarUnidade(id, domain);
    }
}