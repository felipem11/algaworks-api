package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Estado de código: %d em uso";
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado com o código: %d não encontrado";
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
		}
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}

}
