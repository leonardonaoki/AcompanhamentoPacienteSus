package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrarProntuarioPacienteUseCase {
    private final IProntuarioGateway prontuarioGateway;

    public InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain){
        domain.setDataInicio(LocalDateTime.now());
        return prontuarioGateway.registrarProntuarioPaciente(domain);
    }
}
