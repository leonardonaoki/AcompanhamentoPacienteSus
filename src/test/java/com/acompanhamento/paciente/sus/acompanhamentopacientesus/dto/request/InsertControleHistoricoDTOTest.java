package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertControleHistoricoDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidation_WhenIdsArePositive() {
        InsertControleHistoricoDTO dto = new InsertControleHistoricoDTO(1L, 1L);

        Set<ConstraintViolation<InsertControleHistoricoDTO>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }
}
