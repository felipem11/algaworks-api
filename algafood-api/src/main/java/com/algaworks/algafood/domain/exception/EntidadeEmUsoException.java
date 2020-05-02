package com.algaworks.algafood.domain.exception;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services<p>
 * 8.2. Lançando exceções customizadas anotadas com @ResponseStatus<p>
 * 8.4. Estendendo ResponseStatusException<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */
//@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
	

}
