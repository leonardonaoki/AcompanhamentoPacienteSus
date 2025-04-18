package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUpdateUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Unidade de Saúde", description = "Operações relacionadas às unidades de saúde")
@RequestMapping("/unidadesaude")
public interface IUnidadeSaudeDocumentation {

    /**
     * Lista uma unidade de saúde por ID.
     * <p>
     * Esse endpoint lista uma unidade de saúde por ID.
     * </p>
     *
     * @param id Identificador único da unidade de saúde.
     * @return ResponseEntity contendo os dados da unidade de saúde.
     */
    @Operation(
            operationId = "listarUnidadeSaudePorId",
            summary = "Lista uma unidade de saúde por ID.",
            description = "Esse endpoint lista uma unidade de saúde por ID.",
            tags = "Unidade de Saúde",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UnidadeSaudeDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UnidadeSaudeDTO> listarUnidadeSaudePorId(@PathVariable long id);

    /**
     * Lista todas as unidades de saúde de forma paginada.
     * <p>
     * Esse endpoint lista todas as unidades de saúde de forma paginada.
     * </p>
     *
     * @param offset Posição inicial da listagem.
     * @param limit Número máximo de registros a serem retornados.
     * @return ResponseEntity contendo a lista de unidades de saúde.
     */
    @Operation(
            operationId = "listarUnidadesSaude",
            summary = "Lista todas as unidades de saúde de forma paginada.",
            description = "Esse endpoint lista todas as unidades de saúde de forma paginada.",
            tags = "Unidade de Saúde",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UnidadeSaudeDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<UnidadeSaudeDTO>> listarUnidadesSaude(
            @RequestParam(value = "_offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "_limit", required = false, defaultValue = "10") int limit
    );

    /**
     * Registra uma nova unidade de saúde.
     * <p>
     * Esse endpoint registra uma nova unidade de saúde.
     * </p>
     *
     * @param dto Dados da unidade de saúde a ser registrada.
     * @return ResponseEntity contendo a mensagem de sucesso.
     */
    @Operation(
            operationId = "registrarUnidadeSaude",
            summary = "Registra uma nova unidade de saúde.",
            description = "Esse endpoint registra uma nova unidade de saúde.",
            tags = "Unidade de Saúde",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = InsertMessageDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @PostMapping
    ResponseEntity<InsertMessageDTO> registrarUnidadeSaude(@Valid @RequestBody InsertUpdateUnidadeSaudeDTO dto);

    /**
     * Atualiza os dados de uma unidade de saúde.
     * <p>
     * Esse endpoint atualiza os dados de uma unidade de saúde existente.
     * </p>
     *
     * @param id Identificador único da unidade de saúde.
     * @param dto Dados atualizados da unidade de saúde.
     * @return ResponseEntity contendo a mensagem de sucesso.
     */
    @Operation(
            operationId = "atualizarUnidadeSaude",
            summary = "Atualiza os dados de uma unidade de saúde.",
            description = "Esse endpoint atualiza os dados de uma unidade de saúde existente.",
            tags = "Unidade de Saúde",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = InsertMessageDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<InsertMessageDTO> atualizarUnidadeSaude(@PathVariable long id, @Valid @RequestBody InsertUpdateUnidadeSaudeDTO dto);


    /**
     * Deleta uma unidade de saúde.
     * <p>
     * Esse endpoint deleta uma unidade de saúde pelo ID.
     * </p>
     *
     * @param id Identificador único da unidade de saúde.
     * @return ResponseEntity contendo a mensagem de sucesso.
     */
    @Operation(
            operationId = "deletarUnidadeSaude",
            summary = "Deleta uma unidade de saúde.",
            description = "Esse endpoint deleta uma unidade de saúde pelo ID.",
            tags = "Unidade de Saúde",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(mediaType = "application/json")
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<InsertMessageDTO> deletarUnidadeSaude(@PathVariable long id);
}