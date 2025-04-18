package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUpdateUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UnidadeSaudeMapperTest {

    private UnidadeSaudeMapper unidadeSaudeMapper;

    @BeforeEach
    void setUp() {
        unidadeSaudeMapper = new UnidadeSaudeMapper();
    }

    @Test
    void deveConverterEntityParaDTO() {
        UnidadeSaudeEntity entity = new UnidadeSaudeEntity(
                "Hospital Central",
                "Rua Principal, 100",
                "Hospital",
                "11987654321",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );
        entity.setId(1L);

        UnidadeSaudeDTO dto = unidadeSaudeMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("Hospital Central", dto.nomeUnidade());
        assertEquals("Rua Principal, 100", dto.endereco());
        assertEquals("Hospital", dto.tipoUnidade());
        assertEquals("11987654321", dto.telefone());
        assertEquals(LocalTime.of(7, 0), dto.horaAbertura());
        assertEquals(LocalTime.of(19, 0), dto.horaFechamento());
    }

    @Test
    void deveConverterDTOParaDomain() {
        InsertUpdateUnidadeSaudeDTO dto = new InsertUpdateUnidadeSaudeDTO(
                "Posto de Saúde Centro",
                "Avenida Brasil, 200",
                "Posto de Saúde",
                "21987654321",
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );

        UnidadeSaudeDomain domain = unidadeSaudeMapper.toDomain(dto);

        assertNotNull(domain);
        assertEquals("Posto de Saúde Centro", domain.getNomeUnidade());
        assertEquals("Avenida Brasil, 200", domain.getEndereco());
        assertEquals("Posto de Saúde", domain.getTipoUnidade());
        assertEquals("21987654321", domain.getTelefone());
        assertEquals(LocalTime.of(8, 0), domain.getHoraAbertura());
        assertEquals(LocalTime.of(18, 0), domain.getHoraFechamento());
    }

    @Test
    void deveConverterDomainParaEntity() {
        UnidadeSaudeDomain domain = new UnidadeSaudeDomain(
                2L,
                "Clínica Popular",
                "Rua das Flores, 50",
                "Clínica",
                "31987654321",
                LocalTime.of(9, 0),
                LocalTime.of(20, 0)
        );

        UnidadeSaudeEntity entity = unidadeSaudeMapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals("Clínica Popular", entity.getNomeUnidade());
        assertEquals("Rua das Flores, 50", entity.getEndereco());
        assertEquals("Clínica", entity.getTipoUnidade());
        assertEquals("31987654321", entity.getTelefone());
        assertEquals(LocalTime.of(9, 0), entity.getHoraAbertura());
        assertEquals(LocalTime.of(20, 0), entity.getHoraFechamento());
    }

    @Test
    void deveAtualizarEntityComDomain() {
        UnidadeSaudeDomain domain = new UnidadeSaudeDomain(
                3L,
                "Hospital Universitário",
                "Rua Acadêmica, 300",
                "Hospital",
                "41987654321",
                LocalTime.of(6, 0),
                LocalTime.of(22, 0)
        );

        UnidadeSaudeEntity entity = new UnidadeSaudeEntity(
                "Nome Antigo",
                "Endereço Antigo",
                "Tipo Antigo",
                "00000000000",
                LocalTime.of(0, 0),
                LocalTime.of(0, 0)
        );
        entity.setId(3L);

        unidadeSaudeMapper.updateEntityFromDomain(domain, entity);

        assertEquals(3L, entity.getId());
        assertEquals("Hospital Universitário", entity.getNomeUnidade());
        assertEquals("Rua Acadêmica, 300", entity.getEndereco());
        assertEquals("Hospital", entity.getTipoUnidade());
        assertEquals("41987654321", entity.getTelefone());
        assertEquals(LocalTime.of(6, 0), entity.getHoraAbertura());
        assertEquals(LocalTime.of(22, 0), entity.getHoraFechamento());
    }
}
