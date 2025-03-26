package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.prontuario.ProntuarioSpecification;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProntuarioGateway implements IProntuarioGateway{
    private final IProntuarioRepository prontuarioRepository;
    private final IProntuarioMapper prontuarioMapper;

    @Override
    public List<ProntuarioDTO> listarProntuarioPacientePorIdControle(long idControle, String especialidade, LocalDateTime data, String solicitacao, StatusSolicitacaoProntuario statusSolicitacaoProntuario){
        Specification<ProntuarioPacienteEntity> spec = Specification
                .where(ProntuarioSpecification.equalsIdHistoricoPaciente(idControle))
                .and(ProntuarioSpecification.likeEspecialidadeMedico(especialidade))
                .and(ProntuarioSpecification.greaterThanOrEqualDataInicio(data))
                .and(ProntuarioSpecification.likeSolicitacao(solicitacao))
                .and(ProntuarioSpecification.equalsStatusHistorico(statusSolicitacaoProntuario));

        List<ProntuarioPacienteEntity> listaProntuario = prontuarioRepository.findAll(spec);

        if(listaProntuario.isEmpty())
            throw new EntityNotFoundException("Não foi possível encontrar registros com os filtros aplicados.");

        return listaProntuario.stream().map(prontuarioMapper::toDTO).toList();
    }
    @Override
    public InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain){
        ProntuarioPacienteEntity entitySalvo = prontuarioRepository.save(prontuarioMapper.toEntity(domain));
        return new InsertMessageDTO("ID gerado: " + entitySalvo.getId());
    }
}
