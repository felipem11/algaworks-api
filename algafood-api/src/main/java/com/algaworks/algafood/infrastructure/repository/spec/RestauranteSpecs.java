package com.algaworks.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;
/**
 * 5.18. Criando uma fábrica de Specifications
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis(){
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
//		return new RestauranteComFreteGratisSpec();
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, builder) ->
				builder.like(root.get("nome"), "%" + nome + "%");
	}
}
