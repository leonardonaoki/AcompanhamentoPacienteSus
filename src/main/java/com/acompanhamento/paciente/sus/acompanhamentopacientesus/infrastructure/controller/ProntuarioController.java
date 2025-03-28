package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.controlehistoricopaciente.ListarHistoricoPacientePorIdControleUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.ListarProntuarioPorIdUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.prontuariopaciente.RegistrarProntuarioPacienteUseCase;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.request.InsertProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums.StatusSolicitacaoProntuario;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.controller.documentation.IProntuarioDocumentation;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProntuarioController implements IProntuarioDocumentation {
    private final ListarProntuarioPorIdUseCase listarProntuarioPorIdUseCase;
    private final RegistrarProntuarioPacienteUseCase registrarProntuarioPacienteUseCase;
    private final ListarHistoricoPacientePorIdControleUseCase listarHistoricoPacientePorIdControleUseCase;
    private final IProntuarioMapper prontuarioMapper;


    @Override
    public ResponseEntity<List<ProntuarioDTO>> listarProntuarioPacientePorIdControle(@PathVariable long idControle,
                                                                               @RequestParam(required = false) String especialidade,
                                                                               @RequestParam(required = false) LocalDateTime data,
                                                                               @RequestParam(required = false) String solicitacao,
                                                                             @RequestParam(required = false) String status,
                                                                             @RequestParam(value = "_offset", required = false, defaultValue = "0") int offset,
                                                                             @RequestParam(value = "_limit", required = false, defaultValue = "10") int limit){
        StatusSolicitacaoProntuario statusFiltro = null;
        try{
            if(status != null && !status.isEmpty() && !status.isBlank())
                statusFiltro = StatusSolicitacaoProntuario.valueOf(status);
        }
        catch (IllegalArgumentException erro){
            throw new IllegalArgumentException("Valor inválido para Status, valores possíveis: " + Arrays.toString(StatusSolicitacaoProntuario.values()));
        }
        return ResponseEntity.ok().body(listarProntuarioPorIdUseCase.listarProntuarioPacientePorIdControle(idControle,especialidade,data,solicitacao,statusFiltro,offset,limit));
    }


    @Override
    public ResponseEntity<InsertMessageDTO> registrarHistoricoPaciente(
            @PathVariable Long idControle,
            @Valid @RequestBody InsertProntuarioDTO dto) {
        try{
            StatusSolicitacaoProntuario.valueOf(dto.statusSolicitacaoProntuario());
        }
        catch (IllegalArgumentException erro){
            throw new IllegalArgumentException("Valor inválido para statusSolicitacaoProntuario, valores possíveis: " + Arrays.toString(StatusSolicitacaoProntuario.values()));
        }
        //Garante que o ID existe.
        listarHistoricoPacientePorIdControleUseCase.listarPacientePorIdControle(idControle);
        ProntuarioPacienteDomain domain = prontuarioMapper.toDomain(idControle,dto);
        InsertMessageDTO mensagem = registrarProntuarioPacienteUseCase
                .registrarProntuarioPaciente(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }
}
