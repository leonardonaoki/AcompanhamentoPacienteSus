package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProntuarioPacienteEntityTest {

    private ProntuarioPacienteEntity entity;

    @BeforeEach
    void setUp() {
        entity = new ProntuarioPacienteEntity();
    }

    @Test
    void deveDefinirProntuarioEObterValoresCorretamente() {
        long id = 1L;
        long idHistoricoPaciente = 2L;
        String especialidadeMedico = "Cardiologia";
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataValidade = LocalDateTime.now().plusDays(30);
        String solicitacao = "Exames laboratoriais";
        String statusSolicitacaoProntuario = "SOLICITADO";

        entity.setId(id);
        entity.setIdHistoricoPaciente(idHistoricoPaciente);
        entity.setEspecialidadeMedico(especialidadeMedico);
        entity.setDataInicio(dataInicio);
        entity.setDataValidade(dataValidade);
        entity.setSolicitacao(solicitacao);
        entity.setStatusSolicitacaoProntuario(statusSolicitacaoProntuario);

        assertEquals(id, entity.getId());
        assertEquals(idHistoricoPaciente, entity.getIdHistoricoPaciente());
        assertEquals(especialidadeMedico, entity.getEspecialidadeMedico());
        assertEquals(dataInicio, entity.getDataInicio());
        assertEquals(dataValidade, entity.getDataValidade());
        assertEquals(solicitacao, entity.getSolicitacao());
        assertEquals(statusSolicitacaoProntuario, entity.getStatusSolicitacaoProntuario());
    }
}
