package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ControleHistoricoPacienteMapperTest {

    private ControleHistoricoPacienteMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ControleHistoricoPacienteMapper();
    }

    @Test
    void testToDTO() {
        // Arrange
        ControleHistoricoPacienteEntity entity = new ControleHistoricoPacienteEntity();
        entity.setIdHistoricoPaciente(1L);
        entity.setIdPaciente(2L);
        entity.setIdUnidade(3L);
        entity.setDataCadastro(LocalDateTime.of(2023,1,1,0,0));
        entity.setDataAtualizacao(LocalDateTime.of(2023,1,2,0,0));
        entity.setStatusControle(StatusHistoricoPaciente.EM_CURSO);

        // Act
        ControleHistoricoDTO dto = mapper.toDTO(entity);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.idHistoricoPaciente());
        assertEquals(2L, dto.idPaciente());
        assertEquals(3L, dto.idUnidade());
        assertEquals(LocalDateTime.of(2023, 1,1,0,0), dto.dataCadastro());
        assertEquals(LocalDateTime.of(2023,1,2,0,0), dto.dataAtualizacao());
        assertEquals(StatusHistoricoPaciente.EM_CURSO, dto.statusControle());
    }

    @Test
    void testToDomain() {
        // Arrange
        InsertControleHistoricoDTO dto = new InsertControleHistoricoDTO(2L, 3L);

        // Act
        ControleHistoricoPacienteDomain domain = mapper.toDomain(dto);

        // Assert
        assertNotNull(domain);
        assertEquals(2L, domain.getIdPaciente());
        assertEquals(3L, domain.getIdUnidade());
    }

    @Test
    void testToEntity() {
        // Arrange
        ControleHistoricoPacienteDomain domain = new ControleHistoricoPacienteDomain();
        domain.setIdPaciente(2L);
        domain.setIdUnidade(3L);
        domain.setDataCadastro(LocalDateTime.of(2023,1,1,0,0));
        domain.setDataAtualizacao(LocalDateTime.of(2023,1,2,0,0));
        domain.setStatusControle(StatusHistoricoPaciente.ENCERRADO);

        // Act
        ControleHistoricoPacienteEntity entity = mapper.toEntity(domain);

        // Assert
        assertNotNull(entity);
        assertEquals(2L, entity.getIdPaciente());
        assertEquals(3L, entity.getIdUnidade());
        assertEquals(LocalDateTime.of(2023,1,1,0,0), entity.getDataCadastro());
        assertEquals(LocalDateTime.of(2023,1,2,0,0), entity.getDataAtualizacao());
        assertEquals(StatusHistoricoPaciente.ENCERRADO, entity.getStatusControle());
    }
}
