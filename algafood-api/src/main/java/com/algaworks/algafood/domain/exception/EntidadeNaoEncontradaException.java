package com.algaworks.algafood.domain.exception;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class EntidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	

}
