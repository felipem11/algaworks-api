package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;


import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.ClienteAtivadoEvent;
/**
 * 2.20. Mudando o comportamento da aplicação com Spring Profiles	<p>
 * 2.25. Criando e acessando propriedades customizadas com @Value	<p>
 * 2.26. Acessando propriedades com @ConfigurationProperties		<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

//@Primary // quando encontra um bean semelhante como o SMS o Primary irá ser o bean usado
		// isso quando não está esperando uma coleção
//@Qualifier("normal")	// 2.18. Desambiguação de beans com @Qualifier
//@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL) // 2.19. Desambiguação de beans com anotação customizada
@Component
public class NotificadorEmail implements Notificador {
	
//	@Value("${notificador.email.host-servidor}")
//	private String host;
//	@Value("${notificador.email.porta-servidor}")
//	private String porta;
	
	@Autowired
	private NotificadorProperties properties;
	
	public NotificadorEmail() {
		System.out.println("Notificador Email REAL");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s : %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
		System.out.println("Host: " + properties.getHostServidor());
		System.out.println("Porta: " + properties.getPortaServidor());
	}
	
}
