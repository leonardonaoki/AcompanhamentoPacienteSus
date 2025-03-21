package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProntuarioRepository extends JpaRepository<ProntuarioPacienteEntity,Long> {
}
