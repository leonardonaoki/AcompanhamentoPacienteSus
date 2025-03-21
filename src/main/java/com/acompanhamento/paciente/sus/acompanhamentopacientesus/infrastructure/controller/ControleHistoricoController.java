package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.AtualizarStatusHistoricoPacienteUseCase;
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

import java.util.Arrays;

@RestController
@RequestMapping ("/controlehistorico")
@RequiredArgsConstructor
public class ControleHistoricoController{
    private final ListarHistoricoPacientePorIdUseCase listarHistoricoPacientePorIdUseCase;
    private final RegistrarHistoricoPacienteUseCase registrarHistoricoPacienteUseCase;
    private final AtualizarStatusHistoricoPacienteUseCase atualizarStatusHistoricoPacienteUseCase;
    private final IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;

    @GetMapping("/{idPaciente}")
    public ResponseEntity<ControleHistoricoDTO> listarHistoricoPacientePorId(@PathVariable long idPaciente){
        return ResponseEntity.ok().body(listarHistoricoPacientePorIdUseCase.listarPacientePorId(idPaciente));
    }

    @PostMapping()
    public ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(
            @Valid @RequestBody InsertControleHistoricoDTO dto) {
        ControleHistoricoPacienteDomain domain = controleHistoricoPacienteMapper.toDomain(dto);
        InsertMessageDTO mensagem = registrarHistoricoPacienteUseCase.registrarHistoricoPaciente(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
    @PatchMapping("/{idPaciente}")
    public ResponseEntity<ControleHistoricoDTO> atualizarStatusHistoricoPaciente(
            @PathVariable long idPaciente,
            @Valid @RequestBody UpdateControleHistoricoDTO dto) {
        StatusHistoricoPaciente novoStatus;
        try{
            novoStatus = StatusHistoricoPaciente.valueOf(dto.novoStatus());
        }
        catch (IllegalArgumentException erro){
            throw new IllegalArgumentException("Valor inválido para um novoStatus, valores possíveis: " + Arrays.toString(StatusHistoricoPaciente.values()));
        }
        ControleHistoricoDTO mensagem = atualizarStatusHistoricoPacienteUseCase.atualizarStatusHistoricoPaciente(idPaciente,novoStatus);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);

    }

}
