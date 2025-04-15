package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertPacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdatePacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PacienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ListarTodosPacientesUseCase listarTodosPacientesUseCase;

    @Mock
    private ListarPacientePorIdUseCase listarPacientePorIdUseCase;

    @Mock
    private RegistrarPacienteUseCase registrarPacienteUseCase;

    @Mock
    private AtualizarPacienteUseCase atualizarPacienteUseCase;

    @Mock
    private DeletarPacienteUseCase deletarPacienteUseCase;

    @InjectMocks
    private PacienteController pacienteController;

    private PacienteDTO pacienteDTO;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteController).build();

        // ✅ Suporte ao LocalDateTime no JSON
        objectMapper.registerModule(new JavaTimeModule());

        pacienteDTO = new PacienteDTO(
                1L,
                "João Silva",
                "12345678901",
                "Rua Exemplo, 123",
                "3185056436",
                LocalDateTime.of(1990, 5, 20, 0, 0),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Test
    void deveListarTodosPacientes() throws Exception {
        when(listarTodosPacientesUseCase.listarTodosPacientes()).thenReturn(List.of(pacienteDTO));

        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Silva"));

        verify(listarTodosPacientesUseCase, times(1)).listarTodosPacientes();
    }

    @Test
    void deveListarPacientePorId() throws Exception {
        when(listarPacientePorIdUseCase.listarPacientePorId(1L)).thenReturn(pacienteDTO);

        mockMvc.perform(get("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(listarPacientePorIdUseCase, times(1)).listarPacientePorId(1L);
    }

    @Test
    void deveRetornarNotFoundQuandoPacienteNaoExiste() throws Exception {
        when(listarPacientePorIdUseCase.listarPacientePorId(1L)).thenReturn(null);

        mockMvc.perform(get("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(listarPacientePorIdUseCase, times(1)).listarPacientePorId(1L);
    }

    @Test
    void deveRegistrarPaciente() throws Exception {
        InsertPacienteDTO dto = new InsertPacienteDTO(1L, "João Silva", "12345678901", "Rua Exemplo, 123","3185056436", LocalDateTime.of(1990, 5, 20, 0, 0));

        when(registrarPacienteUseCase.registrarPaciente(any(PacienteDomain.class))).thenReturn(pacienteDTO);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(registrarPacienteUseCase, times(1)).registrarPaciente(any(PacienteDomain.class));
    }

    @Test
    void deveAtualizarPaciente() throws Exception {
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("João Silva");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua Nova, 456");
        dto.setTelefoneCelular("3185056436");
        dto.setDataNascimento(LocalDateTime.of(1990, 5, 20, 0, 0));

        when(atualizarPacienteUseCase.atualizarPaciente(eq(1L), any(PacienteDomain.class))).thenReturn(pacienteDTO);

        mockMvc.perform(put("/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));

        verify(atualizarPacienteUseCase, times(1)).atualizarPaciente(eq(1L), any(PacienteDomain.class));
    }

    @Test
    void deveRetornarNotFoundQuandoAtualizarPacienteNaoExistente() throws Exception {
        UpdatePacienteDTO dto = new UpdatePacienteDTO();
        dto.setNome("João Silva");
        dto.setCpf("12345678901");
        dto.setEndereco("Rua Nova, 456");
        dto.setDataNascimento(LocalDateTime.of(1990, 5, 20, 0, 0));

        when(atualizarPacienteUseCase.atualizarPaciente(eq(1L), any(PacienteDomain.class))).thenReturn(null);

        mockMvc.perform(put("/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());

        verify(atualizarPacienteUseCase, times(1)).atualizarPaciente(eq(1L), any(PacienteDomain.class));
    }

    @Test
    void deveDeletarPaciente() throws Exception {
        doNothing().when(deletarPacienteUseCase).deletarPaciente(1L);

        mockMvc.perform(delete("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 🔄 Mudança de isNoContent() para isOk()

        verify(deletarPacienteUseCase, times(1)).deletarPaciente(1L);
    }

}
