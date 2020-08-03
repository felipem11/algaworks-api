package com.algaworks.algafood.domain.exception;

/**
 * 12.9. Desafio: implementando os endpoints de usuarios<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
//	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//		super(status, mensagem);
//	}

	public UsuarioNaoEncontradoException(String mensagem) {
//		this(HttpStatus.NOT_FOUND, mensagem);
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long id) { 
		this(String.format("Usuário com o código: %d não encontrado", id));
	}
	

}
