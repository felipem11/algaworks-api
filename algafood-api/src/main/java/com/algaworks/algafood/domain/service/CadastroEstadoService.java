package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRespository;
	
	public Estado salvar(Estado estado) {
		return estadoRespository.save(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRespository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado com o código: %d não encontrado", id));
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado de código: %d em uso", id));
		}
	}

}
