package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ControleHistoricoPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IControleHistoricoPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ControleHistoricoGatewayTest {

    @Mock
    private IControleHistoricoPacienteRepository controleHistoricoRepository;

    @Mock
    private IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;

    @InjectMocks
    private ControleHistoricoGateway controleHistoricoGateway;

    private ControleHistoricoPacienteEntity entity;
    private ControleHistoricoDTO dto;

    @BeforeEach
    void setUp() {
        entity = new ControleHistoricoPacienteEntity();
        entity.setIdHistoricoPaciente(1L);
        entity.setStatusControle(StatusHistoricoPaciente.PRIMEIRA_CONSULTA);
        entity.setDataCadastro(LocalDateTime.now());

        dto = new ControleHistoricoDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now(),StatusHistoricoPaciente.PRIMEIRA_CONSULTA);
    }

    @Test
    void testListarHistoricoPacientePorIdControle_Sucesso() {
        when(controleHistoricoRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(controleHistoricoPacienteMapper.toDTO(entity)).thenReturn(dto);

        ControleHistoricoDTO resultado = controleHistoricoGateway.listarHistoricoPacientePorIdControle(1L);

        assertNotNull(resultado);
        verify(controleHistoricoRepository).findById(1L);
    }

    @Test
    void testListarHistoricoPacientePorIdControle_NaoEncontrado() {
        when(controleHistoricoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                controleHistoricoGateway.listarHistoricoPacientePorIdControle(1L)
        );
    }

    @Test
    void testRegistrarHistoricoPaciente_Sucesso() {
        ControleHistoricoPacienteDomain domain = new ControleHistoricoPacienteDomain();
        when(controleHistoricoPacienteMapper.toEntity(domain)).thenReturn(entity);
        when(controleHistoricoRepository.save(entity)).thenReturn(entity);

        InsertMessageDTO resultado = controleHistoricoGateway.registrarHistoricoPaciente(domain);

        assertNotNull(resultado);
        assertTrue(resultado.message().contains("ID de controle gerado"));
    }

    @Test
    void testAtualizarStatusHistoricoPaciente_Sucesso() {
        when(controleHistoricoRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(controleHistoricoRepository.save(entity)).thenReturn(entity);
        when(controleHistoricoPacienteMapper.toDTO(entity)).thenReturn(dto);

        ControleHistoricoDTO resultado = controleHistoricoGateway.atualizarStatusHistoricoPaciente(1L, StatusHistoricoPaciente.RETORNO);

        assertNotNull(resultado);
    }

    @Test
    void testAtualizarStatusHistoricoPaciente_NaoEncontrado() {
        when(controleHistoricoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                controleHistoricoGateway.atualizarStatusHistoricoPaciente(1L, StatusHistoricoPaciente.EM_CURSO)
        );
    }

    @Test
    void testAtualizarStatusHistoricoPaciente_StatusIgual() {
        when(controleHistoricoRepository.findById(1L)).thenReturn(Optional.of(entity));

        assertThrows(IllegalArgumentException.class, () ->
                controleHistoricoGateway.atualizarStatusHistoricoPaciente(1L, StatusHistoricoPaciente.PRIMEIRA_CONSULTA)
        );
    }

    @Test
    void testListarHistoricoPacientePorId_Sucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ControleHistoricoPacienteEntity> entities = List.of(entity);
        Page<ControleHistoricoPacienteEntity> page = new PageImpl<>(entities, pageable, entities.size());

        when(controleHistoricoRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(controleHistoricoPacienteMapper.toDTO(any())).thenReturn(dto);

        List<ControleHistoricoDTO> resultado = controleHistoricoGateway.listarHistoricoPacientePorId(1L, 1L, LocalDateTime.now(), StatusHistoricoPaciente.PRIMEIRA_CONSULTA, 0, 10);

        assertFalse(resultado.isEmpty());
    }

    @Test
    void testListarHistoricoPacientePorId_NaoEncontrado() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ControleHistoricoPacienteEntity> page = Page.empty(pageable);

        when(controleHistoricoRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

        assertThrows(EntityNotFoundException.class, () ->
                controleHistoricoGateway.listarHistoricoPacientePorId(1L, 1L, null, StatusHistoricoPaciente.PRIMEIRA_CONSULTA, 0, 10)
        );
    }
}
