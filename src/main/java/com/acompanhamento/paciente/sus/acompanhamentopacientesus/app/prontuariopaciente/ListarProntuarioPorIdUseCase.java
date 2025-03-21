package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarProntuarioPorIdUseCase {
    private final IProntuarioGateway prontuarioGateway;

    public ProntuarioDTO listarProntuarioPacientePorIdControle(long idControle){
        return prontuarioGateway.listarProntuarioPacientePorIdControle(idControle);
    }
}
