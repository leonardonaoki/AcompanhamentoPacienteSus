package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrarPacienteUseCase {
    private final IPacienteGateway pacienteGateway;

    public PacienteDTO registrarPaciente(PacienteDomain domain) {
        // Definindo datas e lógica de inicialização, se necessário
        domain.setDataCadastro(LocalDateTime.now());
        domain.setDataAtualizacao(LocalDateTime.now());
        return pacienteGateway.registrarPaciente(domain);
    }
}
