package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertProntuarioDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidation_WhenFieldsAreValid() {
        InsertProntuarioDTO dto = new InsertProntuarioDTO("Cardiologia", LocalDateTime.now(), "Solicitação válida", StatusSolicitacaoProntuario.SOLICITADO.toString());

        Set<ConstraintViolation<InsertProntuarioDTO>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldFailValidation_WhenEspecialidadeMedicoIsBlank() {
        InsertProntuarioDTO dto = new InsertProntuarioDTO("", LocalDateTime.now(), "Solicitação válida", StatusSolicitacaoProntuario.SOLICITADO.toString());

        Set<ConstraintViolation<InsertProntuarioDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidation_WhenStatusSolicitacaoProntuarioIsBlank() {
        InsertProntuarioDTO dto = new InsertProntuarioDTO("Cardiologia", LocalDateTime.now(), "Solicitação válida", "");

        Set<ConstraintViolation<InsertProntuarioDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidation_WhenStatusSolicitacaoProntuarioIsNull() {
        InsertProntuarioDTO dto = new InsertProntuarioDTO("Cardiologia", LocalDateTime.now(), "Solicitação válida", null);

        Set<ConstraintViolation<InsertProntuarioDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
    }
}

