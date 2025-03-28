package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Prontuario", description = "Prontuario do paciente")
@RequestMapping("/prontuario")
public interface IProntuarioDocumentation {
    @Operation(
            operationId = "listarProntuarioPacientePorIdControle",
            summary = "Lista o prontuário do paciente de forma paginada por ID.",
            description = "Esse endpoint lista o prontuário do paciente de forma paginada por ID.",
            tags = "Prontuario",
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
    @GetMapping("/{idControle}")
    ResponseEntity<List<ProntuarioDTO>> listarProntuarioPacientePorIdControle(long idControle,String especialidade,LocalDateTime data,String solicitacao,String status,int offset,int limit);
    @Operation(
            operationId = "registrarHistoricoPaciente",
            summary = "Registra um registro do histórico do paciente.",
            description = "Esse endpoint registra um registro do histórico do paciente.Status permitidos: SOLICITADO,ENTREGUE,EXAME_REALIZADO",
            tags = "Prontuario",
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
    @PostMapping("/{idControle}")
    ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(Long idControle,InsertProntuarioDTO dto);
}
