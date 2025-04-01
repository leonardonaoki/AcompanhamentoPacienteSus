package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarUnidadeSaudeUseCase {
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    public InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain domain) {
        // Verifica se a unidade de saúde existe (opcional, dependendo da sua lógica de negócio)
        // Antes de fazer a atualização, validações podem ser feitas, como se o id é válido e se todos os campos estão presentes.

        // Atualiza a unidade de saúde via gateway
        return unidadeSaudeGateway.atualizarUnidade(id, domain);
    }
}
