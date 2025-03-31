package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ControleHistoricoDTOTest {

    @Test
    void shouldCreateControleHistoricoDTOWithValidValues() {
        LocalDateTime now = LocalDateTime.now();
        ControleHistoricoDTO dto = new ControleHistoricoDTO(1L, 2L, 3L, now, now, StatusHistoricoPaciente.PRIMEIRA_CONSULTA);

        assertNotNull(dto);
        assertEquals(1L, dto.idHistoricoPaciente());
        assertEquals(2L, dto.idPaciente());
        assertEquals(3L, dto.idUnidade());
        assertEquals(now, dto.dataCadastro());
        assertEquals(now, dto.dataAtualizacao());
        assertEquals(StatusHistoricoPaciente.PRIMEIRA_CONSULTA, dto.statusControle());
    }
}

