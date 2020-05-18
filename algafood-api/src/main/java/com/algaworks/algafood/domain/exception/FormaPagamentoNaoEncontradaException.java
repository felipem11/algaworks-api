package com.algaworks.algafood.domain.exception;

/**
 * 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradaException(Long id) {
		this(String.format("Forma de pagamento com o código: %d não encontrada", id));
	}
	

}
