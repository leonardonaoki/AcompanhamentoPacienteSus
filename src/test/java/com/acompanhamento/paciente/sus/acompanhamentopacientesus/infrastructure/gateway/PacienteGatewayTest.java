package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteGatewayTest {

    @Mock
    private IPacienteRepository pacienteRepository;

    @Mock
    private IPacienteMapper pacienteMapper;

    @InjectMocks
    private PacienteGateway pacienteGateway;

    private PacienteEntity pacienteEntity;
    private PacienteDomain pacienteDomain;
    private PacienteDTO pacienteDTO;

    @BeforeEach
    void setUp() {
        pacienteEntity = new PacienteEntity();
        pacienteEntity.setIdPaciente(1L);
        pacienteEntity.setNome("Carlos Souza");
        pacienteEntity.setCpf("12345678901");
        pacienteEntity.setEndereco("Rua A, 123");
        pacienteEntity.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));
        pacienteEntity.setDataCadastro(LocalDateTime.now());
        pacienteEntity.setDataAtualizacao(LocalDateTime.now());

        pacienteDomain = new PacienteDomain();
        pacienteDomain.setIdPaciente(1L);
        pacienteDomain.setNome("Carlos Souza");
        pacienteDomain.setCpf("12345678901");
        pacienteDomain.setEndereco("Rua A, 123");
        pacienteDomain.setDataNascimento(LocalDateTime.of(1995, 4, 15, 0, 0));

        pacienteDTO = new PacienteDTO(
                1L,
                "Carlos Souza",
                "12345678901",
                "Rua A, 123",
                LocalDateTime.of(1995, 4, 15, 0, 0),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Test
    void deveRegistrarPacienteComSucesso() {
        when(pacienteMapper.toEntity(pacienteDomain)).thenReturn(pacienteEntity);
        when(pacienteRepository.save(pacienteEntity)).thenReturn(pacienteEntity);
        when(pacienteMapper.toDTO(pacienteEntity)).thenReturn(pacienteDTO);

        PacienteDTO resultado = pacienteGateway.registrarPaciente(pacienteDomain);

        assertThat(resultado).isEqualTo(pacienteDTO);
        verify(pacienteRepository, times(1)).save(pacienteEntity);
    }

    @Test
    void deveAtualizarPacienteExistente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEntity));
        when(pacienteRepository.save(pacienteEntity)).thenReturn(pacienteEntity);
        when(pacienteMapper.toDTO(pacienteEntity)).thenReturn(pacienteDTO);

        PacienteDTO resultado = pacienteGateway.atualizarPaciente(1L, pacienteDomain);

        assertThat(resultado).isEqualTo(pacienteDTO);
        verify(pacienteRepository, times(1)).save(pacienteEntity);
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarPacienteInexistente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pacienteGateway.atualizarPaciente(1L, pacienteDomain))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Não foi possível identificar o paciente com o ID 1");

        verify(pacienteRepository, never()).save(any());
    }

    @Test
    void deveRetornarPacientePorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEntity));
        when(pacienteMapper.toDTO(pacienteEntity)).thenReturn(pacienteDTO);

        PacienteDTO resultado = pacienteGateway.listarPacientePorId(1L);

        assertThat(resultado).isEqualTo(pacienteDTO);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoForEncontradoPorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pacienteGateway.listarPacientePorId(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Não foi possível identificar o paciente com o ID 1");
    }

    @Test
    void deveListarTodosPacientes() {
        List<PacienteEntity> listaPacientes = List.of(pacienteEntity);
        List<PacienteDTO> listaDTOs = List.of(pacienteDTO);

        when(pacienteRepository.findAll()).thenReturn(listaPacientes);
        when(pacienteMapper.toDTOList(listaPacientes)).thenReturn(listaDTOs);

        List<PacienteDTO> resultado = pacienteGateway.listarTodosPacientes();

        assertThat(resultado).hasSize(1).containsExactly(pacienteDTO);
    }

    @Test
    void deveDeletarPacienteExistente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEntity));

        pacienteGateway.deletarPaciente(1L);

        verify(pacienteRepository, times(1)).delete(pacienteEntity);
    }

    @Test
    void deveLancarExcecaoQuandoTentarDeletarPacienteInexistente() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pacienteGateway.deletarPaciente(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Não foi possível identificar o paciente com o ID 1");

        verify(pacienteRepository, never()).delete(any());
    }
}
