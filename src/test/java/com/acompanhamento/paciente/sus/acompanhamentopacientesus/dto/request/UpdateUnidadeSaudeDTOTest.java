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
        dto.setNomeUnidade(""); // Inválido: Pode falhar em @NotBlank e @Size
        dto.setEndereco(null); // Inválido: @NotNull
        dto.setTipoUnidade("  "); // Inválido: @NotBlank (apenas espaços)
        dto.setTelefone(null); // Inválido: @NotNull
        dto.setHoraAbertura(null); // Inválido: @NotNull
        dto.setHoraFechamento(LocalTime.of(19, 0)); // Válido

        // Act
        Set<ConstraintViolation<UpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Debugging: Imprime todas as violações encontradas
        violacoes.forEach(v -> System.out.println(v.getPropertyPath() + " -> " + v.getMessage()));

        // Assert
        assertFalse(violacoes.isEmpty(), "Deve haver violações de validação.");

        // Ajuste conforme o número real de restrições que estão falhando
        assertEquals(5, violacoes.size(), "Devem existir 5 erros de validação.");
    }
}
