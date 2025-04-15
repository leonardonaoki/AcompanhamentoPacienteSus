package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.AtualizarStatusHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdControleUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.RegistrarHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente.ListarPacientePorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude.ListarUnidadePorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ControleHistoricoControllerTest {

    @Mock
    private ListarHistoricoPacientePorIdUseCase listarHistoricoPacientePorIdUseCase;

    @Mock
    private ListarHistoricoPacientePorIdControleUseCase listarHistoricoPacientePorIdControleUseCase;

    @Mock
    private RegistrarHistoricoPacienteUseCase registrarHistoricoPacienteUseCase;

    @Mock
    private AtualizarStatusHistoricoPacienteUseCase atualizarStatusHistoricoPacienteUseCase;

    @Mock
    private ListarUnidadePorIdUseCase listarUnidadePorIdUseCase;
    @Mock
    private IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;
    @InjectMocks
    private ControleHistoricoController controller;
    @Mock
    private ListarPacientePorIdUseCase listarPacientePorIdUseCase;
    private ControleHistoricoDTO controleHistoricoDTO;
    private InsertMessageDTO insertMessageDTO;

    @BeforeEach
    void setUp() {
        controleHistoricoDTO = new ControleHistoricoDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now(),StatusHistoricoPaciente.PRIMEIRA_CONSULTA);
        insertMessageDTO = new InsertMessageDTO("ID de controle gerado: 1");
    }

    @Test
    void testListarHistoricoPacientePorIdControle_Sucesso() {
        List<ControleHistoricoDTO> listaRetorno = List.of(controleHistoricoDTO);
        when(listarHistoricoPacientePorIdUseCase.listarPacientePorId(1L,null,null,null,0,10)).thenReturn(listaRetorno);
        ResponseEntity<List<ControleHistoricoDTO>> response = controller.listarHistoricoPacientePorId(1L,null,null,null,0,10);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testListarHistoricoPacientePorId_Sucesso() {
        when(listarHistoricoPacientePorIdControleUseCase.listarPacientePorIdControle(1L)).thenReturn(controleHistoricoDTO);
        ResponseEntity<ControleHistoricoDTO> response = controller.listarHistoricoPacientePorIdControle(1L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRegistrarHistoricoPaciente_Sucesso() {
        InsertControleHistoricoDTO insertDTO = new InsertControleHistoricoDTO(1,1);
        when(controleHistoricoPacienteMapper.toDomain(insertDTO)).thenReturn(null);
        when(registrarHistoricoPacienteUseCase.registrarHistoricoPaciente(any())).thenReturn(insertMessageDTO);
        when(listarPacientePorIdUseCase.listarPacientePorId(1)).thenReturn(
                new PacienteDTO(1,"Gilberto","123.456.789-01","Rua teste","3185056436",LocalDateTime.now(),
                        LocalDateTime.now(),LocalDateTime.now()));
        when(listarUnidadePorIdUseCase.listarUnidadePorId(1)).thenReturn(
                new UnidadeSaudeDTO(1,"Aclimacao","Rua aclimacao","Saude","12345-789", LocalTime.now(),LocalTime.now()));
        ResponseEntity<InsertMessageDTO> response = controller.registrarHistoricoPaciente(insertDTO);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("ID de controle gerado: 1", response.getBody().message());
    }
    @Test
    void testRegistrarHistoricoPaciente_PacienteNotFound() {
        InsertControleHistoricoDTO insertDTO = new InsertControleHistoricoDTO(1,1);
        when(listarPacientePorIdUseCase.listarPacientePorId(1)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class,() ->controller.registrarHistoricoPaciente(insertDTO));
    }
    @Test
    void testRegistrarHistoricoPaciente_UnidadeNotFound() {
        InsertControleHistoricoDTO insertDTO = new InsertControleHistoricoDTO(1,1);
        when(listarPacientePorIdUseCase.listarPacientePorId(1)).thenReturn(
                new PacienteDTO(1,"Gilberto","123.456.789-01","Rua teste","3185056436",LocalDateTime.now(),
                        LocalDateTime.now(),LocalDateTime.now()));
        when(listarUnidadePorIdUseCase.listarUnidadePorId(1)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class,() ->controller.registrarHistoricoPaciente(insertDTO));
    }

    @Test
    void testAtualizarStatusHistoricoPaciente_Sucesso() {
        UpdateControleHistoricoDTO updateDTO = new UpdateControleHistoricoDTO("RETORNO");
        when(atualizarStatusHistoricoPacienteUseCase.atualizarStatusHistoricoPaciente(1L, StatusHistoricoPaciente.RETORNO)).thenReturn(controleHistoricoDTO);

        ResponseEntity<ControleHistoricoDTO> response = controller.atualizarStatusHistoricoPaciente(1L, updateDTO);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testAtualizarStatusHistoricoPaciente_Erro() {
        UpdateControleHistoricoDTO updateDTO = new UpdateControleHistoricoDTO("NAO PERMITIDO");
        assertThrows(IllegalArgumentException.class,() -> controller.atualizarStatusHistoricoPaciente(1L, updateDTO));
    }
}

