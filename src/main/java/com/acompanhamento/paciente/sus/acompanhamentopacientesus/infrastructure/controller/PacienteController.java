package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertPacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdatePacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PacienteController {

    private final ListarTodosPacientesUseCase listarTodosPacientesUseCase;
    private final ListarPacientePorIdUseCase listarPacientePorIdUseCase;
    private final RegistrarPacienteUseCase registrarPacienteUseCase;
    private final AtualizarPacienteUseCase atualizarPacienteUseCase;
    private final DeletarPacienteUseCase deletarPacienteUseCase;

    @Operation(summary = "Lista todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = listarTodosPacientesUseCase.listarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Obtém as informações de um paciente", description = "Retorna as informações detalhadas de um paciente com base no ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteDTO> listarPacientePorId(@PathVariable long idPaciente) {
        PacienteDTO paciente = listarPacientePorIdUseCase.listarPacientePorId(idPaciente);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Registra um novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PacienteDTO> registrarPaciente(@Valid @RequestBody InsertPacienteDTO dto) {
        PacienteDomain domain = new PacienteDomain();
        PacienteDTO pacienteCriado = registrarPacienteUseCase.registrarPaciente(domain);
        return ResponseEntity.status(201).body(pacienteCriado);
    }

    @Operation(summary = "Atualiza as informações de um paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable long idPaciente, @Valid @RequestBody UpdatePacienteDTO dto) {
        PacienteDomain domain = new PacienteDomain();
        PacienteDTO pacienteAtualizado = atualizarPacienteUseCase.atualizarPaciente(idPaciente, domain);
        return pacienteAtualizado != null ? ResponseEntity.ok(pacienteAtualizado) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta um paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable long idPaciente) {
        deletarPacienteUseCase.deletarPaciente(idPaciente);
        return ResponseEntity.ok().build();
    }
}