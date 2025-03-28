package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.AtualizarStatusHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdControleUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.RegistrarHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.UpdateControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusHistoricoPaciente;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ("/controlehistorico")
@RequiredArgsConstructor
public class ControleHistoricoController{
    private final ListarHistoricoPacientePorIdUseCase listarHistoricoPacientePorIdUseCase;
    private final ListarHistoricoPacientePorIdControleUseCase listarHistoricoPacientePorIdControleUseCase;
    private final RegistrarHistoricoPacienteUseCase registrarHistoricoPacienteUseCase;
    private final AtualizarStatusHistoricoPacienteUseCase atualizarStatusHistoricoPacienteUseCase;
    private final IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;

    @GetMapping("/{idControle}")
    public ResponseEntity<ControleHistoricoDTO> listarHistoricoPacientePorIdControle(@PathVariable Long idControle){
        return ResponseEntity.ok()
                .body(listarHistoricoPacientePorIdControleUseCase.listarPacientePorIdControle(idControle));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<ControleHistoricoDTO>> listarHistoricoPacientePorId(@PathVariable Long idPaciente,
                                                                                   @RequestParam(required = false) Long idUnidade,
                                                                                   @RequestParam(required = false) LocalDateTime dataCadastro,
                                                                                   @RequestParam(required = false) StatusHistoricoPaciente statusHistoricoPaciente
                                                                             ){
        return ResponseEntity.ok().body(listarHistoricoPacientePorIdUseCase.listarPacientePorId(idPaciente,idUnidade,dataCadastro,statusHistoricoPaciente));
    }

    @PostMapping()
    public ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(
            @Valid @RequestBody InsertControleHistoricoDTO dto) {
        ControleHistoricoPacienteDomain domain = controleHistoricoPacienteMapper.toDomain(dto);
        InsertMessageDTO mensagem = registrarHistoricoPacienteUseCase.registrarHistoricoPaciente(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
    @PatchMapping("/{idControle}")
    public ResponseEntity<ControleHistoricoDTO> atualizarStatusHistoricoPaciente(
            @PathVariable long idControle,
            @Valid @RequestBody UpdateControleHistoricoDTO dto) {
        StatusHistoricoPaciente novoStatus;
        try{
            novoStatus = StatusHistoricoPaciente.valueOf(dto.novoStatus());
        }
        catch (IllegalArgumentException erro){
            throw new IllegalArgumentException("Valor inválido para um novoStatus, valores possíveis: " + Arrays.toString(StatusHistoricoPaciente.values()));
        }
        ControleHistoricoDTO mensagem = atualizarStatusHistoricoPacienteUseCase.atualizarStatusHistoricoPaciente(idControle,novoStatus);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }
}
