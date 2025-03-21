package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarStatusHistoricoPacienteUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public ControleHistoricoDTO atualizarStatusHistoricoPaciente(long id, StatusHistoricoPaciente status){
        return controleHistoricoGateway.atualizarStatusHistoricoPaciente(id,status);
    }
}
