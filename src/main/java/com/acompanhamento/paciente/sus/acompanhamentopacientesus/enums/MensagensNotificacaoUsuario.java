package com.acompanhamento.paciente.sus.acompanhamentopacientesus.enums;

import java.text.MessageFormat;

public enum MensagensNotificacaoUsuario {
    VENCIMENTO_SOLICITACAO_EXAMES("A data para realização do/a {0} venceu, agende outra consulta para renovar o seu pedido"),
    VENCIMENTO_HOJE_SOLICITACAO_EXAMES("A data para realização do/a {0} vence hoje,realize o procendimento antes do vencimento ou sera necesssario outra consulta"),
    ANTES_VENCIMENTO_SOLICITACAO_EXAMES("A data para realização do/a {0} vence em {1} dia(s),realize o procendimento antes do vencimento ou sera necesssario outra consulta"),
    RETORNO_6MESES("Já faz mais de 6 meses da sua ultima consulta agende outra consulta para manter a sua saude em dia!"),
    APOS_15DIAS_MEDICO("Já faz 15 dias da sua ultima consulta, certifique de estar seguindo as orientaçoes medicas e ao sinal de qualquer sintoma diferente se encaminha upa mais proxima ou marque uma nova consulta"),
    EXAME_EXPIRADO("A data do seu exame expirou, será necessario marcar uma nova consulta e realizar o exame novamente"),
    EXAME_VENCE_HOJE("A data do seu exame vence hoje,se voce não for ao medico hoje irá perder o exame e será necessario marcar outra consulta para fazer outro pedido"),
    EXAME_VENCE_EM_X_DIAS("A data do seu exame vence em {0} dia(s),retorne ao medico antes do vencimento ou sera necesssario outra consulta"),
    LEMBRETE_VALIDADE("Lembre - se, depois de 30 dias o seu retorno perde a validade e será necessario marcar outra consulta. Se voce está recebendo essa mensagem, o seu retorno ainda não venceu");
    private final String mensagem;

    MensagensNotificacaoUsuario(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(Object ...args){
        return MessageFormat.format(mensagem,args);
    }
}