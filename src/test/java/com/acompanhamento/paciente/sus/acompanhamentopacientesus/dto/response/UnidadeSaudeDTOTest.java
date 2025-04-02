package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeSaudeDTOTest {

    @Test
    void deveCriarUnidadeSaudeDTOComValoresCorretos() {
        // Arrange
        long id = 1L;
        String nomeUnidade = "Hospital Central";
        String endereco = "Rua Principal, 123";
        String tipoUnidade = "Hospital";
        String telefone = "11988887777";
        LocalTime horaAbertura = LocalTime.of(8, 0);
        LocalTime horaFechamento = LocalTime.of(18, 0);

        // Act
        UnidadeSaudeDTO dto = new UnidadeSaudeDTO(id, nomeUnidade, endereco, tipoUnidade, telefone, horaAbertura, horaFechamento);

        // Assert
        assertNotNull(dto);
        assertEquals(id, dto.id());
        assertEquals(nomeUnidade, dto.nomeUnidade());
        assertEquals(endereco, dto.endereco());
        assertEquals(tipoUnidade, dto.tipoUnidade());
        assertEquals(telefone, dto.telefone());
        assertEquals(horaAbertura, dto.horaAbertura());
        assertEquals(horaFechamento, dto.horaFechamento());
    }

    @Test
    void deveManterValoresImutaveisAoCriarUnidadeSaudeDTO() {
        // Arrange
        UnidadeSaudeDTO dto = new UnidadeSaudeDTO(
                2L,
                "Unidade Básica de Saúde",
                "Avenida Saúde, 456",
                "Posto de Saúde",
                "11999998888",
                LocalTime.of(7, 30),
                LocalTime.of(17, 30)
        );

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> dto.getClass().getMethod("setNomeUnidade", String.class));
    }
}
