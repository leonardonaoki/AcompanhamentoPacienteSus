package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.ListarHistoricoPacientePorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.RegistrarHistoricoPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ControleHistoricoPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.InsertControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IControleHistoricoPacienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/controlehistorico")
@RequiredArgsConstructor
public class ControleHistoricoController{
    private final ListarHistoricoPacientePorIdUseCase listarHistoricoPacientePorIdUseCase;
    private final RegistrarHistoricoPacienteUseCase registrarHistoricoPacienteUseCase;
    private final IControleHistoricoPacienteMapper controleHistoricoPacienteMapper;

    @GetMapping("/{idPaciente}")
    public ResponseEntity<ControleHistoricoDTO> listarHistoricoPacientePorId(@PathVariable long idPaciente)
    {
        return ResponseEntity.ok().body(listarHistoricoPacientePorIdUseCase.listarPacientePorId(idPaciente));
    }

    @PostMapping()
    public ResponseEntity<ControleHistoricoDTO> registrarHistoricoPaciente(
            @Valid @RequestBody InsertControleHistoricoDTO dto) {
        ControleHistoricoPacienteDomain domain = controleHistoricoPacienteMapper.toDomain(dto);
        ControleHistoricoDTO clienteInserido = registrarHistoricoPacienteUseCase.registrarHistoricoPaciente(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteInserido);
    }

}
