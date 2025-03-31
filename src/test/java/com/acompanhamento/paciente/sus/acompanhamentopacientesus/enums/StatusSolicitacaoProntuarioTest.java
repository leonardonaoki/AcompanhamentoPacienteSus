package com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusSolicitacaoProntuarioTest {

    @Test
    void testEnumValues() {
        // Assert that the enum contains the expected values
        StatusSolicitacaoProntuario[] values = StatusSolicitacaoProntuario.values();
        assertEquals(3, values.length);
        assertEquals(StatusSolicitacaoProntuario.SOLICITADO, values[0]);
        assertEquals(StatusSolicitacaoProntuario.ENTREGUE, values[1]);
        assertEquals(StatusSolicitacaoProntuario.EXAME_REALIZADO, values[2]);
    }

    @Test
    void testValueOf() {
        // Assert that valueOf correctly maps strings to enum values
        assertEquals(StatusSolicitacaoProntuario.SOLICITADO, StatusSolicitacaoProntuario.valueOf("SOLICITADO"));
        assertEquals(StatusSolicitacaoProntuario.ENTREGUE, StatusSolicitacaoProntuario.valueOf("ENTREGUE"));
        assertEquals(StatusSolicitacaoProntuario.EXAME_REALIZADO, StatusSolicitacaoProntuario.valueOf("EXAME_REALIZADO"));
    }

    @Test
    void testOrdinal() {
        // Assert the ordinal values of the enum constants
        assertEquals(0, StatusSolicitacaoProntuario.SOLICITADO.ordinal());
        assertEquals(1, StatusSolicitacaoProntuario.ENTREGUE.ordinal());
        assertEquals(2, StatusSolicitacaoProntuario.EXAME_REALIZADO.ordinal());
    }
}
