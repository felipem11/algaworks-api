package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

import jdk.jfr.SettingDefinition;
import lombok.Setter;

/**
 * 
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

//@Qualifier("urgente") // 2.18. Desambiguação de beans com @Qualifier
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do SMS %s : %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}
	
}
