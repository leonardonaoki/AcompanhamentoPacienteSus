package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeSaudeEntityTest {

    @Test
    void deveCriarUnidadeSaudeEntityComValoresCorretos() {
        // Arrange
        String nomeUnidade = "Hospital Municipal";
        String endereco = "Rua Central, 456";
        String tipoUnidade = "Hospital";
        String telefone = "11987654321";
        LocalTime horaAbertura = LocalTime.of(7, 0);
        LocalTime horaFechamento = LocalTime.of(19, 0);

        // Act
        UnidadeSaudeEntity unidadeSaude = new UnidadeSaudeEntity(nomeUnidade, endereco, tipoUnidade, telefone, horaAbertura, horaFechamento);

        // Assert
        assertNotNull(unidadeSaude);
        assertEquals(nomeUnidade, unidadeSaude.getNomeUnidade());
        assertEquals(endereco, unidadeSaude.getEndereco());
        assertEquals(tipoUnidade, unidadeSaude.getTipoUnidade());
        assertEquals(telefone, unidadeSaude.getTelefone());
        assertEquals(horaAbertura, unidadeSaude.getHoraAbertura());
        assertEquals(horaFechamento, unidadeSaude.getHoraFechamento());
    }

    @Test
    void devePermitirDefinirValoresNaEntidade() {
        // Arrange
        UnidadeSaudeEntity unidadeSaude = new UnidadeSaudeEntity();
        String nomeUnidade = "Posto de Saúde Bairro Novo";
        String endereco = "Avenida das Árvores, 789";
        String tipoUnidade = "Posto de Saúde";
        String telefone = "11991234567";
        LocalTime horaAbertura = LocalTime.of(8, 30);
        LocalTime horaFechamento = LocalTime.of(17, 30);

        // Act
        unidadeSaude.setNomeUnidade(nomeUnidade);
        unidadeSaude.setEndereco(endereco);
        unidadeSaude.setTipoUnidade(tipoUnidade);
        unidadeSaude.setTelefone(telefone);
        unidadeSaude.setHoraAbertura(horaAbertura);
        unidadeSaude.setHoraFechamento(horaFechamento);

        // Assert
        assertEquals(nomeUnidade, unidadeSaude.getNomeUnidade());
        assertEquals(endereco, unidadeSaude.getEndereco());
        assertEquals(tipoUnidade, unidadeSaude.getTipoUnidade());
        assertEquals(telefone, unidadeSaude.getTelefone());
        assertEquals(horaAbertura, unidadeSaude.getHoraAbertura());
        assertEquals(horaFechamento, unidadeSaude.getHoraFechamento());
    }

    @Test
    void deveCriarUnidadeSaudeComIdAutomatico() {
        // Arrange & Act
        UnidadeSaudeEntity unidadeSaude = new UnidadeSaudeEntity();

        // O ID será gerado pelo banco de dados, então inicialmente ele será 0 (valor padrão de `long`).
        assertEquals(0, unidadeSaude.getId());

        // Simulamos um ID definido pelo banco (exemplo em um teste de integração)
        unidadeSaude.setId(10L);

        // Assert
        assertEquals(10L, unidadeSaude.getId());
    }
}
