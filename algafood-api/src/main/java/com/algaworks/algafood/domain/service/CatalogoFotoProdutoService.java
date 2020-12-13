package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * 14.7. Excluindo e substituindo cadastro de foto de produto<p>
 * @see  "http://modelmapper.org/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto){

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()){
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(foto);
    }

}
