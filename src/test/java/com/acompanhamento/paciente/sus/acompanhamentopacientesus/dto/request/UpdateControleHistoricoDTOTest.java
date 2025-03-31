package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateControleHistoricoDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "Ativo, 0",
            "'', 1"
    })
    void shouldPassValidation_WhenNovoStatusIsValid(String arg, int violation) {
        UpdateControleHistoricoDTO dto = new UpdateControleHistoricoDTO(arg);

        Set<ConstraintViolation<UpdateControleHistoricoDTO>> violations = validator.validate(dto);

        assertEquals(violation, violations.size());
    }

    @Test
    void shouldFailValidation_WhenNovoStatusIsNull() {
        UpdateControleHistoricoDTO dto = new UpdateControleHistoricoDTO(null);

        Set<ConstraintViolation<UpdateControleHistoricoDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
    }
}
