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
    void deveGerarViolacoesQuandoCamposObrigatoriosNaoForemPreenchidos() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome(""); // Inválido
        dto.setCpf(null); // Inválido
        dto.setEndereco("   "); // Inválido
        dto.setDataNascimento(null); // Inválido

        // Act
        Set<ConstraintViolation<UpdatePacienteDTO>> violations = validator.validate(dto);

        // Debugging: Exibir todas as violações encontradas
        violations.forEach(v -> System.out.println(v.getPropertyPath() + " -> " + v.getMessage()));

        // Assert
        assertFalse(violations.isEmpty(), "Deve haver violações de validação.");
        assertEquals(4, violations.size(), "Deve haver exatamente 4 violações de validação.");
    }

    @Test
    void deveTestarGettersESetters() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        LocalDateTime dataNascimento = LocalDateTime.of(1990, 5, 10, 0, 0);

        // Act
        dto.setNome("Ana Paula");
        dto.setCpf("98765432100");
        dto.setEndereco("Avenida Central, 456");
        dto.setDataNascimento(dataNascimento);

        // Assert
        assertEquals("Ana Paula", dto.getNome());
        assertEquals("98765432100", dto.getCpf());
        assertEquals("Avenida Central, 456", dto.getEndereco());
        assertEquals(dataNascimento, dto.getDataNascimento());
    }

    @Test
    void deveTestarEqualsEHashCodeComObjetosDiferentes() {
        // Arrange
        UpdatePacienteDTO dto1 = new UpdatePacienteDTO();
        dto1.setNome("Maria Oliveira");
        dto1.setCpf("11122233344");
        dto1.setEndereco("Rua XYZ, 789");
        dto1.setDataNascimento(LocalDateTime.of(1988, 3, 25, 0, 0));

        UpdatePacienteDTO dto2 = new UpdatePacienteDTO();
        dto2.setNome("João Silva");
        dto2.setCpf("99988877766");
        dto2.setEndereco("Rua ABC, 123");
        dto2.setDataNascimento(LocalDateTime.of(1990, 6, 12, 0, 0));

        // Assert
        assertNotEquals(dto1, dto2, "Os objetos devem ser diferentes.");
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Os hashCodes devem ser diferentes.");
    }

    @Test
    void deveTestarEqualsComObjetoNulo() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        // Assert
        assertNotEquals(null, dto, "DTO não pode ser igual a null.");
    }

    @Test
    void deveTestarEqualsComObjetoDeOutraClasse() {
        // Arrange
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("Carlos Souza");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua A, 123");
        dto.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        String outroObjeto = "Não sou um DTO";

        // Assert
        assertNotEquals(dto, outroObjeto, "DTO não pode ser igual a um objeto de outra classe.");
    }
}
