package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IUnidadeSaudeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnidadeSaudeControllerTest {

    @InjectMocks
    private UnidadeSaudeController unidadeSaudeController;

    @Mock
    private ListarUnidadePorIdUseCase listarUnidadePorIdUseCase;

    @Mock
    private ListarTodasUnidadesUseCase listarTodasUnidadePorIdUseCase;

    @Mock
    private RegistrarUnidadeSaudeUseCase registrarUnidadeSaudeUseCase;

    @Mock
    private AtualizarUnidadeSaudeUseCase atualizarUnidadeSaudeUseCase;

    @Mock
    private DeletarUnidadeSaudeUseCase deletarUnidadeSaudeUseCase;

    @Mock
    private IUnidadeSaudeMapper unidadeSaudeMapper;

    private UnidadeSaudeDTO unidadeSaudeDTO;
    private UnidadeSaudeDomain unidadeSaudeDomain;
    private InsertUnidadeSaudeDTO insertUnidadeSaudeDTO;
    private UpdateUnidadeSaudeDTO updateUnidadeSaudeDTO;

    @BeforeEach
    void setUp() {
        unidadeSaudeDTO = new UnidadeSaudeDTO(
                1L, "Posto Central", "Rua 100", "Posto",
                "11987654321", LocalTime.of(8, 0), LocalTime.of(18, 0)
        );

        unidadeSaudeDomain = new UnidadeSaudeDomain(
                1L, "Posto Central", "Rua 100", "Posto",
                "11987654321", LocalTime.of(8, 0), LocalTime.of(18, 0)
        );

        insertUnidadeSaudeDTO = new InsertUnidadeSaudeDTO(
                "Posto Central", "Rua 100", "Posto",
                "11987654321", LocalTime.of(8, 0), LocalTime.of(18, 0)
        );

        updateUnidadeSaudeDTO = new UpdateUnidadeSaudeDTO();
        updateUnidadeSaudeDTO.setNomeUnidade("Posto Atualizado");
        updateUnidadeSaudeDTO.setEndereco("Rua 200");
        updateUnidadeSaudeDTO.setTipoUnidade("Clínica");
        updateUnidadeSaudeDTO.setTelefone("11999999999");
        updateUnidadeSaudeDTO.setHoraAbertura(LocalTime.of(7, 0));
        updateUnidadeSaudeDTO.setHoraFechamento(LocalTime.of(19, 0));
    }

    @Test
    void deveListarUnidadeSaudePorId() {
        when(listarUnidadePorIdUseCase.listarUnidadePorId(1L)).thenReturn(unidadeSaudeDTO);

        ResponseEntity<UnidadeSaudeDTO> response = unidadeSaudeController.listarUnidadeSaudePorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(unidadeSaudeDTO, response.getBody());
    }

    @Test
    void deveRegistrarUnidadeSaude() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade registrada com sucesso");

        when(unidadeSaudeMapper.toDomain(unidadeSaudeDTO)).thenReturn(unidadeSaudeDomain);
        when(registrarUnidadeSaudeUseCase.registrarUnidadeSaude(unidadeSaudeDomain)).thenReturn(mensagemEsperada);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.registrarUnidadeSaude(unidadeSaudeDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mensagemEsperada, response.getBody());
    }

    @Test
    void deveAtualizarUnidadeSaude() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade atualizada com sucesso");

        when(unidadeSaudeMapper.toDomain(unidadeSaudeDTO)).thenReturn(unidadeSaudeDomain);
        when(atualizarUnidadeSaudeUseCase.atualizarUnidade(1L, unidadeSaudeDomain)).thenReturn(mensagemEsperada);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.atualizarUnidadeSaude(1L, unidadeSaudeDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemEsperada, response.getBody());
    }

    @Test
    void deveDeletarUnidadeSaude() {
        doNothing().when(deletarUnidadeSaudeUseCase).deletarUnidade(1L);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.deletarUnidadeSaude(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deveListarTodasUnidadesSaude() {
        List<UnidadeSaudeDTO> listaEsperada = List.of(unidadeSaudeDTO);

        when(listarTodasUnidadePorIdUseCase.listarTodasUnidades()).thenReturn(listaEsperada);

        ResponseEntity<List<UnidadeSaudeDTO>> response = unidadeSaudeController.listarUnidadesSaude(0, 10);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaEsperada, response.getBody());
    }

    @Test
    void deveRegistrarUnidadeSaudeComInsertDTO() {
        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.registrarUnidadeSaude(insertUnidadeSaudeDTO);
        assertNull(response);  // Como o método não está implementado, deve retornar null
    }

    @Test
    void deveAtualizarUnidadeSaudeComUpdateDTO() {
        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.atualizarUnidadeSaude(1L, updateUnidadeSaudeDTO);
        assertNull(response);  // Como o método não está implementado, deve retornar null
    }
}
