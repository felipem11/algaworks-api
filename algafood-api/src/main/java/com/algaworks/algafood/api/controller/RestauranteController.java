package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 4.30. Modelando e implementando a inclusão de recursos de restaurantes<p>
 * 4.33. Analisando solução para atualização parcial de recursos com PATCH<p>
 * 4.34. Finalizando a atualização parcial com a API de Reflections do Spring<p>
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * 8.24. Lançando exception de desserialização na atualização parcial (PATCH)<p>
 * 9.5. Conhecendo e adicionando mais constraints de validação no modelo<p>
 * 9.7. Agrupando e restringindo constraints que devem ser usadas na validação<p>
 * 12.18. Implementando ativação e inativação em massa de restaurantes<p>
 * @see  "https://github.com/felipem11/algaworks-api"
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
	
	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao){
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restauranteModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteModel);
//
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//		if ("apanas-nome".equals(projecao)){
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)){
//			restaurantesWrapper.setSerializationView(null);
//		}
//		return restaurantesWrapper;
//
//	}

	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteModel> listar(){
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteModel> listarAdpenasNome(){
		return listar();
	}
	
	@GetMapping("/{id}")
	public RestauranteModel buscar(@PathVariable Long id){
		Restaurante restaurante =  cadastroRestaurante.buscarOuFalhar(id);
		
		return restauranteModelAssembler.toModel(restaurante);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(
			@RequestBody @Valid RestauranteInput restauranteInput){
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public RestauranteModel alterar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput){
		Restaurante restauranteDB = cadastroRestaurante.buscarOuFalhar(id);
		
//		Restaurante restaurante = restauranteDisassembler.toDomainObject(restauranteInput);
		
		restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteDB);
		
//		BeanUtils.copyProperties(restaurante, restauranteDB, "id", "formasPagamento", "endereco",
//								"dataCadastro", "dataAtualizacao");
		
		try {
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteDB));
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		cadastroRestaurante.excluir(id);
	}
	
	@PatchMapping("/{restauranteId}")
	public RestauranteModel atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos, HttpServletRequest request){
		
		Restaurante restauranteDB = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		merge(campos, restauranteDB, request);
		
		validate(restauranteDB, "restaurante");
		
//		return alterar(restauranteId, restauranteDB);
		return null;
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
	}
	@DeleteMapping("/{restauranteId}/inativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
	}

	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abertura(@PathVariable Long restauranteId) {
		cadastroRestaurante.abertura(restauranteId);
	}
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechamento(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechamento(restauranteId);
	}

	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = 
				new BeanPropertyBindingResult(restaurante, objectName);
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
		validator.validate(restaurante, bindingResult);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,
			HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request); 
		
		try {
		
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES	, true);
			
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
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
	
	
	
	
	

}
