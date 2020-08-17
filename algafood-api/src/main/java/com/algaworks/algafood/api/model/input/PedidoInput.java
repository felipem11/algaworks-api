package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.api.model.RestauranteResumoModel;
import com.algaworks.algafood.domain.model.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	private Long id;
	
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	
	private OffsetDateTime dataEntrega;
	
	private RestauranteResumoModel restaurante;
	
	private Endereco enderecoEntrega;
	
	private FormaPagamentoModel formaPagamento;
	
	private List<ItemPedidoModel> itens = new ArrayList<>();
	
	
}
