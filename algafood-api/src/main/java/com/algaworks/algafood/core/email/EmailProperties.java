package com.algaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
/**
 * 15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring<p>
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

}
