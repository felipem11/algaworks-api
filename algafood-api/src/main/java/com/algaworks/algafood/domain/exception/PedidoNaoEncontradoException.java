package com.algaworks.algafood.domain.exception;

/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PedidoNaoEncontradoException(Long id) { 
		this(String.format("Pedido com o código: %d não encontrado", id));
	}
	

}
