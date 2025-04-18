package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.paciente.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertPacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdatePacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation.IPacienteDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por expor a API REST para operações de gerenciamento de pacientes.
 * <p>
 * Essa classe lida com as requisições HTTP para listar, registrar, atualizar e deletar pacientes,
 * fornecendo os respectivos endpoints para interagir com o sistema.
 * </p>
 */
@RestController
@RequiredArgsConstructor
public class PacienteController implements IPacienteDocumentation {

    private final ListarTodosPacientesUseCase listarTodosPacientesUseCase;
    private final ListarPacientePorIdUseCase listarPacientePorIdUseCase;
    private final RegistrarPacienteUseCase registrarPacienteUseCase;
    private final AtualizarPacienteUseCase atualizarPacienteUseCase;
    private final DeletarPacienteUseCase deletarPacienteUseCase;
    /**
     * Endpoint para listar todos os pacientes registrados no sistema.
     * <p>
     * Esse método retorna todos os pacientes no formato de uma lista de {@link PacienteDTO}.
     * </p>
     *
     * @return Uma lista de objetos {@link PacienteDTO} com todos os pacientes registrados.
     */
    @Operation(summary = "Lista todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = listarTodosPacientesUseCase.listarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Endpoint para obter as informações detalhadas de um paciente baseado no ID.
     * <p>
     * Esse método retorna as informações do paciente especificado pelo ID.
     * </p>
     *
     * @param idPaciente O identificador único do paciente a ser encontrado.
     * @return Um objeto {@link PacienteDTO} com as informações do paciente ou um status 404 caso não seja encontrado.
     */
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

    /**
     * Endpoint para registrar um novo paciente no sistema.
     * <p>
     * Esse método cria um novo paciente no sistema com os dados fornecidos no corpo da requisição.
     * </p>
     *
     * @param dto O objeto {@link InsertPacienteDTO} contendo os dados do novo paciente.
     * @return O objeto {@link PacienteDTO} representando o paciente criado com sucesso.
     */
    @Operation(summary = "Registra um novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PacienteDTO> registrarPaciente(@Valid @RequestBody InsertPacienteDTO dto) {
        PacienteDomain domain = new PacienteDomain();
        domain.setNome(dto.nome());
        domain.setCpf(dto.cpf());
        domain.setEndereco(dto.endereco());
        domain.setTelefone(dto.telefoneCelular());
        domain.setDataNascimento(dto.dataNascimento());
        PacienteDTO pacienteCriado = registrarPacienteUseCase.registrarPaciente(domain);
        return ResponseEntity.status(201).body(pacienteCriado);
    }

    /**
     * Endpoint para atualizar as informações de um paciente existente.
     * <p>
     * Esse método permite atualizar os dados de um paciente específico, baseado no ID.
     * </p>
     *
     * @param idPaciente O identificador único do paciente a ser atualizado.
     * @param dto O objeto {@link UpdatePacienteDTO} contendo os dados atualizados do paciente.
     * @return O objeto {@link PacienteDTO} representando o paciente atualizado ou um status 404 caso o paciente não seja encontrado.
     */
    @Operation(summary = "Atualiza as informações de um paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable long idPaciente, @Valid @RequestBody UpdatePacienteDTO dto) {
        PacienteDomain domain = new PacienteDomain();
        domain.setNome(dto.getNome());
        domain.setCpf(dto.getCpf());
        domain.setEndereco(dto.getEndereco());
        domain.setTelefone(dto.getTelefoneCelular());
        domain.setDataNascimento(dto.getDataNascimento());
        PacienteDTO pacienteAtualizado = atualizarPacienteUseCase.atualizarPaciente(idPaciente, domain);
        return pacienteAtualizado != null ? ResponseEntity.ok(pacienteAtualizado) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para deletar um paciente do sistema.
     * <p>
     * Esse método exclui um paciente com base no ID fornecido na URL.
     * </p>
     *
     * @param idPaciente O identificador único do paciente a ser deletado.
     * @return Uma resposta vazia com status 200, indicando que o paciente foi deletado com sucesso.
     */
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
