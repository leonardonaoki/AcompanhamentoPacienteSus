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
        InsertUpdateUnidadeSaudeDTO dto = new InsertUpdateUnidadeSaudeDTO(
                "Hospital Central",
                "Rua ABC, 123",
                "Hospital",
                "11999999999",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );

        // Validação
        Set<ConstraintViolation<InsertUpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertTrue(violacoes.isEmpty(), "Não deve haver violações de validação.");
    }

    @Test
    void deveGerarErroQuandoCamposObrigatoriosNaoForemPreenchidos() {
        // Arrange & Act
        InsertUpdateUnidadeSaudeDTO dto = new InsertUpdateUnidadeSaudeDTO(
                "", // Nome vazio (Pode falhar em @NotBlank e @Size)
                null, // Endereço nulo (@NotNull)
                "  ", // Tipo de unidade apenas com espaços (@NotBlank)
                null, // Telefone nulo (@NotNull)
                null, // Hora de abertura nula (@NotNull)
                LocalTime.of(19, 0) // Hora de fechamento válida
        );

        // Validação
        Set<ConstraintViolation<InsertUpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Depuração: Imprime todas as violações encontradas
        violacoes.forEach(v -> System.out.println(v.getPropertyPath() + " -> " + v.getMessage()));

        // Assert
        assertFalse(violacoes.isEmpty(), "Deve haver violações de validação.");

        // Ajuste o número esperado de violações
        assertEquals(5, violacoes.size(), "Devem existir 5 erros de validação.");
    }
}
