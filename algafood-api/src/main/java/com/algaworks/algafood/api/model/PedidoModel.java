package com.algaworks.algafood.api.model;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@Setter
@Getter
public class PedidoModel {
	
	private Long id;
	
	private String nome;
	
	
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	
	private String status;
	
	private RestauranteResumoModel restaurante;
	
	private UsuarioModel cliente;
	
	private EnderecoModel endereco;
	
	private FormaPagamentoModel formaPagamento;
	
	private List<ItemPedidoModel> itemPedido;

}
