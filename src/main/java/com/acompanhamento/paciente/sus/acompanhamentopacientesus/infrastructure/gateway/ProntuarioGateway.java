package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.prontuario.ProntuarioSpecification;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProntuarioGateway implements IProntuarioGateway{
    private final IProntuarioRepository prontuarioRepository;
    private final IProntuarioMapper prontuarioMapper;

    @Value("${controleprontuario-base-path}")
    private String urlControleProntuario;

    @Override
    public List<ProntuarioDTO> listarProntuarioPacientePorIdControle(long idControle, String especialidade, LocalDateTime data, String solicitacao, StatusSolicitacaoProntuario statusSolicitacaoProntuario,int offset,int limit){
        Specification<ProntuarioPacienteEntity> spec = Specification
                .where(ProntuarioSpecification.equalsIdHistoricoPaciente(idControle))
                .and(ProntuarioSpecification.likeEspecialidadeMedico(especialidade))
                .and(ProntuarioSpecification.greaterThanOrEqualDataInicio(data))
                .and(ProntuarioSpecification.likeSolicitacao(solicitacao))
                .and(ProntuarioSpecification.equalsStatusHistorico(statusSolicitacaoProntuario.toString()));
        Pageable pageable = PageRequest.of(offset,limit);
        Page<ProntuarioPacienteEntity> listaProntuario = prontuarioRepository.findAll(spec,pageable);

        if(listaProntuario.isEmpty())
            throw new EntityNotFoundException("Não foi possível encontrar registros com os filtros aplicados.");

        return listaProntuario.stream().map(prontuarioMapper::toDTO).toList();
    }
    @Override
    @Transactional
    public InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain){
        WebClient webClient = WebClient.create(urlControleProntuario);
        UpdateControleHistoricoDTO updateBody = new UpdateControleHistoricoDTO(StatusHistoricoPaciente.EM_CURSO.toString());
        if(domain.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.SOLICITADO) && (domain.getSolicitacao() == null || domain.getSolicitacao().isBlank())){
            throw new IllegalArgumentException("Não é possível mudar o estado para solicitado sem nenhuma solicitação informada.");
        }
        else if (domain.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.ENTREGUE) ||
                domain.getStatusSolicitacaoProntuario().equals(StatusSolicitacaoProntuario.EXAME_REALIZADO))
        {
            if(domain.getSolicitacao() != null)
                updateBody = new UpdateControleHistoricoDTO(StatusHistoricoPaciente.RETORNO.toString());
            else
                updateBody = new UpdateControleHistoricoDTO(StatusHistoricoPaciente.ENCERRADO.toString());
        }
        ProntuarioPacienteEntity entitySalvo = prontuarioRepository.save(prontuarioMapper.toEntity(domain));
        webClient.patch()
                .uri("/controlehistorico/" + domain.getIdControleHistorico())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateBody)
                .retrieve()
                .bodyToMono(String.class);

        return new InsertMessageDTO("ID de registro do prontuário gerado: " + entitySalvo.getId());
    }
}
