package com.algaworks.algafood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

/**
 * 15.8. Desafio: implementando serviço de envio de e-mail fake<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(Mensagem mensagem) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate
        // da classe pai para "protected", para poder chamar aqui
        String corpo = processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}