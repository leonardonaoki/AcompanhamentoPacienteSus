package com.acompanhamento.paciente.sus.acompanhamentopacientesus.mapper;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.PacienteDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.PacienteDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.entityjpa.PacienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PacienteMapperTest {

    private PacienteMapper pacienteMapper;
    private PacienteEntity pacienteEntity;
    private PacienteDomain pacienteDomain;
    private PacienteDTO pacienteDTO;

    @BeforeEach
    void setUp() {
        pacienteMapper = new PacienteMapper();

        pacienteEntity = new PacienteEntity();
        pacienteEntity.setIdPaciente(1L);
        pacienteEntity.setNome("João Silva");
        pacienteEntity.setCpf("12345678901");
        pacienteEntity.setEndereco("Rua Exemplo, 123");
        pacienteEntity.setDataNascimento(LocalDateTime.of(1990, 5, 20, 0, 0));
        pacienteEntity.setDataCadastro(LocalDateTime.now());
        pacienteEntity.setDataAtualizacao(LocalDateTime.now());

        pacienteDomain = new PacienteDomain();
        pacienteDomain.setIdPaciente(1L);
        pacienteDomain.setNome("João Silva");
        pacienteDomain.setCpf("12345678901");
        pacienteDomain.setEndereco("Rua Exemplo, 123");
        pacienteDomain.setDataNascimento(LocalDateTime.of(1990, 5, 20, 0, 0));
        pacienteDomain.setDataCadastro(LocalDateTime.now());
        pacienteDomain.setDataAtualizacao(LocalDateTime.now());

        pacienteDTO = new PacienteDTO(
                1L,
                "João Silva",
                "12345678901",
                "Rua Exemplo, 123",
                LocalDateTime.of(1990, 5, 20, 0, 0),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Test
    void deveConverterPacienteEntityParaDTO() {
        PacienteDTO resultado = pacienteMapper.toDTO(pacienteEntity);

        assertThat(resultado).isNotNull();
        assertThat(resultado.idPaciente()).isEqualTo(pacienteEntity.getIdPaciente());
        assertThat(resultado.nome()).isEqualTo(pacienteEntity.getNome());
        assertThat(resultado.cpf()).isEqualTo(pacienteEntity.getCpf());
        assertThat(resultado.endereco()).isEqualTo(pacienteEntity.getEndereco());
        assertThat(resultado.dataNascimento()).isEqualTo(pacienteEntity.getDataNascimento());
        assertThat(resultado.dataCadastro()).isEqualTo(pacienteEntity.getDataCadastro());
        assertThat(resultado.dataAtualizacao()).isEqualTo(pacienteEntity.getDataAtualizacao());
    }

    @Test
    void deveConverterPacienteDomainParaEntity() {
        PacienteEntity resultado = pacienteMapper.toEntity(pacienteDomain);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo(pacienteDomain.getNome());
        assertThat(resultado.getCpf()).isEqualTo(pacienteDomain.getCpf());
        assertThat(resultado.getEndereco()).isEqualTo(pacienteDomain.getEndereco());
        assertThat(resultado.getDataNascimento()).isEqualTo(pacienteDomain.getDataNascimento());
    }

    @Test
    void deveConverterPacienteDTOParaDomain() {
        PacienteDomain resultado = pacienteMapper.toDomain(pacienteDTO);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getIdPaciente()).isEqualTo(pacienteDTO.idPaciente());
        assertThat(resultado.getNome()).isEqualTo(pacienteDTO.nome());
        assertThat(resultado.getCpf()).isEqualTo(pacienteDTO.cpf());
        assertThat(resultado.getEndereco()).isEqualTo(pacienteDTO.endereco());
        assertThat(resultado.getDataNascimento()).isEqualTo(pacienteDTO.dataNascimento());
        assertThat(resultado.getDataCadastro()).isEqualTo(pacienteDTO.dataCadastro());
        assertThat(resultado.getDataAtualizacao()).isEqualTo(pacienteDTO.dataAtualizacao());
    }

    @Test
    void deveConverterListaDeEntitiesParaListaDeDTOs() {
        List<PacienteEntity> listaEntities = List.of(pacienteEntity);

        List<PacienteDTO> resultado = pacienteMapper.toDTOList(listaEntities);

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).idPaciente()).isEqualTo(pacienteEntity.getIdPaciente());
    }
}
