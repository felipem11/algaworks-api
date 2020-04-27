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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 4.30. Modelando e implementando a inclusão de recursos de restaurantes<p>
 * 4.33. Analisando solução para atualização parcial de recursos com PATCH<p>
 * 4.34. Finalizando a atualização parcial com a API de Reflections do Spring<p>
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
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
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> alterar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
		Optional<Restaurante> restauranteDB = restauranteRepository.findById(restauranteId);
		
		if (restauranteDB.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(restaurante, restauranteDB.get(), "id", "formasPagamento", "endereco",
								"dataCadastro", "dataAtualizacao");
		
		try {
			cadastroRestaurante.salvar(restauranteDB.get());
			return ResponseEntity.ok(restauranteDB);
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){

		try {
			cadastroRestaurante.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object> campos){
		Optional<Restaurante> restauranteDB = restauranteRepository.findById(id);
		
		merge(campos, restauranteDB.get());
		
		return alterar(id, restauranteDB.get());
		
		
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
