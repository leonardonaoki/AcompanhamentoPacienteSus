package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.repository.IPacienteRepository;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper.IPacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PacienteGateway implements IPacienteGateway {
    private static final String ERRO_ID_NAO_ENCONTRADO = "Não foi possível identificar o paciente com o ID ";
    private final IPacienteRepository pacienteRepository;
    private final IPacienteMapper pacienteMapper;

    @Override
    public PacienteDTO registrarPaciente(PacienteDomain domain) {
        PacienteEntity entitySalvo = pacienteRepository.save(pacienteMapper.toEntity(domain));
        return pacienteMapper.toDTO(entitySalvo);
    }

    @Override
    public PacienteDTO atualizarPaciente(long id, PacienteDomain domain) {
        PacienteEntity pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));

        pacienteExistente.setNome(domain.getNome());
        pacienteExistente.setCpf(domain.getCpf());
        pacienteExistente.setEndereco(domain.getEndereco());
        pacienteExistente.setDataNascimento(domain.getDataNascimento());
        pacienteExistente.setDataAtualizacao(domain.getDataAtualizacao());

        PacienteEntity entityAtualizado = pacienteRepository.save(pacienteExistente);
        return pacienteMapper.toDTO(entityAtualizado);
    }

    @Override
    public PacienteDTO listarPacientePorId(long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));
    }

    @Override
    public List<PacienteDTO> listarTodosPacientes() {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toDTOList(pacientes);
    }

    @Override
    public void deletarPaciente(long id) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERRO_ID_NAO_ENCONTRADO + id));

        pacienteRepository.delete(paciente);
    }
}
