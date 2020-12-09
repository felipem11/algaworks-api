package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * 5.18. Criando uma fábrica de Specifications
 * 13.6. Implementando pesquisas complexas na API
 * 13.9. Desafio: implementando paginação e ordenação de pedidos
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class PedidoSpecs {
	
	public static Specification<Pedido> usandoFiltro(PedidoFilter filter){
		return (root, query, builder) -> {
			if (Pedido.class.equals(query.getResultType())){
				// fetch para evitar o N+1
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
				// from Pedido p join fetch p.restaurante
			}

			var predicates = new ArrayList<Predicate>();

			if (filter.getClienteId() != null){
				predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
			}

			if (filter.getRestauranteId() != null){
				predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
			}

			if (filter.getDataCriacaoInicio() != null){
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
			}

			if (filter.getDataCriacaoFim() != null){
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};

	}

}
