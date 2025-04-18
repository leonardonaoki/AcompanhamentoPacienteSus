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
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                "3185056436",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertTrue(violations.isEmpty(), "Não deve haver violações de validação para um DTO válido.");
    }

    @Test
    void deveFalharQuandoNomeForMuitoCurto() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                "AB", // Inválido: mínimo 3 caracteres
                "12345678901",
                "Rua A, 123",
                "3185056436",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para nome muito curto.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nome")),
                "A violação deve estar relacionada ao nome.");
    }

    @Test
    void deveFalharQuandoCpfNaoTiver11Caracteres() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                "Carlos Souza",
                "12345", // Inválido: CPF deve ter 11 caracteres
                "Rua A, 123",
                "3185056436",
                LocalDateTime.of(1995, 4, 15, 0, 0)
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para CPF inválido.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cpf")),
                "A violação deve estar relacionada ao CPF.");
    }

    @Test
    void deveFalharQuandoDataNascimentoForNula() {
        // Arrange
        InsertPacienteDTO dto = new InsertPacienteDTO(
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                "3185056436",
                null // Inválido: não pode ser nulo
        );

        // Act
        Set<ConstraintViolation<InsertPacienteDTO>> violations = validator.validate(dto);

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação para data de nascimento nula.");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("dataNascimento")),
                "A violação deve estar relacionada à data de nascimento.");
    }
}
