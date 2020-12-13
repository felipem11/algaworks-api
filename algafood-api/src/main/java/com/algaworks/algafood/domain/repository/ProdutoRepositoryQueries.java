package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FotoProduto;

/**
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * @see  "http://modelmapper.org/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public interface ProdutoRepositoryQueries {

    FotoProduto save (FotoProduto foto);
}
