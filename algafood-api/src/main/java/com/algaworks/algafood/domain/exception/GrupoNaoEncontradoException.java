package com.algaworks.algafood.domain.exception;

/**
 * 12.8. Desafio: implementando os endpoints de grupos
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
//	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//		super(status, mensagem);
//	}

	public GrupoNaoEncontradoException(String mensagem) {
//		this(HttpStatus.NOT_FOUND, mensagem);
		super(mensagem);
	}
	
	public GrupoNaoEncontradoException(Long id) { 
		this(String.format("Grupo com o código: %d não encontrado", id));
	}
	

}
