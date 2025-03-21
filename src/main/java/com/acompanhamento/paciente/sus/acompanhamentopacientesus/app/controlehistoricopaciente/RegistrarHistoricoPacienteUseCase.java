package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrarHistoricoPacienteUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public InsertMessageDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain){
        domain.setDataCadastro(LocalDateTime.now());
        domain.setDataAtualizacao(LocalDateTime.now());
        domain.setStatusControle(StatusHistoricoPaciente.PRIMEIRA_CONSULTA);
        return controleHistoricoGateway.registrarHistoricoPaciente(domain);
    }
}
