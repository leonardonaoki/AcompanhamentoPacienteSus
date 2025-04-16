package com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MensagensNotificacaoUsuarioTest {

    @Test
    @DisplayName("Teste da mensagem com parâmetro único")
    void testMensagemComUmParametro() {
        String esperado = "A data para realização do/a Exame de sangue venceu, agende outra consulta para renovar o seu pedido";
        String resultado = MensagensNotificacaoUsuario.VENCIMENTO_SOLICITACAO_EXAMES.getMensagem("Exame de sangue");

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste da mensagem com dois parâmetros")
    void testMensagemComDoisParametros() {
        String esperado = "A data para realização do/a Exame de imagem vence em 5 dia(s),realize o procendimento antes do vencimento ou sera necesssario outra consulta";
        String resultado = MensagensNotificacaoUsuario.ANTES_VENCIMENTO_SOLICITACAO_EXAMES.getMensagem("Exame de imagem", 5);

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste da mensagem sem parâmetros")
    void testMensagemSemParametros() {
        String esperado = "Já faz mais de 6 meses da sua ultima consulta agende outra consulta para manter a sua saude em dia!";
        String resultado = MensagensNotificacaoUsuario.RETORNO_6MESES.getMensagem();

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste da mensagem com placeholder mas sem argumento (esperado erro)")
    void testMensagemFaltandoParametros() {
        String esperado = "A data para realização do/a {0} venceu, agende outra consulta para renovar o seu pedido";
        String resultado = MensagensNotificacaoUsuario.VENCIMENTO_SOLICITACAO_EXAMES.getMensagem();

        // Neste caso, o MessageFormat simplesmente insere o placeholder "{0}"
        assertEquals(esperado, resultado);
    }
}

