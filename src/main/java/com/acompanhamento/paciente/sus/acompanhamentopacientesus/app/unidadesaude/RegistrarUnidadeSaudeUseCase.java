package com.acompanhamento.paciente.sus.acompanhamentopacientesus.app.unidadesaude;

import com.acompanhamento.paciente.sus.acompanhamentopacientesus.domain.entity.UnidadeSaudeDomain;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.InsertMessageDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.dto.response.UnidadeSaudeDTO;
import com.acompanhamento.paciente.sus.acompanhamentopacientesus.infrastructure.gateway.IUnidadeSaudeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrarUnidadeSaudeUseCase {
    private final IUnidadeSaudeGateway unidadeSaudeGateway;

    public InsertMessageDTO registrarUnidadeSaude(UnidadeSaudeDomain domain) {

        // Aqui, você pode adicionar alguma lógica adicional antes de salvar
        // como, por exemplo, a data de cadastro ou outras verificações.

        // Supondo que você precise definir a data de cadastro da unidade,
        // você pode adicionar um campo de data no seu domínio para isso.
        // Exemplo:
        // domain.setDataCadastro(LocalDateTime.now());

        return unidadeSaudeGateway.registrarUnidade(domain);
    }
}
