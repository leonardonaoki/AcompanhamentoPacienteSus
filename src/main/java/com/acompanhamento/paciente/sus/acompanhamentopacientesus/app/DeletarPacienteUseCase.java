package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarPacienteUseCase {
    private final IPacienteGateway pacienteGateway;

    public void deletarPaciente(long id) {
        pacienteGateway.deletarPaciente(id);
    }
}
