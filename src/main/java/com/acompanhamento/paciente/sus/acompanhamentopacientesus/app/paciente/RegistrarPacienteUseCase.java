package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrarPacienteUseCase {
    private final IPacienteGateway pacienteGateway;

    public PacienteDTO registrarPaciente(PacienteDomain domain) {

        domain.setDataCadastro(LocalDateTime.now());
        domain.setDataAtualizacao(LocalDateTime.now());
        return pacienteGateway.registrarPaciente(domain);
    }
}
