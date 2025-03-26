package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdControleUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.ListarProntuarioPorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.RegistrarProntuarioPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ControleHistoricoDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping ("/prontuario")
@RequiredArgsConstructor
public class ProntuarioController {
    private final ListarProntuarioPorIdUseCase listarProntuarioPorIdUseCase;
    private final RegistrarProntuarioPacienteUseCase registrarProntuarioPacienteUseCase;
    private final ListarHistoricoPacientePorIdControleUseCase listarHistoricoPacientePorIdControleUseCase;
    private final IProntuarioMapper prontuarioMapper;

    @GetMapping("/{idControle}")
    public ResponseEntity<List<ProntuarioDTO>> listarProntuarioPacientePorIdControle(@PathVariable long idControle,
                                                                               @RequestParam(required = false) String especialidade,
                                                                               @RequestParam(required = false) LocalDateTime data,
                                                                               @RequestParam(required = false) String solicitacao,
                                                                               @RequestParam(required = false) StatusSolicitacaoProntuario statusSolicitacaoProntuario){
        return ResponseEntity.ok().body(listarProntuarioPorIdUseCase.listarProntuarioPacientePorIdControle(idControle,especialidade,data,solicitacao,statusSolicitacaoProntuario));
    }

    @PostMapping("/{idControle}")
    public ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(
            @PathVariable Long idControle,
            @Valid @RequestBody InsertProntuarioDTO dto) {
        //Garante que o ID existe.
        ControleHistoricoDTO controleHistoricoDTO = listarHistoricoPacientePorIdControleUseCase.listarPacientePorIdControle(idControle);
        ProntuarioPacienteDomain domain = prontuarioMapper.toDomain(dto);
        InsertMessageDTO mensagem = registrarProntuarioPacienteUseCase
                .registrarProntuarioPaciente(controleHistoricoDTO.idHistoricoPaciente(),domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
}
