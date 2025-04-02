package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PacienteEntityTest {

    private PacienteEntity paciente;

    @BeforeEach
    void setUp() {
        paciente = new PacienteEntity();
    }

    @Test
    void deveCriarPacienteEntityComValoresValidos() {
        // Arrange
        long idPaciente = 1L;
        String nome = "Carlos Souza";
        String cpf = "12345678901";
        String endereco = "Rua A, 123";
        LocalDateTime dataNascimento = LocalDateTime.of(1995, 4, 15, 0, 0);
        LocalDateTime dataCadastro = LocalDateTime.of(2024, 3, 10, 14, 30);
        LocalDateTime dataAtualizacao = LocalDateTime.of(2024, 3, 15, 16, 45);

        // Act
        paciente.setIdPaciente(idPaciente);
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setEndereco(endereco);
        paciente.setDataNascimento(dataNascimento);
        paciente.setDataCadastro(dataCadastro);
        paciente.setDataAtualizacao(dataAtualizacao);

        // Assert
        assertThat(paciente.getIdPaciente()).isEqualTo(idPaciente);
        assertThat(paciente.getNome()).isEqualTo(nome);
        assertThat(paciente.getCpf()).isEqualTo(cpf);
        assertThat(paciente.getEndereco()).isEqualTo(endereco);
        assertThat(paciente.getDataNascimento()).isEqualTo(dataNascimento);
        assertThat(paciente.getDataCadastro()).isEqualTo(dataCadastro);
        assertThat(paciente.getDataAtualizacao()).isEqualTo(dataAtualizacao);
    }

    @Test
    void devePermitirAlteracaoDosValores() {
        // Arrange
        paciente.setNome("Ana Pereira");
        paciente.setCpf("98765432100");
        paciente.setEndereco("Rua B, 456");

        // Act
        paciente.setNome("Bruno Silva");
        paciente.setCpf("11122233344");
        paciente.setEndereco("Rua C, 789");

        // Assert
        assertThat(paciente.getNome()).isEqualTo("Bruno Silva");
        assertThat(paciente.getCpf()).isEqualTo("11122233344");
        assertThat(paciente.getEndereco()).isEqualTo("Rua C, 789");
    }
}
