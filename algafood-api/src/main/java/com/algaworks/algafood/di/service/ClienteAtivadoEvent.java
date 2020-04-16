package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;

import lombok.Getter;

/**
 * 2.22. Publicando e consumindo eventos customizados
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Getter
public class ClienteAtivadoEvent {
	
	private Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	
	

}
