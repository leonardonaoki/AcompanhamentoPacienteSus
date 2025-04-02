package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.UnidadeSaudeEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IUnidadeSaudeRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IUnidadeSaudeMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnidadeSaudeGatewayTest {

    @Mock
    private IUnidadeSaudeRepository unidadeSaudeRepository;

    @Mock
    private IUnidadeSaudeMapper unidadeSaudeMapper;

    @InjectMocks
    private UnidadeSaudeGateway unidadeSaudeGateway;

    private UnidadeSaudeEntity unidadeSaudeEntity;
    private UnidadeSaudeDomain unidadeSaudeDomain;
    private UnidadeSaudeDTO unidadeSaudeDTO;

    @BeforeEach
    void setUp() {
        unidadeSaudeEntity = new UnidadeSaudeEntity(
                "Hospital Central",
                "Rua Principal, 100",
                "Hospital",
                "11987654321",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );
        unidadeSaudeEntity.setId(1L);

        unidadeSaudeDomain = new UnidadeSaudeDomain(
                1L,
                "Hospital Central",
                "Rua Principal, 100",
                "Hospital",
                "11987654321",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );

        unidadeSaudeDTO = new UnidadeSaudeDTO(
                1L,
                "Hospital Central",
                "Rua Principal, 100",
                "Hospital",
                "11987654321",
                LocalTime.of(7, 0),
                LocalTime.of(19, 0)
        );
    }

    @Test
    void deveListarUnidadesDeSaudeComSucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        List<UnidadeSaudeEntity> unidades = List.of(unidadeSaudeEntity);
        Page<UnidadeSaudeEntity> page = new PageImpl<>(unidades, pageable, unidades.size());

        when(unidadeSaudeRepository.findAll(pageable)).thenReturn(page);
        when(unidadeSaudeMapper.toDTO(unidadeSaudeEntity)).thenReturn(unidadeSaudeDTO);

        List<UnidadeSaudeDTO> resultado = unidadeSaudeGateway.listarUnidadesSaude(0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Hospital Central", resultado.get(0).nomeUnidade());
    }

    @Test
    void deveLancarExcecaoAoListarUnidadesQuandoNaoExistem() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UnidadeSaudeEntity> pageVazia = Page.empty(pageable);

        when(unidadeSaudeRepository.findAll(pageable)).thenReturn(pageVazia);

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> unidadeSaudeGateway.listarUnidadesSaude(0, 10)
        );

        assertEquals("Não foi possível encontrar unidades de saúde.", exception.getMessage());
    }

    @Test
    void deveBuscarUnidadePorIdComSucesso() {
        when(unidadeSaudeRepository.findById(1L)).thenReturn(Optional.of(unidadeSaudeEntity));
        when(unidadeSaudeMapper.toDTO(unidadeSaudeEntity)).thenReturn(unidadeSaudeDTO);

        UnidadeSaudeDTO resultado = unidadeSaudeGateway.buscarUnidadePorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Hospital Central", resultado.nomeUnidade());
    }

    @Test
    void deveLancarExcecaoAoBuscarUnidadePorIdInexistente() {
        when(unidadeSaudeRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> unidadeSaudeGateway.buscarUnidadePorId(99L)
        );

        assertEquals("Unidade de saúde não encontrada com o ID: 99", exception.getMessage());
    }

    @Test
    void deveRegistrarUnidadeComSucesso() {
        when(unidadeSaudeMapper.toEntity(unidadeSaudeDomain)).thenReturn(unidadeSaudeEntity);
        when(unidadeSaudeRepository.save(unidadeSaudeEntity)).thenReturn(unidadeSaudeEntity);

        InsertMessageDTO resultado = unidadeSaudeGateway.registrarUnidade(unidadeSaudeDomain);

        assertEquals("Unidade de saúde registrada com ID: 1", resultado);
    }

    @Test
    void deveAtualizarUnidadeComSucesso() {
        when(unidadeSaudeRepository.findById(1L)).thenReturn(Optional.of(unidadeSaudeEntity));
        doNothing().when(unidadeSaudeMapper).updateEntityFromDomain(unidadeSaudeDomain, unidadeSaudeEntity);
        when(unidadeSaudeRepository.save(unidadeSaudeEntity)).thenReturn(unidadeSaudeEntity);

        InsertMessageDTO resultado = unidadeSaudeGateway.atualizarUnidade(1L, unidadeSaudeDomain);

        assertEquals("Unidade de saúde com ID: 1 foi atualizada.", resultado);
    }

    @Test
    void deveLancarExcecaoAoAtualizarUnidadeInexistente() {
        when(unidadeSaudeRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> unidadeSaudeGateway.atualizarUnidade(99L, unidadeSaudeDomain)
        );

        assertEquals("Unidade de saúde não encontrada com o ID: 99", exception.getMessage());
    }

    @Test
    void deveDeletarUnidadeComSucesso() {
        when(unidadeSaudeRepository.findById(1L)).thenReturn(Optional.of(unidadeSaudeEntity));
        doNothing().when(unidadeSaudeRepository).delete(unidadeSaudeEntity);

        InsertMessageDTO resultado = unidadeSaudeGateway.deletarUnidade(1L);

        assertEquals("Unidade de saúde com ID: 1 foi deletada.", resultado);
    }

    @Test
    void deveLancarExcecaoAoDeletarUnidadeInexistente() {
        when(unidadeSaudeRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> unidadeSaudeGateway.deletarUnidade(99L)
        );

        assertEquals("Unidade de saúde não encontrada com o ID: 99", exception.getMessage());
    }
}
