package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

/**
 * 5.11. Implementando um repositório SDJ customizado<p>
 * 5.12. Implementando uma consulta dinâmica com JPQL<p>
 * 5.14. Adicionando restrições na cláusula where com Criteria API<p>
 * 5.15. Tornando a consulta com Criteria API com filtros dinâmicos<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @see  https://www.infoq.com/br/articles/java-10-var-type/
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<Restaurante> find(String nome, BigDecimal freteInicial, BigDecimal freteFinal){
//		var jpql = "from Restaurante where nome like :nome and taxaFrete between :freteInicial and :freteFinal";
		
		var jpql = new StringBuilder("from Restaurante where 0 = 0 ");
		TypedQuery<Restaurante> tQuery = manager.createQuery(jpql.toString(), Restaurante.class);
		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome");
			tQuery.setParameter("nome", nome);
		}
		
		if (freteInicial != null) {
			jpql.append("and taxaFrete >= :freteInicial");
			tQuery.setParameter("freteInicial", freteInicial);
		}
		if (freteFinal != null) {
			jpql.append("and taxaFrete <= :freteFinal");
			tQuery.setParameter("freteFinal", freteFinal).getResultList();
		}
		
		
		return tQuery.getResultList();
				
				
				
		
		
	}
	@Override
	public List<Restaurante> findCriteria(String nome, BigDecimal freteInicial, BigDecimal freteFinal) {
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasLength(nome)) {
			predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
		}
		if (freteInicial != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), freteInicial));
		}
		if (freteFinal != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), freteFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	

}
