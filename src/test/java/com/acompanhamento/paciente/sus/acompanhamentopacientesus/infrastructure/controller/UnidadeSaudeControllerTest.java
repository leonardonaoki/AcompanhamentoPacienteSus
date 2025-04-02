package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IUnidadeSaudeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @BeforeEach
    void setUp() {
        unidadeSaudeDTO = new UnidadeSaudeDTO(
                1L,
                "Posto de Saúde Central",
                "Rua Principal, 100",
                "Posto",
                "11987654321",
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );

        unidadeSaudeDomain = new UnidadeSaudeDomain(
                1L,
                "Posto de Saúde Central",
                "Rua Principal, 100",
                "Posto",
                "11987654321",
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );
    }

    @Test
    void deveListarUnidadeSaudePorId() {
        when(listarUnidadePorIdUseCase.listarUnidadePorId(1L)).thenReturn(unidadeSaudeDTO);

        ResponseEntity<UnidadeSaudeDTO> response = unidadeSaudeController.listarUnidadeSaudePorId(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(unidadeSaudeDTO, response.getBody());
    }

    @Test
    void deveRegistrarUnidadeSaude() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade de saúde registrada com sucesso");

        when(unidadeSaudeMapper.toDomain(unidadeSaudeDTO)).thenReturn(unidadeSaudeDomain);
        when(registrarUnidadeSaudeUseCase.registrarUnidadeSaude(unidadeSaudeDomain)).thenReturn(mensagemEsperada);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.registrarUnidadeSaude(unidadeSaudeDTO);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mensagemEsperada, response.getBody()); // ✅ Comparação correta do corpo da resposta
    }

    @Test
    void deveAtualizarUnidadeSaude() {
        InsertMessageDTO mensagemEsperada = new InsertMessageDTO("Unidade de saúde atualizada com sucesso");

        when(unidadeSaudeMapper.toDomain(unidadeSaudeDTO)).thenReturn(unidadeSaudeDomain);
        when(atualizarUnidadeSaudeUseCase.atualizarUnidade(1L, unidadeSaudeDomain)).thenReturn(mensagemEsperada);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.atualizarUnidadeSaude(1L, unidadeSaudeDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mensagemEsperada, response.getBody()); // ✅ Comparação correta do corpo da resposta
    }

    @Test
    void deveDeletarUnidadeSaude() {
        doNothing().when(deletarUnidadeSaudeUseCase).deletarUnidade(1L);

        ResponseEntity<InsertMessageDTO> response = unidadeSaudeController.deletarUnidadeSaude(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue()); // ✅ DELETE retorna apenas status, sem corpo
    }

    @Test
    void deveListarTodasUnidadesSaude() {
        List<UnidadeSaudeDTO> listaEsperada = List.of(unidadeSaudeDTO);

        when(listarTodasUnidadePorIdUseCase.listarTodasUnidades()).thenReturn(listaEsperada);

        ResponseEntity<List<UnidadeSaudeDTO>> response = unidadeSaudeController.listarUnidadesSaude(0, 10);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(listaEsperada, response.getBody());
    }
}
