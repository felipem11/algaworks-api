package com.algaworks.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 11.3. Criando classes de mixin para usar as anotações do Jackson
 * @see  
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class RestauranteMixin {

	@JsonIgnoreProperties(value = {"nome", "hibernateLazyInitializer"}, allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<Produto> produtos;
	
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
