package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDomainTest {

    @Test
    void deveCriarPacienteEDefinirValoresCorretamente() {
        // Arrange
        PacienteDomain paciente = new PacienteDomain();

        long idPaciente = 1L;
        String nome = "Carlos Souza";
        String cpf = "111.222.333-44";
        String endereco = "Rua A, 123";
        LocalDateTime dataNascimento = LocalDateTime.of(1995, 4, 15, 0, 0);
        LocalDateTime dataCadastro = LocalDateTime.now();
        LocalDateTime dataAtualizacao = LocalDateTime.now();

        // Act
        paciente.setIdPaciente(idPaciente);
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setEndereco(endereco);
        paciente.setDataNascimento(dataNascimento);
        paciente.setDataCadastro(dataCadastro);
        paciente.setDataAtualizacao(dataAtualizacao);

        // Assert
        assertEquals(idPaciente, paciente.getIdPaciente());
        assertEquals(nome, paciente.getNome());
        assertEquals(cpf, paciente.getCpf());
        assertEquals(endereco, paciente.getEndereco());
        assertEquals(dataNascimento, paciente.getDataNascimento());
        assertEquals(dataCadastro, paciente.getDataCadastro());
        assertEquals(dataAtualizacao, paciente.getDataAtualizacao());
    }

    @Test
    void devePermitirModificarValores() {
        // Arrange
        PacienteDomain paciente = new PacienteDomain();
        paciente.setIdPaciente(2L);
        paciente.setNome("Ana Oliveira");
        paciente.setCpf("222.333.444-55");
        paciente.setEndereco("Avenida B, 456");
        paciente.setDataNascimento(LocalDateTime.of(1988, 8, 25, 0, 0));
        paciente.setDataCadastro(LocalDateTime.now().minusYears(1));
        paciente.setDataAtualizacao(LocalDateTime.now().minusDays(5));

        // Act
        paciente.setNome("Ana Silva");
        paciente.setCpf("333.444.555-66");
        paciente.setEndereco("Rua Nova, 789");

        // Assert
        assertEquals("Ana Silva", paciente.getNome());
        assertEquals("333.444.555-66", paciente.getCpf());
        assertEquals("Rua Nova, 789", paciente.getEndereco());
    }
}
