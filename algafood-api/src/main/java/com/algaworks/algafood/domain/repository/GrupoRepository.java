package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Grupo;

/**
 * 12.8. Desafio: implementando os endpoints de grupos
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	
	
}
