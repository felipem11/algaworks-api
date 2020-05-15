package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

/**
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services<p>
 * 5.4. Refatorando o código do projeto para usar o repositório do SDJ<p>
 * 8.5. Simplificando o código com o uso de @ResponseStatus em exceptions<p>
 * 11.1. Analisando e definindo melhor o escopo das transações<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
		
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id)
					);
		}
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
	
}
