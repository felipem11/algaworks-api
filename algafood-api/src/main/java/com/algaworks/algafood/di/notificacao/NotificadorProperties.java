package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 2.26. Acessando propriedades com @ConfigurationProperties
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Component
@ConfigurationProperties("notificador.email")
@Getter @Setter
public class NotificadorProperties {
	
	private String hostServidor;
	private Integer portaServidor = 30; // porta padrao 30

}
