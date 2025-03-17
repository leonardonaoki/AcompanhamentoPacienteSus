package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarHistoricoPacientePorIdUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public ControleHistoricoDTO listarPacientePorId(long id){
        return controleHistoricoGateway.listarHistoricoPacientePorId(id);
    }
}
