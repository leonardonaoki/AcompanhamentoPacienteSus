package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para registrar uma nova unidade de saúde.
 * <p>
 * Esta classe é responsável por orquestrar o processo de registro de uma nova unidade de saúde,
 * delegando a persistência dos dados ao gateway de persistência.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class RegistrarUnidadeSaudeUseCase {

    /**
     * Gateway utilizado para registrar os dados de uma unidade de saúde.
     */
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    /**
     * Registra uma nova unidade de saúde.
     * <p>
     * Este método recebe os dados de uma nova unidade de saúde e delega a persistência ao gateway.
     * </p>
     *
     * @param domain O objeto {@link UnidadeSaudeDomain} contendo os dados da nova unidade de saúde.
     * @return Um objeto {@link InsertMessageDTO} com a mensagem de sucesso ou falha da operação.
     * @throws SomeException Caso ocorra algum erro ao tentar registrar os dados da unidade de saúde,
     *                       uma exceção pode ser lançada, dependendo da implementação do {@link IUnidadeSaudeGateway}.
     */
    public InsertMessageDTO registrarUnidadeSaude(UnidadeSaudeDomain domain) {
        // Aqui, você pode adicionar alguma lógica adicional antes de salvar
        // como, por exemplo, a data de cadastro ou outras verificações.

        // Supondo que você precise definir a data de cadastro da unidade,
        // você pode adicionar um campo de data no seu domínio para isso.
        // Exemplo:
        // domain.setDataCadastro(LocalDateTime.now());

        return unidadeSaudeGateway.registrarUnidade(domain);
    }
}