package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 4.30. Modelando e implementando a inclusão de recursos de restaurantes<p>
 * 4.33. Analisando solução para atualização parcial de recursos com PATCH<p>
 * 4.34. Finalizando a atualização parcial com a API de Reflections do Spring<p>
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id){
		return cadastroRestaurante.buscarOuFalhar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante){
		try {
			return cadastroRestaurante.salvar(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public Restaurante alterar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		Restaurante restauranteDB = cadastroRestaurante.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(restaurante, restauranteDB, "id", "formasPagamento", "endereco",
								"dataCadastro", "dataAtualizacao");
		
		try {
			return cadastroRestaurante.salvar(restauranteDB);
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		cadastroRestaurante.excluir(id);
	}
	
	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object> campos){
		Restaurante restauranteDB = cadastroRestaurante.buscarOuFalhar(id);
		
		merge(campos, restauranteDB);
		
		return alterar(id, restauranteDB);
		
		
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class); // Usado para conseguir converter os atributos para 
																									// os tipo correto, somente com Reflection não daria
																									// para saber se Ex. taxaFrete: 2 é Integer ou Bigdecimal
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);	// busca o atributo com o nome que tiver na variavel
																								// nomePropriedade
				field.setAccessible(true);	// alterar os atributos do objeto Restaurante para public
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				System.out.println(nomePropriedade + " - " + valorPropriedade + " - " + novoValor);
				
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
		
		});
	}
	
	

}
