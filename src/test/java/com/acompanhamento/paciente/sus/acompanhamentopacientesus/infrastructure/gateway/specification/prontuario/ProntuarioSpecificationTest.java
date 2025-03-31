package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.prontuario;

import static org.junit.jupiter.api.Assertions.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class ProntuarioSpecificationTest {

    @Test
    void testEqualsIdHistoricoPaciente() {
        Long idHistoricoPaciente = 1L;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.equalsIdHistoricoPaciente(idHistoricoPaciente);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idHistoricoPaciente")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idHistoricoPaciente))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullIdHistoricoPaciente() {
        Long idHistoricoPaciente = null;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.equalsIdHistoricoPaciente(idHistoricoPaciente);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idHistoricoPaciente")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idHistoricoPaciente))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testLikeEspecialidadeMedico() {
        String especialidadeMedico = "Cardiologia";
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.likeEspecialidadeMedico(especialidadeMedico);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("especialidadeMedico")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(any(), eq("%" + especialidadeMedico + "%"))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullEspecialidadeMedico() {
        String especialidadeMedico = null;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.likeEspecialidadeMedico(especialidadeMedico);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("especialidadeMedico")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(any(), eq("%" + especialidadeMedico + "%"))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testGreaterThanOrEqualDataInicio() {
        LocalDateTime dataInicio = LocalDateTime.now();
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.greaterThanOrEqualDataInicio(dataInicio);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("dataInicio")).thenReturn(mock(Path.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(dataInicio))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullDataInicio() {
        LocalDateTime dataInicio = null;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.greaterThanOrEqualDataInicio(dataInicio);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("dataInicio")).thenReturn(mock(Path.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(dataInicio))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testLikeSolicitacao() {
        String solicitacao = "Exames";
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.likeSolicitacao(solicitacao);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("solicitacao")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(any(), eq("%" + solicitacao + "%"))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullSolicitacao() {
        String solicitacao = null;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.likeSolicitacao(solicitacao);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("solicitacao")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(any(), eq("%" + solicitacao + "%"))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testEqualsStatusHistorico() {
        StatusSolicitacaoProntuario status = StatusSolicitacaoProntuario.ENTREGUE;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.equalsStatusHistorico(status.toString());

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("statusSolicitacaoProntuario")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(status.toString()))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullStatusHistorico() {
        String status = null;
        Specification<ProntuarioPacienteEntity> spec = ProntuarioSpecification.equalsStatusHistorico(status);

        Root<ProntuarioPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("statusSolicitacaoProntuario")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(status))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
}

