package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services<p>
 * 5.4. Refatorando o código do projeto para usar o repositório do SDJ<p>
 * 5.20. Estendendo o JpaRepository para customizar o repositório base<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{

	List<Cozinha> findByNome(String nome);
	List<Cozinha> findQualquerCoisaByNome(String nome);
	Optional<Cozinha> findCozinhaByNome(String nome);
	
}
