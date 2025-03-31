package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
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
    public ProntuarioPacienteDomain toDomain(Long idControle,InsertProntuarioDTO dto) {
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setIdControleHistorico(idControle);
        domain.setEspecialidadeMedico(dto.especialidadeMedico());
        domain.setDataValidade(dto.dataValidade());
        domain.setSolicitacao(dto.solicitacao());
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.valueOf(dto.statusSolicitacaoProntuario()));
        return domain;
    }

    @Override
    public ProntuarioPacienteEntity toEntity(ProntuarioPacienteDomain domain){
        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setIdHistoricoPaciente(domain.getIdControleHistorico());
        entity.setEspecialidadeMedico(domain.getEspecialidadeMedico());
        entity.setDataInicio(domain.getDataInicio());
        entity.setDataValidade(domain.getDataValidade());
        entity.setSolicitacao(domain.getSolicitacao());
        entity.setStatusSolicitacaoProntuario(domain.getStatusSolicitacaoProntuario().toString());
        return entity;
    }
}
