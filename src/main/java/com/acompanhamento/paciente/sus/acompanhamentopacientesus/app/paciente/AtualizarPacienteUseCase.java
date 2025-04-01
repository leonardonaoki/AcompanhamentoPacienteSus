package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtualizarPacienteUseCase {
    private final IPacienteGateway pacienteGateway;

    public PacienteDTO atualizarPaciente(long id, PacienteDomain domain) {
        // Atualiza a data de atualização sempre que o paciente for modificado
        domain.setDataAtualizacao(LocalDateTime.now());
        return pacienteGateway.atualizarPaciente(id, domain);
    }
}
