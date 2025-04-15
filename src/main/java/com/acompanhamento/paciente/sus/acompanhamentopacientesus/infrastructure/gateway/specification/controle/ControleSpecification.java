package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.specification.controle;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
public class ControleSpecification {

    private ControleSpecification(){}

    public static Specification<ControleHistoricoPacienteEntity> equalsIdPaciente(Long idPaciente) {
        return (root, query, criteriaBuilder) ->
                idPaciente == null ? null : criteriaBuilder.equal(root.get("idPaciente"), idPaciente);
    }
    public static Specification<ControleHistoricoPacienteEntity> equalsIdUnidade(Long idUnidade) {
        return (root, query, criteriaBuilder) ->
                idUnidade == null ? null : criteriaBuilder.equal(root.get("idUnidade"), idUnidade);
    }
    public static Specification<ControleHistoricoPacienteEntity> greaterThanOrEqualDataCadastro(LocalDateTime dataCadastro) {
        return (root, query, criteriaBuilder) ->
                dataCadastro == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("dataCadastro"), dataCadastro);
    }
    public static Specification<ControleHistoricoPacienteEntity> equalsStatusHistorico(StatusHistoricoPaciente statusHistoricoPaciente) {
        return (root, query, criteriaBuilder) ->
                statusHistoricoPaciente == null ? null : criteriaBuilder.equal(root.get("statusControle"), statusHistoricoPaciente);
    }
    
    public static Specification<ControleHistoricoPacienteEntity> statusHistoricoEmAberto() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.notEqual(root.get("statusControle"), StatusHistoricoPaciente.PRIMEIRA_CONSULTA),
        		criteriaBuilder.notEqual(root.get("statusControle"), StatusHistoricoPaciente.ENCERRADO));
    }
    
    public static Specification<ControleHistoricoPacienteEntity> buscaUltimoHistoricoPaciente(StatusHistoricoPaciente statusHistoricoPaciente) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.equal(root.get("statusControle"), statusHistoricoPaciente), 
        		criteriaBuilder.isNotNull(root.get("dataEncerramento")));
    }
    
    
}
