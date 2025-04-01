package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertPacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Paciente", description = "Gerenciamento de pacientes")
@RequestMapping("/paciente")
public interface IPacienteDocumentation {

    @Operation(
            operationId = "listarPacientePorId",
            summary = "Lista as informações de um paciente pelo ID.",
            description = "Esse endpoint lista as informações detalhadas de um paciente com base no ID fornecido.",
            tags = "Paciente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paciente não encontrado",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno do servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("/{idPaciente}")
    ResponseEntity<PacienteDTO> listarPacientePorId(long idPaciente);

    @Operation(
            operationId = "registrarPaciente",
            summary = "Registra um novo paciente.",
            description = "Esse endpoint permite o registro de um novo paciente no sistema.",
            tags = "Paciente",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Paciente criado com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PacienteDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ApiErrorResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno do servidor",
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
    ResponseEntity<PacienteDTO> registrarPaciente(InsertPacienteDTO dto);
}
