package com.algaworks.algafood.api.model;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
/**
 * 12.13. Desafio: implementando os endpoints de produtos
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@Setter
@Getter
public class ProdutoModel {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private boolean ativo;

}
