package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "ControleHistorico", description = "Controle de histórico")
@RequestMapping("/controlehistorico")
public interface IControleHistoricoDocumentation {
    @Operation(
            operationId = "listarHistoricoPacientePorIdControle",
            summary = "Lista o controle de histórico por ID",
            description = "Esse endpoint lista o controle de histórico por ID",
            tags = "ControleHistorico",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ControleHistoricoDTO.class)
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
    @GetMapping("/{idControle}")
    ResponseEntity<ControleHistoricoDTO> listarHistoricoPacientePorIdControle(Long idControle);

    @Operation(
            operationId = "listarHistoricoPacientePorId",
            summary = "Lista o histórico do paciente de forma paginada por ID.",
            description = "Esse endpoint lista o histórico do paciente de forma paginada por ID.",
            tags = "ControleHistorico",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ControleHistoricoDTO.class))
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
    @GetMapping("/paciente/{idPaciente}")
    ResponseEntity<List<ControleHistoricoDTO>> listarHistoricoPacientePorId(Long idPaciente,Long idUnidade,
                                                                            LocalDateTime dataCadastro,
                                                                            StatusHistoricoPaciente statusHistoricoPaciente,
                                                                            int offset,
                                                                            int limit);
    @Operation(
            operationId = "registrarHistoricoPaciente",
            summary = "Registra um registro do histórico do paciente.",
            description = "Esse endpoint registra um registro do histórico do paciente.",
            tags = "ControleHistorico",
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
    @PostMapping()
    ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(InsertControleHistoricoDTO dto);
    @Operation(
            operationId = "atualizarStatusHistoricoPaciente",
            summary = "Atualiza o status do id de controle para um dos tipos existentes",
            description = "Esse endpoint atualiza o status do id de controle para um dos tipos existentes. Status Permitidos: PRIMEIRA_CONSULTA,EM_CURSO,RETORNO,ENCERRADO",
            tags = "ControleHistorico",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ControleHistoricoDTO.class)
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
    @PatchMapping("/{idControle}")
    ResponseEntity<ControleHistoricoDTO> atualizarStatusHistoricoPaciente(long idControle,UpdateControleHistoricoDTO dto);
}
