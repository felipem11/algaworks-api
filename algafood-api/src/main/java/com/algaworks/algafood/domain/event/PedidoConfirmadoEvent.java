package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 15.11. Publicando Domain Events a partir do Aggregate Root<p>
 * @see "https://ajuda.locaweb.com.br/wiki/boas-praticas-de-html-para-email-marketing-ajuda-locaweb/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;

}
