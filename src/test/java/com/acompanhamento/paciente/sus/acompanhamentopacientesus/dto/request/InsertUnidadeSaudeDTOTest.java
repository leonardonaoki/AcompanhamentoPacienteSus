package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InsertUnidadeSaudeDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveCriarInsertUnidadeSaudeDTOComDadosValidos() {
        // Arrange & Act
        InsertUnidadeSaudeDTO dto = new InsertUnidadeSaudeDTO(
                "Hospital Central",
                "Rua ABC, 123",
                "Hospital",
                "11999999999",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );

        // Validação
        Set<ConstraintViolation<InsertUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertTrue(violacoes.isEmpty(), "Não deve haver violações de validação.");
    }

    @Test
    void deveGerarErroQuandoCamposObrigatoriosNaoForemPreenchidos() {
        // Arrange & Act
        InsertUnidadeSaudeDTO dto = new InsertUnidadeSaudeDTO(
                "", // Nome vazio
                null, // Endereço nulo
                "  ", // Tipo de unidade apenas com espaços
                null, // Telefone nulo
                null, // Hora de abertura nula
                LocalTime.of(19, 0) // Hora de fechamento válida
        );

        // Validação
        Set<ConstraintViolation<InsertUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertFalse(violacoes.isEmpty(), "Deve haver violações de validação.");
        assertEquals(4, violacoes.size(), "Devem existir 4 erros de validação.");
    }
}
