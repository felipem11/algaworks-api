package com.algaworks.algafood.di.modelo;

import lombok.Getter;

public class Cliente {
	
	@Getter
	private String nome;
	@Getter
	private String email;
	@Getter
	private String telefone;
	private boolean ativo = false;

	public Cliente(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}
	
	public void ativar() {
		this.ativo = true;
	}

}
