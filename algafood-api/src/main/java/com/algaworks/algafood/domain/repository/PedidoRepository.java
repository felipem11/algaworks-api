package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 12.8. Desafio: implementando os endpoints de grupos<p>
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem
 * 13.6. Implementando pesquisas complexas na API
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
		JpaSpecificationExecutor<Pedido> {

	Optional<Pedido> findByCodigo(String codigo);

	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	
}
