package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepositoryQueries;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * @see  "http://modelmapper.org/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto){
        return manager.merge(foto);
    }

}
