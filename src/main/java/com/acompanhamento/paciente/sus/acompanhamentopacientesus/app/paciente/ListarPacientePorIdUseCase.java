package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarPacientePorIdUseCase {
    private final IPacienteGateway pacienteGateway;

    public PacienteDTO listarPacientePorId(long id) {
        return pacienteGateway.listarPacientePorId(id);
    }
}
