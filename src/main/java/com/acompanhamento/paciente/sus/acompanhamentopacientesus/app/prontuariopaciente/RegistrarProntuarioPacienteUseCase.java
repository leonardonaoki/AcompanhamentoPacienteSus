package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarProntuarioPacienteUseCase {
    private final IProntuarioGateway prontuarioGateway;

    public InsertMessageDTO registrarProntuarioPaciente(long idControle,ProntuarioPacienteDomain domain){
        domain.setIdControle(idControle);
        return prontuarioGateway.registrarProntuarioPaciente(domain);
    }
}
