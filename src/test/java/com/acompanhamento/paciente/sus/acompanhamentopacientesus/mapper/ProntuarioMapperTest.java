package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProntuarioMapperTest {

    private ProntuarioMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProntuarioMapper();
    }

    @Test
    void testToDTO() {
        // Arrange
        ProntuarioPacienteEntity entity = new ProntuarioPacienteEntity();
        entity.setIdHistoricoPaciente(1L);
        entity.setEspecialidadeMedico("Cardiologista");
        entity.setDataInicio(LocalDateTime.of(2023,1,1,0,0));
        entity.setDataValidade(LocalDateTime.of(2023,12,31,0,0));
        entity.setSolicitacao("Tomar 1 comprimido por dia");
        entity.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.ENTREGUE.toString());

        // Act
        ProntuarioDTO dto = mapper.toDTO(entity);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.idHistoricoPaciente());
        assertEquals("Cardiologista", dto.especialidadeMedico());
        assertEquals(LocalDateTime.of(2023,1,1,0,0), dto.dataInicio());
        assertEquals(LocalDateTime.of(2023,12,31,0,0), dto.dataValidade());
        assertEquals("Tomar 1 comprimido por dia", dto.solicitacao());
        assertEquals(StatusSolicitacaoProntuario.ENTREGUE.toString(), dto.statusSolicitacaoProntuario());
    }

    @Test
    void testToDomain() {
        // Arrange
        Long idControle = 1L;
        InsertProntuarioDTO dto = new InsertProntuarioDTO(
                "Ortopedista",
                LocalDateTime.of(2023,12,31,0,0),
                "Tomar 1 capsula por dia",
                StatusSolicitacaoProntuario.SOLICITADO.toString()
        );

        // Act
        ProntuarioPacienteDomain domain = mapper.toDomain(idControle, dto);

        // Assert
        assertNotNull(domain);
        assertEquals(1L, domain.getIdControleHistorico());
        assertEquals("Ortopedista", domain.getEspecialidadeMedico());
        assertEquals(LocalDateTime.of(2023,12,31,0,0), domain.getDataValidade());
        assertEquals("Tomar 1 capsula por dia", domain.getSolicitacao());
        assertEquals(StatusSolicitacaoProntuario.SOLICITADO, domain.getStatusSolicitacaoProntuario());
    }

    @Test
    void testToEntity() {
        // Arrange
        ProntuarioPacienteDomain domain = new ProntuarioPacienteDomain();
        domain.setIdControleHistorico(1L);
        domain.setEspecialidadeMedico("Neurologista");
        domain.setDataInicio(LocalDateTime.of(2023,1,1,0,0));
        domain.setDataValidade(LocalDateTime.of(2023,6,30,0,0));
        domain.setSolicitacao("Realizar tomografia");
        domain.setStatusSolicitacaoProntuario(StatusSolicitacaoProntuario.EXAME_REALIZADO);

        // Act
        ProntuarioPacienteEntity entity = mapper.toEntity(domain);

        // Assert
        assertNotNull(entity);
        assertEquals(1L, entity.getIdHistoricoPaciente());
        assertEquals("Neurologista", entity.getEspecialidadeMedico());
        assertEquals(LocalDateTime.of(2023,1,1,0,0), entity.getDataInicio());
        assertEquals(LocalDateTime.of(2023,6,30,0,0), entity.getDataValidade());
        assertEquals("Realizar tomografia", entity.getSolicitacao());
        assertEquals(StatusSolicitacaoProntuario.EXAME_REALIZADO.toString(), entity.getStatusSolicitacaoProntuario());
    }
}
