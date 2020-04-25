package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

	
	
}
