package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Pedido;

/**
 * 12.8. Desafio: implementando os endpoints de grupos<p>
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>{

	Optional<Pedido> findByCodigo(String codigo);

	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	
}
