package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IControleHistoricoPacienteRepository extends
        JpaRepository<ControleHistoricoPacienteEntity,Long>,
        JpaSpecificationExecutor<ControleHistoricoPacienteEntity> {
}
