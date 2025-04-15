package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IPacienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarPacienteUseCaseTest {

    @Mock
    private IPacienteGateway pacienteGateway;

    @InjectMocks
    private AtualizarPacienteUseCase atualizarPacienteUseCase;

    private PacienteDomain pacienteDomain;
    private PacienteDTO pacienteDTO;

    @BeforeEach
    void setUp() {
        LocalDateTime dataNascimento = LocalDateTime.of(1990, 5, 20, 0, 0);
        LocalDateTime dataCadastro = LocalDateTime.now().minusMonths(6);
        LocalDateTime dataAtualizacao = LocalDateTime.now().minusDays(1);

        pacienteDomain = new PacienteDomain();
        pacienteDomain.setIdPaciente(1L);
        pacienteDomain.setNome("João Silva");
        pacienteDomain.setCpf("123.456.789-00");
        pacienteDomain.setEndereco("Rua das Flores, 123");
        pacienteDomain.setTelefone("3185056436");
        pacienteDomain.setDataNascimento(dataNascimento);
        pacienteDomain.setDataCadastro(dataCadastro);
        pacienteDomain.setDataAtualizacao(dataAtualizacao);

        pacienteDTO = new PacienteDTO(
                1L,
                "João Silva Atualizado",
                "123.456.789-00",
                "Rua das Flores, 123",
                "3185056436",
                dataNascimento,
                dataCadastro,
                LocalDateTime.now()
        );
    }

    @Test
    void deveAtualizarPacienteComSucesso() {
        // Arrange
        long pacienteId = 1L;
        when(pacienteGateway.atualizarPaciente(eq(pacienteId), any(PacienteDomain.class)))
                .thenReturn(pacienteDTO);

        // Act
        PacienteDTO resultado = atualizarPacienteUseCase.atualizarPaciente(pacienteId, pacienteDomain);

        // Assert
        assertNotNull(resultado);
        assertEquals(pacienteDTO.idPaciente(), resultado.idPaciente());
        assertEquals(pacienteDTO.nome(), resultado.nome());
        assertEquals(pacienteDTO.cpf(), resultado.cpf());
        assertEquals(pacienteDTO.endereco(), resultado.endereco());
        assertEquals(pacienteDTO.telefoneCelular(), resultado.telefoneCelular());
        assertEquals(pacienteDTO.dataNascimento(), resultado.dataNascimento());
        assertEquals(pacienteDTO.dataCadastro(), resultado.dataCadastro());

        // Garante que a data de atualização foi modificada corretamente
        assertNotNull(pacienteDomain.getDataAtualizacao());
        assertTrue(pacienteDomain.getDataAtualizacao().isAfter(pacienteDTO.dataAtualizacao().minusSeconds(1)));

        // Verifica se o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).atualizarPaciente(eq(pacienteId), any(PacienteDomain.class));
    }

    @Test
    void deveLancarExcecaoQuandoGatewayFalhar() {
        // Arrange
        long pacienteId = 1L;
        when(pacienteGateway.atualizarPaciente(eq(pacienteId), any(PacienteDomain.class)))
                .thenThrow(new RuntimeException("Erro ao atualizar paciente"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                atualizarPacienteUseCase.atualizarPaciente(pacienteId, pacienteDomain)
        );

        assertEquals("Erro ao atualizar paciente", exception.getMessage());

        // Verifica se o método foi chamado exatamente uma vez
        verify(pacienteGateway, times(1)).atualizarPaciente(eq(pacienteId), any(PacienteDomain.class));
    }
}
