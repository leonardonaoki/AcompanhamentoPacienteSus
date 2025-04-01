package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarUnidadeSaudeUseCase {
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    public void deletarUnidade(long id) {
        unidadeSaudeGateway.deletarUnidade(id);
    }
}
