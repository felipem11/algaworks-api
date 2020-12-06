package com.algaworks.algafood.domain.exception;

/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 *
 * @author Felipe Martins
 * @version 1.0
 * @see "https://github.com/felipem11/algaworks-api"
 * @since 2020-04-15
 */

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;



    public PedidoNaoEncontradoException(String codigo) {

        super(String.format("Pedido com o código: %s não encontrado", codigo));
    }


}
