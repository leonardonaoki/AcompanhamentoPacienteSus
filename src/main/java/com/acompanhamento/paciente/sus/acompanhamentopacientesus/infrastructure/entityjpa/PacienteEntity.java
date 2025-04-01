package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representação da entidade de paciente no banco de dados.
 * <p>
 * Esta classe é usada para mapear os dados de um paciente no banco de dados,
 * utilizando JPA (Java Persistence API). Cada instância dessa classe representa
 * uma linha na tabela de pacientes do banco de dados.
 * </p>
 */
@Getter
@Setter
@Entity
public class PacienteEntity {

    /**
     * Identificador único do paciente.
     * <p>
     * A anotação {@link Id} define esse campo como a chave primária da tabela de pacientes.
     * O valor deste campo é gerado automaticamente pelo banco de dados, utilizando
     * a estratégia {@link GenerationType.IDENTITY}.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPaciente;

    /**
     * Nome completo do paciente.
     * <p>
     * Este campo armazena o nome do paciente, utilizado para identificá-lo no sistema.
     * </p>
     */
    private String nome;

    /**
     * CPF do paciente.
     * <p>
     * Este campo armazena o CPF do paciente, utilizado para a identificação única
     * do paciente dentro do sistema e em conformidade com as regulamentações legais.
     * </p>
     */
    private String cpf;

    /**
     * Endereço do paciente.
     * <p>
     * Este campo armazena o endereço completo do paciente, utilizado para contato e localização.
     * </p>
     */
    private String endereco;

    /**
     * Data de nascimento do paciente.
     * <p>
     * Este campo armazena a data de nascimento do paciente, utilizada para calcular a idade e outros
     * processos de validação no sistema.
     * </p>
     */
    private LocalDateTime dataNascimento;

    /**
     * Data em que o paciente foi cadastrado no sistema.
     * <p>
     * Este campo armazena a data e hora em que o paciente foi registrado no sistema.
     * A data é gerada no momento do cadastro.
     * </p>
     */
    private LocalDateTime dataCadastro;

    /**
     * Data da última atualização das informações do paciente.
     * <p>
     * Este campo armazena a data e hora da última modificação nos dados do paciente,
     * permitindo controlar quando o paciente foi atualizado no sistema.
     * </p>
     */
    private LocalDateTime dataAtualizacao;
}
