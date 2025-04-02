package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.controle.ControleSpecification;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IControleHistoricoPacienteRepository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ControleHistoricoGateway implements IControleHistoricoGateway{
    private static final String ERRO_ID_NAO_ENCONTRADO = "Não foi possível o ID ";
    private final IControleHistoricoPacienteRepository controleHistoricoRepository;
    private final IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;
    @Override
    public ControleHistoricoDTO listarHistoricoPacientePorIdControle(Long idControle){
        return controleHistoricoRepository.findById(idControle)
                .map(controleHistoricoPacienteMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + idControle));
    }

    @Override
    public List<ControleHistoricoDTO> listarHistoricoPacientePorId(Long idPaciente,Long idUnidade,LocalDateTime data,StatusHistoricoPaciente statusHistoricoPaciente,int offset,int limit){
        Specification<ControleHistoricoPacienteEntity> spec = Specification
                .where(ControleSpecification.equalsIdPaciente(idPaciente))
                .and(ControleSpecification.equalsIdUnidade(idUnidade))
                .and(ControleSpecification.greaterThanOrEqualDataCadastro(data))
                .and(ControleSpecification.equalsStatusHistorico(statusHistoricoPaciente));
        Pageable pageable = PageRequest.of(offset,limit);
        Page<ControleHistoricoPacienteEntity> lista = controleHistoricoRepository.findAll(spec,pageable);
        if(lista.isEmpty())
            throw new EntityNotFoundException("Não foi possível encontrar registros com os filtros aplicados.");

        return lista.stream().map(controleHistoricoPacienteMapper::toDTO).toList();
    }
    @Override
    public InsertMessageDTO registrarHistoricoPaciente(ControleHistoricoPacienteDomain domain){
        ControleHistoricoPacienteEntity entitySalvo = controleHistoricoRepository.save(controleHistoricoPacienteMapper.toEntity(domain));
        return new InsertMessageDTO("ID de controle gerado: " + entitySalvo.getIdHistoricoPaciente());
    }
    @Override
    public ControleHistoricoDTO atualizarStatusHistoricoPaciente(Long id,StatusHistoricoPaciente novoStatus){
        ControleHistoricoPacienteEntity entidadeEncontrada = controleHistoricoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));

        if(entidadeEncontrada.getStatusControle().equals(novoStatus))
            throw new IllegalArgumentException("Não é possível atualizar um registro para o mesmo status");

        entidadeEncontrada.setStatusControle(novoStatus);
        entidadeEncontrada.setDataAtualizacao(LocalDateTime.now());

        ControleHistoricoPacienteEntity entidadeSalva = controleHistoricoRepository.save(entidadeEncontrada);
        return controleHistoricoPacienteMapper.toDTO(entidadeSalva);
    }
}
