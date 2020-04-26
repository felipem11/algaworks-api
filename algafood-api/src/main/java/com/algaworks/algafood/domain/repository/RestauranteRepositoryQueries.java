package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;
/**
 * 5.12. Implementando uma consulta dinâmica com JPQL
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal freteInicial, BigDecimal freteFinal);

	List<Restaurante> findCriteria(String nome, BigDecimal freteInicial, BigDecimal freteFinal);
	
	List<Restaurante> findComFrenteGratis(String nome);

}