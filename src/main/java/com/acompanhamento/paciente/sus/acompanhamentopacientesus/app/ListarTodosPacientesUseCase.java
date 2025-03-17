package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTodosPacientesUseCase {
    private final IPacienteGateway pacienteGateway;

    public List<PacienteDTO> listarTodosPacientes() {
        return pacienteGateway.listarTodosPacientes();
    }
}
