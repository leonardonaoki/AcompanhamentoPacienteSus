package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IProntuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProntuarioPorIdUseCase {
    private final IProntuarioGateway prontuarioGateway;

    public List<ProntuarioDTO> listarProntuarioPacientePorIdControle(long idControle, String especialidade, LocalDateTime data, String solicitacao, StatusSolicitacaoProntuario statusSolicitacaoProntuario){
        return prontuarioGateway.listarProntuarioPacientePorIdControle(idControle,especialidade,data,solicitacao,statusSolicitacaoProntuario);
    }
}
