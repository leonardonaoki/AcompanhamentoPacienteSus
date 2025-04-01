package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositório responsável pela persistência de dados da entidade {@link UnidadeSaudeEntity}.
 *
 * A interface {@link IUnidadeSaudeRepository} estende duas interfaces do Spring Data JPA:
 *
 * <ul>
 *     <li>{@link JpaRepository}: Fornece métodos básicos de CRUD (Create, Read, Update, Delete) para a entidade {@link UnidadeSaudeEntity}.</li>
 *     <li>{@link JpaSpecificationExecutor}: Permite o uso de especificações para consultas complexas e dinâmicas, proporcionando flexibilidade nas consultas ao banco de dados.</li>
 * </ul>
 *
 * O Spring Data JPA gera automaticamente a implementação dessa interface, que interage com o banco de dados sem a necessidade de escrever código SQL manual.
 *
 * @author [Seu Nome]
 * @version 1.0
 * @since 2025-04-01
 */
public interface IUnidadeSaudeRepository extends
        JpaRepository<UnidadeSaudeEntity, Long>, // Métodos básicos de CRUD
        JpaSpecificationExecutor<UnidadeSaudeEntity> { // Métodos para consultas complexas usando especificações
    // O Spring Data JPA fornece a implementação de métodos básicos de CRUD e métodos para consultas dinâmicas automaticamente.
}
