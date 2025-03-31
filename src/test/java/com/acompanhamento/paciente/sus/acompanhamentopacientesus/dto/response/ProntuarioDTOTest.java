package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ProntuarioDTOTest {

    @Test
    void shouldCreateProntuarioDTOWithValidValues() {
        LocalDateTime now = LocalDateTime.now();
        ProntuarioDTO dto = new ProntuarioDTO(1L, "Cardiologia", now, now.plusDays(30), "Solicitação válida", "Pendente");

        assertNotNull(dto);
        assertEquals(1L, dto.idHistoricoPaciente());
        assertEquals("Cardiologia", dto.especialidadeMedico());
        assertEquals(now, dto.dataInicio());
        assertEquals(now.plusDays(30), dto.dataValidade());
        assertEquals("Solicitação válida", dto.solicitacao());
        assertEquals("Pendente", dto.statusSolicitacaoProntuario());
    }
}

