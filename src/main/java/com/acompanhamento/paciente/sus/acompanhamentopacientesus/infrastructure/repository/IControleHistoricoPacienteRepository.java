package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IControleHistoricoPacienteRepository extends JpaRepository<ControleHistoricoPacienteEntity,Long> {
}
