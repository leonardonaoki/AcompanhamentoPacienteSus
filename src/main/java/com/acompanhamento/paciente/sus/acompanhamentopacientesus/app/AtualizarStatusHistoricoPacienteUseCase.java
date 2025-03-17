package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtualizarStatusHistoricoPacienteUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public ControleHistoricoDTO atualizarStatusHistoricoPaciente(long id, StatusHistoricoPaciente status){
        return controleHistoricoGateway.atualizarStatusHistoricoPaciente(id,status);
    }
}
