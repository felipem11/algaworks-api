package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 15.14. Desafio: enviando e-mails no cancelamento de pedidos<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;

}