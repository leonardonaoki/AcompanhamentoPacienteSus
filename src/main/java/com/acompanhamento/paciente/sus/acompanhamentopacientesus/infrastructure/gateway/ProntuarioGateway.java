package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.ProntuarioPacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.ProntuarioDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.ProntuarioPacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IProntuarioRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IProntuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProntuarioGateway implements IProntuarioGateway{
    private static final String ERRO_ID_NAO_ENCONTRADO = "Não foi possível identificar o Id de Controle com o ID ";
    private final IProntuarioRepository prontuarioRepository;
    private final IProntuarioMapper prontuarioMapper;

    @Override
    public ProntuarioDTO listarProntuarioPacientePorIdControle(long idControle){
        return prontuarioRepository.findById(idControle)
                .map(prontuarioMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + idControle));
    }
    @Override
    public InsertMessageDTO registrarProntuarioPaciente(ProntuarioPacienteDomain domain){
        ProntuarioPacienteEntity entitySalvo = prontuarioRepository.save(prontuarioMapper.toEntity(domain));
        return new InsertMessageDTO("ID gerado: " + entitySalvo.getId());
    }
}
