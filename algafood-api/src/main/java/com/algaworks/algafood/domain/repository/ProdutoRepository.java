package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 5.7. Usando as keywords para definir critérios de query methods<p>
 * 5.8. Conhecendo os prefixos de query methods<p>
 * 5.10. Externalizando consultas JPQL para um arquivo XML<p>
 * 5.11. Implementando um repositório SDJ customizado<p>
 * 5.20. Estendendo o JpaRepository para customizar o repositório base<p>
 * 6.14. Resolvendo o Problema do N+1 com fetch join na JPQL<p>
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * @see  "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries{

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId,
							   @Param("produto") Long produtoId);

	List<Produto> findTodosByRestaurante(Restaurante restaurante);

	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);

	@Query("select f from FotoProduto f join f.produto p "
			+ "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);





}
