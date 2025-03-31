package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import static org.junit.jupiter.api.Assertions.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ControleHistoricoPacienteEntityTest {

    private ControleHistoricoPacienteEntity entity;

    @BeforeEach
    void setUp() {
        entity = new ControleHistoricoPacienteEntity();
    }

    @Test
    void deveDefinirControleHistoricoEObterValoresCorretamente() {
        long idHistoricoPaciente = 1L;
        long idPaciente = 2L;
        long idUnidade = 3L;
        LocalDateTime dataCadastro = LocalDateTime.now();
        LocalDateTime dataAtualizacao = LocalDateTime.now();
        StatusHistoricoPaciente status = StatusHistoricoPaciente.PRIMEIRA_CONSULTA;

        entity.setIdHistoricoPaciente(idHistoricoPaciente);
        entity.setIdPaciente(idPaciente);
        entity.setIdUnidade(idUnidade);
        entity.setDataCadastro(dataCadastro);
        entity.setDataAtualizacao(dataAtualizacao);
        entity.setStatusControle(status);

        assertEquals(idHistoricoPaciente, entity.getIdHistoricoPaciente());
        assertEquals(idPaciente, entity.getIdPaciente());
        assertEquals(idUnidade, entity.getIdUnidade());
        assertEquals(dataCadastro, entity.getDataCadastro());
        assertEquals(dataAtualizacao, entity.getDataAtualizacao());
        assertEquals(status, entity.getStatusControle());
    }
}
