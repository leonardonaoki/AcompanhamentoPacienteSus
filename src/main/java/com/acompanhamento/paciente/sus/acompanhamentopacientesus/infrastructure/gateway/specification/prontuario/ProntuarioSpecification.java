package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.prontuario;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ProntuarioSpecification {
    private ProntuarioSpecification(){}

    public static Specification<ProntuarioPacienteEntity> equalsIdHistoricoPaciente(Long idHistoricoPaciente) {
        return (root, query, criteriaBuilder) ->
                idHistoricoPaciente == null ? null : criteriaBuilder.equal(root.get("idHistoricoPaciente"), idHistoricoPaciente);
    }

    public static Specification<ProntuarioPacienteEntity> likeEspecialidadeMedico(String especialidadeMedico) {
        return (root, query, criteriaBuilder) ->
                especialidadeMedico == null ? null : criteriaBuilder.like(root.get("especialidadeMedico"), "%" + especialidadeMedico + "%");
    }
    public static Specification<ProntuarioPacienteEntity> greaterThanOrEqualDataInicio(LocalDateTime dataInicio) {
        return (root, query, criteriaBuilder) ->
                dataInicio == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("dataInicio"), dataInicio);
    }
    public static Specification<ProntuarioPacienteEntity> likeSolicitacao(String solicitacao) {
        return (root, query, criteriaBuilder) ->
                solicitacao == null ? null : criteriaBuilder.like(root.get("solicitacao"), "%" + solicitacao + "%");
    }
    public static Specification<ProntuarioPacienteEntity> equalsStatusHistorico(StatusSolicitacaoProntuario statusSolicitacaoProntuario) {
        return (root, query, criteriaBuilder) ->
                statusSolicitacaoProntuario == null ? null : criteriaBuilder.equal(root.get("statusSolicitacaoProntuario"), statusSolicitacaoProntuario.toString());
    }
}
