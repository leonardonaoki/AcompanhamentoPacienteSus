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

class InsertPacienteDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveCriarInsertPacienteDTOComValoresValidos() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                1L,
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação para um DTO válido.");
    }

    @Test
    void deveFalharQuandoIdPacienteNaoForPositivo() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                0L,  // Inválido: deve ser positivo
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação quando o ID não for positivo.");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be greater than 0")));
    }

    @Test
    void deveFalharQuandoNomeForMuitoCurto() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                1L,
                "AB", // Inválido: mínimo 3 caracteres
                "12345678901",
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para nome muito curto.");
    }

    @Test
    void deveFalharQuandoCpfNaoTiver11Caracteres() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                1L,
                "Carlos Souza",
                "12345", // Inválido: CPF deve ter 11 caracteres
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para CPF inválido.");
    }

    @Test
    void deveFalharQuandoDataNascimentoForNula() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                1L,
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                null // Inválido: não pode ser nulo
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para data de nascimento nula.");
    }
}
