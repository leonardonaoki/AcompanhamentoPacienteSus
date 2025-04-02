package com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeSaudeDomainTest {

    @Test
    void deveCriarUnidadeSaudeComSucesso() {
        // Arrange
        long id = 1L;
        String nomeUnidade = "Hospital Teste";
        String endereco = "Rua ABC, 123";
        String tipoUnidade = "Hospital";
        String telefone = "11999999999";
        LocalTime horaAbertura = LocalTime.of(7, 0);
        LocalTime horaFechamento = LocalTime.of(19, 0);

        // Act
        UnidadeSaudeDomain unidade = new UnidadeSaudeDomain(id, nomeUnidade, endereco, tipoUnidade, telefone, horaAbertura, horaFechamento);

        // Assert
        assertNotNull(unidade);
        assertEquals(id, unidade.getId());
        assertEquals(nomeUnidade, unidade.getNomeUnidade());
        assertEquals(endereco, unidade.getEndereco());
        assertEquals(tipoUnidade, unidade.getTipoUnidade());
        assertEquals(telefone, unidade.getTelefone());
        assertEquals(horaAbertura, unidade.getHoraAbertura());
        assertEquals(horaFechamento, unidade.getHoraFechamento());
    }

    @Test
    void deveModificarAtributosComSucesso() {
        // Arrange
        UnidadeSaudeDomain unidade = new UnidadeSaudeDomain();

        // Act
        unidade.setId(2L);
        unidade.setNomeUnidade("Posto de Saúde Central");
        unidade.setEndereco("Avenida XYZ, 456");
        unidade.setTipoUnidade("Posto de Saúde");
        unidade.setTelefone("11888888888");
        unidade.setHoraAbertura(LocalTime.of(8, 0));
        unidade.setHoraFechamento(LocalTime.of(18, 0));

        // Assert
        assertEquals(2L, unidade.getId());
        assertEquals("Posto de Saúde Central", unidade.getNomeUnidade());
        assertEquals("Avenida XYZ, 456", unidade.getEndereco());
        assertEquals("Posto de Saúde", unidade.getTipoUnidade());
        assertEquals("11888888888", unidade.getTelefone());
        assertEquals(LocalTime.of(8, 0), unidade.getHoraAbertura());
        assertEquals(LocalTime.of(18, 0), unidade.getHoraFechamento());
    }
}
