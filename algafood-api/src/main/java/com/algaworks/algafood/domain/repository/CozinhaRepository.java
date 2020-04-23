package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(Long id);
	List<Cozinha> consultarPorNome(String nome);
	
	Cozinha salvar(Cozinha cozinha);
	List<Cozinha> salvar(List<Cozinha> cozinhas);
	void remover(Long id);
	
}
