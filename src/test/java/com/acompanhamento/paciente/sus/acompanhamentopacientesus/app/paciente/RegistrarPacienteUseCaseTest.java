package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrarPacienteUseCaseTest {

    @Mock
    private IPacienteGateway pacienteGateway;

    @InjectMocks
    private RegistrarPacienteUseCase registrarPacienteUseCase;

    @Test
    void deveRegistrarPacienteComSucesso() {
        // Arrange
        PacienteDomain pacienteDomain = new PacienteDomain();
        pacienteDomain.setNome("Carlos Souza");
        pacienteDomain.setCpf("111.222.333-44");
        pacienteDomain.setEndereco("Rua A, 123");
        pacienteDomain.setTelefone("3185056436");

        PacienteDTO pacienteDTO = new PacienteDTO(
                1L, pacienteDomain.getNome(), pacienteDomain.getCpf(), pacienteDomain.getEndereco(),pacienteDomain.getTelefone(),
                LocalDateTime.of(1995, 4, 15, 0, 0),
                LocalDateTime.now(), LocalDateTime.now()
        );

        when(pacienteGateway.registrarPaciente(any(PacienteDomain.class))).thenReturn(pacienteDTO);

        // Act
        PacienteDTO resultado = registrarPacienteUseCase.registrarPaciente(pacienteDomain);

        // Assert
        assertNotNull(resultado);
        assertEquals("Carlos Souza", resultado.nome());
        assertEquals("111.222.333-44", resultado.cpf());
        assertEquals("Rua A, 123", resultado.endereco());
        assertEquals("3185056436", resultado.telefoneCelular());
        assertNotNull(pacienteDomain.getDataCadastro());
        assertNotNull(pacienteDomain.getDataAtualizacao());

        // Verifica se o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).registrarPaciente(pacienteDomain);
    }

    @Test
    void deveLancarExcecaoQuandoGatewayFalhar() {
        // Arrange
        PacienteDomain pacienteDomain = new PacienteDomain();
        pacienteDomain.setNome("Carlos Souza");
        pacienteDomain.setCpf("111.222.333-44");
        pacienteDomain.setEndereco("Rua A, 123");

        when(pacienteGateway.registrarPaciente(any(PacienteDomain.class)))
                .thenThrow(new RuntimeException("Erro ao registrar paciente"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                registrarPacienteUseCase.registrarPaciente(pacienteDomain)
        );

        assertEquals("Erro ao registrar paciente", exception.getMessage());

        // Verifica que o método foi chamado exatamente uma vez antes da falha
        verify(pacienteGateway, times(1)).registrarPaciente(pacienteDomain);
    }
}
