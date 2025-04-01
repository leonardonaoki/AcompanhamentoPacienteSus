package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pela persistência de dados da entidade {@link PacienteEntity}.
 *
 * A interface {@link IPacienteRepository} estende {@link JpaRepository}, fornecendo os métodos básicos de CRUD (Create, Read, Update, Delete)
 * para a entidade {@link PacienteEntity}, como salvar, buscar por ID, listar todos os pacientes, deletar, etc.
 *
 * O Spring Data JPA gera automaticamente a implementação dessa interface, que interage com o banco de dados sem a necessidade de escrever código SQL manual.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IPacienteRepository extends JpaRepository<PacienteEntity, Long> {
    // O Spring Data JPA fornece a implementação de métodos básicos de CRUD automaticamente.
}
