package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
/**
 * 5.20. Estendendo o JpaRepository para customizar o repositório base<p>
 * 12.11. Implementando regra de negócio para evitar usuários com e-mails duplicados<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class CustomJpaRepositoryimpl<T, ID> extends SimpleJpaRepository<T, ID>
			implements CustomJpaRepository<T, ID> {
	
	private EntityManager manager;
	
	
	
	public CustomJpaRepositoryimpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
		this.manager = entityManager;
	}
	
	@Override
	public Optional<T> buscarPrimeiro(){
		var jpql = "from " + getDomainClass().getName();
		
		T entity = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();
		
		return Optional.ofNullable(entity);
		
	}

	@Override
	public void detach(T entity) {
		manager.detach(entity);
	}



	
	

}
