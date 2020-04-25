package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 5.7. Usando as keywords para definir critérios de query methods<p>
 * 5.8. Conhecendo os prefixos de query methods<p>
 * 5.10. Externalizando consultas JPQL para um arquivo XML<p>
 * 5.11. Implementando um repositório SDJ customizado<p>
 * 
 * @see  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface RestauranteRepository 
				extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
				JpaSpecificationExecutor<Restaurante>{

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
//	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	Optional<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	boolean existsByNome(String nome);
	
	int countByCozinhaId(Long Cozinha);
	
}
