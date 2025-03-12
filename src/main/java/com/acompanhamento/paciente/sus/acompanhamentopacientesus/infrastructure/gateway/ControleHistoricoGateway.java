package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IControleHistoricoPacienteRepository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControleHistoricoGateway implements IControleHistoricoGateway{
    private static final String ERRO_ID_NAO_ENCONTRADO = "Não foi possível identificar o paciente com o ID ";
    private final IControleHistoricoPacienteRepository controleHistoricoRepository;
    private final IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;

    @Override
    public ControleHistoricoDTO listarHistoricoPacientePorId(long id){
        return controleHistoricoRepository.findById(id)
                .map(controleHistoricoPacienteMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));
    }
    @Override
    public ControleHistoricoDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain){
        ControleHistoricoPacienteEntity entitySalvo = controleHistoricoRepository.save(controleHistoricoPacienteMapper.toEntity(domain));
        return controleHistoricoPacienteMapper.toDTO(entitySalvo);
    }
}
