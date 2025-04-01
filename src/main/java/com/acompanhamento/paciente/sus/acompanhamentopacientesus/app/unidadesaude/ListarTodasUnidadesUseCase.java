package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTodasUnidadesUseCase {
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    public List<UnidadeSaudeDTO> listarTodasUnidades() {
        return unidadeSaudeGateway.listarUnidadesSaude(0, Integer.MAX_VALUE); // Paginação com limite máximo
    }
}
