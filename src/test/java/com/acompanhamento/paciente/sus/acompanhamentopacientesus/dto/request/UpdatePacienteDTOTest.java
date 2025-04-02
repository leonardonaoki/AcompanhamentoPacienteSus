package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePacienteDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveCriarUpdatePacienteDTOComValoresValidos() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação para um DTO válido.");
    }

    @Test
    void deveFalharQuandoNomeForVazio() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome(""); // Inválido: nome não pode ser vazio
        dto.setCpf("12345678901");
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para nome vazio.");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Nome não pode ser vazio.")));
    }

    @Test
    void deveFalharQuandoCpfForVazio() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf(""); // Inválido: CPF não pode ser vazio
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para CPF vazio.");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("CPF não pode ser vazio.")));
    }

    @Test
    void deveFalharQuandoEnderecoForVazio() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf("12345678901");
        dto.setEndereco(""); // Inválido: Endereço não pode ser vazio
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para endereço vazio.");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Endereço não pode ser vazio.")));
    }

    @Test
    void deveFalharQuandoDataNascimentoForNula() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(null); // Inválido: Data de nascimento não pode ser nula

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para data de nascimento nula.");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Data de nascimento não pode ser nula.")));
    }
}
