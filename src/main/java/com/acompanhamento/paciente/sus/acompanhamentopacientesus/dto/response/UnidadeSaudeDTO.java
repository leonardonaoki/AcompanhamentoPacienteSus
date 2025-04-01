package com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response;

import java.time.LocalTime;

public record UnidadeSaudeDTO(
        long id,
        String nomeUnidade,
        String endereco,
        String tipoUnidade, // Ex: Hospital, Posto de Saúde, Clínica
        String telefone,
        LocalTime horaAbertura,
        LocalTime horaFechamento
) {}
