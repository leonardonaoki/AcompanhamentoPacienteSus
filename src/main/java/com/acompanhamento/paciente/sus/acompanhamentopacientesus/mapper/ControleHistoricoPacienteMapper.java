package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ControleHistoricoPacienteMapper implements IControleHistoricoPacienteMapper{

    @Override
    public ControleHistoricoDTO toDTO(ControleHistoricoPacienteEntity controleHistorico) {
        return new ControleHistoricoDTO(
                controleHistorico.getIdHistoricoPaciente(),
                controleHistorico.getIdPaciente(),
                controleHistorico.getIdUnidade(),
                controleHistorico.getDataCadastro(),
                controleHistorico.getDataAtualizacao(),
                controleHistorico.getStatusControle()
        );
    }
    @Override
    public ControleHistoricoPacienteDomain toDomain(InsertControleHistoricoDTO dto) {
        ControleHistoricoPacienteDomain domain = new ControleHistoricoPacienteDomain();
        domain.setIdPaciente(dto.idPaciente());
        domain.setIdUnidade(dto.idUnidade());
        return domain;
    }

    @Override
    public ControleHistoricoPacienteEntity toEntity(ControleHistoricoPacienteDomain domain){
        ControleHistoricoPacienteEntity entity = new ControleHistoricoPacienteEntity();
        entity.setIdPaciente(domain.getIdPaciente());
        entity.setIdUnidade(domain.getIdUnidade());
        entity.setDataCadastro(domain.getDataCadastro());
        entity.setDataAtualizacao(domain.getDataAtualizacao());
        entity.setStatusControle(domain.getStatusControle());
        return entity;
    }
}
