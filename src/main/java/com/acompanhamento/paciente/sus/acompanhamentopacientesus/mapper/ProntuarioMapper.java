package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ProntuarioMapper implements IProntuarioMapper{

    @Override
    public ProntuarioDTO toDTO(ProntuarioPacienteEntity prontuario) {
        return new ProntuarioDTO(
                prontuario.getIdHistoricoPaciente(),
                prontuario.getEspecialidadeMedico(),
                prontuario.getDataInicio(),
                prontuario.getDataValidade(),
                prontuario.getSolicitacao(),
                prontuario.getStatusSolicitacaoProntuario()
        );
    }
    @Override
    public ProntuarioPacienteDomain toDomain(InsertProntuarioDTO dto) {
        return new ProntuarioPacienteDomain(
                dto.idControleHistorico(),
                dto.especialidadeMedico(),
                dto.dataInicio(),
                dto.dataValidade(),
                dto.solicitacao(),
                dto.statusSolicitacaoProntuario()
        );
    }

    @Override
    public ProntuarioPacienteEntity toEntity(ProntuarioPacienteDomain domain){
        return new ProntuarioPacienteEntity(
                domain.getIdControleHistorico(),
                domain.getEspecialidadeMedico(),
                domain.getDataInicio(),
                domain.getDataValidade(),
                domain.getSolicitacao(),
                domain.getStatusSolicitacaoProntuario()
        );
    }
}
