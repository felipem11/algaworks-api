package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 15.4. Usando o serviço de envio de e-mails na confirmação de pedidos<p>
 * 15.5. Processando template do corpo de e-mails com Apache FreeMarker<p>
 * 15.11. Publicando Domain Events a partir do Aggregate Root<p>
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        pedido.cancelar();

        pedidoRepository.save(pedido);

    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

}