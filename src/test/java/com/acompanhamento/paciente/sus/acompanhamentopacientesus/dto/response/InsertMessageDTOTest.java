package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertMessageDTOTest {

    @Test
    void shouldCreateInsertMessageDTOWithValidMessage() {
        InsertMessageDTO dto = new InsertMessageDTO("Hello, World!");
        assertNotNull(dto);
        assertEquals("Hello, World!", dto.message());
    }

    @Test
    void shouldAllowNullMessage() {
        InsertMessageDTO dto = new InsertMessageDTO(null);
        assertNotNull(dto);
        assertNull(dto.message());
    }
}
