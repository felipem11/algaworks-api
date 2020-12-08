package com.algaworks.algafood.api.model;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 * 13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson<p>
 * 13.3. Limitando os campos retornados pela API com Squiggly<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @see  "https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666"
 * @see  "https://github.com/bohnman/squiggly"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

//@JsonFilter("pedidoFIlter")
@Setter
@Getter
public class PedidoResumoModel {
	
	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;

}
