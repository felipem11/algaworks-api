package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Builder
    @Getter
    class Mensagem {

        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
    }
}
