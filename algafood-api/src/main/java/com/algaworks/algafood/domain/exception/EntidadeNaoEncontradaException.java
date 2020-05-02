package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services<p>
 * 8.2. Lançando exceções customizadas anotadas com @ResponseStatus<p>
 * 8.4. Estendendo ResponseStatusException<p>
 * 8.5. Simplificando o código com o uso de @ResponseStatus em exceptions<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
//	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//		super(status, mensagem);
//	}

	public EntidadeNaoEncontradaException(String mensagem) {
//		this(HttpStatus.NOT_FOUND, mensagem);
		super(mensagem);
	}
	

}
