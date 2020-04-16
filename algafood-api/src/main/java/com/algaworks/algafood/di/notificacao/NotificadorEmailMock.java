package com.algaworks.algafood.di.notificacao;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

/**
 * 2.19. Desambiguação de beans com anotação customizada
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

//@Primary // quando encontra um bean semelhante como o SMS o Primary irá ser o bean usado
		// isso quando não está esperando uma coleção
//@Qualifier("normal")	// 2.18. Desambiguação de beans com @Qualifier
@Profile("dev")
@TipoDoNotificador(NivelUrgencia.NORMAL) // 2.19. Desambiguação de beans com anotação customizada
@Component
public class NotificadorEmailMock implements Notificador {
	
	public NotificadorEmailMock() {
		System.out.println("Notificador Email Mock");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Mock: Notificação seria enviada para "
				+ "%s através do e-mail %s : %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
}
