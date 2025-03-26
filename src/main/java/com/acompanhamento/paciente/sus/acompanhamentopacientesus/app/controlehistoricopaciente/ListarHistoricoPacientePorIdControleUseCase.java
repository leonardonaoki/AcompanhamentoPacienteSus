package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarHistoricoPacientePorIdControleUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public ControleHistoricoDTO listarPacientePorIdControle(Long idControle){
        return controleHistoricoGateway.listarHistoricoPacientePorIdControle(idControle);
    }
}
