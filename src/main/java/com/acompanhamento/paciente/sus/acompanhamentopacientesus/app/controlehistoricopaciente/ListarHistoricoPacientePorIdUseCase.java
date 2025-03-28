package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IControleHistoricoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarHistoricoPacientePorIdUseCase {
    private final IControleHistoricoGateway controleHistoricoGateway;

    public List<ControleHistoricoDTO> listarPacientePorId(Long idPaciente,
                                                          Long idUnidade,
                                                          LocalDateTime data,
                                                          StatusHistoricoPaciente statusHistoricoPaciente,
                                                          int offset,
                                                          int limit){
        return controleHistoricoGateway.listarHistoricoPacientePorId(idPaciente,idUnidade,data,statusHistoricoPaciente,offset,limit);
    }
}
