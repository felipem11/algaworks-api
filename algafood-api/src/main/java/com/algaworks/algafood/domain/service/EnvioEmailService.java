package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
 * 15.4. Usando o serviço de envio de e-mails na confirmação de pedidos<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Builder
    @Getter
    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;
        private String corpo;
    }
}
