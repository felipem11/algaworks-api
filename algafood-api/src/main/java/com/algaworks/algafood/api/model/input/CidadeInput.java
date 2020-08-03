package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	
	@NotBlank
	private String nome;
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	@Valid
	private EstadoIdInput estado;

}
