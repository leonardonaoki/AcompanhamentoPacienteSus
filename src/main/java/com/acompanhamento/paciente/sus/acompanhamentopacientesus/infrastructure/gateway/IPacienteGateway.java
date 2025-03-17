package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.PacienteDTO;

import java.util.List;

public interface IPacienteGateway {
    PacienteDTO registrarPaciente(PacienteDomain domain); // Create
    PacienteDTO atualizarPaciente(long id, PacienteDomain domain); // Update
    PacienteDTO listarPacientePorId(long id); // Read (Single)
    List<PacienteDTO> listarTodosPacientes(); // Read (All)
    void deletarPaciente(long id); // Delete
}
