package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.algaworks.algafood.core.validation.Multiplo;
import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
public class RestauranteInput {
	
	
	@NotBlank
	private String nome;
	
	@NotNull
	@PositiveOrZero(message = "{taxaFrete.invalida}")
	@Multiplo(numero = 5)
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
}
