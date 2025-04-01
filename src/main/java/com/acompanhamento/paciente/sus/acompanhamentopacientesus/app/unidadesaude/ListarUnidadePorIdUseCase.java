package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarUnidadePorIdUseCase {
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    public UnidadeSaudeDTO listarUnidadePorId(long id) {
        return unidadeSaudeGateway.buscarUnidadePorId(id);
    }
}
