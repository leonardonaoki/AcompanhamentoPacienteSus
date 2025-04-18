package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUpdateUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation.IUnidadeSaudeDocumentation;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IUnidadeSaudeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por expor a API REST para operações de gerenciamento de unidades de saúde.
 * <p>
 * Essa classe lida com as requisições HTTP para listar, registrar, atualizar e deletar unidades de saúde,
 * fornecendo os respectivos endpoints para interagir com o sistema.
 * </p>
 */
@RestController
@RequiredArgsConstructor
public class UnidadeSaudeController implements IUnidadeSaudeDocumentation {

    private final ListarUnidadePorIdUseCase listarUnidadePorIdUseCase;
    private final ListarTodasUnidadesUseCase listarTodasUnidadePorIdUseCase;
    private final RegistrarUnidadeSaudeUseCase registrarUnidadeSaudeUseCase;
    private final AtualizarUnidadeSaudeUseCase atualizarUnidadeSaudeUseCase;
    private final DeletarUnidadeSaudeUseCase deletarUnidadeSaudeUseCase;
    private final IUnidadeSaudeMapper unidadeSaudeMapper;

    /**
     * Endpoint para listar uma unidade de saúde específica com base no ID.
     * <p>
     * Esse método retorna as informações da unidade de saúde especificada pelo ID.
     * </p>
     *
     * @param id O identificador único da unidade de saúde.
     * @return O objeto {@link UnidadeSaudeDTO} contendo as informações da unidade de saúde.
     */
    @Override
    public ResponseEntity<UnidadeSaudeDTO> listarUnidadeSaudePorId(@PathVariable long id) {
        UnidadeSaudeDTO unidadeSaudeDTO = listarUnidadePorIdUseCase.listarUnidadePorId(id);
        return ResponseEntity.ok().body(unidadeSaudeDTO);
    }

    /**
     * Endpoint para registrar uma nova unidade de saúde.
     * <p>
     * Esse método cria uma nova unidade de saúde no sistema a partir dos dados fornecidos no corpo da requisição.
     * </p>
     *
     * @param dto O objeto {@link UnidadeSaudeDTO} contendo as informações da nova unidade de saúde.
     * @return Uma resposta {@link ResponseEntity} com um código HTTP 201 e uma mensagem indicando que a unidade foi criada.
     */
    @Override
    public ResponseEntity<InsertMessageDTO> registrarUnidadeSaude(@Valid @RequestBody InsertUpdateUnidadeSaudeDTO dto) {
        UnidadeSaudeDomain domain = unidadeSaudeMapper.toDomain(dto);
        InsertMessageDTO mensagem = registrarUnidadeSaudeUseCase.registrarUnidadeSaude(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    /**
     * Endpoint para atualizar as informações de uma unidade de saúde existente.
     * <p>
     * Esse método permite atualizar as informações de uma unidade de saúde com base no ID e nas novas informações fornecidas.
     * </p>
     *
     * @param id O identificador único da unidade de saúde a ser atualizada.
     * @param dto O objeto {@link UnidadeSaudeDTO} contendo os novos dados da unidade de saúde.
     * @return Uma resposta {@link ResponseEntity} com um código HTTP 200 e uma mensagem indicando que a unidade foi atualizada.
     */
    @Override
    public ResponseEntity<InsertMessageDTO> atualizarUnidadeSaude(@PathVariable long id, @Valid @RequestBody InsertUpdateUnidadeSaudeDTO dto) {
        UnidadeSaudeDomain domain = unidadeSaudeMapper.toDomain(dto);
        InsertMessageDTO mensagem = atualizarUnidadeSaudeUseCase.atualizarUnidade(id, domain);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }

    /**
     * Endpoint para deletar uma unidade de saúde do sistema.
     * <p>
     * Esse método exclui a unidade de saúde especificada pelo ID.
     * </p>
     *
     * @param id O identificador único da unidade de saúde a ser deletada.
     * @return Uma resposta vazia com status HTTP 204, indicando que a unidade foi deletada com sucesso.
     */
    @Override
    public ResponseEntity<InsertMessageDTO> deletarUnidadeSaude(@PathVariable long id) {
        InsertMessageDTO mensagem = new InsertMessageDTO("Unidade de saúde deletada com sucesso");
        deletarUnidadeSaudeUseCase.deletarUnidade(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensagem);
    }

    /**
     * Endpoint para listar todas as unidades de saúde com paginação.
     * <p>
     * Esse método retorna uma lista de unidades de saúde, com suporte à paginação através dos parâmetros `_offset` e `_limit`.
     * </p>
     *
     * @param offset O índice inicial para a paginação. O valor padrão é 0.
     * @param limit O número máximo de unidades de saúde a serem retornadas. O valor padrão é 10.
     * @return Uma lista de objetos {@link UnidadeSaudeDTO} contendo as unidades de saúde.
     */
    @Override
    public ResponseEntity<List<UnidadeSaudeDTO>> listarUnidadesSaude(
            @RequestParam(value = "_offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "_limit", required = false, defaultValue = "10") int limit) {
        List<UnidadeSaudeDTO> unidadesSaude = listarTodasUnidadePorIdUseCase.listarTodasUnidades();
        return ResponseEntity.ok().body(unidadesSaude);
    }
}
