package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
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
