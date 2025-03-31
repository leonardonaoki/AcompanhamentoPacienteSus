package com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleRuntimeException_ShouldReturnInternalServerError() {
        RuntimeException exception = new RuntimeException("Test runtime exception");

        ResponseEntity<ApiErrorResponse> response = globalExceptionHandler.handleRuntimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Test runtime exception", response.getBody().getMessage());
    }

    @Test
    void handleGenericException_ShouldReturnInternalServerError() {
        Exception exception = new Exception("Test generic exception");

        ResponseEntity<ApiErrorResponse> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Test generic exception", response.getBody().getMessage());
    }
}

