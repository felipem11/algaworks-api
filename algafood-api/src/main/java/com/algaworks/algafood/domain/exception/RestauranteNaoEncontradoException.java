package com.algaworks.algafood.domain.exception;

/**
 * 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
//	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//		super(status, mensagem);
//	}

	public RestauranteNaoEncontradoException(String mensagem) {
//		this(HttpStatus.NOT_FOUND, mensagem);
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format("Restaurante com o código: %d não encontrado", id));
	}
	

}
