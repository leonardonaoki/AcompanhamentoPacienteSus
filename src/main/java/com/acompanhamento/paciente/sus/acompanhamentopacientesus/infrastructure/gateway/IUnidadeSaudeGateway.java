package com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;

import java.util.List;

public interface IUnidadeSaudeGateway {

    // Listar todas as unidades de saúde com possibilidade de paginação
    List<UnidadeSaudeDTO> listarUnidadesSaude(int offset, int limit);

    // Buscar uma unidade de saúde por ID
    UnidadeSaudeDTO buscarUnidadePorId(long id);

    // Registrar uma nova unidade de saúde
    InsertMessageDTO registrarUnidade(UnidadeSaudeDomain unidadeSaudeDomain);

    // Atualizar os dados de uma unidade de saúde
    InsertMessageDTO atualizarUnidade(long id, UnidadeSaudeDomain unidadeSaudeDomain);

    // Deletar uma unidade de saúde por ID
    InsertMessageDTO deletarUnidade(long id);
}
