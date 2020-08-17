package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 5.7. Usando as keywords para definir critérios de query methods<p>
 * 5.8. Conhecendo os prefixos de query methods<p>
 * 5.10. Externalizando consultas JPQL para um arquivo XML<p>
 * 5.11. Implementando um repositório SDJ customizado<p>
 * 5.20. Estendendo o JpaRepository para customizar o repositório base<p>
 * 6.14. Resolvendo o Problema do N+1 com fetch join na JPQL<p>
 * @see  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

	List<Produto> findByRestaurante(Restaurante restaurante);
	
	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId, 
            @Param("produto") Long produtoId);
	
	List<Produto> findByIdAndRestaurante(Long produtoId, Long restauranteId);

	
	
}
