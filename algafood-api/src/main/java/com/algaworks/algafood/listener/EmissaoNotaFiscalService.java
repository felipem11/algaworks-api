package com.algaworks.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

/**
 * 2.22. Publicando e consumindo eventos customizados
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
* @version 1.0
* @since   2020-04-15 
 */


@Component
public class EmissaoNotaFiscalService {
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Nota Fiscal emitida para o cliente: " + event.getCliente());
	}

}
