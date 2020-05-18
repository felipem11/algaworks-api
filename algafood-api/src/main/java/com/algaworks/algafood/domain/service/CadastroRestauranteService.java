package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

/**
 * 4.30. Modelando e implementando a inclusão de recursos de restaurantes<p>
 * 5.4. Refatorando o código do projeto para usar o repositório do SDJ<p>
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com o código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
			restauranteRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, id)
					);
		}
	}
	
	@Transactional
	public void ativar(Long RestauranteId) {
		Restaurante restauranteDB = buscarOuFalhar(RestauranteId);
		
		restauranteDB.inativar();
	}
	@Transactional
	public void inativar(Long RestauranteId) {
		Restaurante restauranteDB = buscarOuFalhar(RestauranteId);
		
		restauranteDB.ativar();
	}
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}
	
	
}
