package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 15.11. Publicando Domain Events a partir do Aggregate Root<p>
 * 15.12. Observando e reagindo a Domain Events<p>
 * @see "https://ajuda.locaweb.com.br/wiki/boas-praticas-de-html-para-email-marketing-ajuda-locaweb/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

//    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){
        Pedido pedido = event.getPedido();

        Mensagem mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmailService.enviar(mensagem);

    }
}
