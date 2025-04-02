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

class UpdateUnidadeSaudeDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveCriarUpdateUnidadeSaudeDTOComDadosValidos() {
        // Arrange
        UpdateUnidadeSaudeDTO dto = new UpdateUnidadeSaudeDTO();
        dto.setNomeUnidade("Hospital Municipal");
        dto.setEndereco("Rua das Flores, 123");
        dto.setTipoUnidade("Hospital");
        dto.setTelefone("11988887777");
        dto.setHoraAbertura(LocalTime.of(7, 0));
        dto.setHoraFechamento(LocalTime.of(19, 0));

        // Act
        Set<ConstraintViolation<UpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertTrue(violacoes.isEmpty(), "Não deve haver violações de validação.");
    }

    @Test
    void deveGerarErrosQuandoCamposObrigatoriosNaoForemPreenchidos() {
        // Arrange
        UpdateUnidadeSaudeDTO dto = new UpdateUnidadeSaudeDTO();
        dto.setNomeUnidade("");
        dto.setEndereco(null);
        dto.setTipoUnidade("  "); // Apenas espaços
        dto.setTelefone(null);
        dto.setHoraAbertura(null);
        dto.setHoraFechamento(LocalTime.of(19, 0)); // Válido

        // Act
        Set<ConstraintViolation<UpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertFalse(violacoes.isEmpty(), "Deve haver violações de validação.");
        assertEquals(4, violacoes.size(), "Devem existir 4 erros de validação.");
    }
}
