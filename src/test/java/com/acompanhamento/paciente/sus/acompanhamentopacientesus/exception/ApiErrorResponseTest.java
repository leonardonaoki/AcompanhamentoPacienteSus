package com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    @Test
    void deveCriarApiErrorResponseComValoresCorretos() {
        // Arrange
        int status = 400;
        String error = "Bad Request";
        String message = "Requisição inválida";

        // Act
        ApiErrorResponse response = new ApiErrorResponse(status, error, message);

        // Assert
        assertNotNull(response.getTimestamp(), "O timestamp não deve ser nulo.");
        assertEquals(status, response.getStatus(), "O status deve ser 400.");
        assertEquals(error, response.getError(), "O erro deve ser 'Bad Request'.");
        assertEquals(message, response.getMessage(), "A mensagem deve ser 'Requisição inválida'.");
    }

    @Test
    void deveManterOTimestampNoMomentoDaCriacao() {
        // Arrange
        LocalDateTime before = LocalDateTime.now();
        ApiErrorResponse response = new ApiErrorResponse(500, "Internal Server Error", "Erro interno");
        LocalDateTime after = LocalDateTime.now();

        // Assert
        assertNotNull(response.getTimestamp());
        assertTrue(!response.getTimestamp().isBefore(before) && !response.getTimestamp().isAfter(after),
                "O timestamp deve estar dentro do intervalo esperado.");
    }

    @Test
    void deveTestarGetters() {
        // Arrange
        int status = 404;
        String error = "Not Found";
        String message = "Recurso não encontrado";

        // Act
        ApiErrorResponse response = new ApiErrorResponse(status, error, message);

        // Assert
        assertEquals(status, response.getStatus());
        assertEquals(error, response.getError());
        assertEquals(message, response.getMessage());
    }
}
