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
        dto.setNomeUnidade(""); // Inválido: @NotBlank
        dto.setEndereco(null); // Inválido: @NotBlank
        dto.setTipoUnidade("  "); // Inválido: @NotBlank (apenas espaços)
        dto.setTelefone(null); // Inválido: @NotBlank
        dto.setHoraAbertura(null); // Inválido: @NotNull
        dto.setHoraFechamento(null); // Inválido: @NotNull

        // Act
        Set<ConstraintViolation<UpdateUnidadeSaudeDTO>> violacoes = validator.validate(dto);

        // Assert
        assertFalse(violacoes.isEmpty(), "Deve haver violações de validação.");
        assertEquals(6, violacoes.size(), "Devem existir 6 erros de validação.");
    }

    @Test
    void deveTestarGettersESetters() {
        // Arrange
        UpdateUnidadeSaudeDTO dto = new UpdateUnidadeSaudeDTO();
        LocalTime horaAbertura = LocalTime.of(8, 0);
        LocalTime horaFechamento = LocalTime.of(18, 0);

        // Act
        dto.setNomeUnidade("Posto de Saúde Central");
        dto.setEndereco("Avenida Principal, 200");
        dto.setTipoUnidade("Posto de Saúde");
        dto.setTelefone("11977776666");
        dto.setHoraAbertura(horaAbertura);
        dto.setHoraFechamento(horaFechamento);

        // Assert
        assertEquals("Posto de Saúde Central", dto.getNomeUnidade());
        assertEquals("Avenida Principal, 200", dto.getEndereco());
        assertEquals("Posto de Saúde", dto.getTipoUnidade());
        assertEquals("11977776666", dto.getTelefone());
        assertEquals(horaAbertura, dto.getHoraAbertura());
        assertEquals(horaFechamento, dto.getHoraFechamento());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        UpdateUnidadeSaudeDTO dto1 = new UpdateUnidadeSaudeDTO();
        dto1.setNomeUnidade("Hospital X");
        dto1.setEndereco("Rua ABC, 100");
        dto1.setTipoUnidade("Hospital");
        dto1.setTelefone("11999995555");
        dto1.setHoraAbertura(LocalTime.of(6, 30));
        dto1.setHoraFechamento(LocalTime.of(20, 0));

        UpdateUnidadeSaudeDTO dto2 = new UpdateUnidadeSaudeDTO();
        dto2.setNomeUnidade("Hospital X");
        dto2.setEndereco("Rua ABC, 100");
        dto2.setTipoUnidade("Hospital");
        dto2.setTelefone("11999995555");
        dto2.setHoraAbertura(LocalTime.of(6, 30));
        dto2.setHoraFechamento(LocalTime.of(20, 0));

        // Assert
        assertEquals(dto1, dto2, "Os objetos devem ser iguais.");
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Os hashCodes devem ser iguais.");
    }

    @Test
    void deveTestarToString() {
        // Arrange
        UpdateUnidadeSaudeDTO dto = new UpdateUnidadeSaudeDTO();
        dto.setNomeUnidade("Unidade Teste");
        dto.setEndereco("Rua Teste, 123");
        dto.setTipoUnidade("Clínica");
        dto.setTelefone("11912345678");
        dto.setHoraAbertura(LocalTime.of(9, 0));
        dto.setHoraFechamento(LocalTime.of(18, 0));

        // Act
        String dtoString = dto.toString();

        // Assert
        assertTrue(dtoString.contains("Unidade Teste"));
        assertTrue(dtoString.contains("Rua Teste, 123"));
        assertTrue(dtoString.contains("Clínica"));
        assertTrue(dtoString.contains("11912345678"));
    }
}
