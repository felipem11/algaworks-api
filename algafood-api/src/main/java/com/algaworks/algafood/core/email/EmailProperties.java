package com.algaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
 * 15.4. Usando o serviço de envio de e-mails na confirmação de pedidos<p>
 * 15.8. Desafio: implementando serviço de envio de e-mail fake<p>
 * 15.9. Desafio: Implementando serviço de envio de e-mail sandbox<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String remetente;
    private Implementacao impl = Implementacao.FAKE;

    private Sandbox sandbox = new Sandbox();


    public enum Implementacao {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {

        private String destinatario;

    }

}
