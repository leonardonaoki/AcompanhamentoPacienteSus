package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude.*;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertUnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateUnidadeSaudeDTO;
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

@RestController
@RequiredArgsConstructor
public class UnidadeSaudeController implements IUnidadeSaudeDocumentation {

    private final ListarUnidadePorIdUseCase listarUnidadePorIdUseCase;
    private final ListarTodasUnidadesUseCase listarTodasUnidadePorIdUseCase;
    private final RegistrarUnidadeSaudeUseCase registrarUnidadeSaudeUseCase;
    private final AtualizarUnidadeSaudeUseCase atualizarUnidadeSaudeUseCase;
    private final DeletarUnidadeSaudeUseCase deletarUnidadeSaudeUseCase;
    private final IUnidadeSaudeMapper unidadeSaudeMapper;

    @Override
    @GetMapping("/unidade/{id}")
    public ResponseEntity<UnidadeSaudeDTO> listarUnidadeSaudePorId(@PathVariable long id) {
        UnidadeSaudeDTO unidadeSaudeDTO = listarUnidadePorIdUseCase.listarUnidadePorId(id);
        return ResponseEntity.ok().body(unidadeSaudeDTO);
    }

    @PostMapping("/unidade")
    @Override
    public ResponseEntity<InsertMessageDTO> registrarUnidadeSaude(@Valid @RequestBody UnidadeSaudeDTO dto) {
        UnidadeSaudeDomain domain = unidadeSaudeMapper.toDomain(dto);
        InsertMessageDTO mensagem = registrarUnidadeSaudeUseCase.registrarUnidadeSaude(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PutMapping("/unidade/{id}")
    @Override
    public ResponseEntity<InsertMessageDTO> atualizarUnidadeSaude(@PathVariable long id, @Valid @RequestBody UnidadeSaudeDTO dto) {
        UnidadeSaudeDomain domain = unidadeSaudeMapper.toDomain(dto);
        InsertMessageDTO mensagem = atualizarUnidadeSaudeUseCase.atualizarUnidade(id, domain);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }

    @Override
    @DeleteMapping("/unidade/{id}")
    public ResponseEntity<InsertMessageDTO> deletarUnidadeSaude(@PathVariable long id) {
        InsertMessageDTO mensagem = new InsertMessageDTO("Unidade de saúde deletada com sucesso");
        deletarUnidadeSaudeUseCase.deletarUnidade(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensagem);
    }

    @Override
    @GetMapping("/unidade")
    public ResponseEntity<List<UnidadeSaudeDTO>> listarUnidadesSaude(
            @RequestParam(value = "_offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "_limit", required = false, defaultValue = "10") int limit) {
        List<UnidadeSaudeDTO> unidadesSaude = listarTodasUnidadePorIdUseCase.listarTodasUnidades();
        return ResponseEntity.ok().body(unidadesSaude);
    }

    @Override
    public ResponseEntity<InsertMessageDTO> registrarUnidadeSaude(InsertUnidadeSaudeDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<InsertMessageDTO> atualizarUnidadeSaude(long id, UpdateUnidadeSaudeDTO dto) {
        return null;
    }
}
