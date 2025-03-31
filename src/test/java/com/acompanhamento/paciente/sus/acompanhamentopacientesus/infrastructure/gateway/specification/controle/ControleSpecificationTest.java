package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.controle;

import static org.junit.jupiter.api.Assertions.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class ControleSpecificationTest {

    @Test
    void testEqualsIdPaciente() {
        Long idPaciente = 1L;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsIdPaciente(idPaciente);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idPaciente")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idPaciente))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullIdPaciente() {
        Long idPaciente = null;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsIdPaciente(idPaciente);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idPaciente")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idPaciente))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }

    @Test
    void testEqualsIdUnidade() {
        Long idUnidade = 2L;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsIdUnidade(idUnidade);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idUnidade")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idUnidade))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullIdUnidade() {
        Long idUnidade = null;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsIdUnidade(idUnidade);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("idUnidade")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(idUnidade))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testGreaterThanOrEqualDataCadastro() {
        LocalDateTime dataCadastro = LocalDateTime.now();
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.greaterThanOrEqualDataCadastro(dataCadastro);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("dataCadastro")).thenReturn(mock(Path.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(dataCadastro))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullDataCadastro() {
        LocalDateTime dataCadastro = null;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.greaterThanOrEqualDataCadastro(dataCadastro);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("dataCadastro")).thenReturn(mock(Path.class));
        when(criteriaBuilder.greaterThanOrEqualTo(any(), eq(dataCadastro))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testEqualsStatusHistorico() {
        StatusHistoricoPaciente status = StatusHistoricoPaciente.PRIMEIRA_CONSULTA;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsStatusHistorico(status);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("statusControle")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(status))).thenReturn(mock(Predicate.class));

        assertNotNull(spec.toPredicate(root, query, criteriaBuilder));
    }
    @Test
    void testNullStatusHistorico() {
        StatusHistoricoPaciente status = null;
        Specification<ControleHistoricoPacienteEntity> spec = ControleSpecification.equalsStatusHistorico(status);

        Root<ControleHistoricoPacienteEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        when(root.get("statusControle")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), eq(status))).thenReturn(mock(Predicate.class));

        assertNull(spec.toPredicate(root, query, criteriaBuilder));
    }
}

